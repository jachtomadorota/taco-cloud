package com.dorotajachtoma.tacocloud.repository;

import com.dorotajachtoma.tacocloud.model.Order;

public interface OrderRepository {

    Order save(Order order);
}
