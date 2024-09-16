package fr.eni.pizzeni.bo;

import java.util.List;

public class DetailCommande {

    private int quantite;
    private Produit produit;

    public DetailCommande() {
    }

    public DetailCommande(int quantite, Produit produit) {
        this.quantite = quantite;
        this.produit = produit;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }


    @Override
    public String toString() {
        return "DetailCommande{" +
                "quantite=" + quantite +
                ", produit=" + produit +
                '}';
    }
}



