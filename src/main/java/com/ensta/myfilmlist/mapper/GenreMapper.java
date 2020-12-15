package com.ensta.myfilmlist.mapper;

import com.ensta.myfilmlist.dto.GenreDTO;
import com.ensta.myfilmlist.model.Genre;
import com.ensta.myfilmlist.pojo.GenrePojo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GenreMapper {
    private GenreMapper() {
    }

    public static Optional<GenreDTO> genreToGenreDTO(Optional<Genre> genre) {
        if (genre.isPresent())
            return Optional.of(
                    new GenreDTO(genre.get().getId(), genre.get().getNom()));
        else
            return Optional.empty();
    }

    public static List<Optional<GenreDTO>> listGenreToListGenreDTO(
            List<Optional<Genre>> listGenre) {
        List<Optional<GenreDTO>> listGenreDTO = new ArrayList<>();
        for (Optional<Genre> genre : listGenre) {
            listGenreDTO.add(genreToGenreDTO(genre));
        }
        return listGenreDTO;
    }

    public static Optional<Genre> genrePojoToGenre(Optional<GenrePojo> genre) {
        if (genre.isEmpty())
            return Optional.empty();
        return Optional.of(
                new Genre(genre.get().getId(), genre.get().getNom()));
    }

    public static List<Optional<Genre>> listGenrePojoToListGenre(
            List<GenrePojo> listGenre) {
        List<Optional<Genre>> listGenreDTO = new ArrayList<>();
        for (GenrePojo genre : listGenre) {
            listGenreDTO.add(genrePojoToGenre(Optional.ofNullable(genre)));
        }
        return listGenreDTO;
    }

    public static Genre genreDTOToGenre(GenreDTO genre) {
        return new Genre(genre.getId(), genre.getNom());
    }

    public static GenrePojo genreToGenrePojo(Genre genre) {
        return new GenrePojo(genre.getId(), genre.getNom());
    }
}
