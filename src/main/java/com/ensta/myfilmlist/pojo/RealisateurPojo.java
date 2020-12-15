package com.ensta.myfilmlist.pojo;

import java.util.Date;
import java.util.Objects;

public class RealisateurPojo {
    private final long id;
    private final String prenom;
    private final String nom;
    private final Date dateNaissance;

    public RealisateurPojo(long id, String prenom, String nom,
                           Date dateNaissance) {
        this.id = id;
        this.prenom = prenom;
        this.nom = nom;
        this.dateNaissance = dateNaissance;
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
    public String toString() {
        return "RealisateurPojo{" +
                "id=" + id +
                ", prenom='" + prenom + '\'' +
                ", nom='" + nom + '\'' +
                ", dateNaissance=" + dateNaissance +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        RealisateurPojo that = (RealisateurPojo) o;
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
