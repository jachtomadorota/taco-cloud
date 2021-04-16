package com.dorotajachtoma.tacocloud.controller;


import com.dorotajachtoma.tacocloud.model.Taco;
import com.dorotajachtoma.tacocloud.repository.TacoRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/design-rest",produces = "application/json")
@CrossOrigin(value = "*")
public class TacoRestController {

    private final TacoRepository repository;

    public TacoRestController(TacoRepository repository) {
        this.repository = repository;
    }


    @GetMapping(value = "/recent")
    public Iterable<Taco> recentTacos(){
        PageRequest page = PageRequest.of(0,12, Sort.by("createdAt").descending());
        return repository.findAll(page).getContent();
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<Taco> getOneTaco(@PathVariable Long id){
        Optional<Taco> taco = repository.findById(id);
        if (taco.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(taco.get(),HttpStatus.OK);
        }
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Taco createTacoPost(@RequestBody Taco taco){
            return repository.save(taco);
    }
}
