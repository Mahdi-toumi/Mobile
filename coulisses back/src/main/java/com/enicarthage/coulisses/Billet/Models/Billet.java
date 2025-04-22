package com.enicarthage.coulisses.Billet.Models;


import com.enicarthage.coulisses.Spectacles.Models.Spectacle;
import jakarta.persistence.*;

@Entity
@Table(name = "BILLET")
public class Billet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDBILLET")
    private Long id;

    @Column(name = "CATEGORIE", nullable = false)
    private String categorie;

    @Column(name = "PRIX", nullable = false)
    private Double prix;

    @ManyToOne
    @JoinColumn(name = "IDSPEC", nullable = false)
    private Spectacle spectacle;

    @Column(name = "VENDU", nullable = false)
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
