package com.dorotajachtoma.tacocloud.repository;

import com.dorotajachtoma.tacocloud.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient,Long> {


}
