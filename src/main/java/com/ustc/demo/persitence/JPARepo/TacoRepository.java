package com.ustc.demo.persitence.JPARepo;

import com.ustc.demo.domain.Taco;
import org.springframework.data.repository.CrudRepository;

public interface TacoRepository extends CrudRepository<Taco,Long> {
}
