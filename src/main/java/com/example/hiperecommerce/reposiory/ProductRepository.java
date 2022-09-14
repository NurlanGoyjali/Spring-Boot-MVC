package com.example.hiperecommerce.reposiory;

import com.example.hiperecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
