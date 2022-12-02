package com.example.mealer24;

import android.widget.ArrayAdapter;

public class CuisinierEtRepasInfo {
    private String nomCuisinier;
    private String cuisinierAddress;
    private String cuisinierDescription;
    private String repasNom;
    private Double prix;
    private String typeDeRepas;
    private String typeDeCuisine;
    private String listeIngredients;
    private String listeAllergies;
    private String descriptionRepas;

    public CuisinierEtRepasInfo(Repas meal,Cuisinier cook){

        nomCuisinier = cook.getNom();
        cuisinierAddress = cook.getAddress();
        cuisinierDescription = cook.getDescription();
        repasNom = meal.getNomDuRepas();
        prix = meal.getPrix();
        typeDeRepas = meal.getTypeDeRepas();
        typeDeCuisine = meal.getTypeDeCuisine();
        listeIngredients = meal.getIngredients();
        listeAllergies = meal.getAllergies();
        descriptionRepas = meal.getDescription();
    }

    public String getNomCuisinier() {
        return nomCuisinier;
    }

    public String getRepasNom() {
        return repasNom;
    }

    public Double getLePrix() {
        return prix;
    }

    public String getTypeDeCuisine() {
        return typeDeCuisine;
    }

    public String getTypeDeRepas() {
        return typeDeRepas;
    }

    public String getListeIngredients() {
        return listeIngredients;
    }

    public String getListeAllergies() {
        return listeAllergies;
    }

    public String getDescriptionRepas() {
        return descriptionRepas;
    }

    public String getCuisinierDescription() {
        return cuisinierDescription;
    }

    public String getCuisinierAddress() {
        return cuisinierAddress;
    }
}
