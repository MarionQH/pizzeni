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
        //commande.setDetailsCommandes(detailCommande.selectDetailsCommandeByIdCommande(id));


//        return commande;

        if(commande != null) {
            //Récupérer les details commande de la BDD depuis l'id detail commande
            commande.setDetailsCommandes(detailCommande.selectDetailsCommandeByIdCommande(id));
            return commande;
        }
        return new Commande();
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

    @Override
    public Long calculPrixTotal(Commande commande) {

        List<DetailCommande> detailsCommandes = commande.getDetailsCommandes(); // Suppose qu'il existe une liste des articles

        // Calculer le prix total
        Long prixTotal = detailsCommandes.stream()
                .mapToLong(detailcommande -> detailcommande.getProduit().getPrix() * detailcommande.getQuantite())
                .sum();

        return prixTotal;
    }
}
