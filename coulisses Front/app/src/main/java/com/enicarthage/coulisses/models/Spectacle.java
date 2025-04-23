package com.enicarthage.coulisses.models;

import com.google.gson.annotations.SerializedName;
import java.math.BigDecimal;

public class Spectacle {
    private Long id;
    private String titre;
    private String date; // peut rester en String si parsé dans l'adapter
    private BigDecimal heureDebut;
    private BigDecimal duree;
    private int nbSpectateurs;

    @SerializedName("idLieu")
    private Lieu lieu;

    @SerializedName("imageUrl")
    private String imageUrl;

    @SerializedName("siteWeb")
    private String siteWeb;

    @SerializedName("description")
    private String description;

    // Getters & Setters

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getSiteWeb() {
        return siteWeb;
    }

    public void setSiteWeb(String siteWeb) {
        this.siteWeb = siteWeb;
    }
}
