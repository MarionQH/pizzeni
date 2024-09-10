package fr.eni.pizzeni.bll;

import fr.eni.pizzeni.bo.TypeProduit;

import java.util.List;

public interface ITypeProduitManager {

    List<TypeProduit> getTypesProduits();
    TypeProduit getTypeProduitByIt(Long id);
}
