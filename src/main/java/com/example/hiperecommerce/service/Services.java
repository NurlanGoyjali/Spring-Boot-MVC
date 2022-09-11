package com.example.hiperecommerce.service;

import com.example.hiperecommerce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

public interface Services<T> {

    public User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    public T get(long id );
    public List<T> getAll(long id);
    public T getByName(String name);
    public List<T> getByNameList(String name);
    public boolean isReady (long id);




}
