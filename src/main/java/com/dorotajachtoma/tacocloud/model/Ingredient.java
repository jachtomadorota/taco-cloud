package com.dorotajachtoma.tacocloud.model;


import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Id;


@Data
@NoArgsConstructor
@Entity
@AllArgsConstructor
public class Ingredient {


    @Id
    private Long id;
    private String index;
    private String name;
    private Type type;


    public static enum Type{
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }
}
