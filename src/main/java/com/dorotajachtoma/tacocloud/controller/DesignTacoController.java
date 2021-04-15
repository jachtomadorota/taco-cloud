package com.dorotajachtoma.tacocloud.controller;


import com.dorotajachtoma.tacocloud.model.Ingredient;
import com.dorotajachtoma.tacocloud.model.TacoOrder;
import com.dorotajachtoma.tacocloud.model.Taco;
import com.dorotajachtoma.tacocloud.repository.IngredientRepository;
import com.dorotajachtoma.tacocloud.repository.TacoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping(value = "/design",produces = "application/json")
@CrossOrigin(origins = "*")
public class DesignTacoController {

    private final IngredientRepository ingredientRepository;
    private final TacoRepository tacoRepository;

    public DesignTacoController(IngredientRepository ingredientRepository, TacoRepository tacoRepository) {
        this.ingredientRepository = ingredientRepository;
        this.tacoRepository = tacoRepository;
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

    @GetMapping(value = "/recent")
    public Iterable<Taco> recentTacos(){
        PageRequest page = PageRequest.of(0,12, Sort.by("createdAt").descending());
        return tacoRepository.findAll(page).getContent();
    }

    @GetMapping(value="/{id}")
    public Taco getOneTaco(@PathVariable Long id){
        Optional<Taco> taco = tacoRepository.findById(id);
        if (taco.isEmpty()) {
            return null;
        }else{
            return taco.get();
        }
    }

    @PostMapping
    public String processDesign(@Valid @ModelAttribute("design") Taco design, BindingResult result, @ModelAttribute TacoOrder tacoOrder){
        if(result.hasErrors()){
            return "design";
        }else {
            log.info("Processing design : " + design);
            tacoRepository.save(design);
        }
        return "redirect:/orders/current";
    }
}
