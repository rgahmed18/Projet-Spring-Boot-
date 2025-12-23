package com.commande.produitservice.config;

import com.commande.produitservice.model.Produit;
import com.commande.produitservice.repository.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private ProduitRepository produitRepository;

    @Override
    public void run(String... args) throws Exception {
        produitRepository.save(new Produit("Laptop Dell", "Ordinateur portable Dell", 899.99, 10, "http://localhost:8082/images/dell.png"));
        produitRepository.save(new Produit("Souris Logitech", "Souris sans fil", 29.99, 50, "http://localhost:8082/images/souris.png"));
        produitRepository.save(new Produit("Clavier Mécanique", "Clavier gaming", 79.99, 20, "http://localhost:8082/images/clavier.png"));

        System.out.println("✅ Données de test Produit chargées - " + produitRepository.count() + " produits");
    }
}