package com.bankapi.bankapi.Controller;

import com.bankapi.bankapi.Controller.jwt.LoginController;
import com.bankapi.bankapi.Model.User;
import com.bankapi.bankapi.Repositories.UserRepository;
import com.bankapi.bankapi.Repositories.jwt.AccountByUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import com.bankapi.bankapi.Model.Account;
import com.bankapi.bankapi.services.AccountService;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/account")
public class AccountController {
    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    public AccountService accountService;
    @Autowired    private UserRepository userRepository;

    @Autowired  private AccountByUserRepository accountByUserRepository;
    @GetMapping()
    public ArrayList<Account> getAccounts(){
        logger.debug("...............................");
        return accountService.getAccounts();
    }



    @GetMapping(path= "/accountbyuser/{number}")
    public Account accountByUserId(@PathVariable Long number){
        User user = userRepository.findById(number.intValue()).orElse(null);
        Account account = accountByUserRepository.findByUser(user);
        System.out.println("llama aqui ???? ");

        return account;

    }
    @PostMapping()
    public Account saveAccount(@RequestBody Account account){
        return accountService.saveAccount(account);
    }
    @GetMapping(path= "/{number}")
    public Optional<Account> findAccount(@PathVariable Long number){
        return  accountService.findAccount(number);
    }

    @GetMapping(path = "/type")
    public ArrayList<Account> findByTypeAccount(@PathVariable String type){
        return accountService.findByTypeAccount(type);
    }
    

}
