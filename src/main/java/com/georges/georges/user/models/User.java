package com.georges.georges.user.models;

import com.georges.georges.role.models.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Collection;

@Entity
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @NotBlank(message = "Le champ nom n'est pas renseigné")
    @NotNull(message = "Le champ nom ne peut pas être vide !")
    private String nom;


    @NotBlank(message = "Le champ pseudo n'est pas renseigné")
    @NotNull(message = "Le champ pseudo ne peut pas être vide !")
    private String pseudo;
    @NotBlank(message = "Le champ email n'est pas renseigné")
    @NotNull(message = "Le champ email ne peut pas être vide !")
    private String email;
    @NotBlank(message = "Le champ password n'est pas renseigné")
    @Size(min = 6, message = "Le password doit avoir au moins 6 caractères")

    private String motdepasse;
    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private Collection<Role> roles;

    public User() {
    }

    public User(String nom, String pseudo, String email, String motdepasse) {
        this.nom = nom;
        this.pseudo = pseudo;
        this.email = email;
        this.motdepasse = motdepasse;
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

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotdepasse() {
        return motdepasse;
    }

    public void setMotdepasse(String motdepasse) {
        this.motdepasse = motdepasse;
    }
}

