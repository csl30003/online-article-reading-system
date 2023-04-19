package com.example.eebighomework;

import com.example.eebighomework.util.JwtUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.eebighomework", "com.example.eebighomework.util"})
public class EeBigHomeworkApplication {

    public static void main(String[] args) {
        SpringApplication.run(EeBigHomeworkApplication.class, args);
    }
}
