package com.mycompany.gestionrecettes;

import java.util.ArrayList;
import java.util.List;

public class GestionnaireRecettes {
    private List<Recette> recettes;

    public GestionnaireRecettes() {
        recettes = new ArrayList<>();

    }

    public List<Recette> getRecettes() {
        return recettes;
    }

    public void ajouterRecette(Recette recette) {
        recettes.add(recette);
    }

    public void modifierRecette(int index, Recette nouvelleRecette) {
        recettes.set(index, nouvelleRecette);
    }

    public void supprimerRecette(int index) {
        recettes.remove(index);
    }

    List<Recette> rechercherRecetteParNom(String nom) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    void supprimerRecette(String nom) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    void modifierRecette(String nom, Recette nouvelleRecette) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    List<Recette> rechercherRecetteParIngredient(String ingredient) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}