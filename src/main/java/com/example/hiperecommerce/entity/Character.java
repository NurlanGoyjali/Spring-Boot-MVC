package com.example.hiperecommerce.entity;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Characters")
@Data
public class Character {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String name;
    private String secondname;
    @NotNull
    private String surname;
    @NotNull
    private String Gender = gender.NON.toString();
    @NotNull
    private LocalDate birtdate;

    @Column(length = 11)
    private String mobilenumber;
    @Column(length = 11)
    private String homenumber;
    @Column(length = 11)
    private String officephonenumber;

    @OneToOne(mappedBy = "character")
    private User user;

}
enum gender{
        NON,MALE,FEMALE;
}
