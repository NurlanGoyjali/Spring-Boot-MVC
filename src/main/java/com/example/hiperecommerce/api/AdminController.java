package com.example.hiperecommerce.api;

import com.example.hiperecommerce.entity.Role;
import com.example.hiperecommerce.entity.Role.*;
import com.example.hiperecommerce.service.RoleService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Log4j2
@RequestMapping("/api/admin")
public class AdminController {

    private RoleService roleService;


    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/getrole/{userid}")
    public ResponseEntity GetRole(@PathVariable("userid") Long userid) {
        log.info("getrole worked... userid = " + userid);
        return ResponseEntity.ok(roleService.getRoles(userid));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/addrole/{userid}/{roleid}")
    public ResponseEntity AddRole(@PathVariable("userid") Long userid, @PathVariable("roleid") int roleid  /* , @RequestBody Role role */) {
        var roles = roleService.getAll().stream().filter(x -> x.getId() == roleid).findFirst().get();

        log.info("addrole worked" + roles);

        return ResponseEntity.ok(roleService.addRole(roles, userid));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/deleterole/{userid}/{roleid}")
    public ResponseEntity DeleteRole(@PathVariable("userid") Long userid
            , @PathVariable("roleid") Long roleid) {

        return ResponseEntity.ok(roleService.deleteRole(userid, roleid));
    }


}
