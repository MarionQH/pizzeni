package fr.eni.pizzeni.bll;

import fr.eni.pizzeni.bo.Utilisateur;
import fr.eni.pizzeni.dao.IDAOAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthManager {

    @Autowired
    IDAOAuth idaOAuth;

    public ManagerResponse<Utilisateur> authenticate (String email, String password) {
        Utilisateur foundUser = idaOAuth.login(email, password);
        // Si couple email/password incorect code 756
        if (foundUser == null) {
            return ManagerResponse.performResponse("756","Couple email/mot de passe incorrect",null);
        }

        //Sinon code 202
        return ManagerResponse.performResponse("202","Vous êtes connecté(e) avec succès",foundUser);
    }
}

