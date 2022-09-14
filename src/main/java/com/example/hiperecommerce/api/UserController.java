package com.example.hiperecommerce.api;

import com.example.hiperecommerce.DTOs.UserDto;
import com.example.hiperecommerce.DTOs.UserRb;
import com.example.hiperecommerce.entity.User;
import com.example.hiperecommerce.service.RoleService;
import com.example.hiperecommerce.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@RestController
@Log4j2
@AllArgsConstructor
@RequestMapping( "/api/user")
public class UserController {

    private UserService userService;
    private RoleService roleService;
    private ModelMapper mapper;

    @Bean
    CommandLineRunner commandLineRunner() {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                BCryptPasswordEncoder bCryptPasswordEncoder =
                        new BCryptPasswordEncoder();
                var asd = bCryptPasswordEncoder.encode("12345");

                log.info("ENCODED FKING PASSWORD : " + asd);
            }
        };
    }
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    @GetMapping("/dev")
    public ResponseEntity BelkeBuGunBelkeSeher( @AuthenticationPrincipal User user ) {
        User userDetails = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info("MY USER : " + SecurityContextHolder.getContext().getAuthentication().getPrincipal()  );
        log.info("MY USER roles: " + SecurityContextHolder.getContext().getAuthentication().getAuthorities()  );
        log.info("MY USER  user: " + user );
        log.info("MY USER  user: " + userDetails.getId() );
        return ResponseEntity.ok(SecurityContextHolder.getContext().getAuthentication().getAuthorities()+"\n"+user);
    }


    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    @GetMapping("/getmaping")
    public List<UserDto> GetAll() {
       return userService.getAll()
               .stream().map(x->mapper.map(x,UserDto.class))
               .collect(Collectors.toList());
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/getuser/{userId}")
    public ResponseEntity GetUser (  @PathVariable("userId") Long userId ) {
        return ResponseEntity.ok(userService.getOne(userId));
    }

    @GetMapping("/checkusername/{username}")
    public ResponseEntity CheckUsername(@PathVariable("username") String username){
        return userService.checkUsername(username)
                ? ResponseEntity.ok("Ad istifadeye uyğundur")
                : ResponseEntity.ok("bu adda başqa istifadeçi var lütfen deyişiklik edin.");
    }

    @GetMapping("/checkemail/{email}")
    public ResponseEntity CheckEmail(@PathVariable("email") String email){
        return userService.checkEmail(email)
                ? ResponseEntity.ok("E-mail istifadeye uyğundur")
                : ResponseEntity.ok("bu E-maile sahib başqa istifadeçi var lütfen deyişiklik edin.");
    }


    @PostMapping("/createuser")
    public ResponseEntity CreateUser(@Valid @RequestBody UserRb user ) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()) );
        user.addRole( roleService.getRoleByName("USER") );
        user.addDate(LocalDateTime.now());
        System.out.println("userdto: " +user );
        System.out.println("localdate: " + LocalDateTime.now() );
        System.out.println("user: " + mapper.map(user,User.class) );
        return userService.checkEmail(user.getEmail()) && userService.checkUsername(user.getUsername())
                ?  ResponseEntity.ok(userService.createUser(mapper.map(user,User.class)) )
                : ResponseEntity.ok( "Istifadeçi hazırlama prosesinde xeta baş verdi"
                        +"\n"
                        +CheckEmail(user.getEmail()).getBody().toString()
                        +"\n"
                        +CheckUsername(user.getUsername()).getBody().toString()
                        );

    }

    @PreAuthorize("hasAuthority('USER')")
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
