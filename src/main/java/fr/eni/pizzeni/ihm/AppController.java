package fr.eni.pizzeni.ihm;


import fr.eni.pizzeni.bll.ICommandeManager;
import fr.eni.pizzeni.bll.ITypeProduitManager;
import fr.eni.pizzeni.bll.ProduitManager;
import fr.eni.pizzeni.bo.Commande;
import fr.eni.pizzeni.bo.Produit;
import fr.eni.pizzeni.bo.TypeProduit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AppController {

    private final ProduitManager produitManager;


    public AppController(ProduitManager produitManager) {
        this.produitManager = produitManager;
    }

    @Autowired
    ITypeProduitManager typeProduitManager;

    @Autowired
    ICommandeManager commandeManager;


    @GetMapping("")
    public String getBase() {
        return "base.html";
    }


    @GetMapping("accueil")
    public String index() {
        return "accueil.html";
    }


    @GetMapping("ajout-pizza")
    public String getAjoutPizza(Long id, Model model) {

        Produit produit = new Produit();
        List<TypeProduit> typesProduits = typeProduitManager.getTypesProduits();
        System.out.println(produit);



        System.out.println(typesProduits);
        model.addAttribute("typesProduits", typesProduits);


        model.addAttribute("produit", produit);

        return "ajout-pizza.html";
    }

    @PostMapping("ajout-pizza")
    public String postAjoutPizza(Produit produit) {

        produitManager.saveProduit(produit);

        return "redirect:/ajout-pizza";
    }


    @GetMapping("creation-commande")
    public String getCreationCommande(Long id, Model model) {

        Commande commande = new Commande();

        model.addAttribute("commande", commande);


        return "creation-commande.html";
    }

    @PostMapping("creation-commande")
    public String postCreationCommande(Commande commande) {

        commandeManager.saveCommande(commande);

        return "redirect:/creation-commande";
    }

    @GetMapping("carte")
    public String getCarte(Model model) {

        // récupérer la liste
        List<Produit> produits = produitManager.getProduits();

        // envoyer les films dans le modele
        model.addAttribute("produits", produits);

        for (int i = 0; i < produits.size(); i++) {
            System.out.println(produits.get(i).getNom());
            System.out.println(produits.get(i).getTypeProduit());
        }


        return "carte.html";
    }

    @GetMapping("liste-commandes")
    public String getListCommandes() {
        return "commandes.html";
    }


    // ajout @Chloé test Controller get/ redirect

    @GetMapping("ajout-panier")
    public String getAjoutPanier() {
// expliquer le but de la méthode
        //todo: code pour ajouter produit au panier
        //todo: flash message "produit ajouté"

        return "redirect:/carte";
    }


    @GetMapping("supprimer-produit/**")
    public String getSupprimerProduit(Long id) {

        //done appel requete sql suppression produit
        //todo: flash message ou modale "Etes vous sûr.e?"
        produitManager.deleteProduitById(id);

        return "redirect:/carte";
    }

    @GetMapping("modifier-produit")
    public String getModifierProduit(Long id) {

       //todo: s'assurer que la page ajout pizza est chargée avec le bon produit
        return "ajout-pizza.html";
    }
}





