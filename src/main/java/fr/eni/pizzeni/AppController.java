package fr.eni.pizzeni;


import fr.eni.pizzeni.bll.ITypeProduitManager;
import fr.eni.pizzeni.bll.ProduitManager;
import fr.eni.pizzeni.bll.TypeProduitManager;
import fr.eni.pizzeni.bo.Produit;
import fr.eni.pizzeni.bo.TypeProduit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.lang.reflect.Member;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Controller
public class AppController {

    private final ProduitManager produitManager;

    @Autowired
    ITypeProduitManager typeProduitManager;

    public AppController(ProduitManager produitManager) {
        this.produitManager = produitManager;
    }



    @GetMapping("")
    public String getBase() {
        return "base.html";
    }


    @GetMapping("accueil")
    public String index() {
        return "accueil.html";
    }


    @GetMapping("ajout-pizza")
    public String getAjoutPizza(Model model) {

        Produit produit = new Produit();
        List<TypeProduit> typesProduits= typeProduitManager.getTypesProduits();

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
    public String getCreationCommande() {
        return "creation-commande.html";
    }

    @PostMapping("creation-commande")
    public String postCreationCommande() {
        return  "redirect:/creation-commande";
    }

    @GetMapping("carte")
    public String getCarte(Model model) {

        // récupérer la liste
        List<Produit> produits = produitManager.getProduits();

        // envoyer les films dans le modele
        model.addAttribute("produits", produits);


        return "carte.html";
    }

    @GetMapping("liste-commandes")
        public String getListCommandes() {
            return "commandes.html";
        }



}

