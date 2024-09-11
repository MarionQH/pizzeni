package fr.eni.pizzeni.bll;

import fr.eni.pizzeni.bo.Produit;
import fr.eni.pizzeni.dao.IDAOProduit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProduitManager implements IProduitManager {

    @Autowired
    IDAOProduit daoProduit;

    @Override
    public List<Produit> getProduits() {

        List<Produit> produits = daoProduit.selectProduits();

        return produits;
    }

    @Override
    public Produit getProduitById(Long id) {
        Produit produit = daoProduit.selectProduitById(id);
        return produit;
    }

    @Override
    public void saveProduit(Produit produit) {

        daoProduit.saveProduit(produit);

    }

    @Override
    public void deleteProduitById(Long id) {
        daoProduit.deleteProduitById(id);

    }
}
