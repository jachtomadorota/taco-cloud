package com.dorotajachtoma.tacocloud.controller;


import com.dorotajachtoma.tacocloud.model.Ingredient;
import com.dorotajachtoma.tacocloud.repository.IngredientRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class HomeController {

    private final IngredientRepository repository;

    public HomeController(IngredientRepository repository) {
        this.repository = repository;
    }

    @GetMapping(value = "/")
    public String homePage(){

        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient(1L,"FLTO","Flour Torilla", Ingredient.Type.WRAP),
                new Ingredient(2L,"COTO", "Corn Tortilla", Ingredient.Type.WRAP),
                new Ingredient(3L,"GRBF", "Groud Beef", Ingredient.Type.PROTEIN),
                new Ingredient(4L,"CARN", "Carnitas", Ingredient.Type.PROTEIN),
                new Ingredient(5L,"TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES),
                new Ingredient(6L,"LETC", "Lettuce", Ingredient.Type.VEGGIES),
                new Ingredient(7L,"CHED", "Cheddar", Ingredient.Type.CHEESE),
                new Ingredient(8L,"JACK", "Monterrey Jack", Ingredient.Type.CHEESE),
                new Ingredient(9L,"SLSA", "Salsa", Ingredient.Type.SAUCE),
                new Ingredient(10L,"SRCR", "Sour Cream", Ingredient.Type.SAUCE)
        );
        repository.saveAll(ingredients);return "home";
    }
}
