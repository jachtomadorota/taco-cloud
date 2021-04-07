package com.dorotajachtoma.tacocloud;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
      /*  auth.inMemoryAuthentication()
                .withUser("admin")
                .password("admin")
                .authorities("ROLE_USER")
                .and()
                .withUser("dorota")
                .password("dorota")
                .authorities("ROLE_USER");

       */

        auth.ldapAuthentication()
                .userSearchBase("ou=people")
                .userSearchFilter("(uid={0})")
                .userSearchBase("ou=groups")
                .userSearchFilter("member={0}")
                .passwordCompare()
                .passwordEncoder(new Pbkdf2PasswordEncoder())
                .passwordAttribute("passcode")
                .and()
                .contextSource()
                .root("dc=taco-cloud,dv=com");
    }
}
