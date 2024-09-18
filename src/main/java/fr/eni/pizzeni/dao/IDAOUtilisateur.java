package fr.eni.pizzeni.dao;

import fr.eni.pizzeni.bo.Utilisateur;

import java.util.List;

public interface IDAOUtilisateur {

    public List<Utilisateur> selectUtilisateurs();

    public Utilisateur selectUtilisateursById(Long id);

    public void saveUtilisateur(Utilisateur utilisateur);
}
