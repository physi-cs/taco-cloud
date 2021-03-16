package com.ustc.demo.persitence.JPARepo;

import com.ustc.demo.domain.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order,Long> {
}
