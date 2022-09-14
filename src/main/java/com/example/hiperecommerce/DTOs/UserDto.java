package com.example.hiperecommerce.DTOs;

import com.example.hiperecommerce.entity.Character;
import com.example.hiperecommerce.entity.Role;
import com.example.hiperecommerce.entity.User;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Collection;

@Data
@AllArgsConstructor
public class UserDto{

    private String username;
    private String email;
    private String password;

    private LocalDateTime created_on;

    public UserDto(){

    }

}
