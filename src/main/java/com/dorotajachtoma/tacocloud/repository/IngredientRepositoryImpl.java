package com.dorotajachtoma.tacocloud.repository;

import com.dorotajachtoma.tacocloud.model.Ingredient;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class IngredientRepositoryImpl implements IngredientRepository {

    private final JdbcTemplate jdbc;

    public IngredientRepositoryImpl(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Iterable<Ingredient> findAll() {
        return jdbc.query("SELECT id, name, type FROM Ingredient", this::mapRowToIngredient);
    }


    @Override
    public Ingredient findOne(Long id) {
        return jdbc.queryForObject("SELECT id, name, type FROM Ingredient where id=?;",
                new RowMapper<Ingredient>() {
            public Ingredient mapRow(ResultSet resultSet, int rowNum) throws SQLException{
                return new Ingredient(resultSet.getString("id"), resultSet.getString("name"),
                        Ingredient.Type.valueOf(resultSet.getString("type")));
            }
                });
    }

    @Override
    public Ingredient save(Ingredient ingredient) {
        jdbc.update("INSERT INTO Ingredient (id,name,type) VALUES (?, ?, ?)",
                ingredient.getId(), ingredient.getName(), ingredient.getType().toString());
        return ingredient;
    }

    private Ingredient mapRowToIngredient(ResultSet resultSet, int rowNum) throws SQLException {
        return new Ingredient(
                resultSet.getString("id"),
                resultSet.getString("name"),
                Ingredient.Type.valueOf(resultSet.getString("type")));

    }
}
