package com.commande.apigateway;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class FallbackController {

    @RequestMapping("/fallback/Produits")
    public Mono<String> fallbackProduitsService() {
        return Mono.just("Le service des produits est momentanément indisponible. Veuillez réessayer plus tard.");
    }

    // Vous pouvez ajouter d'autres méthodes de fallback pour d'autres services ici
    @RequestMapping("/fallback/Commandes")
    public Mono<String> fallbackCommandesService() {
        return Mono.just("Le service des commandes est momentanément indisponible.");
    }
}