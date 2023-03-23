package com.bankapi.bankapi.Controller;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.bankapi.bankapi.services.UserService;
import com.bankapi.bankapi.Model.User;

@RestController
@RequestMapping("/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);


    @Autowired
    public UserService userService;

    @GetMapping()
    public ArrayList<User> getUsers() {
        logger.debug("...............................");
        return userService.getUsers();
    }

    @GetMapping("/userid/{id}")
    public User  getByIdUser(@PathVariable Integer id){
        logger.debug("...............................");
        User user = userService.getUser(id);
        return user;
    }

    @PostMapping()
    public User saveUser(@RequestBody User user) {

        User user1 = userService.saveUser(user);


        return user1;
    }

    @DeleteMapping(path = "/{id}")
    public String DeleteById(@PathVariable Integer id) {
        boolean ok = userService.DeleteById(id);
        if (ok) {
            return "Se elimino el usuario con el id" + id;
        } else {
            return "Error al eliminar el usuario con el id" + id;
        }
    }
}
