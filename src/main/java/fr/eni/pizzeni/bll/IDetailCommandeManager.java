package fr.eni.pizzeni.bll;

import fr.eni.pizzeni.bo.Commande;
import fr.eni.pizzeni.bo.DetailCommande;

import java.util.List;

public interface IDetailCommandeManager {

    public List<DetailCommande> getDetailsCommandes();

    public DetailCommande getDetailCommandeByIdCommande(Long id);

    public void saveDetailCommande(DetailCommande detailCommande,Long idCommande);

    public void updateDetailCommande(DetailCommande detailCommande ,Long idProduit,Long idCommande);

    public Boolean detectMatchByIdCommandeAndIdProduit(Long idCommande, Long idProduit);
}
