package com.dorotajachtoma.tacocloud.repository;


import com.dorotajachtoma.tacocloud.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
}
