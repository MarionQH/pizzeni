package fr.eni.pizzeni.bll;


import fr.eni.pizzeni.bo.Produit;
import org.springframework.stereotype.Component;

import java.util.List;


public interface IProduitManager {

    public List<Produit> getProduits();
    public Produit getProduitById(Long id);
    public void saveProduit(Produit produit);

}
