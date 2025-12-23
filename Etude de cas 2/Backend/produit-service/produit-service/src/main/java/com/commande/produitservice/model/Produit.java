package com.commande.produitservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "PRODUIT")
public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String description;
    private Double prix;
    private Integer quantiteStock;
    private String imageUrl;

    // Constructeurs
    public Produit() {}

    public Produit(String nom, String description, Double prix, Integer quantiteStock) {
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.quantiteStock = quantiteStock;
    }

    public Produit(String nom, String description, Double prix, Integer quantiteStock, String imageUrl) {
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.quantiteStock = quantiteStock;
        this.imageUrl = imageUrl;
    }

    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    @JsonProperty("name")
    public String getName() { return nom; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Double getPrix() { return prix; }
    public void setPrix(Double prix) { this.prix = prix; }

    @JsonProperty("price")
    public Double getPrice() { return prix; }

    @JsonProperty("imageUrl")
    public String getImageUrl() { return imageUrl; }

    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public Integer getQuantiteStock() { return quantiteStock; }
    public void setQuantiteStock(Integer quantiteStock) { this.quantiteStock = quantiteStock; }
}