package fr.eni.pizzeni.ihm;


import fr.eni.pizzeni.bll.*;
import fr.eni.pizzeni.bo.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@SessionAttributes({"idCommande"})
@Controller
public class PanierController {

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
    public String getCreationCommande(Long id, Model model) {

        Commande commande = new Commande();
        List<Client> clients = clientManager.getClients();
        List<DetailCommande> detailsCommande = detailCommandeManager.getDetailsCommandes();

        model.addAttribute("commande", commande);
        model.addAttribute("clients", clients);


        return "creation-commande.html";
    }

    @PostMapping("panier")
    public String postCreationCommande(Commande commande, List<DetailCommande> detailsCommande) {

        //Update chaque ligne de detail commande
        for (DetailCommande detailCommande : detailsCommande) {

            detailCommandeManager.updateDetailCommande(detailCommande,detailCommande.getProduit().getId(), commande.getId());
        }

        //gerer le changement d'état de la commande:
        commande.setIdEtat(2L);

        //update la commande avec la nouvelle heure de livraison
        commandeManager.updateCommande(commande);

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





