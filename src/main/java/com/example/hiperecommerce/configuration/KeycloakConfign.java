package com.example.hiperecommerce.configuration;

import lombok.SneakyThrows;
//import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
//import org.keycloak.adapters.springsecurity.KeycloakConfiguration;
//import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
//import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;


//@KeycloakConfiguration
//@Import(KeycloakSpringBootConfigResolver.class)
public class KeycloakConfign /* extends KeycloakWebSecurityConfigurerAdapter */ {
/*
    @Autowired
    @SneakyThrows
    public void configureGlobal (AuthenticationManagerBuilder builder){
        KeycloakAuthenticationProvider provider = keycloakAuthenticationProvider();
        provider.setGrantedAuthoritiesMapper(new SimpleAuthorityMapper());
        builder.authenticationProvider(provider);
        builder.authenticationProvider(keycloakAuthenticationProvider());
    }
    @Bean
    public SessionAuthenticationStrategy sessionAuthenticationStrategy(){
//  same with return type so u may write return register; :P
//        RegisterSessionAuthenticationStrategy register = new
//        RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
        return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        http.authorizeRequests()
                //.antMatchers("/","home","/login").hasAnyAuthority()
                .antMatchers("/logout").hasRole("USER")
                .anyRequest().permitAll()
                .and()
                .build();
    }

    */
}
