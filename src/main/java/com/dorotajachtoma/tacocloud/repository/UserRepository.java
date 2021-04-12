package com.dorotajachtoma.tacocloud.repository;

import com.dorotajachtoma.tacocloud.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface UserRepository extends CrudRepository<Long,User> {

    User findByUsername(String username);

}
