package com.shopping.kshopping;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value={"com.shopping.kshopping.**.mapper"})
public class KshoppingApplication {

    public static void main(String[] args) {
        SpringApplication.run(KshoppingApplication.class, args);
    }

}
