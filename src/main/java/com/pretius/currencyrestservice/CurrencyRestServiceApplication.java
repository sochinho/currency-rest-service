package com.pretius.currencyrestservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.pretius.currencyrestservice")
public class CurrencyRestServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CurrencyRestServiceApplication.class, args);
    }
}
