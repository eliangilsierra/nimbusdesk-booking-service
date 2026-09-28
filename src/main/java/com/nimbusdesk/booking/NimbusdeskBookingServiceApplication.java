package com.nimbusdesk.booking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class NimbusdeskBookingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(NimbusdeskBookingServiceApplication.class, args);
    }

}
