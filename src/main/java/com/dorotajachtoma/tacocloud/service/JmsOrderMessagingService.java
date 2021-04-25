package com.dorotajachtoma.tacocloud.service;


import com.dorotajachtoma.tacocloud.model.TacoOrder;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessagePostProcessor;
import org.springframework.stereotype.Service;
import javax.jms.JMSException;
import javax.jms.Message;


@Service
public class JmsOrderMessagingService implements OrderMessagingService {


    private final JmsTemplate jms;

    public JmsOrderMessagingService(JmsTemplate jms) {
        this.jms = jms;
    }

    @Override
    public void sendOrder(TacoOrder order) {
      //  jms.send(session -> session.createObjectMessage(order));

        jms.convertAndSend("taco-cloud.order.queue", order, message -> {
            message.setStringProperty("X_ORDER_SOURCE","WEB");
            return message;
        });
    }
}
