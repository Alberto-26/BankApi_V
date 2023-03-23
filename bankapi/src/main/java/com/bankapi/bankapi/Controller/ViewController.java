package com.bankapi.bankapi.Controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {
    private static final Logger logger = LoggerFactory.getLogger(ViewController.class);


    @GetMapping("/login")
    public String    getLoginWeb() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        logger.info("User data: {}", auth.getPrincipal());
        logger.info("Permit Data {}", auth.getAuthorities());
        logger.info("is authenticated? {}", auth.isAuthenticated());
        return "login";
    }
}
