package com.example.board.api.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class BoardApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(BoardApiGatewayApplication.class, args);
    }

}
