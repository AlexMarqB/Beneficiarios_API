package com.ijb.beneficiarios_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BeneficiariosApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BeneficiariosApiApplication.class, args);
    }

}
