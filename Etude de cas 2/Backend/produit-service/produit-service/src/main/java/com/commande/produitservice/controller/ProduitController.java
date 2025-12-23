package com.commande.produitservice.controller;

import com.commande.produitservice.model.Produit;
import com.commande.produitservice.repository.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping({"/api/produits", "/api/products"})
public class ProduitController {

    @Autowired
    private ProduitRepository produitRepository;

    @GetMapping
    public List<Produit> getAllProduits() {
        return produitRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produit> getProduitById(@PathVariable Long id) {
        return produitRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Produit createProduit(@RequestBody Produit produit) {
        return produitRepository.save(produit);
    }

    @PutMapping("/{id}")
    public Produit updateProduit(@PathVariable Long id, @RequestBody Produit produitDetails) {
        return produitRepository.findById(id)
                .map(produit -> {
                    produit.setNom(produitDetails.getNom());
                    produit.setDescription(produitDetails.getDescription());
                    produit.setPrix(produitDetails.getPrix());
                    produit.setQuantiteStock(produitDetails.getQuantiteStock());
                    return produitRepository.save(produit);
                })
                .orElseGet(() -> {
                    produitDetails.setId(id);
                    return produitRepository.save(produitDetails);
                });
    }

    @DeleteMapping("/{id}")
    public void deleteProduit(@PathVariable Long id) {
        produitRepository.deleteById(id);
    }
}