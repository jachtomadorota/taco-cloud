package com.dorotajachtoma.tacocloud.controller;


import com.dorotajachtoma.tacocloud.model.Taco;
import com.dorotajachtoma.tacocloud.model.TacoOrder;
import com.dorotajachtoma.tacocloud.repository.OrderRepository;
import com.dorotajachtoma.tacocloud.repository.TacoRepository;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.persistence.logging.SessionLog;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.annotation.Resources;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/design",produces = "application/json")
@CrossOrigin(value = "*")
@Slf4j
public class TacoRestController {

    private final TacoRepository tacoRepository;
    private final OrderRepository orderRepository;

    public TacoRestController(TacoRepository tacoRepository, OrderRepository orderRepository) {
        this.tacoRepository = tacoRepository;
        this.orderRepository = orderRepository;
    }

    @GetMapping(value = "/recent")
    public CollectionModel<EntityModel<Taco>> recentTacos(){
        PageRequest page = PageRequest.of(0,12, Sort.by("createdAt").descending());
        List<Taco> tacos = tacoRepository.findAll(page).getContent();
        CollectionModel<EntityModel<Taco>> tacosCollection = CollectionModel.wrap(tacos);
        tacosCollection.add(new Link("http://localhost:8080/design/recent", "recents"));
        return tacosCollection;
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<Taco> getOneTaco(@PathVariable Long id){
        Optional<Taco> taco = tacoRepository.findById(id);
        if (taco.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(taco.get(),HttpStatus.OK);
        }
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Taco createTacoPost(@RequestBody Taco taco){
            return tacoRepository.save(taco);
    }

    @PatchMapping(value = "/{id}")
    public TacoOrder patchOrder(@PathVariable("id") Long id, @RequestBody TacoOrder patch){
        Optional<TacoOrder> order = orderRepository.findById(id);
        if(order.isPresent()) {
            TacoOrder tacoOrder = order.get();
            if (patch.getName() != null) {
                tacoOrder.setName(patch.getName());}
            if(patch.getStreet() != null){
                tacoOrder.setStreet(patch.getStreet());}
            if(patch.getCity() != null){
                tacoOrder.setCity(patch.getCity());}
            if(patch.getState() != null){
                tacoOrder.setState(patch.getState());}
            if(patch.getZip() != null){
                tacoOrder.setZip(patch.getZip());}
            if(patch.getCcNumber() != null){
                tacoOrder.setCcNumber(patch.getCcNumber());}
            if(patch.getCcExpiration() != null){
                tacoOrder.setCcExpiration(patch.getCcExpiration());}
            if(patch.getCcCVV() != null){
                tacoOrder.setCcCVV(patch.getCcCVV());}
            return orderRepository.save(tacoOrder);
        }else{
            return null;
        }
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable("id") Long id){
        try{
            orderRepository.deleteById(id);
        }catch(EmptyResultDataAccessException e){
            log.info("EmptyResultDataAccessException is thrown");
        }
    }
}
