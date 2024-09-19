package fr.eni.pizzeni.ihm;


import fr.eni.pizzeni.bll.*;
import fr.eni.pizzeni.bo.*;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.support.SessionStatus;

import java.util.List;

@SessionAttributes({"idCommande"})
@Controller
public class PanierController {

    private static final Logger log = LogManager.getLogger(PanierController.class);

    private final ProduitManager produitManager;

    @Autowired
    private DetailCommandeManager detailCommandeManager;


    public PanierController(ProduitManager produitManager) {
        this.produitManager = produitManager;
    }


    @Autowired
    ICommandeManager commandeManager;

    @Autowired
    IClientManager clientManager;


    @GetMapping("panier")
    public String getCreationCommande(@SessionAttribute(name = "idCommande", required = false) Long idCommande, Model model) {

        Commande commande = commandeManager.getCommandeById(idCommande);
        model.addAttribute("commande", commande);
        System.out.println(commande);

        List<Client> clients = clientManager.getClients();
        model.addAttribute("clients", clients);


        return "creation-commande.html";


    }

    @PostMapping("panier")
    public String postCreationCommande(Commande commande, HttpSession session, SessionStatus status) {


//        TRIGGER (action qui déclenche le PostMapping 'panier')
//        - Appui sur bouton commander
//        - Appui sur changement de quantité aussi ? (pour MAJ les prix en temps réél) // Plus tard

        //ET SI TOUS LES CHAMPS SONT REMPLIS

        //Mettre à jour le statut -> doit devenir 2 (à préparer)
        commande.setIdEtat(2L);

        //- Recalculer le prix total
        //- Mettre à jour le prix total

        commande.setPrixTotal(commandeManager.calculPrixTotal(commande));

        //Update de la commande
        commandeManager.updateCommande(commande);


        //Update de les détails commande
        List<DetailCommande> listeDetailsCommande = commande.getDetailsCommandes();

        for (int i = 0; i < listeDetailsCommande.size(); i++) {

            DetailCommande detailCommande = listeDetailsCommande.get(i);
            Long idProduit = detailCommande.getProduit().getId();
            int quantite = detailCommande.getQuantite();
            Long idCommande = commande.getId();

            detailCommandeManager.updateDetailCommande(quantite, idProduit, idCommande);

            System.out.println(detailCommande);
        }

//        - Retirer l'id commande de la session
//        ne fonctionne pas ?
//        session.removeAttribute("idCommande");

//         nettoyer toute la session
        status.setComplete();


        return "redirect:/carte";
    }


    @PostMapping("ajout-panier")
    public String getAjoutPanier(Model model, DetailCommande detailCommande, @SessionAttribute(name = "idCommande", required = false) Long idCommande) {


        if (idCommande == null) {

            Client tempClient = clientManager.getClientById(1L);
            Commande commande = new Commande(tempClient);
            commandeManager.saveCommande(commande);
            Long idLastCommande = commandeManager.getIdLastCommandeEnregistreeBDD();
            model.addAttribute("idCommande", idLastCommande);
            idCommande = idLastCommande;

        }

        // créer le use case où on a déjà ajouté ce détail commande au panier
        // parser la table details commande
        // si une ligne correspond au detailCommande,
        // alors brancher sur la requête Udpate de Marion


        Boolean hasDetailCommandMatch = detailCommandeManager.detectMatchByIdCommandeAndIdProduit(idCommande, detailCommande.getProduit().getId());

        if (!hasDetailCommandMatch) {
            detailCommandeManager.saveDetailCommande(detailCommande, idCommande);
        }


        return "redirect:/carte";

        //todo: code pour ajouter produit au panier
        //todo: flash message "produit ajouté"

    }


}





