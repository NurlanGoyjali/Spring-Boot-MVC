package com.example.hiperecommerce.api;

import com.example.hiperecommerce.entity.Seller;
import com.example.hiperecommerce.service.SellerService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Log4j2
@RequestMapping("/api/seller")
public class SellerController {

    private SellerService service;

    @PreAuthorize("hasAnyAuthority('ADMIN','SELLER')")
    @PutMapping("/create/{userid}")
    public ResponseEntity AddSeller(@PathVariable("userid") Long userid, @RequestBody Seller seller) {
        log.info("createseller worked... userid + seller = " + userid+ seller);
        return ResponseEntity.ok(service.addSeller(seller));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','SELLER')")
    @PutMapping("/update")
    public ResponseEntity UpdateSeller(@RequestBody Seller seller){
        return ResponseEntity.ok(service.updateSeller(seller));
    }


    @PreAuthorize("hasAnyAuthority('ADMIN','SELLER')")
    @PutMapping("/delete/{sellerid}")
    public ResponseEntity DeleteSeller(@PathVariable("sellerid") Long sellerid ) {

        return ResponseEntity.ok(service.deleteSeller(sellerid));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    @PutMapping("/get/{sellerid}")
    public ResponseEntity GetSeller(@PathVariable("sellerid") long sellerid){
        return ResponseEntity.ok(service.getSeller(sellerid));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','SELLER')")
    @PutMapping("/getsellers/{sellerid}")
    public ResponseEntity GetSellersByUserId(@PathVariable("userid") long userid){
        return ResponseEntity.ok(service.getSellers(userid));
    }


    }

