package fr.eni.pizzeni.dao;

import fr.eni.pizzeni.bo.Utilisateur;

public interface IDAOAuth {

    /**
     * Permettera de récupérer un utilisateur dans les données
     * @param email
     * @param password
     * @return
     */
    Utilisateur login(String email, String password);

}
