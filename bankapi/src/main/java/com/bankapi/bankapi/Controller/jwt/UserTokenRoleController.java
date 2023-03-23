package com.bankapi.bankapi.Controller.jwt;


import com.bankapi.bankapi.Model.Dto.UserDataDto;
import com.bankapi.bankapi.Model.Dto.UserTokenDto;
import com.bankapi.bankapi.Model.User;
import com.bankapi.bankapi.Model.jwt.UserLogin;
import com.bankapi.bankapi.Repositories.UserRepository;
import com.bankapi.bankapi.Repositories.jwt.UserTokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("")
public class UserTokenRoleController {
    @Autowired private UserTokenRepo userTokenRepoRole;
    @Autowired    private UserRepository userRepository;


    @GetMapping("/allusersroles")
    public List<UserLogin> showAllUser_Roles(){

        List<UserLogin> allRolesUsers = userTokenRepoRole.findAll();

        return allRolesUsers;
    }

    @GetMapping("/allusersrolesdto")
    public List<UserTokenDto> showAllUser_RolesDto(){
        UserTokenDto userTokenDto;// =new UserDto();
        List<UserTokenDto> userTokenDtoList = new ArrayList<>();

        List<UserLogin> allRolesUsers = userTokenRepoRole.findAll();
        for (UserLogin allRolesUser : allRolesUsers) {
            userTokenDto = new UserTokenDto(allRolesUser.getId() ,allRolesUser.getUsername(),allRolesUser.getEmail(),allRolesUser.getPassword(),allRolesUser.getRole());;
            userTokenDtoList.add(userTokenDto);

        }

        return userTokenDtoList;
    }
    @GetMapping("/alluserdatadto")
    public List<UserDataDto> showAllUser_RolesDtoDataUser(){
        // usar UserDataDto
        UserDataDto  userDataDto;
        List<UserDataDto> userTokenDtoList =new ArrayList<>();

        Iterable<User> allDataUsers = userRepository.findAll();
        List<UserLogin> allTokenUsers = userTokenRepoRole.findAll();
        for (UserLogin allTokenUser : allTokenUsers) {
            for (User allDataUser : allDataUsers) {
                if (allTokenUser. getEmail()  .equals(allDataUser.getEmail())) {

                    userDataDto = new UserDataDto(allDataUser.getUserId(),allDataUser.getName(),   allTokenUser.getRole()            ,allDataUser.getLastname(),
                            allTokenUser.getUsername(),   allTokenUser.getPassword(),allTokenUser.getEmail(),allDataUser.getCelphoneNumber());
                    userTokenDtoList.add(userDataDto);

                  //  System.out.println("\n mostrando similitud entre dos array ");
                  //  System.out.println("\n allTokenUser  "+allTokenUser.getEmail() +" tiene similitud con "+allDataUser.getEmail());


                }

            }

        }

        return userTokenDtoList;

    }

}
