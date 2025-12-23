package com.COMMANDE.commande_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient  // ✅ Ajoute cette annotation
public class CommandeServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(CommandeServiceApplication.class, args);
    }
}