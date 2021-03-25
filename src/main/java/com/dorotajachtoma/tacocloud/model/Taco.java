package com.dorotajachtoma.tacocloud.model;


import lombok.Data;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Data
@RequestMapping
public class Taco {

    private String name;

    private List<Ingredient> ingredients;
}
