package com.dorotajachtoma.tacocloud;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    protected DataSource dataSource(){
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("taco_schema.sql")
                .addScripts("user_data.sql","ingredient_data.sql")
                .build();
    }

    @Bean
    protected PasswordEncoder passwordEncoder(){
        return new Pbkdf2PasswordEncoder();
    }

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
                .loginPage("/login")
                .defaultSuccessUrl("/design",true)
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .and()
                .csrf()
                .disable();

        /*
        http.authorizeRequest()
        .antMatcher("/design","/order")
        .access("hasRole('ROLE_USER')")
        .antiMatchers("/","/**").access("permitAll");
         */
    }
}
