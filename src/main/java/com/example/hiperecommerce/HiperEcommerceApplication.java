package com.example.hiperecommerce;

import org.apache.coyote.Request;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestBody;


@SpringBootApplication
public class HiperEcommerceApplication {

    public static void main(String[] args) {
        System.setProperty("spring.main.lazy-initialization","false");
        SpringApplication.run(HiperEcommerceApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}

