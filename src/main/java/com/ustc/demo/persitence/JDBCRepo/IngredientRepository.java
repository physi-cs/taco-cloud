package com.ustc.demo.persitence.JDBCRepo;

import com.ustc.demo.domain.Ingredient;

public interface IngredientRepository {
    Iterable<Ingredient> findAll();

    Ingredient findById(String id);

    Ingredient save(Ingredient ingredient);
}
