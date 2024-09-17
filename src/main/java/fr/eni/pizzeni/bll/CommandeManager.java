package fr.eni.pizzeni.bll;

import fr.eni.pizzeni.bo.Commande;
import fr.eni.pizzeni.dao.IDAOCommande;
import fr.eni.pizzeni.dao.IDAODetailCommande;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommandeManager implements ICommandeManager {

    @Autowired
    IDAODetailCommande detailCommande;
    @Autowired
    IDAOCommande daoCommande;

    @Override
    public List<Commande> getCommandes() {

        List<Commande> commandes = daoCommande.selectCommandes();

        return commandes;
    }

    @Override
    public Commande getCommandeById(Long id) {

        Commande commande = daoCommande.selectCommandeById(id);
        commande.setDetailsCommandes(detailCommande.selectDetailsCommandeByIdCommande(id));

        return commande;
    }

    @Override
    public void saveCommande(Commande commande) {

        daoCommande.saveCommande(commande);

    }

    @Override
    public Long getIdLastCommandeEnregistreeBDD() {

      return daoCommande.getIdLastCommandeEnregistreeBDD();

    }

    @Override
    public void updateCommande(Commande commande) {

        daoCommande.updateCommande(commande);

    }
}
