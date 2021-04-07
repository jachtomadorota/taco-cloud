package com.dorotajachtoma.tacocloud.controller;


import com.dorotajachtoma.tacocloud.model.Order;
import com.dorotajachtoma.tacocloud.model.User;
import com.dorotajachtoma.tacocloud.repository.OrderRepositoryImpl;
import com.dorotajachtoma.tacocloud.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@Slf4j
@RequestMapping(value = "/orders")
public class OrderController {

    private final OrderRepositoryImpl orderRepository;
    private final UserRepository userRepository;

    public OrderController(OrderRepositoryImpl orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    @GetMapping(value = "/current")
    public String orderForm(Model model){
        return "orderForm";
    }

    //it's possible to use second option - @AuthenticationPrincipal User user object instead of Principal to retrieve current logged user and his role
    @PostMapping
    public String processOrder(@Valid Order order, Errors errors, SessionStatus sessionStatus, Principal principal){
        if(errors.hasErrors()){
            return "orderForm";
        }
        User user = userRepository.findByUsername(principal.getName());
        order.setUser(user);
        orderRepository.save(order);
        sessionStatus.setComplete();
        return "redirect:/";

    }
}
