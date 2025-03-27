package com.example.educationalsystembackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
public class EducationalSystemBackEndApplication {

    public static void main(String[] args) {
        SpringApplication.run(EducationalSystemBackEndApplication.class, args);
    }

}
