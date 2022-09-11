package com.example.hiperecommerce.api;

import com.example.hiperecommerce.DTOs.UserDto;
import com.example.hiperecommerce.entity.User;
import com.example.hiperecommerce.service.RoleService;
import com.example.hiperecommerce.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.actuate.trace.http.HttpTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/user")
@Log4j2
@AllArgsConstructor
public class UserController {

    private UserService userService;
    private ModelMapper mapper;





    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    @GetMapping("/getmaping")
    public List<UserDto> GetAll() {
       return userService.getAll()
               .stream().map(x->mapper.map(x,UserDto.class))
               .collect(Collectors.toList());
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/getuser/{userId}")
    public ResponseEntity GetUser(@PathVariable("userId") Long userId ) {
        return ResponseEntity.ok(userService.getOne(userId));
    }

    @GetMapping("/createuser")
    public ResponseEntity CreateUser() {
        User user = new User("DENEME", "zxc", "12345", LocalDateTime.now(), null);
        return ResponseEntity.ok(userService.createUser(user));
    }

    @GetMapping("/updateuser")
    public ResponseEntity UpdateUser() {
        var deneme = ThreadLocalRandom.current().doubles();
        User user = mapper.map(userService.getOne(3L),User.class);
        System.out.println(user);
        user.setUsername("samet");
        user.setEmail(String.valueOf(deneme));
        return ResponseEntity.ok(userService.updateUser(user, 3L));
    }




}
