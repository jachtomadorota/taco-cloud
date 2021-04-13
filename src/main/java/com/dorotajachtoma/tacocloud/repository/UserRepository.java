package com.dorotajachtoma.tacocloud.repository;

import com.dorotajachtoma.tacocloud.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User,Long> {


    @Query(value = "SELECT * FROM user WHERE username=?;",nativeQuery = true)
    User findByUsername(String username);

}
