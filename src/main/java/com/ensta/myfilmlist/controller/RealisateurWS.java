package com.ensta.myfilmlist.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.ensta.myfilmlist.dto.RealisateurDTO;
import com.ensta.myfilmlist.exception.ControllerException;
import com.ensta.myfilmlist.exception.ServiceException;
import com.ensta.myfilmlist.page.Page;
import com.ensta.myfilmlist.service.RealisateurService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;

/**
 * Realisateur Web Service.
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController("RealisateurWS")
@RequestMapping(value = "/realisateur")
public class RealisateurWS {

    /**
     * service
     */
    private final RealisateurService realisateurService;

    /**
     *
     * @param service the service provided
     */
    public RealisateurWS(final RealisateurService service) {
        this.realisateurService = service;
    }

    /**
     *
     * @param taillePage Taille de la page à retourner
     * @param offsetPage Numéro de la poge à retourner
     * @return ResponseEntity<Page<RealisateurDTO>>
     * @throws ControllerException exception
     */
    @GetMapping(value = "/all")
    @ApiOperation(value = "Récupère la liste des réalisateurs")
    public final ResponseEntity<Page<RealisateurDTO>> retrieveRealisateur(
            @RequestParam(name = "size") final int taillePage,
            @RequestParam(name = "offset") final int offsetPage)
            throws ControllerException {
        try {
            List<RealisateurDTO> realisateurs = realisateurService
                    .findAll(taillePage, offsetPage).stream().map(Optional::get)
                    .collect(Collectors.toList());
            int total = realisateurService.count();
            Page<RealisateurDTO> page = new Page<>(
                    new ArrayList<>(realisateurs),
                    taillePage, offsetPage, total);
            return ResponseEntity.status(HttpStatus.OK).body(page);
        } catch (ServiceException e) {
            throw new ControllerException(e.getMessage());
        }
    }

    /**
     *
     * @param id id du réalisateur
     * @return ResponseEntity<RealisateurDTO>
     * @throws ControllerException exception
     */
    @GetMapping
    @ApiOperation(value = "Récupère un réalisateur depuis son identifiant")
    public final ResponseEntity<RealisateurDTO> retrieveRealisateur(
            @RequestParam final int id) throws ControllerException {
        try {
            Optional<RealisateurDTO> realisateur = realisateurService.find(id);
            if (realisateur.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new RealisateurDTO());
            }
            return ResponseEntity.status(HttpStatus.OK).body(realisateur.get());
        } catch (ServiceException e) {
            throw new ControllerException(e.getMessage());
        }
    }

    /**
     *
     * @param realisateur nouveau réalisateur
     * @return ResponseEntity<RealisateurDTO>
     * @throws ControllerException exception
     */
    @PostMapping
    @ApiOperation(value = "Crée un realisateur")
    public final ResponseEntity<RealisateurDTO> createRealisateur(
            @RequestBody final RealisateurDTO realisateur)
            throws ControllerException {
        try {
            realisateurService.createRealisateur(realisateur);
            return ResponseEntity.status(HttpStatus.OK).body(realisateur);
        } catch (ServiceException e) {
            throw new ControllerException(e.getMessage());
        }
    }

    /**
     *
     * @param id id du réalisateur à supprimer
     * @return ResponseEntity<Boolean>
     * @throws ControllerException exception
     */
    @DeleteMapping()
    @ApiOperation(value = "Supprime un realisateur")
    public final ResponseEntity<Boolean> deleteRealisateur(
            @RequestParam final int id) throws ControllerException {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(realisateurService.deleteRealisateur(id));
        } catch (ServiceException e) {
            throw new ControllerException(e.getMessage());
        }
    }

    /**
     *
     * @param realisateur réalisateur mis à jour
     * @return ResponseEntity<RealisateurDTO>
     * @throws ControllerException exception
     */
    @PutMapping()
    @ApiOperation(value = "Mise à jour d'un un realisateur")
    public final ResponseEntity<RealisateurDTO> updateRealisateur(
            @RequestBody final RealisateurDTO realisateur)
            throws ControllerException {
        try {
            int nbUpdate = realisateurService.updateRealisateur(realisateur);
            if (nbUpdate == 1) {
                return ResponseEntity.status(HttpStatus.OK).body(realisateur);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(realisateur);
        } catch (ServiceException e) {
            throw new ControllerException(e.getMessage());
        }
    }
}
