package fr.eni.pizzeni.bo;

import java.util.List;

public class DetailCommande {

    private int quantite;
    private Produit produit;
    private Commande commande;

    public DetailCommande() {
    }

    public DetailCommande(int quantite, Commande commande, Produit produit) {
        this.quantite = quantite;
        this.commande = commande;
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

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    @Override
    public String toString() {
        return "DetailCommande{" +
                "quantite=" + quantite +
                ", produit=" + produit +
                '}';
    }
}



