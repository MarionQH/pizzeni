package fr.eni.pizzeni.bo;

public class Client extends Utilisateur {

    private String rue;
    private Long codePostal;
    private String ville;

    public Client() {
    }

    public Client(Long id, String nom, String prenom, String email, String password, String rue, Long codePostal, String ville) {
        super(id, nom, prenom, email, password);
        this.rue = rue;
        this.codePostal = codePostal;
        this.ville = ville;
//        super.setId(id);
//        super.setNom(nom);
//        super.setPrenom(prenom);
//        super.setEmail(email);
//        super.setPassword(password);

    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public Long getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(Long codePostal) {
        this.codePostal = codePostal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }
}
