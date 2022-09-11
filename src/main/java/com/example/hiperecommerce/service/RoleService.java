package com.example.hiperecommerce.service;

import com.example.hiperecommerce.entity.Role;
import com.example.hiperecommerce.entity.User;
import com.example.hiperecommerce.reposiory.RoleRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Log4j2
@AllArgsConstructor
public class RoleService {

    private  RoleRepository roleRepository;
    private  UserService userService;

    public List<Role> getAll(){
        return roleRepository.findAll();
    }

    public Role getRoleByName (String name){
        return roleRepository.findAll().stream().filter(x->x.getName().equals(name)).findFirst().get();
    }

    public List<Role> getRoles(Long userId){
        return  userService.getAll().stream().filter(x->x.getId()==userId)
               .findAny().get().getRoles().stream().toList();
    }

    @Transactional
    public List<? extends GrantedAuthority> addRole(Role role,Long userId){

        var roles = userService.getOne(userId).getRoles().add(role);
        var ok = userService.getOne(userId).getRoles();
        log.info("add role : " + ok);
        return getRoles(userId);
    }
    @Transactional
    public User deleteRole(Long userid , Long roleid){
       var a = userService.getOne(userid).getRoles().removeIf(x->x.getId()==roleid);
       log.info("silme durumu : "+ a);
        return userService.getOne(userid);
    }



}
