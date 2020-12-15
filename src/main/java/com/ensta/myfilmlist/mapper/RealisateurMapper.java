package com.ensta.myfilmlist.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.ensta.myfilmlist.dto.RealisateurDTO;
import com.ensta.myfilmlist.model.Realisateur;
import com.ensta.myfilmlist.pojo.RealisateurPojo;

public class RealisateurMapper {
    private RealisateurMapper() {
    }

    public static Optional<RealisateurDTO> realisateurToRealisateurDTO(
            Optional<Realisateur> realisateur) {
        if (realisateur.isPresent())
            return Optional.of(
                    new RealisateurDTO(realisateur.get().getId(),
                            realisateur.get().getPrenom(),
                            realisateur.get().getNom(),
                            realisateur.get().getDateNaissance()));
        else
            return Optional.empty();
    }

    public static List<Optional<RealisateurDTO>> listRealisateurToListRealisateurDTO(
            List<Optional<Realisateur>> listRealisateur) {
        List<Optional<RealisateurDTO>> listRealisateurDTO = new ArrayList<>();
        for (Optional<Realisateur> realisateur : listRealisateur) {
            listRealisateurDTO
                    .add(realisateurToRealisateurDTO(realisateur));
        }
        return listRealisateurDTO;
    }

    public static Optional<Realisateur> realisateurPojoToRealisateur(
            Optional<RealisateurPojo> realisateur) {
        if (realisateur.isEmpty())
            return Optional.empty();
        return Optional.of(new Realisateur(realisateur.get().getId(),
                realisateur.get().getPrenom(), realisateur.get().getNom(),
                realisateur.get().getDateNaissance()));
    }

    public static List<Optional<Realisateur>> listRealisateurPojoToListRealisateur(
            List<RealisateurPojo> listRealisateur) {
        List<Optional<Realisateur>> listRealisateurDTO = new ArrayList<>();
        for (RealisateurPojo realisateur : listRealisateur) {
            listRealisateurDTO.add(realisateurPojoToRealisateur(
                    Optional.ofNullable(realisateur)));
        }
        return listRealisateurDTO;
    }

    public static Realisateur realisateurDTOToRealisateur(
            RealisateurDTO realisateur) {
        return new Realisateur(realisateur.getId(), realisateur.getPrenom(),
                realisateur.getNom(), realisateur.getDateNaissance());
    }

    public static RealisateurPojo realisateurToRealisateurPojo(
            Realisateur realisateur) {
        return new RealisateurPojo(realisateur.getId(), realisateur.getPrenom(),
                realisateur.getNom(), realisateur.getDateNaissance());
    }

}
