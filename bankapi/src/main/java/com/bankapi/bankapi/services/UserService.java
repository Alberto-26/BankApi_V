package com.bankapi.bankapi.services;

import java.util.ArrayList;
import java.util.Optional;

import com.bankapi.bankapi.Model.jwt.UserLogin;
import com.bankapi.bankapi.Repositories.jwt.UserTokenRepo;
import com.bankapi.bankapi.util.ApiRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankapi.bankapi.Model.User;
import com.bankapi.bankapi.Repositories.UserRepository;

@Service
public class UserService {
    @Autowired    public UserRepository userRepository;
    @Autowired private UserTokenRepo userTokenRepo;

    public ArrayList<User> getUsers() {
        return (ArrayList<User>) this.userRepository.findAll();
    }

    public User getUser(Integer id){

        User user1 = userRepository.findById(id).orElse(null);

        return user1;

    }

    public User saveUser(User user) {
       // public User saveUser(User user) {

        Boolean aBoolean = userTokenRepo.existsByEmail(user.getEmail());

        if(!aBoolean){
             throw new ApiRequestException("for the correct login you must register your access token");



        }

        if(userTokenRepo.existsByUsername(user.getName())){
            throw new ApiRequestException("for the correct login you must register your access token");
        }


            /*    if(user.getName().equals(byEmail.getUsername())){
                    throw new ApiRequestException("error Debe Registrarse:");

                }*/
        User save = this.userRepository.save(user);

        return save;
    }

    public boolean DeleteById(Integer id) {
        try {
            this.userRepository.deleteById(id);
            return true;

        } catch (Error e) {
            return false;
        }
    }

}
