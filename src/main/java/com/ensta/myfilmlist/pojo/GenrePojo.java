package com.ensta.myfilmlist.pojo;

public class GenrePojo {
    private final long id;
    private final String nom;

    public GenrePojo(long id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    @Override
    public String toString() {
        return "GenrePojo{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                '}';
    }
}
