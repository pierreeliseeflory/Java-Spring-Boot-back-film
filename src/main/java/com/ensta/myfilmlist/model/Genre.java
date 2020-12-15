package com.ensta.myfilmlist.model;

public class Genre {
    private final long id;
    private final String nom;

    public long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public Genre(long id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                '}';
    }
}
