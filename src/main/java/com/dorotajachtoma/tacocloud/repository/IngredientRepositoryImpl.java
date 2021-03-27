package com.dorotajachtoma.tacocloud.repository;

import com.dorotajachtoma.tacocloud.model.Ingredient;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;

public class IngredientRepositoryImpl implements IngredientRepository {

    private final JdbcTemplate jdbc;

    public IngredientRepositoryImpl(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Iterable<Ingredient> findAll() {
        return jdbc.query("SELECT id, name, type FROM ingredient", this::mapRowToIngredient);
    }


    @Override
    public Ingredient findOne(Long id) {
        return null;
    }

    @Override
    public Ingredient save(Ingredient ingredient) {
        return null;
    }

    private Ingredient mapRowToIngredient(ResultSet resultSet, int rowNum) throws SQLException {
        return new Ingredient(
                resultSet.getString("id"),
                resultSet.getString("name"),
                Ingredient.Type.valueOf(resultSet.getString("type")));

    }
}
