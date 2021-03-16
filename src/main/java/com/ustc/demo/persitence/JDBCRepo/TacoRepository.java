package com.ustc.demo.persitence.JDBCRepo;


import com.ustc.demo.domain.Taco;

public interface TacoRepository  {

  Taco save(Taco design);
  
}
