package com.dorotajachtoma.tacocloud.service;


import com.dorotajachtoma.tacocloud.model.TacoOrder;
import org.springframework.stereotype.Service;

@Service
public class JmsOrderMessagingService implements OrderMessagingService {
    @Override
    public void sendOrder(TacoOrder order) {

    }
}
