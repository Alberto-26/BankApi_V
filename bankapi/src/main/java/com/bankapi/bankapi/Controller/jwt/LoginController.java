package com.bankapi.bankapi.Controller.jwt;


import com.bankapi.bankapi.Model.jwt.AuthenticationReq;
import com.bankapi.bankapi.Model.jwt.TokenInfo;
import com.bankapi.bankapi.Model.jwt.UserLogin;
import com.bankapi.bankapi.Repositories.jwt.UserTokenRepo;
import com.bankapi.bankapi.services.jwt.JwtUtilService;
import com.bankapi.bankapi.util.MessageResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("")
public class LoginController {

    @Autowired private UserTokenRepo userTokenRepo;

    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    private JwtUtilService jwtUtilService;


    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);





    @GetMapping("/roluser")
    public ResponseEntity<?> getMensajeUserRole() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        logger.info("User data: {}", auth.getPrincipal());
        logger.info("Permit Data {}", auth.getAuthorities());
        logger.info("is authenticated? {}", auth.isAuthenticated());


        Map<String, String> mensaje = new HashMap<>();
        mensaje.put("contenido", "Hello Role user");
        return ResponseEntity.ok(mensaje);
    }

    @GetMapping("/admin")
    public ResponseEntity<?> getMensajeAdmin() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        logger.info("User data: {}", auth.getPrincipal());
        logger.info("Permit Data {}", auth.getAuthorities());
        logger.info("is authenticated? {}", auth.isAuthenticated());

        Map<String, String> mensaje = new HashMap<>();
        mensaje.put("content", "Hola Admin");
        return ResponseEntity.ok(mensaje);
    }


    @GetMapping("/public")
    public ResponseEntity<?> getMensajePublico() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        logger.info("User data: {}", auth.getPrincipal());
        logger.info("Permit Data {}", auth.getAuthorities());
        logger.info("is authenticated? {}", auth.isAuthenticated());

        Map<String, String> mensaje = new HashMap<>();
        mensaje.put("content", "hello to any user");
        return ResponseEntity.ok(mensaje);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationReq authenticationReq) {



        if (userTokenRepo.existsByEmail(authenticationReq.getUsuario()  ) ) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: mail is already taken!"));

        }
        UserLogin byUsername = userTokenRepo.findByUsername(authenticationReq.getUsuario());

        logger.info("Autenticando al usuario {}", authenticationReq.getUsuario());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationReq.getUsuario(),
                        authenticationReq.getClave()));


        SecurityContextHolder.getContext().setAuthentication(authentication);
        final UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        final String jwt = jwtUtilService.generateToken(userDetails);








        TokenInfo tokenInfo = new TokenInfo(jwt, userDetails.getUsername(), Collections.singletonList(userDetails.getAuthorities().toString()),byUsername.getEmail() );
        ResponseEntity<TokenInfo> ok = ResponseEntity.ok(tokenInfo);
        return ResponseEntity.ok(tokenInfo);
    }
}
