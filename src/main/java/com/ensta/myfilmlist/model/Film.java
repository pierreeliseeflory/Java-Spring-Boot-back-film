package com.ensta.myfilmlist.model;

import java.util.Objects;

public class Film {
    private long id;
    private final String titre;
    private final int duration;
    private int realisateur;
    private int genre;


    public Film(long id, String titre, int duration, int realisateur, int genre) {
        this.id = id;
        this.titre = titre;
        this.duration = duration;
        this.realisateur = realisateur;
        this.genre = genre;
    }

    public String getTitre() {
        return titre;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getDuration() {
        return duration;
    }

    public int getRealisateur() {
        return realisateur;
    }

    public void setRealisateur(int realisateur) {
        this.realisateur = realisateur;
    }

    public int getGenre() {
        return genre;
    }

    public void setGenre(int genre) {
        this.genre = genre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Film film = (Film) o;
        return id == film.id && duration == film.duration && realisateur == film.realisateur && genre == film.genre && Objects.equals(titre, film.titre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titre, duration, realisateur, genre);
    }

    @Override
    public String toString() {
        return "Film{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                ", duration=" + duration +
                ", realisateur=" + realisateur +
                ", genre=" + genre +
                '}';
    }
}