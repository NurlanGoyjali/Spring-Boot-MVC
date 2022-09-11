package com.example.hiperecommerce;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


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

