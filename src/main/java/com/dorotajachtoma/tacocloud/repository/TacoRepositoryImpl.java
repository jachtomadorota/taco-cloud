package com.dorotajachtoma.tacocloud.repository;

import com.dorotajachtoma.tacocloud.model.Ingredient;
import com.dorotajachtoma.tacocloud.model.Taco;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.sql.Types;
import java.util.Arrays;
import java.util.Date;

@Repository
public class TacoRepositoryImpl implements TacoRepository{

    private JdbcTemplate jdbc;

    public TacoRepositoryImpl(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Taco save(Taco design) {
        long tacoId = saveTacoInfo(design);
        design.setId(tacoId);
        for(Ingredient ingredient : design.getIngredients()){
            saveIngredientToTaco(ingredient,tacoId);
        }
        return design;
    }

    private long saveTacoInfo(Taco taco){
        taco.setCreatedAt(new Date());
        PreparedStatementCreator psc = new PreparedStatementCreatorFactory(
                "INSERT INTO Taco (name,createdAt) VALUES (?,?)",
                Types.VARCHAR,Types.TIMESTAMP).newPreparedStatementCreator(
                Arrays.asList(taco.getName(),new Timestamp(taco.getCreatedAt().getTime())));
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(psc,keyHolder);
        return keyHolder.getKey().longValue();

    }

    private void saveIngredientToTaco(Ingredient ingredient, long tacoId){
        jdbc.update("INSERT INTO Taco_Ingredients (taco,ingredient) VALUES (?,?)",
                tacoId,ingredient.getId());
    }
}
