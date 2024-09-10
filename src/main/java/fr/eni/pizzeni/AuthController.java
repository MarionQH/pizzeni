package fr.eni.pizzeni;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @GetMapping("")
    public String getBase() {
        return "base.html";
    }



    @GetMapping("login")
        public String getLogin() {
        return "connexion.html";
        }


   @PostMapping("login")
    public String postLogin() {
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

