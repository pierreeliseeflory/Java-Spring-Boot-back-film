package com.ensta.myfilmlist.dto;

import java.util.Objects;

public class GenreDTO {
    private final long id;
    private final String nom;

    public GenreDTO(long id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public GenreDTO() {
        this.id = 0;
        this.nom = "";
    }

    public long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    @Override
    public String toString() {
        return "GenreDTO{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        GenreDTO genreDTO = (GenreDTO) o;
        return id == genreDTO.id && Objects.equals(nom, genreDTO.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nom);
    }
}
