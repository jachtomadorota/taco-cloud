package com.dorotajachtoma.tacocloud.controller;


import com.dorotajachtoma.tacocloud.model.Ingredient;
import com.dorotajachtoma.tacocloud.model.Order;
import com.dorotajachtoma.tacocloud.model.Taco;
import com.dorotajachtoma.tacocloud.repository.IngredientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping(value = "/design")
@SessionAttributes("order")
public class DesignTacoController {

    private final IngredientRepository ingredientRepository;
    private final TacoRepositoryImpl tacoRepository;

    public DesignTacoController(IngredientRepository ingredientRepository, TacoRepositoryImpl tacoRepository) {
        this.ingredientRepository = ingredientRepository;
        this.tacoRepository = tacoRepository;
    }

    @GetMapping
    public String showDesignForm(Model model){

        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepository.findAll().forEach(ingredients::add);
        Ingredient.Type[] types = Ingredient.Type.values();
        for(Ingredient.Type type : types){
            model.addAttribute(type.toString().toLowerCase(),
                    ingredients.stream().filter(x -> x.getType().equals(type)).collect(Collectors.toList()));
        }
        model.addAttribute("design", new Taco());
        return "design";
    }

    @PostMapping
    public String processDesign(@Valid Taco design, Errors errors, @ModelAttribute Order order){
        if(errors.hasErrors()){
            return "design";
        }else {
            log.info("Processing design : " + design);
            Taco saved = tacoRepository.save(design);
        }
        return "redirect:/orders/current";
    }
}
