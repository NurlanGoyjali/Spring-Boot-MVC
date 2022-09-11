package com.example.hiperecommerce.reposiory;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface iRepository<T> extends JpaRepository<T,Long> { }
