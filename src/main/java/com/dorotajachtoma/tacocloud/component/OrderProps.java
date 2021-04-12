package com.dorotajachtoma.tacocloud.component;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
@Component
@Validated
@ConfigurationProperties(prefix = "taco.order")
public class OrderProps {

    @Min(value = 5,message = "must be between 5 and 25")
    @Max(value = 25,message = "must be between 5 and 25")
    private int pageSize = 20;
}
