package com.dorotajachtoma.tacocloud;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password("admin")
                .authorities("ROLE_USER")
                .and()
                .withUser("dorota")
                .password("dorota")
                .authorities("ROLE_USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                .antMatchers("/design","/orders")
                .hasRole("ROLE_USER")
                .antMatchers("/","/**").permitAll()
                .and()
                .formLogin()
                .loginPage("/login");

        /*
        http.authorizeRequest()
        .antMatcher("/design","/order")
        .access("hasRole('ROLE_USER')")
        .antiMatchers("/","/**").access("permitAll");
         */
    }
}
