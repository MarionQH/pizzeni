package fr.eni.pizzeni;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {

    @GetMapping("home")
    public String index() {
        return "base.html";
    }

}
