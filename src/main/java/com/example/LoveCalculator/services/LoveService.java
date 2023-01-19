package com.example.LoveCalculator.services;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.LoveCalculator.models.CompatibilityDao;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

@Service
public class LoveService {
    
    private static final String LOVE_URL = "https://love-calculator.p.rapidapi.com/getPercentage";
    
    public String getCompatibility(String p1, String p2) {

        // https://love-calculator.p.rapidapi.com/getPercentage?sname=Alice&fname=John
        String url = UriComponentsBuilder
                        .fromUriString(LOVE_URL)
                        .queryParam("sname", p1)
                        .queryParam("fname", p2)
                        .toUriString();
        
        System.out.println(url);
        
        RequestEntity<Void> request = RequestEntity
                                .get(url)
                                .header("X-RapidAPI-Key", "42e96f8adfmshdb0fa985e21f81cp1ff8fdjsn287a8806ed4f")
                                .header("X-RapidAPI-Host","love-calculator.p.rapidapi.com")
                                .accept(MediaType.APPLICATION_JSON)
                                .build();

        RestTemplate template = new RestTemplate();

        ResponseEntity<String> response = template.exchange(request, String.class);

        System.out.println("Service --> " + response);

        return response.getBody();
    }

    public CompatibilityDao toObject(String json) {

        // // I dont want this
        // try(InputStream is = new ByteArrayInputStream(response.getBody().getBytes())) {
        //     JsonReader reader = Json.createReader(is);
        //     JsonObject data = reader.readObject();
            
        // } catch (IOException e) {}

        ObjectMapper mapper = new ObjectMapper();
        CompatibilityDao dao = null;
        try {
            dao = mapper.readValue(json, CompatibilityDao.class);
            System.out.println(dao);

        } catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return dao;
    }
    
}
