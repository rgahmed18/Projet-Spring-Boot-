package com.COMMANDE.commande_service.controller;

import com.COMMANDE.commande_service.model.Commande;
import com.COMMANDE.commande_service.repository.CommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RefreshScope
@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping({"/api/commandes", "/api/orders"})
public class CommandeController {

    @Autowired
    private CommandeRepository commandeRepository;

    // Injection de la propriété personnalisée
    @Value("${mes-config-ms.commandes-last:10}")
    private int commandesLastDays;

    // CRUD - Create
    @PostMapping
    public Commande createCommande(@RequestBody Commande commande) {
        if (commande.getDate() == null) {
            commande.setDate(LocalDate.now());
        }
        return commandeRepository.save(commande);
    }

    // CRUD - Read All
    @GetMapping
    public List<Commande> getAllCommandes() {
        return commandeRepository.findAll();
    }

    // CRUD - Read by ID
    @GetMapping("/{id}")
    public ResponseEntity<Commande> getCommandeById(@PathVariable Long id) {
        return commandeRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // CRUD - Update
    @PutMapping("/{id}")
    public Commande updateCommande(@PathVariable Long id, @RequestBody Commande commandeDetails) {
        return commandeRepository.findById(id)
                .map(commande -> {
                    commande.setDescription(commandeDetails.getDescription());
                    commande.setQuantite(commandeDetails.getQuantite());
                    commande.setDate(commandeDetails.getDate());
                    commande.setMontant(commandeDetails.getMontant());
                    commande.setIdProduit(commandeDetails.getIdProduit());
                    return commandeRepository.save(commande);
                })
                .orElseGet(() -> {
                    commandeDetails.setId(id);
                    return commandeRepository.save(commandeDetails);
                });
    }

    // CRUD - Delete
    @DeleteMapping("/{id}")
    public void deleteCommande(@PathVariable Long id) {
        commandeRepository.deleteById(id);
    }

    // ✅ Endpoint pour tester le health check DOWN (supprimer toutes les commandes)
    @DeleteMapping("/clear")
    public String clearAllCommandes() {
        commandeRepository.deleteAll();
        return "Toutes les commandes ont été supprimées. Le health check devrait passer à DOWN.";
    }

    // Endpoint pour tester la propriété configurée
    @GetMapping("/config")
    public String getConfig() {
        return "Configuration 'mes-config-ms.commandes-last': " + commandesLastDays + " jours";
    }









    // ✅ Correction de la méthode resetTestData
    @PostMapping("/reset")
    public String resetTestData() {
        // Supprimer toutes les commandes existantes
        commandeRepository.deleteAll();

        // Recréer des données de test
        commandeRepository.save(new Commande("Ordinateur Portable", 1, LocalDate.now().minusDays(2), 1200.0 ,11));
        commandeRepository.save(new Commande("Souris USB", 3, LocalDate.now().minusDays(5), 45.0,12));
        commandeRepository.save(new Commande("Clavier Mécanique", 2, LocalDate.now().minusDays(1), 89.99,13));
        commandeRepository.save(new Commande("Ecran 24\"", 1, LocalDate.now().minusDays(8), 250.0,14));
        commandeRepository.save(new Commande("Casque Audio", 1, LocalDate.now().minusDays(15), 75.5,15));

        return "Données de test réinitialisées - " + commandeRepository.count() + " commandes";
    }








    // ✅ Endpoint pour vérifier le nombre de commandes
    @GetMapping("/count")
    public String getCommandesCount() {
        long count = commandeRepository.count();
        return "Nombre de commandes dans la base : " + count;
    }



    // Endpoint pour les commandes récentes (utilise la propriété configurée)
    @GetMapping("/recentes")
    public List<Commande> getCommandesRecentes() {
        LocalDate dateLimite = LocalDate.now().minusDays(commandesLastDays);
        // Cette méthode nécessitera l'implémentation dans le repository
        return commandeRepository.findAll().stream()
                .filter(commande -> commande.getDate().isAfter(dateLimite))
                .toList();
    }


    @GetMapping("/test-timeout")
    public Map<String, String> testTimeout() throws InterruptedException {
        System.out.println("⏳ Début de la simulation de lenteur (5 secondes)...");

        // On dort pendant 5000ms (5s)
        // Le Gateway est configuré pour couper après 3s
        Thread.sleep(5000);

        return Collections.singletonMap("message", "Réponse normale (Si vous voyez ceci, le Circuit Breaker n'a pas marché !)");
    }

    public Map<String, Object> fallbackProduitInfo(Long id) {
        return Collections.singletonMap("message", "fallback: produit service unreachable or timeout");
    }

}