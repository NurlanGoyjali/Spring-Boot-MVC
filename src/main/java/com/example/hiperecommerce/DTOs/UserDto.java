package com.example.hiperecommerce.DTOs;

import com.example.hiperecommerce.entity.Character;
import com.example.hiperecommerce.entity.Role;
import com.example.hiperecommerce.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Collection;

@Getter
@Setter
public class UserDto{

    private  long Id;
    private String username;
    private String email;
    private String password;
    private LocalDateTime last_login;
    private Collection<Role> roles;
    private Character character;



}
