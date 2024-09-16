package fr.eni.pizzeni.ihm;

import fr.eni.pizzeni.bll.IClientManager;
import fr.eni.pizzeni.bll.IProduitManager;
import fr.eni.pizzeni.bo.Client;
import fr.eni.pizzeni.bo.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToProduitConverter implements Converter<String, Produit> {

    @Autowired
    IProduitManager produitManager;

    @Override
    public Produit convert(String idProduit) {
        return produitManager.getProduitById(Long.parseLong(idProduit));
    }
}