package com.example.hiperecommerce.entity;

import lombok.*;
import org.hibernate.annotations.*;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "USERS")
@Data
public class User implements UserDetails {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long Id;
    @NotNull
    private String username;
    @NotNull
    private String email;
    @NotNull
    private String password;
    @NotNull
    @CreationTimestamp
    private LocalDateTime created_on;
    private LocalDateTime last_login;


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles",joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id") )
    private Collection<Role> roles;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "character_id", referencedColumnName = "id")
    private Character character;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    @Fetch(value = FetchMode.SUBSELECT)
    private Set<Seller> sellers;

    public User(@NonNull String username, @NonNull String email, @NonNull String password, LocalDateTime last_login) {

        this.username = username;
        this.email = email;
        this.password = password;
        this.created_on = LocalDateTime.now();
        this.last_login = last_login;
    }

    public User() {

    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
