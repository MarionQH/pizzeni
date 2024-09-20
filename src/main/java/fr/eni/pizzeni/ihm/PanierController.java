package fr.eni.pizzeni.ihm;


import fr.eni.pizzeni.bll.*;
import fr.eni.pizzeni.bo.*;

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
    public String postCreationCommande(Commande commande,SessionStatus status,HttpSession session,RedirectAttributes redirectAttributes)  {


        if (commande.getDateHeureLivraison() == null || commande.getDetailsCommandes() == null) {
            IHMHelpers.sendCommonFlashMessage(redirectAttributes,FlashMessage.TYPE_FLASH_ERROR, "ERREUR ! Veuillez compléter votre commande.");
            return "redirect:/panier";
        }
//        TRIGGER (action qui déclenche le PostMapping 'panier')
//        - Appui sur changement de quantité aussi ? (pour MAJ les prix en temps réél) // Plus tard
//

        //Mettre à jour le prix total
        commande.setPrixTotal(commandeManager.calculPrixTotal(commande));


        //gerer le changement d'état de la commande:
        commande.setIdEtat(2L);

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

        //Retirer l'id commande de la session
        //fonctionne pas ??
        //session.removeAttribute("idCommande");

        //nettoyer toute la session
        status.setComplete();

        IHMHelpers.sendCommonFlashMessage(redirectAttributes, FlashMessage.TYPE_FLASH_SUCESS, "Commande enregistrée avec succès");

        return "redirect:/carte";
    }



    @PostMapping("ajout-panier")
    public String getAjoutPanier(Model model, DetailCommande detailCommande, @SessionAttribute(name = "idCommande", required = false) Long idCommande,RedirectAttributes redirectAttributes) {



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

        IHMHelpers.sendCommonFlashMessage(redirectAttributes, FlashMessage.TYPE_FLASH_SUCESS, "Produit ajouté au panier");

        return "redirect:/carte";

        //todo: code pour ajouter produit au panier
        //todo: flash message "produit ajouté"

    }


}





