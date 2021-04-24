package com.dorotajachtoma.tacocloud;


import org.apache.activemq.artemis.jms.client.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;

import javax.jms.Destination;

@Configuration
public class JmsConfiguration {

    @Bean
    public Destination orderQueue(){
        return new ActiveMQQueue("taco-cloud.order.queue");
    }

    @Bean
    public MappingJackson2MessageConverter messageConverter(){
        return new MappingJackson2MessageConverter();

    }

}
