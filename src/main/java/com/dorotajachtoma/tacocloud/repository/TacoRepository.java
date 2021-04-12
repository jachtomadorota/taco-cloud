package com.dorotajachtoma.tacocloud.repository;


import com.dorotajachtoma.tacocloud.model.Taco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TacoRepository extends JpaRepository<Taco,Long> {


}
