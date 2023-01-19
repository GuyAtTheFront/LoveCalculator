package com.example.LoveCalculator.repositories;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RedisRepository {
    
    @Autowired
    RedisTemplate<String, Object> template;

    public void insertRecord(String json) {
        System.out.println("insertRecord");
        Integer index = template.keys("*").size() + 1;
        System.out.println(index);
        
        
        while(null != template.opsForValue().get(""+index)) {
            index++;
        }
        System.out.println("insertRecord 2");
        template.opsForValue().set(""+index, json);

        return;
    }

    public List<String> getAllRecords() {
        Set<String> keys = template.keys("*");
        List<String> jsons = template.opsForValue().multiGet(keys)
                            .stream()
                            .map(x -> x.toString())
                            .toList();
        return jsons;
    }
}
