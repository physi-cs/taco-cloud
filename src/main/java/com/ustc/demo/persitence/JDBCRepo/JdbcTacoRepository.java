package com.ustc.demo.persitence.JDBCRepo;

import java.sql.*;
import java.util.Date;

import com.ustc.demo.domain.Ingredient;
import com.ustc.demo.domain.Taco;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;



@Repository
public class JdbcTacoRepository implements TacoRepository {

  private JdbcTemplate jdbc;

  public JdbcTacoRepository(JdbcTemplate jdbc) {
    this.jdbc = jdbc;
  }

  @Override
  public Taco save(Taco taco) {
    long tacoId = saveTacoInfo(taco);
    taco.setId(tacoId);
    for (Ingredient ingredient : taco.getIngredients()) {
      saveIngredientToTaco(ingredient, tacoId);
    }

    return taco;
  }

  private long saveTacoInfo(Taco taco) {
    taco.setCreatedAt(new Date());
    //PreparedStatementCreator psc =
    //    new PreparedStatementCreatorFactory(
    //        "insert into Taco (name, createdAt) values (?, ?)",
    //        Types.VARCHAR, Types.TIMESTAMP
    //    ).newPreparedStatementCreator(
    //        Arrays.asList(
    //            taco.getName(),
    //            new Timestamp(taco.getCreatedAt().getTime())));

    PreparedStatementCreator psc = new PreparedStatementCreator() {
      @Override
      public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
        String sql ="insert into Taco (name, createdAt) values (?, ?)";
        PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
        ps.setString(1,taco.getName());
        ps.setString(2, String.valueOf(new Timestamp(taco.getCreatedAt().getTime())));
        return ps;
      }
    };

    KeyHolder keyHolder = new GeneratedKeyHolder();
    jdbc.update(psc,keyHolder);
    return keyHolder.getKey().longValue();
  }

  private void saveIngredientToTaco(
          Ingredient ingredient, long tacoId) {
    jdbc.update(
        "insert into Taco_Ingredients (taco, ingredient) " +
        "values (?, ?)",
        tacoId, ingredient.getId());
  }

}
