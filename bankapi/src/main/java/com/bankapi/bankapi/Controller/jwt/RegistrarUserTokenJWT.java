package com.bankapi.bankapi.Controller.jwt;


import com.bankapi.bankapi.Model.Dto.UserTokenDto;
import com.bankapi.bankapi.Model.jwt.UserLogin;
import com.bankapi.bankapi.Repositories.jwt.UserTokenRepo;
import com.bankapi.bankapi.util.ApiRequestException;
import com.bankapi.bankapi.util.MessageResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class RegistrarUserTokenJWT {

    private static final Logger logger = LoggerFactory.getLogger(RegistrarUserTokenJWT.class);

    //@Autowired    private BCryptPasswordEncoder bCryptPasswordEncoder;

     @Autowired  private  PasswordEncoder passwordEncoder;

    @Autowired private UserTokenRepo userTokenRepo;


    @PostMapping("/authenticate/register")
    public  ResponseEntity< ? > registerUserToken(@RequestBody UserLogin userLogin){
        UserTokenDto userTokenDto = new UserTokenDto(userLogin.getUsername(), userLogin.getEmail(), userLogin.getPassword(), userLogin.getRole());
        String encode = passwordEncoder.encode(userLogin.getPassword());
        userLogin.setPassword(encode);
        if (userTokenRepo.existsByEmail(userLogin.getEmail()  ) ) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: mail is already taken!"));

        }
        if (userTokenRepo.existsByUsername(userLogin.getUsername()) ) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: user name  is already taken!"));

        }


        try {
             userTokenRepo.save(userLogin);
        } catch (DataAccessException e) {
            throw new ApiRequestException("error :"+e.getRootCause().getMessage(),e );
        }

         return ResponseEntity.ok(userTokenDto);
    }
}
