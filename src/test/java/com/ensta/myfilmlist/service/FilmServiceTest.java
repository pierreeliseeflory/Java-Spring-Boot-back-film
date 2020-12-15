package com.ensta.myfilmlist.service;

import com.ensta.myfilmlist.dao.FilmDAO;
import com.ensta.myfilmlist.dto.FilmDTO;
import com.ensta.myfilmlist.dto.GenreDTO;
import com.ensta.myfilmlist.dto.RealisateurDTO;
import com.ensta.myfilmlist.exception.DaoException;
import com.ensta.myfilmlist.exception.ServiceException;
import com.ensta.myfilmlist.pojo.FilmPojo;
import com.ensta.myfilmlist.service.FilmService;
import com.ensta.myfilmlist.service.GenreService;
import com.ensta.myfilmlist.service.RealisateurService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FilmServiceTest {
    @Autowired
    FilmService filmService;

    @MockBean
    FilmDAO filmDAO;

    @MockBean
    RealisateurService realisateurService;

    @MockBean
    GenreService genreService;

    @Test
    public void findAllTest() throws DaoException, ServiceException {
        List<FilmPojo> listFilm = new ArrayList<>();
        FilmPojo film = new FilmPojo(1, "un film", 100, 1, 1);
        listFilm.add(film);

        Date date = new Date();
        Optional<RealisateurDTO> realisateurDTO = Optional
                .of(new RealisateurDTO(1, "prenom", "nom", date));
        Optional<GenreDTO> genreDTO = Optional.of(new GenreDTO(1, "genre"));

        FilmDTO filmResult = new FilmDTO(1, "un film", 100, 1, "prenom nom", 1,
                "genre");

        when(filmDAO.findAll(-1, 0, "id", true)).thenReturn(listFilm);
        when(realisateurService.find(1)).thenReturn(realisateurDTO);
        when(genreService.find(1)).thenReturn(genreDTO);

        List<FilmDTO> resultList = new ArrayList<>();

        for (Optional<FilmDTO> option : filmService
                .findAll(-1, 0, "id", true)) {
            resultList.add(option.get());
        }
        assertEquals(1, resultList.size());
        assertEquals(filmResult, resultList.get(0));
    }

    @Test
    public void findAll() throws DaoException, ServiceException {
        List<FilmPojo> listFilm = new ArrayList<>();
        FilmPojo film = new FilmPojo(1, "un film", 100, 1, 1);

        Date date = new Date();
        Optional<RealisateurDTO> realisateurDTO = Optional
                .of(new RealisateurDTO(1, "prenom", "nom", date));
        Optional<GenreDTO> genreDTO = Optional.of(new GenreDTO(1, "genre"));

        FilmDTO filmResult = new FilmDTO(1, "un film", 100, 1, "prenom nom", 1,
                "genre");

        when(filmDAO.find(1)).thenReturn(Optional.of(film));
        when(realisateurService.find(1)).thenReturn(realisateurDTO);
        when(genreService.find(1)).thenReturn(genreDTO);

        List<FilmDTO> resultList = new ArrayList<>();

        resultList.add(filmService.find(1).get());

        assertEquals(1, resultList.size());
        assertEquals(filmResult, resultList.get(0));
    }
}
