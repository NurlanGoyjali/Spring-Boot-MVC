package com.example.hiperecommerce.service;

import com.example.hiperecommerce.entity.Address;
import com.example.hiperecommerce.entity.User;
import com.example.hiperecommerce.reposiory.AddressRepository;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

@Service
@Log4j2
@AllArgsConstructor
public class AddressService {

    private  AddressRepository addressRepository;

    public List<Address> getAll(){

        return addressRepository.findAll();
    }

    public Address getAddress(Long id ){
        return addressRepository.getReferenceById(id);
    }

    public Address createAddress(Address address){

        log.info("Address Created : " + address  );
        return addressRepository.save(address);
    }

    public String deleteAddress(Long id){
        var address = getAddress(id);
        if (address != null ) {
            addressRepository.delete(address);
            return "deleted" ;
        }
        return "Request denied";
    }
    @Transactional
    public Address updateAddress( Address data, Long id){
        log.info("inside of if");
        if (1==1){
            addressRepository.findById(id).ifPresent(x->{
                x.setDescription(data.getDescription());
                x.setFulladdress(data.getFulladdress());
                x.setName(data.getName());

            });
            return getAddress(data.getId());
        }
        else
            return null;



    }

}
