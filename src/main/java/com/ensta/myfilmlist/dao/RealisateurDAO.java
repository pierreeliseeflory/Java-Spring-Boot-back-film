package com.ensta.myfilmlist.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ensta.myfilmlist.exception.DaoException;
import com.ensta.myfilmlist.persistence.ConnectionManager;
import com.ensta.myfilmlist.pojo.RealisateurPojo;

import java.util.Date;
import java.util.Optional;

import org.springframework.stereotype.Repository;

/**
 * The DAO realisateur.
 */
@Repository
public class RealisateurDAO {
    private static final String FIND_REALISATEURS_QUERY = "SELECT id, prenom," +
            " nom, dateNaissance FROM Realisateur LIMIT ? OFFSET ?;";
    private static final String COUNT_REALISATEUR = "SELECT count(id) FROM " +
            "Realisateur;";
    private static final String FIND_REALISATEUR_QUERY = "SELECT id, prenom, " +
            "nom, dateNaissance FROM Realisateur WHERE id = ?;";
    private static final String CREATE_REALISATEUR_QUERY = "INSERT INTO " +
            "Realisateur(prenom, nom, dateNaissance) VALUES(?, ?, ?);";
    private static final String DELETE_REALISATEUR_QUERY = "DELETE FROM " +
            "Realisateur WHERE id = ?;";
    private static final String UPDATE_REALISATEUR_QUERY = "UPDATE " +
            "Realisateur SET prenom = ?, nom = ?, dateNaissance = ? WHERE id" +
            " = ?;";

    /**
     * Returns all directors of the bdd.
     *
     * @param taillePage taille de la page
     * @param offsetPage numéro de la page
     * @return List<RealisateurPojo>
     * @throws DaoException exception
     */
    public List<RealisateurPojo> findAll(int taillePage,
                                         int offsetPage) throws DaoException {
        List<RealisateurPojo> resultList = new ArrayList<>();
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement statement = conn
                     .prepareStatement(FIND_REALISATEURS_QUERY)) {
            statement.setInt(1, taillePage);
            statement.setInt(2, taillePage * offsetPage);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                RealisateurPojo realisateur = new RealisateurPojo(
                        resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3),
                        new Date(resultSet.getDate(4).getTime()));
                resultList.add(realisateur);
            }
            return resultList;

        } catch (SQLException e) {
            throw new DaoException("Erreur lors du SELECT : " + e.getMessage());
        }
    }

    /**
     * Counts the directors.
     *
     * @return int
     * @throws DaoException exception
     */
    public int count() throws DaoException {
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement statement = conn
                     .prepareStatement(COUNT_REALISATEUR)) {
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getInt(1);

        } catch (SQLException e) {
            throw new DaoException("Erreur lors du COUNT : " + e.getMessage());
        }
    }

    /**
     * Retrieve one director.
     *
     * @param id id of the director
     * @return An option of the director requested
     * @throws DaoException exception
     */
    public Optional<RealisateurPojo> find(int id) throws DaoException {
        Optional<RealisateurPojo> resultOption = Optional.empty();
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement statement = conn
                     .prepareStatement(FIND_REALISATEUR_QUERY)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next())
                resultOption = Optional
                        .of(new RealisateurPojo(resultSet.getInt(1),
                                resultSet.getString(2), resultSet.getString(3),
                                new Date(resultSet.getDate(4).getTime())));
            if (resultSet.next())
                throw new DaoException(
                        ("Erreur lors du Select: trop de résultats"));
            return resultOption;

        } catch (SQLException e) {
            throw new DaoException("Erreur lors du SELECT : " + e.getMessage());
        }
    }

    /**
     * Create a director.
     *
     * @param realisateur the director to be created
     * @throws DaoException exception
     */
    public void createRealisateur(
            RealisateurPojo realisateur) throws DaoException {
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement statement = conn
                     .prepareStatement(CREATE_REALISATEUR_QUERY)) {
            statement.setString(1, realisateur.getPrenom());
            statement.setString(2, realisateur.getNom());
            statement.setDate(3, new java.sql.Date(
                    realisateur.getDateNaissance().getTime()));
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Erreur lors du INSERT : " + e.getMessage());
        }
    }

    /**
     * Upadte a director.
     *
     * @param realisateur the director to update
     * @return the id of the updated director
     * @throws DaoException exception
     */
    public int updateRealisateur(
            RealisateurPojo realisateur) throws DaoException {
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement statement = conn
                     .prepareStatement(UPDATE_REALISATEUR_QUERY)) {
            statement.setString(1, realisateur.getPrenom());
            statement.setString(2, realisateur.getNom());
            statement.setDate(3, new java.sql.Date(
                    realisateur.getDateNaissance().getTime()));
            statement.setLong(4, realisateur.getId());
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Erreur lors du UPDATE : " + e.getMessage());
        }
    }

    /**
     * delete a director.
     *
     * @param id the id of the director to be deleted
     * @throws DaoException exception
     */
    public void deleteRealisateur(int id) throws DaoException {
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement statement = conn
                     .prepareStatement(DELETE_REALISATEUR_QUERY)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Erreur lors du DELETE : " + e.getMessage());
        }
    }
}
