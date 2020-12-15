package com.ensta.myfilmlist.service;

import java.util.ArrayList;
import java.util.List;

import com.ensta.myfilmlist.dao.FilmDAO;
import com.ensta.myfilmlist.dto.FilmDTO;
import com.ensta.myfilmlist.dto.GenreDTO;
import com.ensta.myfilmlist.exception.DaoException;
import com.ensta.myfilmlist.exception.ServiceException;
import com.ensta.myfilmlist.mapper.FilmMapper;
import com.ensta.myfilmlist.model.Film;
import com.ensta.myfilmlist.pojo.FilmPojo;
import com.ensta.myfilmlist.dto.RealisateurDTO;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class FilmService {

    private final FilmDAO filmDAO;
    private final RealisateurService realisateurService;
    private final GenreService genreService;

    public FilmService(FilmDAO filmDAO, RealisateurService realisateurService, GenreService genreService) {
        this.filmDAO = filmDAO;
        this.realisateurService = realisateurService;
        this.genreService = genreService;
    }

    /**
     * Return all films and map them with DTO
     * @return a list of dto films
     */
    public List<Optional<FilmDTO>> findAll(int size, int offset, String category, boolean order) throws ServiceException {
        List<Optional<Film>> listFilmModel;
        List<RealisateurDTO> realisateursDTO = new ArrayList<>();
        List<GenreDTO> genresDTO = new ArrayList<>();
        try {
            List<FilmPojo> filmPojo = filmDAO.findAll(size, offset, category, order);

            Optional<RealisateurDTO> realOption;
            Optional<GenreDTO> genreOption;
            for (FilmPojo pojo : filmPojo) {
                realOption = realisateurService.find(pojo.getRealisateur());
                genreOption = genreService.find(pojo.getGenre());
                if (realOption.isEmpty() || genreOption.isEmpty()) {
                    throw new ServiceException("Optional genre ou realisateur" +
                            " vide");
                }
                realisateursDTO.add(realOption.get());
                genresDTO.add(genreOption.get());
            }
            listFilmModel = FilmMapper.listFilmPojoToListFilm(filmPojo);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
        return FilmMapper.listFilmToListFilmDTO(listFilmModel, realisateursDTO, genresDTO);
    }

    public int count() throws ServiceException {
        try {
            return filmDAO.count();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public Optional<FilmDTO> find(int id) throws ServiceException {
        Optional<Film> filmModel = Optional.empty();
        Optional<RealisateurDTO> realisateurDTO = Optional.empty();
        Optional<GenreDTO> genreDTO = Optional.empty();
        try {
            Optional<FilmPojo> filmPojo = filmDAO.find(id);
            if (filmPojo.isPresent()) {

                realisateurDTO = realisateurService.find(filmPojo.get().getRealisateur());
                genreDTO = genreService.find(filmPojo.get().getGenre());
                filmModel = FilmMapper.filmPojoToFilm(filmPojo);
            }
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
        return FilmMapper.filmToFilmDTO(filmModel, realisateurDTO, genreDTO);
    }

    public void createFilm(FilmDTO film) throws ServiceException {
        try {
            filmDAO.createFilm(FilmMapper.filmToFilmPojo(FilmMapper.filmDTOToFilm(film)));
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public int updateFilm(FilmDTO film) throws ServiceException {
        try {
            return filmDAO.updateFilm(FilmMapper.filmToFilmPojo(FilmMapper.filmDTOToFilm(film)));
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public Boolean deleteFilm(int id) throws ServiceException {
        try {
            filmDAO.deleteFilm(id);
            return Boolean.TRUE;
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}