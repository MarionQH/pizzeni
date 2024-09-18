package fr.eni.pizzeni.bll;

import fr.eni.pizzeni.bo.Client;
import fr.eni.pizzeni.bo.Utilisateur;

import java.util.List;

public interface IUtilisateurManager {

    public List<Utilisateur> getUtilisateurs();

    public Utilisateur getUtlisateurById(Long id);

    public void saveUtilisateur(Utilisateur utilisateur);
}
