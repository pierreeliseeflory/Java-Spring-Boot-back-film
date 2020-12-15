package com.ensta.myfilmlist.service;

import com.ensta.myfilmlist.dao.GenreDAO;
import com.ensta.myfilmlist.dto.GenreDTO;
import com.ensta.myfilmlist.exception.DaoException;
import com.ensta.myfilmlist.exception.ServiceException;
import com.ensta.myfilmlist.pojo.GenrePojo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
public class GenreServiceTest {
    @Autowired
    GenreService genreService;

    @MockBean
    GenreDAO genreDAO;

    @Test
    public void findAll() throws DaoException, ServiceException {
        List<GenrePojo> listGenre = new ArrayList<>();
        GenrePojo genre = new GenrePojo(1, "un genre");
        listGenre.add(genre);

        GenreDTO genreResult = new GenreDTO(1, "un genre");

        when(genreDAO.findAll()).thenReturn(listGenre);

        List<GenreDTO> resultList = new ArrayList<>();

        for (Optional<GenreDTO> genreDTO : genreService.findAll()) {
            resultList.add(genreDTO.get());
        }

        assertEquals(1, resultList.size());
        assertEquals(genreResult, resultList.get(0));
    }

    @Test
    public void find() throws DaoException, ServiceException {
        List<GenrePojo> listGenre = new ArrayList<>();
        GenrePojo genre = new GenrePojo(1, "un genre");
        listGenre.add(genre);

        GenreDTO genreResult = new GenreDTO(1, "un genre");

        when(genreDAO.find(1)).thenReturn(Optional.of(listGenre.get(0)));

        List<GenreDTO> resultList = new ArrayList<>();

        resultList.add(genreService.find(1).get());

        assertEquals(1, resultList.size());
        assertEquals(genreResult, resultList.get(0));
    }
}