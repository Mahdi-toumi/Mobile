package com.enicarthage.coulisses.models;

public class Billet {
    private Long id;
    private String categorie;
    private Double prix;
    private Spectacle spectacle;
    private String vendu;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public Spectacle getSpectacle() {
        return spectacle;
    }

    public void setSpectacle(Spectacle spectacle) {
        this.spectacle = spectacle;
    }

    public String getVendu() {
        return vendu;
    }

    public void setVendu(String vendu) {
        this.vendu = vendu;
    }
}
