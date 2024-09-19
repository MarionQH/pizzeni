package fr.eni.pizzeni.ihm;

import fr.eni.pizzeni.bll.AuthManager;
import fr.eni.pizzeni.bll.IUtilisateurManager;
import fr.eni.pizzeni.bll.ManagerResponse;
import fr.eni.pizzeni.bo.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {

    @Autowired
    AuthManager authManager;

    @GetMapping("login")
        public String getLogin(Model model) {
        Utilisateur utilisateur = new Utilisateur();
        model.addAttribute("utilisateur", utilisateur);

        return "connexion.html";
        }


   @PostMapping("login")
    public String postLogin(@ModelAttribute Utilisateur utilisateur, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
       //tu récupère l'user grace au @ModelAttribute

       //1:: Controle de surface

       if (bindingResult.hasErrors()) {
           // Retourner la page avec les erreurs de validation (le format)
           return "/";
       }


       //2 : Controle metier (le manager)
       ManagerResponse <Utilisateur> response = authManager.authenticate(utilisateur.getEmail(), utilisateur.getPassword());
       //Erreur code 756 retrourner la page avec l'erreur métier
       if (response.code.equals("756")){
           return "/connect";
       }

       //3: Connecter l'user en session
       //mettre user retrouvé en base dans la session
       model.addAttribute("utilisateur", response.data);


//       //ajouter un message temporaire
//       // appeler la fonction: EniIHMHelpers
//       EniIHMHelpers.sendCommonFlashMessage(redirectAttributes, EniFlashMessage.TYPE_FLASH_SUCESS, "Vous êtes connecté(e) avec succès");
//
//       //
       return "redirect:/login";

   }



   @GetMapping ("logout")
        public String getLogout() {
            return "connexion.html";
       }

   @PostMapping ("logout")
    public String postLogout() {
        return "redirect:/login";
   }
   }

