package fr.eni.pizzeni.ihm;


import fr.eni.pizzeni.bll.IClientManager;
import fr.eni.pizzeni.bll.ICommandeManager;
import fr.eni.pizzeni.bll.ITypeProduitManager;
import fr.eni.pizzeni.bll.ProduitManager;
import fr.eni.pizzeni.bo.Client;
import fr.eni.pizzeni.bo.Commande;
import fr.eni.pizzeni.bo.Produit;
import fr.eni.pizzeni.bo.TypeProduit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@SessionAttributes({"idCommande"})
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


    @GetMapping("panier")
    public String getCreationCommande(Long id, Model model) {

        Commande commande = new Commande();
        List<Client> clients = clientManager.getClients();

        model.addAttribute("commande", commande);
        model.addAttribute("clients", clients);


        return "creation-commande.html";
    }

    @PostMapping("panier")
    public String postCreationCommande(Commande commande) {

        commandeManager.saveCommande(commande);

        return "redirect:/panier";
    }

    @GetMapping("carte")
    public String getCarte(Model model) {

        // récupérer la liste
        List<Produit> produits = produitManager.getProduits();

        // envoyer les films dans le modele
        model.addAttribute("produits", produits);

//        for (int i = 0; i < produits.size(); i++) {
//            System.out.println(produits.get(i).getNom());
//            System.out.println(produits.get(i).getTypeProduit());
//        }


        return "carte.html";
    }

    @GetMapping("liste-commandes")
    public String getListCommandes() {
        return "commandes.html";
    }




    @GetMapping("ajout-panier")
    public String getAjoutPanier(Model model) {
// expliquer le but de la méthode
        //todo: code pour ajouter produit au panier
        //todo: flash message "produit ajouté"


        Client tempClient = clientManager.getClientById(1L);
        Commande commande = new Commande(tempClient);
        commandeManager.saveCommande(commande);

        model.addAttribute("idCommande", commande.getId());


       Long idLastCommande = commandeManager.getIdLastCommandeEnregistreeBDD();

       System.out.println(idLastCommande);

        // placer en session

        return "redirect:/carte";
    }


    @GetMapping("supprimer-produit/**")
    public String getSupprimerProduit(Long id) {

        //done appel requete sql suppression produit
        //todo: flash message ou modale "Etes vous sûr.e?"
        produitManager.deleteProduitById(id);

        return "redirect:/carte";
    }


    @GetMapping("instancier-un-client")
    public String getInstancierUnClient() {

        Client client1 = new Client();

        System.out.println(client1.toString());

        Client client2 = new Client(2L,"Jouannet","Lucille","alice.jouannet@gmail.com","123456","rue des lapins",49000L,"Angers");

        System.out.println(client2.toString());

        return "base.html";
    }





}





