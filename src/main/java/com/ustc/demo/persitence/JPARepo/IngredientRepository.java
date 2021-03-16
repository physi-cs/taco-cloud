package com.ustc.demo.persitence.JPARepo;

import com.ustc.demo.domain.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {
}
