package com.COMMANDE.commande_service.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "COMMANDE")
public class Commande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private long  idProduit;

    private String description;
    private Integer quantite;
    private LocalDate date;
    private Double montant;

    // Constructeurs
    public Commande() {}

    public Commande(String description, Integer quantite, LocalDate date, Double montant,long idProduit) {
        this.description = description;
        this.quantite = quantite;
        this.date = date;
        this.montant = montant;
        this.idProduit=idProduit;
    }

    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getIdProduit() { return idProduit; }
    @JsonAlias("productId")
    public void setIdProduit(Long idProduit) { this.idProduit = idProduit; }

    @JsonProperty("productId")
    public Long getProductId() { return idProduit; }

    @JsonProperty("productId")
    public void setProductId(Long productId) { this.idProduit = productId; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Integer getQuantite() { return quantite; }
    public void setQuantite(Integer quantite) { this.quantite = quantite; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public Double getMontant() { return montant; }
    public void setMontant(Double montant) { this.montant = montant; }


    @Override
    public String toString() {
        return "Commande{id=" + id + ", description='" + description + "', quantite=" + quantite +
                ", date=" + date + ", montant=" + montant + ", idProduit" + idProduit+ '}';
    }

}