package fr.eni.pizzeni;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.lang.reflect.Member;
import java.sql.ResultSet;
import java.sql.SQLException;

@Controller
public class AppController {

// test connexion bdd
//    @Autowired
//    JdbcTemplate jdbcTemplate;
//
//    static final RowMapper<Member> MEMBER_ROW_MAPPER = new RowMapper<Member>() {
//
//        @Override
//        public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
//            Member user = new Member();
//
//            user.setFirstName(rs.getString("prenom"));
//            user.setLastName(rs.getString("nom"));
//            user.setEmail(rs.getString("email"));
//            user.setId(rs.getLong("id"));
//
//
//
//            return user;
//        }
//
//    };




    @GetMapping("")
    public String getBase() {
        return "base.html";
    }


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

