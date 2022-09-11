package com.example.hiperecommerce.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "PRODUCT")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NonNull
    private String name;
    @NonNull
    private BigDecimal price;
    @NonNull
    private int stock;
    @NonNull
    private boolean active;
    @NonNull
    private String slug;

    private String size;

    private String color;


    @ManyToOne
    @JoinColumn(name = "category_id",nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "seller_id",nullable = false)
    private Seller seller;


    public Product() {

    }

    public Product(@NonNull String name, @NonNull BigDecimal price, @NonNull String slug, String size, String color, Category category, Seller seller) {
        this.name = name;
        this.price = price;
        this.slug = slug;
        this.size = size;
        this.color = color;
        this.category = category;
        this.seller = seller;
        this.active = true;

    }
}
