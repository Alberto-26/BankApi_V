package com.bankapi.bankapi;


import com.bankapi.bankapi.Model.User;
import com.bankapi.bankapi.Model.jwt.UserLogin;
import com.bankapi.bankapi.Repositories.UserRepository;
import com.bankapi.bankapi.Repositories.jwt.UserTokenRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestUserLogin_and_Data_Uuer {
    @Autowired
    private UserTokenRepo userTokenRepo;
    @Autowired
    private UserRepository userRepository;

    @Test
    void Register_User_data_From_User_Token() {
        UserLogin byEmail = userTokenRepo.findByEmail("sebas@sebas.com");
        System.out.println(" loa datos son " + byEmail.getEmail() + " get role " + byEmail.getRole()
                + " get password " + byEmail.getPassword() + "\n " +
                " get mail " + byEmail.getEmail());

        User user = new User();
        user.setName("sebastian");
        user.setLastname("Esteban");
        user.setEmail(byEmail.getEmail());
        user.setCelphoneNumber("72526534");
        user.setPassword("123456");
        User user1 = userRepository.save(user);
        assert (user1.getName() == user.getName());


    }
}
