package com.mycompany.gestionrecettes;

import java.util.List;

public class Recette {
    private String nom;
    private int temps;
    private List<Ingredient> ingredients;
    private String instructions;

    // Constructeur qui prend tous les paramètres nécessaires
    public Recette(String nom, int temps, List<Ingredient> ingredients, String instructions) {
        this.nom = nom;
        this.temps = temps;
        this.ingredients = ingredients;
        this.instructions = instructions;
    }

    // Getters
    public String getNom() {
        return nom;
    }

    public int getTemps() {
        return temps;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public String getInstructions() {
        return instructions;
    }

    @Override
    public String toString() {
        return nom + " - " + temps + " minutes";
    }
}