package com.enicarthage.coulisses.User.Model;

import com.enicarthage.coulisses.Security.DTOs.userDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;


@Entity
@Table(name = "CLIENT")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_seq")
    @SequenceGenerator(name = "client_seq", sequenceName = "SEQ_IDCLT", allocationSize = 1)
    @Column(name = "IDCLT")
    private Long id;

    @Column(name = "NOMCLT", nullable = false)
    private String nom;

    @Column(name = "PRENOMCLT", nullable = false)
    private String prenom;

    @Column(name = "TEL", nullable = false)
    private String tel;

    @Column(name = "EMAIL", nullable = false, unique = true)
    private String email;

    @Column(name = "MOTP", nullable = false)
    private String motDePasse;

    @Column(name = "RESET_TOKEN")
    private String resetToken;

    @Column(name = "TOKEN_EXPIRY_DATE")
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

    // Implémentation des méthodes de UserDetails
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Par défaut, tous les utilisateurs ont le rôle USER
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return motDePasse;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
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

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public userDTO convertToUserDTO(User user) {
        userDTO dto = new userDTO();
        dto.setId(user.getId());
        dto.setNom(user.getNom());
        dto.setPrenom(user.getPrenom());
        dto.setTel(user.getTel());
        dto.setEmail(user.getEmail());
        dto.setResetToken(user.getResetToken());
        dto.setTokenExpiryDate(user.getTokenExpiryDate());
        return dto;
    }

}
