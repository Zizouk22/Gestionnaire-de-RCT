package com.mycompany.gestionrecettes;

import java.util.List;
import java.util.stream.Collectors;

public class Ingredient {
    private String nom;
    private double quantite;
    private String uniteDeMesure; // Unité de mesure

    // Constructeur pour nom et quantité
    public Ingredient(String nom, double quantite) {
        this.nom = nom;
        this.quantite = quantite;
        this.uniteDeMesure = ""; // Unité vide par défaut
    }

    // Constructeur pour nom, quantité et unité
    public Ingredient(String nom, double quantite, String unite) {
        this.nom = nom;
        this.quantite = quantite;
        this.uniteDeMesure = unite; // Initialisation de l'unité de mesure
    }

    // Getters et Setters
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    public String getUniteDeMesure() {
        return uniteDeMesure; // Retourne l'unité de mesure
    }

    public void setUniteDeMesure(String uniteDeMesure) {
        this.uniteDeMesure = uniteDeMesure;
    }

    // Méthode pour obtenir une représentation sous forme de chaîne d'un ingrédient
    @Override
    public String toString() {
        return quantite + " " + uniteDeMesure + " de " + nom; // Exemple de représentation
    }

    // Méthode statique pour obtenir une chaîne représentant une liste d'ingrédients
    public static String ingredientsString(List<Ingredient> ingredients) {
        return ingredients.stream()
                .map(Ingredient::toString) // Utiliser toString pour chaque ingrédient
                .collect(Collectors.joining(", ")); // Joint les ingrédients avec une virgule
    }
}