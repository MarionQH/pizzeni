package fr.eni.pizzeni.dao;

import fr.eni.pizzeni.bo.Commande;
import fr.eni.pizzeni.bo.DetailCommande;

import java.util.List;

public interface IDAODetailCommande {

    public List<DetailCommande> selectDetailsCommandes();

    public List<DetailCommande> selectDetailsCommandeByIdCommande (Long id);

    public void saveDetailCommande(DetailCommande detailCommande,Long idCommande);

    public void updateDetailCommande(int quantite,Long idProduit,Long idCommande);

    public Boolean detectMatchByIdCommandeAndIdProduit(Long idCommande,Long idProduit);

}
