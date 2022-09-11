package com.example.hiperecommerce.service;

import com.example.hiperecommerce.entity.Category;
import com.example.hiperecommerce.reposiory.iRepository;
import lombok.NonNull;
import lombok.SneakyThrows;
import org.hibernate.annotations.NotFound;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;

import java.io.NotActiveException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CategoryService implements Services<Category> {

    private iRepository<Category> repository;

    @Override
    public Category get(long id) {
        return repository.findById(id).get();
    }

    @Override
    public List<Category> getAll(long id) {
        return repository.findById(id).get().getChids();
    }

    @Override
    public Category getByName(String name) {
        return repository.findAll().stream().filter(x->x.getName().equals(name)).findAny().get();
    }

    @Override
    public List<Category> getByNameList(String name) {
        return repository.findAll().stream().filter(x->x.getName().contains(name)).collect(Collectors.toList());
    }

    @Override
    public boolean isReady(long id) {
        return Optional.of(get(id)).isPresent();
    }

    public Category addCategory(@NonNull Category category){
        return repository.save(category);
    }
    public Category updateCategory(@NonNull Category category){
        return repository.save(category);
    }
    @SneakyThrows
    public Category deleteCategory(Category category){
        repository.delete(category);
        return get(category.getId());

    }




}
