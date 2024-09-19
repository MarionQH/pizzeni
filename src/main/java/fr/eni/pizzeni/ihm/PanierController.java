package fr.eni.pizzeni.ihm;


import fr.eni.pizzeni.bll.*;
import fr.eni.pizzeni.bo.*;
import fr.eni.pizzeni.dao.DAODetailCommandeMySQL;
import fr.eni.pizzeni.dao.IDAODetailCommande;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
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
    public String getCreationCommande(@SessionAttribute(name = "idCommande", required = false) Long idCommande, Model model)  {

        Commande commande = commandeManager.getCommandeById(idCommande);
        model.addAttribute("commande", commande);
        System.out.println(commande);

        List<Client> clients = clientManager.getClients();
        model.addAttribute("clients", clients);


        return "creation-commande.html";


    }

    @PostMapping("panier")
    public String postCreationCommande(Commande commande)  {

        /*

        TRIGGER (action qui déclenche le PostMapping 'panier')
        - Appui sur bouton commander
        - Appui sur changement de quantité aussi ? (pour MAJ les prix en temps réél) // Plus tard

        FONCTIONNALITES A APPELER
        - Mettre à jour les details commande qui ont changé (qté)
        - Mettre à jour le client
        - Recalculer le prix total
        - Mettre à jour le prix total
        - Mettre à jour le mode de réception (livraison ou à emporter)

        ET SI TOUS LES CHAMPS SONT REMPLIS
        - Mettre à jour le statut -> doit devenir 2 (à préparer)
        - Retirer l'id commande de la session

         */

     // - Mettre à jour le prix total
     // - Mettre à jour le mode de réception (livraison ou à emporter)
     // - Mettre à jour le client (via son id)
     // - Mettre à jour le statut -> doit devenir 2 (à préparer)

//        Commande commande = commandeManager.getCommandeById(idCommande);

        commandeManager.updateCommande(commande);

        List<DetailCommande> listeDetailsCommande = commande.getDetailsCommandes();

        for (int i = 0 ; i < listeDetailsCommande.size() ; i++) {

           DetailCommande detailCommande = listeDetailsCommande.get(i);
           Long idProduit = detailCommande.getProduit().getId();
           int quantite = detailCommande.getQuantite();
           Long idCommande = commande.getId();

           detailCommandeManager.updateDetailCommande(quantite, idProduit, idCommande);

           System.out.println(detailCommande);
        }






        // - Mettre à jour les details commande qui ont changé (qté)

//        @Override
//        public void updateDetailCommande(DetailCommande detailCommande,Long idProduit,Long idCommande) {
//
//            daodetailCommande.updateDetailCommande(detailCommande,idProduit,idCommande);
//
//        }

//        model.getAttribute("commande");
//
//        List<DetailCommande> detailsCommande = commande.getDetailsCommandes();
//
//
//        detailCommandeManager.updateDetailCommande();







//        //Update chaque ligne de detail commande
//        for (DetailCommande detailCommande : detailsCommande) {
//
//            detailCommandeManager.updateDetailCommande(detailCommande,detailCommande.getProduit().getId(), commande.getId());
//        }
//
//        //gerer le changement d'état de la commande:
//        commande.setIdEtat(2L);
//
//        //update la commande avec la nouvelle heure de livraison
//        commandeManager.updateCommande(commande);

        return "redirect:/panier";
    }


        @GetMapping("/remove-id-commande-session")
        public String removeIdCommandeFromSession(HttpSession session) {
            // Remove the 'idCommande' attribute from the session
            session.removeAttribute("idCommande");

            // Optionally, redirect to another page or return a view
            return "redirect:/carte";  // Redirects to the homepage, or change as needed
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


        Boolean hasDetailCommandMatch = detailCommandeManager.detectMatchByIdCommandeAndIdProduit(idCommande,detailCommande.getProduit().getId());

        if (!hasDetailCommandMatch) {
                        detailCommandeManager.saveDetailCommande(detailCommande, idCommande);
        }



        return "redirect:/carte";

        //todo: code pour ajouter produit au panier
        //todo: flash message "produit ajouté"

    }


}





