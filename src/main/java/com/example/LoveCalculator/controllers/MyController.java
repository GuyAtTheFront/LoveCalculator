package com.example.LoveCalculator.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.LoveCalculator.models.CompatibilityDao;
import com.example.LoveCalculator.repositories.RedisRepository;
import com.example.LoveCalculator.services.LoveService;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class MyController {
    
    @Autowired
    LoveService loveService;

    @Autowired
    RedisRepository redis;
    
    @GetMapping(path="/")
    public String index() {
        return "index";
    }

    @GetMapping(path="/result")
    public String result(@RequestParam String p1, @RequestParam String p2, Model model, HttpServletResponse response) {
        // Call API
        String json = loveService.getCompatibility(p1, p2);
        CompatibilityDao dao = loveService.toObject(json);
        model.addAttribute("dao", dao);        
        if(null == dao) {
            return "result";
        }

        if(dao.getPercentage().equalsIgnoreCase("0")) {
            response.setStatus(418);
            return "teapot";
        }
        
        redis.insertRecord(json);
        return "result";
    }

    @GetMapping(path="/list")
    public String result(Model model) {
        List<String> jsons = redis.getAllRecords();
        List<CompatibilityDao> daos = jsons.stream()
                                        .map(x -> loveService.toObject(x))
                                        .toList();
                                        
        model.addAttribute("daos", daos);
        return "list";
    }

    @GetMapping("/teapot")
    public String teapot() {
        return "teapot";
    }

}
