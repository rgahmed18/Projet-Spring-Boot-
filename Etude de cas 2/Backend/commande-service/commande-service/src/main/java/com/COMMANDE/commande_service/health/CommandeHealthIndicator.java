package com.COMMANDE.commande_service.health;

import com.COMMANDE.commande_service.repository.CommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class CommandeHealthIndicator implements HealthIndicator {

    @Autowired
    private CommandeRepository commandeRepository;

    @Override
    public Health health() {
        try {
            long count = commandeRepository.count();

            // Point e de l'énoncé : UP s'il y a des commandes, DOWN sinon
            if (count > 0) {
                return Health.up()
                        .withDetail("message", "Microservice en bonne santé - " + count + " commande(s) trouvée(s)")
                        .withDetail("nombreCommandes", count)
                        .build();
            } else {
                return Health.down()
                        .withDetail("message", "Aucune commande dans la base de données")
                        .withDetail("nombreCommandes", 0)
                        .build();
            }
        } catch (Exception e) {
            return Health.down()
                    .withDetail("message", "Erreur lors de la vérification de la santé: " + e.getMessage())
                    .build();
        }
    }
}