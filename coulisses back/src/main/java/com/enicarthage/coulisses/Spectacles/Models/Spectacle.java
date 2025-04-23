package com.enicarthage.coulisses.Spectacles.Models;

import com.enicarthage.coulisses.Lieu.Models.Lieu;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "SPECTACLE")
public class Spectacle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDSPEC")
    private Long id;

    @Column(name = "TITRE", nullable = false)
    private String titre;

    @Column(name = "DATES")
    private LocalDate date;

    @Column(name = "H_DEBUT", nullable = false)
    private BigDecimal heureDebut;

    @Column(name = "DUREES", nullable = false)
    private BigDecimal duree;

    @Column(name = "NBRSPECTATEUR", nullable = false)
    private Integer nbSpectateurs;

    @Column(name = "IMAGEURL")
    private String imageUrl;

    @Column(name = "SITEWEB")
    private String siteWeb;

    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToOne
    @JoinColumn(name = "IDLIEU", nullable = false)
    private Lieu lieu;



    // Getters et Setters
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Getters et Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Lieu getIdLieu() {
        return lieu;
    }

    public void setIdLieu(Lieu lieu) {
        this.lieu = lieu;
    }

    public Integer getNbSpectateurs() {
        return nbSpectateurs;
    }

    public void setNbSpectateurs(Integer nbSpectateurs) {
        this.nbSpectateurs = nbSpectateurs;
    }

    public BigDecimal getDuree() {
        return duree;
    }

    public void setDuree(BigDecimal duree) {
        this.duree = duree;
    }

    public BigDecimal getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(BigDecimal heureDebut) {
        this.heureDebut = heureDebut;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
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
