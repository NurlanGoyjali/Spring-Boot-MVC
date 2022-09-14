package com.example.hiperecommerce.reposiory;

import com.example.hiperecommerce.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
