package com.dorotajachtoma.tacocloud.repository;


import com.dorotajachtoma.tacocloud.model.Order;
import com.dorotajachtoma.tacocloud.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

    List<Order> findbyUserOrderByCreatedAtDesc(User user, Pageable pageable);
}
