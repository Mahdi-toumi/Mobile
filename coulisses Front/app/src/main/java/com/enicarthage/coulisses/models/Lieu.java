package com.enicarthage.coulisses.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Lieu implements Parcelable {
    private Long id;
    private String nom;
    private String adresse;
    private Integer capacite;
    private String ville;
    private Boolean active;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public Integer getCapacite() {
        return capacite;
    }

    public void setCapacite(Integer capacite) {
        this.capacite = capacite;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    protected Lieu(Parcel in) {
        id = in.readLong();
        nom = in.readString();
        adresse = in.readString();
        capacite = in.readInt();
        ville = in.readString();
        active = in.readByte() != 0;
    }

    public static final Creator<Lieu> CREATOR = new Creator<Lieu>() {
        @Override
        public Lieu createFromParcel(Parcel in) {
            return new Lieu(in);
        }

        @Override
        public Lieu[] newArray(int size) {
            return new Lieu[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(nom);
        dest.writeString(adresse);
        dest.writeInt(capacite);
        dest.writeString(ville);
        dest.writeByte((byte) (active ? 1 : 0));
    }
}
