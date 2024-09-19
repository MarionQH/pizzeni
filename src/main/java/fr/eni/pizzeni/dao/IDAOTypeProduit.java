package fr.eni.pizzeni.dao;

import fr.eni.pizzeni.bo.TypeProduit;

import java.util.List;

public interface IDAOTypeProduit {

    List<TypeProduit> findAll();
    TypeProduit findById(Long id);


}
