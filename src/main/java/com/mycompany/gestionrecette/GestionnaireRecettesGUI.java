package com.mycompany.gestionrecettes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class GestionnaireRecettesGUI {
    private JFrame frame;
    private JPanel mainPanel;
    private CardLayout cardLayout; // Pour gérer les panneaux
    private JTextField nomField;
    private JTextField tempsField;
    private JTextArea ingredientsField;
    private JTextArea instructionsField;
    private JTextField rechercheField;
    private JTextArea textArea;
    private ArrayList<Recette> recettes; // Déclaration correcte de la liste
    private int selectedIndex = -1; // Pour garder la trace de la recette sélectionnée

    public GestionnaireRecettesGUI() {
        frame = new JFrame("Gestionnaire de Recettes");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 800); // Taille de la fenêtre
        frame.setLayout(new BorderLayout());

        // Initialisation de la liste des recettes
        recettes = new ArrayList<>();

        // CardLayout pour gérer l'affichage des panneaux
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Créer les boutons avec styles
        JButton ajouterRecetteButton = createStyledButton("Ajouter une Recette", "#b21400");
        JButton rechercherRecetteButton = createStyledButton("Rechercher une Recette", "#f3551c");
        JButton afficherRecettesButton = createStyledButton("Afficher les Recettes", "#00298c");

        // Ajouter un ActionListener pour chaque bouton
        ajouterRecetteButton.addActionListener(e -> cardLayout.show(mainPanel, "ajouter"));
        rechercherRecetteButton.addActionListener(e -> cardLayout.show(mainPanel, "rechercher"));
        afficherRecettesButton.addActionListener(e -> {
            afficherRecettes(); // Met à jour l'affichage des recettes
            cardLayout.show(mainPanel, "afficher");
        });

        // Créer le panneau de boutons
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(ajouterRecetteButton);
        buttonPanel.add(rechercherRecetteButton);
        buttonPanel.add(afficherRecettesButton);

        // Ajouter les sections
        mainPanel.add(createAjoutPanel(), "ajouter");
        mainPanel.add(createRecherchePanel(), "rechercher");
        mainPanel.add(createAffichagePanel(), "afficher");

        frame.add(buttonPanel, BorderLayout.NORTH);
        frame.add(mainPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private JButton createStyledButton(String text, String colorHex) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 24));
        button.setBackground(Color.decode(colorHex));
        button.setForeground(Color.WHITE); // Couleur du texte
        button.setFocusPainted(false); // Retirer le contour au focus
        button.setBorderPainted(false); // Retirer la bordure
        return button;
    }

    private JPanel createAjoutPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(186, 186, 186));

        JLabel label = new JLabel("Ajouter une Recette", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(label, BorderLayout.NORTH);

        // Champs de saisie
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(5, 2));

        inputPanel.add(new JLabel("Nom de la Recette:"));
        nomField = new JTextField();
        inputPanel.add(nomField);

        inputPanel.add(new JLabel("Temps de Préparation:"));
        tempsField = new JTextField();
        inputPanel.add(tempsField);

        inputPanel.add(new JLabel("Ingrédients (séparés par des virgules):"));
        ingredientsField = new JTextArea(2, 10); // Taille ajustée
        inputPanel.add(new JScrollPane(ingredientsField));

        inputPanel.add(new JLabel("Instructions:"));
        instructionsField = new JTextArea(2, 10); // Taille ajustée
        inputPanel.add(new JScrollPane(instructionsField));

        JButton addButton = createStyledButton("Ajouter Recette", "#00a400");
        inputPanel.add(addButton);

        panel.add(inputPanel, BorderLayout.CENTER);

        // Action pour le bouton d'ajout
        addButton.addActionListener(e -> ajouterRecette());

        return panel;
    }

    private JPanel createRecherchePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(186, 186, 186));

        JLabel label = new JLabel("Rechercher une Recette", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(label, BorderLayout.NORTH);

        rechercheField = new JTextField();
        panel.add(rechercheField, BorderLayout.CENTER);

        JButton searchButton = createStyledButton("Rechercher", "#00a400"); // Utiliser la couleur de confirmation
        panel.add(searchButton, BorderLayout.SOUTH);

        searchButton.addActionListener(e -> {
            String recetteRecherchee = rechercheField.getText();
            // Implémenter la recherche ici
            // Afficher les résultats en utilisant textArea
        });

        return panel;
    }

    private JPanel createAffichagePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(186, 186, 186));

        JLabel label = new JLabel("Afficher les Recettes", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(label, BorderLayout.NORTH);

        // Zone de texte pour afficher les recettes
        textArea = new JTextArea();
        textArea.setEditable(false);
        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);

        // Pour afficher les recettes existantes
        afficherRecettes(); // Appeler la méthode pour charger les recettes

        // Créer un panneau pour les boutons de suppression et de modification
        JPanel actionPanel = new JPanel();
        JButton modifierButton = createStyledButton("Modifier", "#00a400");
        JButton supprimerButton = createStyledButton("Supprimer", "#00a400");

        actionPanel.add(modifierButton);
        actionPanel.add(supprimerButton);
        panel.add(actionPanel, BorderLayout.SOUTH);

        // Actions pour les boutons
        modifierButton.addActionListener(e -> modifierRecette());
        supprimerButton.addActionListener(e -> supprimerRecette());

        return panel;
    }

    private void afficherRecettes() {
        StringBuilder allRecettes = new StringBuilder();

        for (int i = 0; i < recettes.size(); i++) {
            allRecettes.append(i + 1).append(". ").append(recettes.get(i).toString()).append("\n\n");
        }

        if (allRecettes.length() == 0) {
            textArea.setText("Aucune recette existante.");
        } else {
            textArea.setText(allRecettes.toString());
        }
    }

  private void modifierRecette() {
    if (selectedIndex != -1) {
        Recette recette = recettes.get(selectedIndex);
        nomField.setText(recette.getNom());
        tempsField.setText(String.valueOf(recette.getTemps()));

        // Extraire les noms des ingrédients pour les afficher
        String ingredientsString = recette.getIngredients().stream()
            .map(Ingredient::getNom) // Assurez-vous d'avoir une méthode getNom() dans la classe Ingredient
            .collect(Collectors.joining(", "));

        ingredientsField.setText(ingredientsString); // Ajuster pour afficher correctement
        instructionsField.setText(recette.getInstructions());
        cardLayout.show(mainPanel, "ajouter"); // Revenir au panneau d'ajout pour modifier
    } else {
        JOptionPane.showMessageDialog(frame, "Veuillez sélectionner une recette à modifier.");
    }
}


    private void supprimerRecette() {
        if (selectedIndex != -1) {
            recettes.remove(selectedIndex);
            selectedIndex = -1; // Réinitialiser l'index
            afficherRecettes(); // Mettre à jour l'affichage
        } else {
            JOptionPane.showMessageDialog(frame, "Veuillez sélectionner une recette à supprimer.");
        }
    }

    // Exemple de méthode pour ajouter une recette
    private void ajouterRecette() {
    String nom = nomField.getText();
    int temps = Integer.parseInt(tempsField.getText());
    String ingredientsText = ingredientsField.getText();
    String instructions = instructionsField.getText();

    // Transformation de la chaîne d'ingrédients en liste
    String[] ingredientsArray = ingredientsText.split(",");
    ArrayList<Ingredient> ingredients = new ArrayList<>();
    for (String ing : ingredientsArray) {
        String[] parts = ing.trim().split(" "); // Séparez le nom de l'ingrédient et sa quantité
        if (parts.length >= 2) {
            String ingredientNom = parts[0];
            double ingredientQuantite = Double.parseDouble(parts[1]); // Supposons que la quantité est le deuxième élément
            ingredients.add(new Ingredient(ingredientNom, ingredientQuantite)); // Créer l'ingrédient
        }
    }

    // Créer une nouvelle recette avec les bons arguments
    Recette nouvelleRecette = new Recette(nom, temps, ingredients, instructions);
    recettes.add(nouvelleRecette);

    // Réinitialiser les champs
    nomField.setText("");
    tempsField.setText("");
    ingredientsField.setText("");
    instructionsField.setText("");

    JOptionPane.showMessageDialog(frame, "Recette ajoutée avec succès!");
    }

    private void clearFields() {
        nomField.setText("");
        tempsField.setText("");
        ingredientsField.setText("");
        instructionsField.setText("");
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(GestionnaireRecettesGUI::new);
    }
}