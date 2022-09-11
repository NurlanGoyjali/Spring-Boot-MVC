package com.example.hiperecommerce.entity;

 import lombok.Data;
 import lombok.NonNull;
 import org.hibernate.annotations.Fetch;
 import org.hibernate.annotations.FetchMode;
 import org.hibernate.annotations.LazyCollection;
 import org.hibernate.annotations.LazyCollectionOption;


 import javax.persistence.*;
import javax.websocket.OnMessage;
 import java.util.Collection;
 import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "SELLER")
@Data
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long Id;
    @NonNull
    private String name;
/*
    private String description;
    private String phonenumber;
    private String mobilenumber;
    private String email;
    private String workmail;
    private String address;
    */
    @NonNull
    private Date start;
    @NonNull
    private Date endtime;
    @NonNull
    private boolean active;

    @NonNull
    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private  User user;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "seller")
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Product> products;


    public Seller() {

    }

    public Seller(@NonNull String name, @NonNull Date start, @NonNull Date endtime, @NonNull boolean active, @NonNull User user) {
        this.name = name;
        this.start = start;
        this.endtime = endtime;
        this.active = active;
        this.user = user;
    }
}
