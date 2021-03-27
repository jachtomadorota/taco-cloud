package com.dorotajachtoma.tacocloud.model;


import lombok.Data;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@Data
@RequestMapping
public class Taco {

    private Long id;

    private Date placedAt;
}
