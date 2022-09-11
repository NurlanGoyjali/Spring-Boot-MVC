package com.example.hiperecommerce.configuration;

import com.example.hiperecommerce.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.core.annotation.Order;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@Configuration
@EnableGlobalMethodSecurity(  prePostEnabled = true,securedEnabled = true,jsr250Enabled = true)
@AllArgsConstructor
@Order(SecurityProperties.BASIC_AUTH_ORDER)
public class SecureConf extends WebSecurityConfigurerAdapter {

    public final UserService userService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/login","/logout","/","/home")
                .permitAll().antMatchers("/api")
                .access("hasRole('USER') or  hasRole('ADMIN')" +
                        " or hasRole('DELIVER') or hasRole('SELLER') or hasRole('MANAGER') " )
                .anyRequest().authenticated()
                .and()
                .formLogin().loginProcessingUrl("/login").permitAll()
                .and()
                .csrf().disable()
                .exceptionHandling().accessDeniedHandler(new AccessDeniedHandler() {
                    @Override
                    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
                         response.setContentType("text/html; charset=UTF-8");
                         response.getWriter().println("WRONG WHERE YOU ARE FLY");
                         response.getWriter().println("WRONG RAIL FOR YOU");
                    }
                })
                .and()
                .logout();



    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(User.withDefaultPasswordEncoder().username("user").password("password").roles("USER").build());
//        return manager;
//    }
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.securityContext((securityContext) -> securityContext
//                .securityContextRepository(new RequestAttributeSecurityContextRepository())  )
//                .sessionManagement(session -> session
//                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS) )
//                .logout(logout -> logout
//                .deleteCookies("JSESSIONID")    )
//                .sessionManagement(session -> session
//                        .maximumSessions(1)
//                        .maxSessionsPreventsLogin(true  )
//                );
//        return http.build();
//    }
//


}
