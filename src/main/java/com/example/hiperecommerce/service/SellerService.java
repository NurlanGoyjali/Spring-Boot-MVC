package com.example.hiperecommerce.service;

import com.example.hiperecommerce.entity.Seller;
import com.example.hiperecommerce.entity.User;
import com.example.hiperecommerce.reposiory.SellerRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@AllArgsConstructor
public class SellerService {

    private SellerRepository sellerRepository;
    private RoleService roleService;

    public List<Seller> getSellers(long userid){

        return sellerRepository.findAll().stream().filter(x->x.getUser().getId()==userid).collect(Collectors.toList());
    }

    public User user(){
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    }

    public Seller getSeller(long sellerid){
        return sellerRepository.findById(sellerid).get();
    }

    public boolean isReady(long sellerId){
        return sellerRepository.findById(sellerId).isPresent();
    }

    public Seller getSellerByName(String sellerName){
        return sellerRepository.findAll().stream().filter(x->x.getName().equals(sellerName)).findFirst().get();
    }

    public Seller addSeller(Seller seller){  //JUST ADMIN Could CALL THıS METHOD
        roleService.addRole(roleService.getRoleByName("SELLER"), user().getId());
        return sellerRepository.save(seller);
    }

    public boolean unActiveToSeller(Seller seller){
        if (seller.isActive()){
            seller.setActive(false);
            roleService.deleteRole(user().getId()
                    ,user().getRoles().stream().filter(x->x.getName().equals("SELLER")).findFirst().get().getId() );
           return true;
        }
        else return seller.isActive();
    }

    public boolean activeToSeller(long sellerid){
            var seller = sellerRepository.findById(sellerid).get();

        if (seller.isActive()) {
            return true;
        } else {
            seller.setActive(true);
            return true;
        }
    }


    @Transactional  //JUST ADMIN Could CALL THıS METHOD
    public Seller updateSeller(@NonNull Seller seller){

        var newseller = getSeller(seller.getId());
        newseller.setName(seller.getName());
        sellerRepository.save(newseller);
        return getSeller(newseller.getId());

    }

    public boolean deleteSeller(long sellerid){ // NOT RECOMMENDED FOR USE
        sellerRepository.deleteById(sellerid);
        return  isReady(sellerid)  ?true :false;

    }




}
