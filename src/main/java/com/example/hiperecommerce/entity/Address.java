package com.example.hiperecommerce.entity;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@Table(name = "ADDRESS")
@Data
public class Address {
    @Id
    @NonNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long Id;
    @NonNull
    private String name;

    private String description;
//    @NonNull
//    private String street;
//    @NonNull
//    private String pass;
//    @NonNull
//    private String district;
//    @NonNull
//    private String city;
      @NonNull
      @Column(length = 100)
      private String  fulladdress;
      @ManyToOne
      @JoinColumn(name="user_id", nullable = false)
      private  User user;



    public Address(@NonNull String name, String description, @NonNull String fulladdress, User user) {
        this.name = name;
        this.description = description;
        this.fulladdress = fulladdress;
        this.user = user;

    }

    public Address() {

    }
}
