package com.COMMANDE.commande_service.controller;

import com.COMMANDE.commande_service.model.Commande;
import com.COMMANDE.commande_service.repository.CommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
@RefreshScope
@RestController
@RequestMapping("/api/commandes")
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
    public Optional<Commande> getCommandeById(@PathVariable Long id) {
        return commandeRepository.findById(id);
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


    // ✅ Endpoint pour réinitialiser les données de test
    @PostMapping("/reset")
    public String resetTestData() {
        CommandeController commande_service = null;
        return commande_service.resetTestData();
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
}