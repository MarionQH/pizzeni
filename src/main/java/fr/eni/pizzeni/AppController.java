package fr.eni.pizzeni;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AppController {

    @GetMapping("accueil")
    public String index() {
        return "accueil.html";
    }


    @GetMapping("ajout-pizza")
    public String getAjoutPizza() {
        return "ajout-pizza.html";
    }

    @PostMapping("ajout-pizza")
    public String postAjoutPizza() {
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
    public String getCarte() {
        return "carte.html";
    }

    @GetMapping("liste-commandes")
        public String getListCommandes() {
            return "commandes.html";
        }



}

