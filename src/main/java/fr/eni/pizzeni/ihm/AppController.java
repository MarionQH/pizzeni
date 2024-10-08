package fr.eni.pizzeni.ihm;


import fr.eni.pizzeni.bll.*;
import fr.eni.pizzeni.bo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AppController {

    private final ProduitManager produitManager;

    @Autowired
    private DetailCommandeManager detailCommandeManager;


    public AppController(ProduitManager produitManager) {
        this.produitManager = produitManager;
    }

    @Autowired
    ITypeProduitManager typeProduitManager;

    @Autowired
    ICommandeManager commandeManager;

    @Autowired
    IClientManager clientManager;

    @Autowired
    IUtilisateurManager utilisateurManager;


    @GetMapping("")
    public String getBase() {
        return "accueil.html";
    }


    @GetMapping("accueil")
    public String index() {
        return "accueil.html";
    }


    @GetMapping("gestion-produit/{id}")

    public String getAjoutPizzaWithId(@PathVariable Long id, Model model) {

        Produit produit = produitManager.getProduitById(id);

        System.out.println(produit.toString());

        List<TypeProduit> typesProduits = typeProduitManager.getTypesProduits();

        model.addAttribute("typesProduits", typesProduits);

        model.addAttribute("produit", produit);

        return "ajout-pizza.html";
    }


    @GetMapping("gestion-produit")
    public String getAjoutPizza(Model model) {

        Produit produit = new Produit();
        List<TypeProduit> typesProduits = typeProduitManager.getTypesProduits();
//        System.out.println(produit);


//        System.out.println(typesProduits);
        model.addAttribute("typesProduits", typesProduits);

        model.addAttribute("produit", produit);

        return "ajout-pizza.html";
    }


    @PostMapping("gestion-produit")
    public String postAjoutPizza(Produit produit,RedirectAttributes redirectAttributes) {

        produitManager.saveProduit(produit);

        IHMHelpers.sendCommonFlashMessage(redirectAttributes, FlashMessage.TYPE_FLASH_SUCESS, "Produit ajouté à la carte avec succès");

        return "redirect:/carte";
    }


    @GetMapping("carte")
    public String getCarte(Model model) {

        // récupérer la liste
        List<Produit> produits = produitManager.getProduits();

        List<DetailCommande> detailsCommande = new ArrayList<DetailCommande>();


        for (int i = 0; i < produits.size(); i++) {

            DetailCommande detailCommande = new DetailCommande();
            //detailCommande = qte et produit
            detailCommande.setProduit(produits.get(i));
            detailCommande.setQuantite(1);

            detailsCommande.add(detailCommande);
        }

        System.out.println(detailsCommande.toString());


        // envoyer les details commande dans le modele
        model.addAttribute("detailsCommande", detailsCommande);

//        for (int i = 0; i < produits.size(); i++) {
//            System.out.println(produits.get(i).getNom());
//            System.out.println(produits.get(i).getTypeProduit());
//        }


        return "carte.html";
    }

    @GetMapping("liste-commandes")
    public String getListCommandes(Model model) {

        List<Commande> commandes = commandeManager.getCommandes();
        model.addAttribute("commandes", commandes);

        return "commandes.html";
    }


    @GetMapping("supprimer-produit/**")
    public String getSupprimerProduit(Long id) {

        //done appel requete sql suppression produit
        //todo: flash message ou modale "Etes vous sûr.e?"
        produitManager.deleteProduitById(id);

        return "redirect:/carte";
    }


    @GetMapping("ajout-utilisateur")
    public String getInstancierUnClient(Model model) {

        Client client = new Client();
        model.addAttribute("client", client);

        return "ajout-utilisateur.html";
    }

    @PostMapping("ajout-utilisateur")
    public String postInstancierUnClient(Client client,RedirectAttributes redirectAttributes) {

        clientManager.saveClient(client);

        IHMHelpers.sendCommonFlashMessage(redirectAttributes, FlashMessage.TYPE_FLASH_SUCESS, "Utilisateur ajouté avec succès");
        return "redirect:/ajout-utilisateur";
    }


    @GetMapping("le-chef-a-prepare-la-commande")
            public String passerLaCommandeEnPrete(RedirectAttributes redirectAttributes) {
        IHMHelpers.sendCommonFlashMessage(redirectAttributes, FlashMessage.TYPE_FLASH_SUCESS, "La commande est bien marquée comme prête");



    return "redirect:/liste-commandes";
    }
}





