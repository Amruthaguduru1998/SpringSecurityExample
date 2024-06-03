package com.org.security.config;

import org.springframework.boot.logging.logback.WhitespaceThrowableProxyConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableMethodSecurity
public class MySecurityConfig {
    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
   public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(
                (auth)->auth.anyRequest().authenticated()).httpBasic(withDefaults());
       // http.sessionManagement(s->s.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
       return http.build();
   }

   @Bean
   public UserDetailsService userDetailsService(){
       UserDetails ammu= User.builder()
               .username("ammu")
               .password(passwordEncoder().encode("ammu"))
               .roles("USER")
               .build();

       UserDetails admin=User.builder()
               .username("admin")
               .password(passwordEncoder().encode("admin"))
               .roles("ADMIN")
               .build();

       return  new InMemoryUserDetailsManager(ammu,admin);
   }
}
