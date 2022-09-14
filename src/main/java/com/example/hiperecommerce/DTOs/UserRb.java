package com.example.hiperecommerce.DTOs;

import com.example.hiperecommerce.entity.Role;
import com.example.hiperecommerce.entity.Roles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.example.hiperecommerce.entity.Roles.USER;

@Data
@AllArgsConstructor
public class UserRb {

   private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @NotNull(message = " istifadeçi adı boş olmamalıdı !")
    private String username;
    @Email(message = " E-mail boş olmamalıdı !")
    private String email;
    @NotNull(message = "Parol boş olmamalıdı !")
    private String password;

    private LocalDateTime created_on;

    private Collection<Role> roles=new ArrayList<>();


    public UserRb() {

    }

    public void addRole(Role role){
     this.roles.add(role);
    }

    public void addDate(LocalDateTime date){
     this.created_on=date;
    }
}
