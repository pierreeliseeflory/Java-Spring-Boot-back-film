package com.ensta.myfilmlist.controller;

import com.ensta.myfilmlist.dto.GenreDTO;
import com.ensta.myfilmlist.exception.ControllerException;
import com.ensta.myfilmlist.exception.ServiceException;
import com.ensta.myfilmlist.service.GenreService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Genre Web Service.
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController("GenreWS")
@RequestMapping(value = "/genre")
public class GenreWS {
    /**
     * service.
     */
    private final GenreService genreService;

    /**
     *
     * @param service The service provided
     */
    public GenreWS(final GenreService service) {
        this.genreService = service;
    }

    /**
     *
     * @return esponseEntity<List<GenreDTO>>
     * @throws ControllerException exception
     */
    @GetMapping(value = "/all")
    @ApiOperation(value = "Récupère la liste des réalisateurs")
    public final ResponseEntity<List<GenreDTO>> retrieveGenre() throws
            ControllerException {
        try {
            List<GenreDTO> genres = genreService.findAll().stream()
                    .map(Optional::get).collect(Collectors.toList());
            return ResponseEntity.status(HttpStatus.OK).body(genres);
        } catch (ServiceException e) {
            throw new ControllerException(e.getMessage());
        }
    }

    /**
     *
     * @param id id du genre à retourner
     * @return ResponseEntity<GenreDTO>
     * @throws ControllerException exception
     */
    @GetMapping
    @ApiOperation(value = "Récupère un réalisateur depuis son identifiant")
    public final ResponseEntity<GenreDTO> retrieveGenre(
            @RequestParam final int id) throws ControllerException {
        try {
            Optional<GenreDTO> genre = genreService.find(id);
            if (genre.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new GenreDTO());
            }
            return ResponseEntity.status(HttpStatus.OK).body(genre.get());
        } catch (ServiceException e) {
            throw new ControllerException(e.getMessage());
        }
    }
}
