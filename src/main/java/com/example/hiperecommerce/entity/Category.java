package com.example.hiperecommerce.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "CATEGORY")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NonNull
    private String name;
    @NonNull
    private String image;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "parentid")
    private List<Category> chids;


    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL,mappedBy = "category")
    private List<Product> products;

}
