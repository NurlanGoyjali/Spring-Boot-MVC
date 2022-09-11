package com.example.hiperecommerce.entity;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NonNull;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "ROLES")
@Data
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long Id;
    private String name = String.valueOf(Names.USER);

    public Role() {

    }

    @Override
    public String getAuthority() {
        return name;
    }


}

enum Names{
    USER,MANAGER,ADMIN,DELIVER,SELLER
}

