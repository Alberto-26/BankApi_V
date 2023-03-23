package com.bankapi.bankapi.Repositories.jwt;

import com.bankapi.bankapi.Model.Account;
import com.bankapi.bankapi.Model.User;
import com.bankapi.bankapi.Model.jwt.UserLogin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountByUserRepository extends JpaRepository<Account,Long> {


    Account findByUser(User user);

}
