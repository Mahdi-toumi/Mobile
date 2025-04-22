package com.enicarthage.coulisses.models;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;

public class Spectacle {
    private Long id;
    private String titre;
    private String date; // ou LocalDate si tu veux l’utiliser avec Gson adapter
    private BigDecimal heureDebut;
    private BigDecimal duree;
    private int nbSpectateurs;
    @SerializedName("idLieu") // S'assurer que ça matche avec le JSON
    private Lieu lieu;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public BigDecimal getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(BigDecimal heureDebut) {
        this.heureDebut = heureDebut;
    }

    public BigDecimal getDuree() {
        return duree;
    }

    public void setDuree(BigDecimal duree) {
        this.duree = duree;
    }

    public int getNbSpectateurs() {
        return nbSpectateurs;
    }

    public void setNbSpectateurs(int nbSpectateurs) {
        this.nbSpectateurs = nbSpectateurs;
    }

    public Lieu getLieu() {
        return lieu;
    }

    public void setLieu(Lieu lieu) {
        this.lieu = lieu;
    }
}