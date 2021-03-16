package com.ustc.demo.persitence.JDBCRepo;


import com.ustc.demo.domain.Order;

public interface OrderRepository {

  Order save(Order order);
  
}
