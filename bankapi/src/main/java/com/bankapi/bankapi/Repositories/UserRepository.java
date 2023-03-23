package com.bankapi.bankapi.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bankapi.bankapi.Model.User;
@Repository
public interface UserRepository extends CrudRepository<User, Integer>{

   User findByEmail(String valor);
    
}
