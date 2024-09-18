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


    @GetMapping("")
    public String getBase() {
        return "base.html";
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
    public String postAjoutPizza(Produit produit) {

        produitManager.saveProduit(produit);

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


    @GetMapping("instancier-un-client")
    public String getInstancierUnClient(Model model) {

        Client client = new Client();
        model.addAttribute("client", client);

//        System.out.println(client1.toString());
//
//        Client client2 = new Client(2L, "Jouannet", "Lucille", "alice.jouannet@gmail.com", "123456", "rue des lapins", 49000L, "Angers");
//
//        System.out.println(client2.toString());

        return "ajout-client.html";
    }

    @PostMapping("instancier-un-client")
    public String postInstancierUnClient(Client client) {

        clientManager.saveClient(client);
        return "redirect:/instancier-un-client";
    }


}





