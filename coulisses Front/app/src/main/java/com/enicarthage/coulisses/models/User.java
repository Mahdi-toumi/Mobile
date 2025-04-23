package com.enicarthage.coulisses.models;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
    private Long id;
    private String nom;
    private String prenom;
    private String tel;
    private String email;
    private String resetToken;
    private String tokenExpiryDate;

    // Constructors
    public User() {}

    public User(Long id, String nom, String prenom, String tel, String email, String resetToken, String tokenExpiryDate) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.tel = tel;
        this.email = email;
        this.resetToken = resetToken;
        this.tokenExpiryDate = tokenExpiryDate;
    }

    // Getters and Setters
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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getResetToken() {
        return resetToken;
    }

    public void setResetToken(String resetToken) {
        this.resetToken = resetToken;
    }

    public String getTokenExpiryDate() {
        return tokenExpiryDate;
    }

    public void setTokenExpiryDate(String tokenExpiryDate) {
        this.tokenExpiryDate = tokenExpiryDate;
    }
    protected User(Parcel in) {
        id = in.readLong();
        nom = in.readString();
        prenom = in.readString();
        tel = in.readString();
        email = in.readString();
        resetToken = in.readString();
        tokenExpiryDate = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
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
        dest.writeString(prenom);
        dest.writeString(tel);
        dest.writeString(email);
        dest.writeString(resetToken);
        dest.writeString(tokenExpiryDate);
    }

}
