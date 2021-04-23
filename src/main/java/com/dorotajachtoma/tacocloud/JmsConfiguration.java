package com.dorotajachtoma.tacocloud;


import org.apache.activemq.artemis.jms.client.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.Destination;

@Configuration
public class JmsConfiguration {

    @Bean
    public Destination orderQueue(){
        return new ActiveMQQueue("taco-cloud.order.queue");
    }

}
