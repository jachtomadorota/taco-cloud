package com.dorotajachtoma.tacocloud.repository;

import com.dorotajachtoma.tacocloud.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<Long, User> {

    User findByUsername(String username);
}
