package com.ensta.myfilmlist.service;

import java.util.List;
import java.util.Optional;

import com.ensta.myfilmlist.dao.RealisateurDAO;
import com.ensta.myfilmlist.dto.RealisateurDTO;
import com.ensta.myfilmlist.exception.DaoException;
import com.ensta.myfilmlist.exception.ServiceException;
import com.ensta.myfilmlist.mapper.RealisateurMapper;
import com.ensta.myfilmlist.model.Realisateur;
import com.ensta.myfilmlist.pojo.RealisateurPojo;
import org.springframework.stereotype.Service;

@Service
public class RealisateurService {

    private final RealisateurDAO realisateurDAO;

    public RealisateurService(RealisateurDAO realisateurDAO) {
        this.realisateurDAO = realisateurDAO;
    }

    /**
     * Return all realisateurs and map them with DTO
     *
     * @return a list of dto realisateurs
     */
    public List<Optional<RealisateurDTO>> findAll(int taillePage,
                                                  int offsetPage) throws ServiceException {
        List<Optional<Realisateur>> listRealisateurModel;
        try {
            listRealisateurModel = RealisateurMapper
                    .listRealisateurPojoToListRealisateur(
                            realisateurDAO.findAll(taillePage, offsetPage));
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
        return RealisateurMapper
                .listRealisateurToListRealisateurDTO(listRealisateurModel);
    }

    public int count() throws ServiceException {
        try {
            return realisateurDAO.count();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public Optional<RealisateurDTO> find(int id) throws ServiceException {
        Optional<Realisateur> realisateurModelOption = Optional.empty();
        try {
            Optional<RealisateurPojo> realisateurPojoOptional = realisateurDAO
                    .find(id);
            if (realisateurPojoOptional.isPresent())
                realisateurModelOption = RealisateurMapper
                        .realisateurPojoToRealisateur(realisateurPojoOptional);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
        return RealisateurMapper
                .realisateurToRealisateurDTO(realisateurModelOption);
    }

    public void createRealisateur(
            RealisateurDTO realisateur) throws ServiceException {
        try {
            realisateurDAO.createRealisateur(RealisateurMapper
                    .realisateurToRealisateurPojo(RealisateurMapper
                            .realisateurDTOToRealisateur(realisateur)));
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public int updateRealisateur(
            RealisateurDTO realisateur) throws ServiceException {
        try {
            return realisateurDAO.updateRealisateur(RealisateurMapper
                    .realisateurToRealisateurPojo(RealisateurMapper
                            .realisateurDTOToRealisateur(realisateur)));
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public Boolean deleteRealisateur(int id) throws ServiceException {
        try {
            realisateurDAO.deleteRealisateur(id);
            return Boolean.TRUE;
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
