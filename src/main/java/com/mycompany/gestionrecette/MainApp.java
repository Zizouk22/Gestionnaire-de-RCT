package com.mycompany.gestionrecette;

import com.mycompany.gestionrecettes.Ingredient;

public class MainApp {
    public static void main(String[] args) {
        Ingredient ingredient1 = new Ingredient("Farine", 500, "grammes");
        Ingredient ingredient2 = new Ingredient("Oeufs", 3, "unités");

        System.out.println("Ingrédients:");
        System.out.println(ingredient1.getQuantite() + " " + ingredient1.getUniteDeMesure() + " de " + ingredient1.getNom());
        System.out.println(ingredient2.getQuantite() + " " + ingredient2.getUniteDeMesure() + " de " + ingredient2.getNom());
    }
}