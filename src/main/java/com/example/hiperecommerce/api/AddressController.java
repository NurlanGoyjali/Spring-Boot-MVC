package com.example.hiperecommerce.api;


import com.example.hiperecommerce.entity.Address;
import com.example.hiperecommerce.entity.User;
import com.example.hiperecommerce.service.AddressService;
import com.example.hiperecommerce.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping("api/address")
@AllArgsConstructor
public class AddressController {

    private AddressService addressService;
    @PostAuthorize("hasAnyAuthority('USER')")
    @GetMapping("/getall")
    public ResponseEntity GetAll(){
        return ResponseEntity.ok( addressService.getAll() );
    }

    @GetMapping("/create")
    public ResponseEntity CreateAddress(@AuthenticationPrincipal User user){
        System.out.println("username : " + user.getUsername());
        Address address = new Address("baki","address1","asdsas",user);
        log.info("CreateAddress : "  + address);
        return ResponseEntity.ok(addressService.createAddress(address));
    }

    @PostAuthorize("hasAnyAuthority('USER')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity DeleteAddress(@PathVariable("id") Long id){
        log.info("delete worked");
        return ResponseEntity.ok( addressService.deleteAddress(id) );
    }

    @PostAuthorize("hasAnyAuthority('USER')")
    @PutMapping("/update")
    public ResponseEntity<Address> UpdateAddress(@PathVariable(value = "id") Long id ,  @RequestBody Address address){
        log.info("update worked");
        var a = addressService.updateAddress(address,id);
        return ResponseEntity.ok( a );
    }

}
