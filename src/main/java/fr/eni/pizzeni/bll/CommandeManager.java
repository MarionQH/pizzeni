package fr.eni.pizzeni.bll;

import fr.eni.pizzeni.bo.Commande;
import fr.eni.pizzeni.bo.DetailCommande;
import fr.eni.pizzeni.bo.Produit;
import fr.eni.pizzeni.bo.TypeProduit;
import fr.eni.pizzeni.dao.IDAOCommande;
import fr.eni.pizzeni.dao.IDAODetailCommande;
import fr.eni.pizzeni.dao.IDAOProduit;
import fr.eni.pizzeni.dao.IDAOTypeProduit;
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

    @Autowired
    IDAOTypeProduit daoTypeProduit;

    @Override
    public List<Commande> getCommandes() {

        List<Commande> commandes = daoCommande.selectCommandes();

        return commandes;
    }

    @Override
    public Commande getCommandeById(Long id) {


        // Récupérer la commande dans la BDD depuis son id
        Commande commande = daoCommande.selectCommandeById(id);

        //Récupérer les details commande de la BDD depuis l'id detail commande
        commande.setDetailsCommandes(detailCommande.selectDetailsCommandeByIdCommande(id));

        //Récupérer le client de la commande depuis l'id client dans la commande
        commande.setClient(commande.getClient());

//        // Récupérer les produits de chaque détail commande
//        List<DetailCommande> detailsCommandes = commande.getDetailsCommandes();
//
//        for (DetailCommande detailCommande : detailsCommandes) {
//
//        Produit produit = detailCommande.getProduit();
//
//        detailCommande.setProduit(produit);

        return commande;

        }







        //Todo récupérer les produits dans les détails produits
//        for (int i = 0 ; i < detailsCommandes.size() ; i++) {
//
//          detailsCommandes(i).setProduit(daoProduit.selectProduitById(detailsCommandes(i).getId()));
//
//        }



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
