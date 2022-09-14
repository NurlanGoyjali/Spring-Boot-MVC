package com.example.hiperecommerce.service;

import com.example.hiperecommerce.entity.User;
import com.example.hiperecommerce.reposiory.UserRepositry;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private UserRepositry userRepositry;

    public Optional<User> findByUsername(String username){

        return  getAll().stream()
                .filter(x->x.getUsername().equals(username)).findFirst();
    }

    public boolean checkUsername(String username){ // if it is empty, this method get return true
        return  userRepositry.findAll().stream().filter(x->x.getUsername().equals(username)).findFirst().isEmpty();
    }

    public boolean checkEmail(String email){ // if it is empty, this method get return true
        return userRepositry.findAll().stream().filter(x->x.getEmail().equals(email)).findAny().isEmpty();
    }

    public User getOne(Long id) {
        return  userRepositry.findById(id).get();
    }

    public List<User> getAll(){
        var users = userRepositry.findAll().stream().collect(Collectors.toList());
        log.info("it is worked");
        return  users ;
    }


    public String deeleteUser(Long id ){
        var user = userRepositry.findById(id).get();
        if (user == null)
            return "Request denied";
        else
            userRepositry.delete(user);
        return "user deleted";

    }

    public String createUser(User user){
        var s = userRepositry.save(user);
        log.info("create user WORKED");
        return   s == null ? null : "Xoş geldiniz. zehmet olmasa giriş sehifesine keçin ve girişinizi edin";
    }

    @Transactional
    public String updateUser(User upData, Long id){
        var user = userRepositry.findById(id).get();
        log.info("inside of if");
        return "updated";
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = findByUsername(username);
        user.get().setLast_login(LocalDateTime.now());
        log.info("user  ->  " +user );
        return user.orElseThrow( () -> new UsernameNotFoundException("Invalid username") );
    }
}
