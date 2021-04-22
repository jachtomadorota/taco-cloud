package com.dorotajachtoma.tacocloud.controller;


import com.dorotajachtoma.tacocloud.model.Ingredient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.client.Traverson;
import org.springframework.http.ResponseEntity;
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

    @GetMapping(value = "/get")
    public Ingredient getIngredientById(String ingredientId){
        return template.getForObject("http://localhost:8080/taco-rest-template/get/{id}",Ingredient.class,ingredientId);
    }

    @GetMapping(value = "/hashmap")
    public Ingredient getIngredientByIdHashMap(String ingredientId){
        Map<String,String> urlVariables = new HashMap<>();
        urlVariables.put("id",ingredientId);
        URI uri = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/taco-rest-template/hashmap/{id}")
                .build(urlVariables);
        return template.getForObject(uri,Ingredient.class);
    }

    @GetMapping(value = "/response-entity")
    public Ingredient getIngredientByIdResponseEntity(String ingredientId){
        ResponseEntity<Ingredient> responseEntity = template.getForEntity("http://localhost:8080/taco-rest-template/response-entity/{id}"
        ,Ingredient.class,ingredientId);
        log.info("Fetch time :"  + responseEntity.getHeaders().getDate());

        return responseEntity.getBody();
    }

    public void updateIngredient(Ingredient ingredient){
        template.put("http://localhost:8080/taco-rest-template/{id}",ingredient,ingredient.getId());
    }

    public void delete(Ingredient ingredient){
        template.delete("http://localhost:8080/taco-rest-template/{id}",ingredient.getId());
    }

    @GetMapping(value = "/ingredients")
    public Ingredient createIngredient(Ingredient ingredient){
        return  template.postForObject("http://localhost:8080/taco-rest-template/ingredients",ingredient,Ingredient.class);
    }

    @GetMapping(value = "/ingredients/response-entity")
    public Ingredient createIngredientByResponseEntity(Ingredient ingredient){
        ResponseEntity<Ingredient> responseEntity = template.postForEntity("http://localhost:8080/taco-rest-template/ingredients/response-entity"
        ,ingredient,Ingredient.class);

        log.info("Fetching new object..."  + responseEntity.getHeaders().getLocation());

        return responseEntity.getBody();
    }

    private Ingredient addIngredient(Ingredient ingredient){
        Traverson traverson = new Traverson(URI.create("http://localhost:8080/api"),MediaTypes.HAL_JSON);
        String ingredientUrl = traverson.follow("ingredients").asLink().getHref();
        return template.postForObject(ingredientUrl,ingredient,Ingredient.class);
    }


}
