package com.bankapi.bankapi.Repositories;

import com.bankapi.bankapi.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoryJpa extends JpaRepository<User, Integer> {

   User  findByEmail(String valor);
    
}
