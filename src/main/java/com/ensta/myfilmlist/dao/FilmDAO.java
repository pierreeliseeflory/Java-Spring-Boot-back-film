package com.ensta.myfilmlist.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ensta.myfilmlist.exception.DaoException;
import com.ensta.myfilmlist.persistence.ConnectionManager;
import com.ensta.myfilmlist.pojo.FilmPojo;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * The DAO film.
 */
@Repository
public class FilmDAO {
    private static final String FIND_FILMS_QUERY = "SELECT id, titre, " +
            "duration, realisateur, genre FROM Film ORDER BY %1$s %2$s LIMIT " +
            "? OFFSET ?;";
    private static final String FIND_FILM_QUERY = "SELECT id, titre, " +
            "duration, realisateur, genre FROM Film WHERE id = ?;";
    private static final String COUNT_FILMS = "SELECT count(id) FROM Film;";
    private static final String CREATE_FILM_QUERY = "INSERT INTO Film(titre, " +
            "duration, realisateur, genre) VALUES(?, ?, ?, ?);";
    private static final String DELETE_FILM_QUERY = "DELETE FROM Film WHERE " +
            "id = ?;";
    private static final String UPDATE_FILM_QUERY = "UPDATE Film SET titre = " +
            "?, duration = ?, realisateur = ?, genre = ? WHERE id = ?;";

    /**
     * Returns all films of the bdd.
     *
     * @param size     sizer of page
     * @param offset   numéro de la page
     * @param category catégorie pour le tri
     * @param order    ordre du tri
     * @return List<FilmPojo>
     * @throws DaoException exception
     */
    public List<FilmPojo> findAll(final int size, final int offset,
                                  final String category,
                                  final boolean order) throws DaoException {
        List<FilmPojo> resultList = new ArrayList<>();
        String ord = order ? "asc" : "desc";
        String sql = String.format(FIND_FILMS_QUERY, category, ord);
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)
        ) {
            statement.setInt(1, size);
            statement.setInt(2, offset * size);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {

                FilmPojo film = new FilmPojo(resultSet.getInt(1),
                        resultSet.getString(2), resultSet.getInt(3),
                        resultSet.getInt(4), resultSet.getInt(5));

                resultList.add(film);
            }
            return resultList;

        } catch (SQLException e) {
            throw new DaoException("Erreur lors du SELECT : " + e.getMessage());
        }
    }

    /**
     * Counts the films.
     *
     * @return int
     * @throws DaoException exception
     */
    public int count() throws DaoException {
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement statement = conn.prepareStatement(COUNT_FILMS)
        ) {
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getInt(1);

        } catch (SQLException e) {
            throw new DaoException("Erreur lors du COUNT : " + e.getMessage());
        }
    }

    /**
     * Retrieve one film.
     *
     * @param id id of the film
     * @return An option of the film requested
     * @throws DaoException exception
     */
    public Optional<FilmPojo> find(final int id) throws DaoException {
        Optional<FilmPojo> result = Optional.empty();
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement statement = conn
                     .prepareStatement(FIND_FILM_QUERY)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next())
                result = Optional.of(new FilmPojo(resultSet.getInt(1),
                        resultSet.getString(2), resultSet.getInt(3),
                        resultSet.getInt(4), resultSet.getInt(5)));
            if (resultSet.next())
                throw new DaoException(
                        ("Erreur lors du Select: trop de résultats"));
            return result;
        } catch (SQLException e) {
            throw new DaoException("Erreur lors du SELECT : " + e.getMessage());
        }
    }

    /**
     * Create a film.
     *
     * @param film the film to be created
     * @throws DaoException exception
     */
    public void createFilm(final FilmPojo film) throws DaoException {
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement statement = conn
                     .prepareStatement(CREATE_FILM_QUERY)) {
            statement.setString(1, film.getTitre());
            statement.setInt(2, film.getDuration());
            statement.setInt(3, film.getRealisateur());
            statement.setInt(4, film.getGenre());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Erreur lors du INSERT : " + e.getMessage());
        }
    }

    /**
     * Upadte a film.
     *
     * @param film the film to update
     * @return the id of the updated film
     * @throws DaoException exception
     */
    public int updateFilm(final FilmPojo film) throws DaoException {
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement statement = conn
                     .prepareStatement(UPDATE_FILM_QUERY)) {
            statement.setString(1, film.getTitre());
            statement.setInt(2, film.getDuration());
            statement.setInt(3, film.getRealisateur());
            statement.setInt(4, film.getGenre());
            statement.setLong(5, film.getId());
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Erreur lors du UPDATE : " + e.getMessage());
        }
    }

    /**
     * delete a film.
     *
     * @param id the id of the film to be deleted
     * @throws DaoException exception
     */
    public void deleteFilm(final int id) throws DaoException {
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement statement = conn
                     .prepareStatement(DELETE_FILM_QUERY)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Erreur lors du DELETE : " + e.getMessage());
        }
    }
}
