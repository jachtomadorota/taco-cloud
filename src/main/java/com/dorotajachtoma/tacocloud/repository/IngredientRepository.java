package com.dorotajachtoma.tacocloud.repository;

import com.dorotajachtoma.tacocloud.model.Ingredient;

public interface IngredientRepository {

    Iterable<Ingredient> findAll();

    Ingredient findOne(Long id);

    Ingredient save(Ingredient ingredient);
}
