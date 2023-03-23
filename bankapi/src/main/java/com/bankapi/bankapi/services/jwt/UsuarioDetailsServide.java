package com.bankapi.bankapi.services.jwt;

import com.bankapi.bankapi.Model.jwt.UserLogin;
import com.bankapi.bankapi.Repositories.jwt.UserTokenRepo;
import com.bankapi.bankapi.util.ApiExceptionHandle;
import com.bankapi.bankapi.util.ApiRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioDetailsServide implements UserDetailsService {
    @Autowired
    private UserTokenRepo userTokenRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserLogin byUsername = userTokenRepo.findByUsername(username);

 /*       if(byUsername==null){

              new ApiExceptionHandle();


        }*/

        if (byUsername.getRole() != null) { //jcabelloc
            User.UserBuilder userBuilder = User.withUsername(username);

            userBuilder.password(byUsername.getPassword()).roles(byUsername.getRole());
            return userBuilder.build();

        } else {
            throw new UsernameNotFoundException(username);
        }


    }
}
