package com.volyanyk.testodoc;

import org.springframework.boot.SpringApplication;

public class TestTestodocApplication {

    public static void main(String[] args) {
        SpringApplication.from(TestodocApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
