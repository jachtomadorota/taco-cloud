package com.dorotajachtoma.tacocloud.service;

import com.dorotajachtoma.tacocloud.model.TacoOrder;

public interface OrderMessagingService {

    void sendOrder(TacoOrder order);
}
