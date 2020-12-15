package com.ensta.myfilmlist.service;

import com.ensta.myfilmlist.dao.RealisateurDAO;
import com.ensta.myfilmlist.dto.RealisateurDTO;
import com.ensta.myfilmlist.exception.DaoException;
import com.ensta.myfilmlist.exception.ServiceException;
import com.ensta.myfilmlist.pojo.RealisateurPojo;
import com.ensta.myfilmlist.service.RealisateurService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RealisateurServiceTest {
    @Autowired
    RealisateurService realisateurService;

    @MockBean
    RealisateurDAO realisateurDAO;

    @Test
    public void findAll() throws DaoException, ServiceException {
        List<RealisateurPojo> listRealisateur = new ArrayList<>();
        Date date = new Date();
        RealisateurPojo realisateur = new RealisateurPojo(1, "prenom", "nom", date);
        listRealisateur.add(realisateur);

        RealisateurDTO realisateurResult = new RealisateurDTO(1, "prenom", "nom", date);

        when(realisateurDAO.findAll(-1, 0)).thenReturn(listRealisateur);

        List<RealisateurDTO> resultList = new ArrayList<>();

        for (Optional<RealisateurDTO> realisateurDTO : realisateurService.findAll(-1, 0)) {
            resultList.add(realisateurDTO.get());
        }

        assertEquals(1, resultList.size());
        assertEquals(realisateurResult, resultList.get(0));
    }

    @Test
    public void find() throws DaoException, ServiceException {
        List<RealisateurPojo> listRealisateur = new ArrayList<>();
        Date date = new Date();
        RealisateurPojo realisateur = new RealisateurPojo(1, "prenom", "nom", date);
        listRealisateur.add(realisateur);

        RealisateurDTO realisateurResult = new RealisateurDTO(1, "prenom", "nom", date);

        when(realisateurDAO.find(1)).thenReturn(Optional.of(listRealisateur.get(0)));

        List<RealisateurDTO> resultList = new ArrayList<>();

        resultList.add(realisateurService.find(1).get());

        assertEquals(1, resultList.size());
        assertEquals(realisateurResult, resultList.get(0));
    }
}
