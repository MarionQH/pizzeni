package fr.eni.pizzeni.bo;

public class Produit {

    private Long id;
    private String nom;
    private String description;
    private Long prix;
    private String imageUrl;
    private TypeProduit typeProduit;

    public Produit() {
    }

    

    public Produit(Long id, String nom, String description, Long prix, String imageUrl, TypeProduit typeProduit) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.imageUrl = imageUrl;
        this.typeProduit = typeProduit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getPrix() {
        return prix;
    }

    public void setPrix(Long prix) {
        this.prix = prix;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public TypeProduit getTypeProduit() {
        return typeProduit;
    }

    public void setTypeProduit(TypeProduit typeProduit) {
        this.typeProduit = typeProduit;
    }
}
