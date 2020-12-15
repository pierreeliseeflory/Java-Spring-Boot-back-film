package com.ensta.myfilmlist.dao;

import com.ensta.myfilmlist.exception.DaoException;
import com.ensta.myfilmlist.persistence.ConnectionManager;
import com.ensta.myfilmlist.pojo.GenrePojo;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The DAO genre.
 */
@Repository
public class GenreDAO {
    private static final String FIND_GENRES_QUERY = "SELECT id, nom FROM Genre;";
    private static final String FIND_GENRE_QUERY = "SELECT id, nom FROM Genre WHERE id = ?;";

    /**
     * Retrieve all genres.
     * @return List<GenrePojo>
     * @throws DaoException exception
     */
    public List<GenrePojo> findAll() throws DaoException {
        List<GenrePojo> resultList = new ArrayList<>();
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement statement = conn.prepareStatement(FIND_GENRES_QUERY)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                GenrePojo genre = new GenrePojo(resultSet.getInt(1), resultSet.getString(2));
                resultList.add(genre);
            }
            return resultList;
        } catch (SQLException e) {
            throw new DaoException("Erreur lors du SELECT ALL : " + e.getMessage());
        }
    }

    /**
     * Retrieve one genre
     * @param id the id of the requested genre
     * @return Optional<GenrePojo>
     * @throws DaoException exception
     */
    public Optional<GenrePojo> find (int id) throws DaoException {
        Optional<GenrePojo> resultOption = Optional.empty();
        try(Connection conn = ConnectionManager.getConnection();
            PreparedStatement statement = conn.prepareStatement(FIND_GENRE_QUERY)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) resultOption = Optional.of(new GenrePojo(resultSet.getInt(1), resultSet.getString(2)));
            if (resultSet.next()) throw new DaoException("Erreur lors du SELECT BY id : trop de résultats");
            return resultOption;
        } catch (SQLException e) {
            throw new DaoException("Erreur lors du SELECT : " + e.getMessage());
        }
    }
}
