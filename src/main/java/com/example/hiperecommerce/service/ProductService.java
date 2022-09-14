package com.example.hiperecommerce.service;

import com.example.hiperecommerce.entity.Product;
import com.example.hiperecommerce.reposiory.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@AllArgsConstructor
public class ProductService implements Services<Product> {

    private ProductRepository repository;
    // DO NOT FORGET REPLACE TO SERViCE
    //private iRepository<Seller> sellerRepository;


    @Override
    public Product get(long id) {
        return repository.findById(id).get();
    }

    @Override
    public List<Product> getAll(long sellerId) {
        return /*sellerRepository.findById(sellerId).get().getProducts()*/null;
    }

    @Override
    public List<Product> getByNameList(String name) {
        return null;
    }

    @Override
    public Product getByName(String name) {
        return repository.findAll().stream().filter(x->x.getName().equals(name)).findAny().get();
    }

    @Override
    public boolean isReady(long id) {
        return get(id).isActive();
    }

    public Product addProduct(Product product){
        log.info("user from interface : " + user);
        return repository.save(product);
    }

    public Product updateProduct(Product product,long sellerId) throws AuthorizationServiceException {
       if (product.getSeller().getUser().getId()==user.getId()){
           return repository.save(product);
       }else return null;

    }

    public Product deleteProduct(Product product ,long sellerId){

        if (product.getSeller().getUser().getId()==user.getId()){
            product.setActive(false);
            return repository.save(product);
        }else return null;
    }

    public Product sellProduct(Product product){
        var real = get(product.getId());
        return real.getStock()>=product.getStock()
                ? get(product.getId())
                : null ;
    }



}
