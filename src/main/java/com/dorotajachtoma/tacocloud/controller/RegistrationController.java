package com.dorotajachtoma.tacocloud.controller;


import com.dorotajachtoma.tacocloud.model.User;
import com.dorotajachtoma.tacocloud.repository.UserRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping(value="/registration")
public class RegistrationController {

    private final UserRepository userRepository;

    public RegistrationController(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    @GetMapping
    public String registerForm(Model model){
        model.addAttribute("user",new User());
        return "registration";
    }

    @PostMapping
    public String processRegistration(@AuthenticationPrincipal User user, Errors errors){
        if(errors.hasErrors()){
            return "registration";
        }else{
            userRepository.save(user);
        }
        return "redirect:/login";
    }
}
