package com.bankapi.bankapi.Repositories.jwt;

import com.bankapi.bankapi.Model.jwt.UserLogin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTokenRepo extends JpaRepository<UserLogin,Long> {
       UserLogin findByUsername(String username);

       UserLogin findByEmail(String mail);

       Boolean existsByEmail(String email);
       Boolean existsByUsername(String username);
}
