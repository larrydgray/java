package com.packt.cardatabase.domain;

import com.packt.cardatabase.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository <User, Long> {
    User findByUsername(String username);
}
