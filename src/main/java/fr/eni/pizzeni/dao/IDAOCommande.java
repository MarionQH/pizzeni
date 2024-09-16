package fr.eni.pizzeni.dao;

import fr.eni.pizzeni.bo.Commande;


import java.util.List;

public interface IDAOCommande {

    public List<Commande> selectCommandes();

    public Commande selectCommandeById(Long id);

    public void saveCommande(Commande commande);

    public Long getIdLastCommandeEnregistreeBDD();

}
