package com.ensta.myfilmlist.dto;

import java.util.Objects;

public class FilmDTO {
    private final long id;
    private final String titre;
    private final int duration;
    private final int realisateur;
    private final String realisateurName;
    private final int genre;
    private final String genreName;


    public FilmDTO() {
        this.id = 0;
        this.titre = "";
        this.duration = 0;
        this.realisateur = 0;
        this.realisateurName = "";
        this.genre = 0;
        this.genreName = "";
    }

    public FilmDTO(long id, String titre, int duration, int realisateur,
                   String realisateurName, int genre, String genreName) {
        this.id = id;
        this.titre = titre;
        this.duration = duration;
        this.realisateur = realisateur;
        this.realisateurName = realisateurName;
        this.genre = genre;
        this.genreName = genreName;
    }

    public String getTitre() {
        return titre;
    }

    public long getId() {
        return id;
    }

    public int getDuration() {
        return duration;
    }

    public int getRealisateur() {
        return realisateur;
    }

    public String getrealisateurName() {
        return realisateurName;
    }

    public int getGenre() {
        return genre;
    }

    public String getgenreName() {
        return genreName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        FilmDTO filmDTO = (FilmDTO) o;
        return id == filmDTO.id && duration == filmDTO.duration && realisateur == filmDTO.realisateur && genre == filmDTO.genre && Objects
                .equals(titre, filmDTO.titre) && Objects
                .equals(realisateurName, filmDTO.realisateurName) && Objects
                .equals(genreName, filmDTO.genreName);
    }

    @Override
    public int hashCode() {
        return Objects
                .hash(id, titre, duration, realisateur, realisateurName, genre,
                        genreName);
    }

    @Override
    public String toString() {
        return "FilmDTO{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                ", duration=" + duration +
                ", realisateur=" + realisateur +
                ", realisateurName='" + realisateurName + '\'' +
                ", genre=" + genre +
                ", genreName='" + genreName + '\'' +
                '}';
    }
}