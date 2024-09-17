package fr.eni.pizzeni.bll;

import fr.eni.pizzeni.bo.Commande;
import fr.eni.pizzeni.bo.DetailCommande;
import fr.eni.pizzeni.dao.IDAOCommande;
import fr.eni.pizzeni.dao.IDAODetailCommande;
import fr.eni.pizzeni.dao.IDAOProduit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommandeManager implements ICommandeManager {

    @Autowired
    IDAODetailCommande detailCommande;
    @Autowired
    IDAOCommande daoCommande;

    @Autowired
    IDAOProduit daoProduit;

    @Override
    public List<Commande> getCommandes() {

        List<Commande> commandes = daoCommande.selectCommandes();

        return commandes;
    }

    @Override
    public Commande getCommandeById(Long id) {

        Commande commande = daoCommande.selectCommandeById(id);
        commande.setDetailsCommandes(detailCommande.selectDetailsCommandeByIdCommande(id));
        List<DetailCommande> detailsCommandes = commande.getDetailsCommandes();

        //Todo récupérer les produits dans les détails produits
//        for (int i = 0 ; i < detailsCommandes.size() ; i++) {
//
//          detailsCommandes(i).setProduit(daoProduit.selectProduitById(detailsCommandes(i).getId()));
//
//        }




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
