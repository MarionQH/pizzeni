package fr.eni.pizzeni.dao;

import fr.eni.pizzeni.bo.Commande;
import fr.eni.pizzeni.bo.Produit;

import java.util.List;

public interface IDAOProduit {

    public List<Produit> selectProduits();

    public Produit selectProduitById(Long id);

    public void saveProduit(Produit produit);
}
