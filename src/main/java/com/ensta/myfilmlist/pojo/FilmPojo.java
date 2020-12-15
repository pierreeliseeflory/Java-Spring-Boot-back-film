package com.ensta.myfilmlist.pojo;

import java.util.Objects;

public class FilmPojo {
    private final long id;
    private final String titre;
    private final int duration;
    private final int realisateur;
    private final int genre;

    public FilmPojo(long id, String titre, int duration, int realisateur,
                    int genre) {
        this.id = id;
        this.titre = titre;
        this.duration = duration;
        this.realisateur = realisateur;
        this.genre = genre;
    }

    public long getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public int getDuration() {
        return duration;
    }

    public int getRealisateur() {
        return realisateur;
    }

    public int getGenre() {
        return genre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        FilmPojo filmPojo = (FilmPojo) o;
        return id == filmPojo.id && duration == filmPojo.duration && realisateur == filmPojo.realisateur && genre == filmPojo.genre && Objects
                .equals(titre, filmPojo.titre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titre, duration, realisateur, genre);
    }

    @Override
    public String toString() {
        return "FilmPojo{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                ", duration=" + duration +
                ", realisateur=" + realisateur +
                ", genre=" + genre +
                '}';
    }
}