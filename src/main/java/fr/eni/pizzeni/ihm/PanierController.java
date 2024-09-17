package fr.eni.pizzeni.ihm;


import fr.eni.pizzeni.bll.*;
import fr.eni.pizzeni.bo.*;
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
    public String postCreationCommande(Commande commande) {

        commandeManager.saveCommande(commande);

        return "redirect:/panier";
    }



    @PostMapping("ajout-panier")
    public String getAjoutPanier(Model model,DetailCommande detailCommande,@SessionAttribute(name = "idCommande", required = false) Long idCommande) {


          // créer le use case où on a déjà ajouter ce détail commande au panier

        if (idCommande == null) {

            Client tempClient = clientManager.getClientById(1L);
            Commande commande = new Commande(tempClient);
            commandeManager.saveCommande(commande);
            Long idLastCommande = commandeManager.getIdLastCommandeEnregistreeBDD();
            model.addAttribute("idCommande", idLastCommande);
            idCommande = idLastCommande;
        }


        detailCommandeManager.saveDetailCommande(detailCommande, idCommande);

        return "redirect:/carte";

        //todo: code pour ajouter produit au panier
        //todo: flash message "produit ajouté"

    }


}





