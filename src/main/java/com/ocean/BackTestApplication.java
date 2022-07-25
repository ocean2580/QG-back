package com.ocean;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
@MapperScan
public class BackTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackTestApplication.class, args);
    }


}
