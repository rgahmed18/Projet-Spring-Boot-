package com.COMMANDE.commande_service.config;

import com.COMMANDE.commande_service.model.Commande;
import com.COMMANDE.commande_service.repository.CommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private CommandeRepository commandeRepository;

    @Override
    public void run(String... args) throws Exception {
        // Ajouter quelques commandes de test
        commandeRepository.save(new Commande("Ordinateur Portable", 1, LocalDate.now().minusDays(2), 1200.0,22));
        commandeRepository.save(new Commande("Souris USB", 3, LocalDate.now().minusDays(5), 45.0,300));
        commandeRepository.save(new Commande("Clavier Mécanique", 2, LocalDate.now().minusDays(1), 89.99,400));
        commandeRepository.save(new Commande("Ecran 24\"", 1, LocalDate.now().minusDays(8), 250.0,500));
        commandeRepository.save(new Commande("Casque Audio", 1, LocalDate.now().minusDays(15), 75.5,600));

        System.out.println("✅ Données de test chargées avec succès - " + commandeRepository.count() + " commandes");
    }
}