package com.enicarthage.coulisses.Security.DTOs;

import com.enicarthage.coulisses.User.Model.User;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;

public class userDTO {

        private Long id;

        private String nom;

        private String prenom;

        private String tel;

        private String email;

        private String resetToken;

        private LocalDateTime tokenExpiryDate;

        // Getters et Setters pour les nouveaux champs
        public String getResetToken() {
            return resetToken;
        }

        public void setResetToken(String resetToken) {
            this.resetToken = resetToken;
        }

        public LocalDateTime getTokenExpiryDate() {
            return tokenExpiryDate;
        }

        public void setTokenExpiryDate(LocalDateTime tokenExpiryDate) {
            this.tokenExpiryDate = tokenExpiryDate;
        }


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




    }

