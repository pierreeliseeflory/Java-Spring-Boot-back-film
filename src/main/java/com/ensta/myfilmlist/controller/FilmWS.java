package com.ensta.myfilmlist.controller;

import java.util.ArrayList;
import java.util.List;

import com.ensta.myfilmlist.dto.FilmDTO;
import com.ensta.myfilmlist.exception.ControllerException;
import com.ensta.myfilmlist.exception.ServiceException;
import com.ensta.myfilmlist.service.FilmService;
import com.ensta.myfilmlist.page.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;

import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Film Web Service.
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController("FilmWS")
@RequestMapping(value = "/film")
public class FilmWS {
    /**
     * service.
     */
    private final FilmService filmService;

    /**
     *
     * @param service the film service provided
     */
    public FilmWS(final FilmService service) {
        this.filmService = service;
    }

    /**
     *
     * @param taillePage taille de la page à retourner
     * @param offsetPage numéro de la page à retourner
     * @param category catégorie permettant de trier l'ordre des elements
     * @param order true=asc  /  false=desc
     * @return ResponseEntity<Page<FilmDTO>>
     * @throws ControllerException exception
     */
    @GetMapping(value = "/all")
    @ApiOperation(value = "Récupère la liste des films")
    public final ResponseEntity<Page<FilmDTO>> retrieveFilm(
            @RequestParam(name = "size") final int taillePage,
            @RequestParam(name = "offset") final int offsetPage,
            @RequestParam(name = "category") final String category,
            @RequestParam(name = "order") final boolean order)
            throws ControllerException {
        try {
            int total = filmService.count();
            List<FilmDTO> films = filmService.findAll(taillePage, offsetPage,
                    category, order
                                                     ).stream()
                    .map(Optional::get).collect(Collectors.toList());
            Page<FilmDTO> page = new Page<>(new ArrayList<>(films),
                    taillePage, offsetPage, total
            );

            return ResponseEntity.status(HttpStatus.OK).body(page);
        } catch (ServiceException e) {
            throw new ControllerException(e.getMessage());
        }
    }

    /**
     *
     * @param id id du film à retrouner
     * @return ResponseEntity<FilmDTO>
     * @throws ControllerException exception
     */
    @GetMapping
    @ApiOperation(value = "Récupère un film depuis son identifiant")
    public final ResponseEntity<FilmDTO> retrieveFilm(
            @RequestParam final int id) throws ControllerException {
        try {
            Optional<FilmDTO> film = filmService.find(id);
            if (film.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new FilmDTO());
            }
            return ResponseEntity.status(HttpStatus.OK).body(film.get());
        } catch (ServiceException e) {
            throw new ControllerException(e.getMessage());
        }
    }

    /**
     *
     * @param film film à créer
     * @return ResponseEntity<FilmDTO>
     * @throws ControllerException exception
     */
    @PostMapping
    @ApiOperation(value = "Crée un film")
    public final ResponseEntity<FilmDTO> createFilm(
            @RequestBody final FilmDTO film) throws ControllerException {
        try {
            filmService.createFilm(film);
            return ResponseEntity.status(HttpStatus.OK).body(film);
        } catch (ServiceException e) {
            throw new ControllerException(e.getMessage());
        }
    }

    /**
     *
     * @param id id du film à supprimer
     * @return ResponseEntity<Boolean>
     * @throws ControllerException exception
     */
    @DeleteMapping()
    @ApiOperation(value = "Supprime un film")
    public final ResponseEntity<Boolean> deleteFilm(
            @RequestParam final int id) throws ControllerException {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(filmService.deleteFilm(id));
        } catch (ServiceException e) {
            throw new ControllerException(e.getMessage());
        }
    }

    /**
     *
     * @param film film à mettre à jour
     * @return ResponseEntity<FilmDTO>
     * @throws ControllerException exception
     */
    @PutMapping()
    @ApiOperation(value = "Mise à jour d'un un film")
    public final ResponseEntity<FilmDTO> updateFilm(
            @RequestBody final FilmDTO film) throws ControllerException {
        try {
            int nbUpdate = filmService.updateFilm(film);
            if (nbUpdate == 1) {
                return ResponseEntity.status(HttpStatus.OK).body(film);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(film);
        } catch (ServiceException e) {
            throw new ControllerException(e.getMessage());
        }
    }
}
