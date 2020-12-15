package com.ensta.myfilmlist.dto;

import java.util.Date;
import java.util.Objects;

public class RealisateurDTO {
    private final long id;
    private final String prenom;
    private final String nom;
    private final Date dateNaissance;

    public RealisateurDTO(long id, String prenom, String nom,
                          Date dateNaissance) {
        this.id = id;
        this.prenom = prenom;
        this.nom = nom;
        this.dateNaissance = dateNaissance;
    }

    public RealisateurDTO() {
        this.id = 0;
        this.prenom = "";
        this.nom = "";
        this.dateNaissance = new Date();
    }

    public long getId() {
        return id;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getNom() {
        return nom;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        RealisateurDTO that = (RealisateurDTO) o;
        return id == that.id &&
                Objects.equals(prenom, that.prenom) &&
                Objects.equals(nom, that.nom) &&
                Objects.equals(dateNaissance, that.dateNaissance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, prenom, nom, dateNaissance);
    }
}
