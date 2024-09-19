package fr.eni.pizzeni.dao;

import fr.eni.pizzeni.bll.IUtilisateurManager;
import fr.eni.pizzeni.bo.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

    @Component
    public class DAOAuthMySQL implements IDAOAuth {

        @Autowired
        IUtilisateurManager utilisateurManager;




        @Override
        public Utilisateur login(String email, String password) {

            List<Utilisateur> utilisateurs=  utilisateurManager.getUtilisateurs();

//            addapter en requet SQL ?
            utilisateurs.stream().filter(
                            utilisateur -> utilisateur.getEmail().equals(email) && utilisateur.getNom().equals(password))
                    .findFirst().orElse(null);
            return null;
        }
    }

