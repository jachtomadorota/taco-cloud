package com.dorotajachtoma.tacocloud.controller;


import com.dorotajachtoma.tacocloud.model.Ingredient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@CrossOrigin(value = "*")
@RequestMapping(value = "/taco-rest-template")
public class TacoRestTemplateController {

    private final RestTemplate template;

    public TacoRestTemplateController(RestTemplate template) {
        this.template = template;
    }

    public Ingredient getIngredientById(String ingredientId){
        return template.getForObject("http://localhost:8080/taco-rest-template/{id}",Ingredient.class,ingredientId);
    }

    @GetMapping(value = "/hashmap")
    public Ingredient getIngredientByIdHashMap(String ingredientId){
        Map<String,String> urlVaribles = new HashMap<>();
        urlVaribles.put("id",ingredientId);
        URI uri = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/taco-rest-template/hashmap/{id}")
                .build(urlVaribles);
        return template.getForObject(uri,Ingredient.class);
    }


}
