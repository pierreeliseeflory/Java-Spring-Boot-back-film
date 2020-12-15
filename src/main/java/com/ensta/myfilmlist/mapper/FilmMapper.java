package com.ensta.myfilmlist.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.ensta.myfilmlist.dto.FilmDTO;
import com.ensta.myfilmlist.dto.GenreDTO;
import com.ensta.myfilmlist.model.Film;
import com.ensta.myfilmlist.dto.RealisateurDTO;
import com.ensta.myfilmlist.pojo.FilmPojo;

public class FilmMapper {

    private FilmMapper() {
    }

    public static Optional<FilmDTO> filmToFilmDTO(Optional<Film> film,
                                                  Optional<RealisateurDTO> realisateur,
                                                  Optional<GenreDTO> genre) {
        if (film.isEmpty() || realisateur.isEmpty() || genre.isEmpty())
            return Optional.empty();
        return Optional
                .of(new FilmDTO(film.get().getId(), film.get().getTitre(),
                        film.get().getDuration(), film.get().getRealisateur(),
                        realisateur.get().getPrenom() + " " + realisateur.get()
                                .getNom(), film.get().getGenre(),
                        genre.get().getNom()));
    }

    public static List<Optional<FilmDTO>> listFilmToListFilmDTO(
            List<Optional<Film>> listFilm, List<RealisateurDTO> listRealisateur,
            List<GenreDTO> listGenre) {
        List<Optional<FilmDTO>> listFilmDTO = new ArrayList<>();
        for (int i = 0; i < listFilm.size(); ++i) {
            listFilmDTO.add(filmToFilmDTO(listFilm.get(i),
                    Optional.of(listRealisateur.get(i)),
                    Optional.of(listGenre.get(i))));
        }
        return listFilmDTO;
    }

    public static Optional<Film> filmPojoToFilm(Optional<FilmPojo> film) {
        if (film.isEmpty())
            return Optional.empty();
        return Optional.of(
                new Film(film.get().getId(), film.get().getTitre(),
                        film.get().getDuration(), film.get().getRealisateur(),
                        film.get().getGenre()));
    }

    public static List<Optional<Film>> listFilmPojoToListFilm(
            List<FilmPojo> listFilm) {
        List<Optional<Film>> listFilmDTO = new ArrayList<>();

        for (FilmPojo filmPojo : listFilm) {
            listFilmDTO.add(filmPojoToFilm(Optional.ofNullable(filmPojo)));
        }
        return listFilmDTO;
    }

    public static Film filmDTOToFilm(FilmDTO film) {
        return new Film(film.getId(), film.getTitre(), film.getDuration(),
                film.getRealisateur(), film.getGenre());
    }

    public static FilmPojo filmToFilmPojo(Film film) {
        return new FilmPojo(film.getId(), film.getTitre(), film.getDuration(),
                film.getRealisateur(), film.getGenre());
    }

}