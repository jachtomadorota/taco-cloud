package com.dorotajachtoma.tacocloud.controller;


import com.dorotajachtoma.tacocloud.model.Order;
import com.dorotajachtoma.tacocloud.repository.OrderRepositoryImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

@Controller
@Slf4j
@RequestMapping(value = "/orders")
public class OrderController {

    private OrderRepositoryImpl orderRepository;

    public OrderController(OrderRepositoryImpl orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping(value = "/current")
    public String orderForm(Model model){
        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid Order order, Errors errors, SessionStatus sessionStatus){
        if(errors.hasErrors()){
            return "orderForm";
        }
        orderRepository.save(order);
        sessionStatus.setComplete();
        return "redirect:/";

    }
}
