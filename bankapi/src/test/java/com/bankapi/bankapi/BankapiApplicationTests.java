package com.bankapi.bankapi;

import com.bankapi.bankapi.Model.Account;
import com.bankapi.bankapi.Model.Transaction;
import com.bankapi.bankapi.Model.TypeAccount;
import com.bankapi.bankapi.Model.User;
import com.bankapi.bankapi.Model.jwt.UserLogin;
import com.bankapi.bankapi.Repositories.*;
import com.bankapi.bankapi.Repositories.jwt.AccountByUserRepository;
import com.bankapi.bankapi.Repositories.jwt.UserTokenRepo;
import com.bankapi.bankapi.services.TransactionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

@SpringBootTest
class BankapiApplicationTests {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private  UserRepositoryJpa userRepositoryJpa;

    @Autowired    private UserRepository userRepository;

    @Autowired private UserTokenRepo userTokenRepo;
    @Autowired  private AccountByUserRepository accountByUserRepository;



    @Autowired private TypeAccountRepository typeAccountRepository;

     @Autowired private  AccountRepository accountRepository;
    @Autowired    TransactionService transactionService;
//***************************************************************************************************************** //
//                                          seccion about roles of users for table UserLogin                       //
//*************************************************************************************************************** //
    @Test
    void createUserRoleAdmin() {
        UserLogin userLogin = new UserLogin();
        userLogin.setUsername("sebas");
        userLogin.setEmail("sebastian.esteban266@gmail.com");
        userLogin.setRole("ADMIN");
        String encode = bCryptPasswordEncoder.encode("123456");
        userLogin.setPassword(encode);
        UserLogin savelogin = userTokenRepo.save(userLogin);
        assert (savelogin.getEmail().equals(userLogin.getEmail()));
    }
    @Test
    void createUserRoleUser() {
        UserLogin userLogin = new UserLogin();
        userLogin.setUsername("juan");
        userLogin.setEmail("juan@juan.com");
        userLogin.setRole("USER");
        String encode = bCryptPasswordEncoder.encode("123456");
        userLogin.setPassword(encode);
        UserLogin savelogin = userTokenRepo.save(userLogin);
        assert (savelogin.getEmail().equals(userLogin.getEmail()));
    }

    //***************************************************************************************************************** //
    //                                          seccion  about data users in table users                                 //
    //*************************************************************************************************************** //

    @Test
    void data_User1() {
        User user = new User();
        UserLogin mailDataToken = userTokenRepo.findByEmail("sebastian.esteban266@gmail.com"); // datos relacionados con el token
        //user.setUserId();
        user.setName("sebas");
        user.setLastname("esteban");
        user.setEmail(mailDataToken.getEmail());
        user.setCelphoneNumber("+542613446617");
        user.setPassword("123456");
		User user1 = userRepository.save(user);
		assert (user1.getName()==user.getName());
	}

    @Test
    void data_User2() {
        User user = new User();
        UserLogin mailDataToken = userTokenRepo.findByEmail("juan@juan.com"); // datos relacionados con el token
        //user.setUserId();
        user.setName("juan");
        user.setLastname("bautista");
        user.setEmail(mailDataToken.getEmail());
        user.setCelphoneNumber("+5699554411");
        user.setPassword("123456");
        User user1 = userRepository.save(user);

        assert (user1.getName()==user.getName());
    }
    @Test
    void findAccountByUser1(){    // no probar
        User byEmail =  userRepository.findByEmail("sebastian.esteban266@gmail.com");
        //    User byEmail = userRepository.findById(1).orElse(null);

        Account account = accountByUserRepository.findByUser(byEmail);
        User user = account.getUser();
        TypeAccount type = account.getType();
        double balance = account.getBalance();
        long accountNumber = account.getAccountNumber();
        Integer userId = account.getUser().getUserId();
        Integer userId1 = user.getUserId();

        System.out.println("\naccount.getUser().getUserId() "+userId1);
        System.out.println("\nuser.getUserId() "+userId);

        System.out.println("account by user "+account.getUser());
        assert (userId==userId1);
    }

    @Test
    void findAccountByUser2(){
        userRepository.findByEmail("juan@juan.com");
        User byEmail = userRepository.findById(1).orElse(null);

        Account account = accountByUserRepository.findByUser(byEmail);
        User user = account.getUser();
        TypeAccount type = account.getType();
        double balance = account.getBalance();
        long accountNumber = account.getAccountNumber();
        Integer userId = account.getUser().getUserId();
        Integer userId1 = user.getUserId();

        System.out.println("\naccount.getUser().getUserId() "+userId1);
        System.out.println("\nuser.getUserId() "+userId);

        System.out.println("account by user "+account.getUser());
        assert (userId==userId1);
    }

    //***************************************************************************************************************** //
    //                                          seccion    about data jwt and relation other tables                     //
    //*************************************************************************************************************** //

    // este metodo inserta datos descriptivos del usuario ,cuenta y  tipo de cuenta  despues de haber registrado usuarios relacionado con login y normas de jwt ,
    // relacionados con dicho token

    @Test
    void insert_Saving_account_user1(){  //sebas
        UserLogin byEmail = userTokenRepo.findByEmail("sebastian.esteban266@gmail.com");
        TypeAccount typeAccount = new TypeAccount();
        User  userData = userRepository.findByEmail(byEmail.getEmail());
        System.out.println(userData. getName());
        typeAccount.setType("SavingAccount");
        TypeAccount typeAccountSave = typeAccountRepository.save(typeAccount);
        Account account = new Account();
        account.setAccountNumber(145454l);
        account.setUser(userData);
        account.setBalance(3000);
        account.setType(typeAccountSave);
        Account saveAccount = accountRepository.save(account);
        assert (account.getUser().getUserId()==saveAccount.getUser().getUserId());
    }

    @Test
    void insert_Saving_account_user2(){ //juan
        UserLogin byEmail = userTokenRepo.findByEmail("juan@juan.com");
        TypeAccount typeAccount = new TypeAccount();
        User  userData = userRepository.findByEmail(byEmail.getEmail());
        System.out.println(userData. getName());
        typeAccount.setType("CheckingAccount");
        TypeAccount typeAccountSave = typeAccountRepository.save(typeAccount);

        Account account = new Account();
        account.setAccountNumber(1454542);
        account.setUser(userData);
        account.setBalance(3000);
        account.setType(typeAccountSave);
        Account saveAccount = accountRepository.save(account);
        assert (account.getUser().getUserId()==saveAccount.getUser().getUserId());
    }

    @Test
    void send_found_from_user1_to_user_2(){
        Transaction transaction = new Transaction();

       /* UserLogin sourceUser = userTokenRepo.findByEmail("sebas@sebas.com");
        User  sourceUserData = userRepository.findByEmail(     sourceUser.getEmail()      );
        accountRepository.findByType()
        transaction.setSourceAccount(sourceUserData);

        transactionService.saveTransaction()*/



    }



}
