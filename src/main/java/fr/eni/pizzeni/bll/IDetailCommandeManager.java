package fr.eni.pizzeni.bll;

import fr.eni.pizzeni.bo.Commande;
import fr.eni.pizzeni.bo.DetailCommande;

import java.util.List;

public interface IDetailCommandeManager {

    public List<DetailCommande> getDetailsCommandes();

    public List<DetailCommande>  getDetailsCommandeByIdCommande(Long id);

    public void saveDetailCommande(DetailCommande detailCommande,Long idCommande);

    public void updateDetailCommande(int quantite ,Long idProduit,Long idCommande);

    public Boolean detectMatchByIdCommandeAndIdProduit(Long idCommande, Long idProduit);
}
