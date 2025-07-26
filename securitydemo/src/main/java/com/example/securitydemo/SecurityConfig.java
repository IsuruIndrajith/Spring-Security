package com.example.securitydemo;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

//because specifying custom security configurations here,
@Configuration
@EnableWebSecurity
//for the RoleBased Authentication
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> requests.anyRequest().authenticated());

//        making java session(cookies) stateless
        http.sessionManagement(session
        -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));


        //        form based Authentication
//        http.formLogin(withDefaults());
        
//        basic Authentication
        http.httpBasic(withDefaults());
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailService(){


        UserDetails user1 = User.withUsername("user1")
//                {noop} making unable to read (encoding the password)
                .password("{noop}password1")
//                Roles given for the role based authentication
                .roles("USER")
                .build();

        UserDetails admin = User.withUsername("admin")
                .password("{noop}adminPass")
                .roles("ADMIN")
                .build();



//        here making use of constructor InMemoryUserDetailsManager(user1, admin)
//        passing the objects of type UserDetails (user1, admin)

        return new InMemoryUserDetailsManager(user1, admin);
    }

}
