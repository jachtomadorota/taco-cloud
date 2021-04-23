package com.dorotajachtoma.tacocloud.service;


import com.dorotajachtoma.tacocloud.model.TacoOrder;
import org.apache.activemq.artemis.jms.client.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import javax.jms.Destination;


@Service
public class JmsOrderMessagingService implements OrderMessagingService {


    private final JmsTemplate jms;

    public JmsOrderMessagingService(JmsTemplate jms) {
        this.jms = jms;
    }

    @Override
    public void sendOrder(TacoOrder order) {
        jms.send(session -> session.createObjectMessage(order));
    }
}
