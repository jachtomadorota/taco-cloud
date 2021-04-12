package com.dorotajachtoma.tacocloud.repository;


import com.dorotajachtoma.tacocloud.model.Order;
import com.dorotajachtoma.tacocloud.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface OrderRepository extends JpaRepository<Order,Long> {

    @Query(value = "SELECT * FROM order where user_id =? ORDER BY createdAt desc;",nativeQuery = true)
    List<Order> findOrderbyUserOrderByCreatedAtDesc(User user, Pageable pageable);
}
