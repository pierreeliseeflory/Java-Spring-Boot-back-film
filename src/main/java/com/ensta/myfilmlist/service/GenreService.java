package com.ensta.myfilmlist.service;


import com.ensta.myfilmlist.dao.GenreDAO;
import com.ensta.myfilmlist.dto.GenreDTO;
import com.ensta.myfilmlist.exception.DaoException;
import com.ensta.myfilmlist.exception.ServiceException;
import com.ensta.myfilmlist.mapper.GenreMapper;
import com.ensta.myfilmlist.model.Genre;
import com.ensta.myfilmlist.pojo.GenrePojo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreService {
    private final GenreDAO genreDAO;

    public GenreService(GenreDAO genreDAO) {
        this.genreDAO = genreDAO;
    }

    public List<Optional<GenreDTO>> findAll() throws ServiceException {
        List<Optional<Genre>> listGenre;
        try {
            listGenre = GenreMapper
                    .listGenrePojoToListGenre(genreDAO.findAll());
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
        return GenreMapper.listGenreToListGenreDTO(listGenre);
    }

    public Optional<GenreDTO> find(int id) throws ServiceException {
        Optional<Genre> genreModelOption = Optional.empty();
        try {
            Optional<GenrePojo> genrePojoOptional = genreDAO.find(id);
            if (genrePojoOptional.isPresent())
                genreModelOption = GenreMapper
                        .genrePojoToGenre(genrePojoOptional);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
        return GenreMapper.genreToGenreDTO(genreModelOption);
    }
}