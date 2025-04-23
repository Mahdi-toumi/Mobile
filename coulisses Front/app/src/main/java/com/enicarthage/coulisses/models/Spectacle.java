package com.enicarthage.coulisses.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import java.math.BigDecimal;

public class Spectacle implements Parcelable {
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

    protected Spectacle(Parcel in) {
        id = in.readLong();
        titre = in.readString();
        date = in.readString();
        heureDebut = new BigDecimal(in.readString());
        duree = new BigDecimal(in.readString());
        nbSpectateurs = in.readInt();
        lieu = in.readParcelable(Lieu.class.getClassLoader());
        imageUrl = in.readString();
        siteWeb = in.readString();
        description = in.readString();
    }

    public static final Creator<Spectacle> CREATOR = new Creator<Spectacle>() {
        @Override
        public Spectacle createFromParcel(Parcel in) {
            return new Spectacle(in);
        }

        @Override
        public Spectacle[] newArray(int size) {
            return new Spectacle[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(titre);
        dest.writeString(date);
        dest.writeString(heureDebut.toString());
        dest.writeString(duree.toString());
        dest.writeInt(nbSpectateurs);
        dest.writeParcelable(lieu, flags);
        dest.writeString(imageUrl);
        dest.writeString(siteWeb);
        dest.writeString(description);
    }
}
