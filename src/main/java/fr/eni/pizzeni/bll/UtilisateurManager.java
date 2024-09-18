package fr.eni.pizzeni.bll;

import fr.eni.pizzeni.bo.Utilisateur;
import fr.eni.pizzeni.dao.IDAOUtilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilisateurManager implements IUtilisateurManager {

    @Autowired
    IDAOUtilisateur daoUtilisateur;

    @Override
    public List<Utilisateur> getUtilisateurs() {

        List<Utilisateur> utilisateurs = daoUtilisateur.selectUtilisateurs();

        return utilisateurs;
    }

    @Override
    public Utilisateur getUtlisateurById(Long id) {

        Utilisateur utilisateur = daoUtilisateur.selectUtilisateursById(id);
        return null;
    }

    @Override
    public void saveUtilisateur(Utilisateur utilisateur) {

        daoUtilisateur.saveUtilisateur(utilisateur);

    }
}
