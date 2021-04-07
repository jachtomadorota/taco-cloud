package com.dorotajachtoma.tacocloud.service;

import com.dorotajachtoma.tacocloud.model.User;
import com.dorotajachtoma.tacocloud.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserRepositoryUserDetailsService implements UserDetailsService {

    private final UserRepository repository;

    public UserRepositoryUserDetailsService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUsername(username);
        if(user!=null){
            return user;
        }
        throw new UsernameNotFoundException("User " + username + " not found");
    }
}
