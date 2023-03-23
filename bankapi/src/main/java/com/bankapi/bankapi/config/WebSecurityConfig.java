package com.bankapi.bankapi.config;

import com.bankapi.bankapi.component.filter.JwtRequestFilter;
import com.bankapi.bankapi.services.jwt.UsuarioDetailsServide;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UsuarioDetailsServide usuarioDetailsServide;




    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http



                .csrf().disable() // (2)
                .cors()
                .and()
                .authorizeRequests()
                .antMatchers("/static/**","/img/**").permitAll() // permitir acceso a recursos estáticos en la carpeta /css

                .antMatchers("/public/**").permitAll()
                .antMatchers("/login/**").permitAll()
                .antMatchers("/authenticate/**").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/roluser/**").hasRole("USER")
                .anyRequest().authenticated()
                .and()

                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
//        http.exceptionHandling().authenticationEntryPoint(new ApiAuthenticationEntryPoint());
//        http.exceptionHandling().accessDeniedHandler(new ApiAccessDeniedHandler());

    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(usuarioDetailsServide);

    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
