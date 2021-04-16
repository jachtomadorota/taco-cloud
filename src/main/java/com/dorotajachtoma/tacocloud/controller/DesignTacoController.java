package com.dorotajachtoma.tacocloud.controller;


import com.dorotajachtoma.tacocloud.model.Ingredient;
import com.dorotajachtoma.tacocloud.model.TacoOrder;
import com.dorotajachtoma.tacocloud.model.Taco;
import com.dorotajachtoma.tacocloud.repository.IngredientRepository;
import com.dorotajachtoma.tacocloud.repository.TacoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping(value = "/design")
public class DesignTacoController {

    private final IngredientRepository ingredientRepository;

    public DesignTacoController(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @GetMapping
    public String showDesignForm(Model model){

        Ingredient.Type[] types = Ingredient.Type.values();
        for(Ingredient.Type type : types){
            model.addAttribute(type.toString().toLowerCase(),
                    ingredientRepository.findAll().stream().filter(x -> x.getType().equals(type)).collect(Collectors.toList()));
        }
        model.addAttribute("design", new Taco());
        return "design";
    }
}
