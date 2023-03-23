package com.bankapi.bankapi;


import com.bankapi.bankapi.Model.User;
import com.bankapi.bankapi.Model.jwt.UserLogin;
import com.bankapi.bankapi.Repositories.UserRepository;
import com.bankapi.bankapi.Repositories.jwt.UserTokenRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TestDummyUser {
    @Autowired
    private UserTokenRepo userTokenRepo;
    @Autowired    private UserRepository userRepository;


    @Test
    void muestra_contenido_usuarios_con_token_registrados_lo_relacina_Datos_usuarios_formales() {
        Iterable<User> allDataUsers = userRepository.findAll();
        for (User allDataUser : allDataUsers) {
            System.out.println("\n muestra telefono "+allDataUser.getCelphoneNumber());
            System.out.println("\n muestra mail data users  "+allDataUser. getEmail());
        }
        List<UserLogin> allTokenUsers = userTokenRepo.findAll();
        for (UserLogin allTokenUser : allTokenUsers) {
            System.out.println("\n  muestra roles   "+allTokenUser.getRole());

            System.out.println("\n  muestra mail usuarios con token "+allTokenUser.getEmail());

        }

       //  if(allTokenUsers.contains(allDataUser.))

        for (UserLogin allTokenUser : allTokenUsers) {
            for (User allDataUser : allDataUsers) {
                if (allTokenUser. getEmail()  .equals(allDataUser.getEmail())) {

                    System.out.println("\n mostrando similitud entre dos array ");
                    System.out.println("\n allTokenUser  "+allTokenUser.getEmail() +" tiene similitud con "+allDataUser.getEmail());


                }

            }

        }



    }
}
