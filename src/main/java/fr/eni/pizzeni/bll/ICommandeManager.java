package fr.eni.pizzeni.bll;

import fr.eni.pizzeni.bo.Commande;


import java.util.List;

public interface ICommandeManager {

    public List<Commande> getCommandes();

    public Commande getCommandeById(Long id);

    public void saveCommande(Commande commande);

    public Long getIdLastCommandeEnregistreeBDD();

    public void updateCommande(Commande commande);
}
