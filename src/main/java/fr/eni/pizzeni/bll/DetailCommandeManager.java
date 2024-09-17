package fr.eni.pizzeni.bll;

import fr.eni.pizzeni.bo.DetailCommande;
import fr.eni.pizzeni.dao.IDAODetailCommande;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetailCommandeManager implements IDetailCommandeManager{

@Autowired
    IDAODetailCommande daodetailCommande;

    @Override
    public List<DetailCommande> getDetailsCommandes() {

        List<DetailCommande> detailsCommandes = daodetailCommande.selectDetailsCommandes();
        return detailsCommandes;
    }

    @Override
    public List<DetailCommande> getDetailsCommandeByIdCommande(Long id) {
        List<DetailCommande> detailsCommande = daodetailCommande.selectDetailsCommandeByIdCommande(id);
        return detailsCommande;
    }

    @Override
    public void saveDetailCommande(DetailCommande detailCommande,Long idCommande) {

        daodetailCommande.saveDetailCommande(detailCommande,idCommande);

    }

    @Override
    public void updateDetailCommande(DetailCommande detailCommande,Long idProduit,Long idCommande) {

        daodetailCommande.updateDetailCommande(detailCommande,idProduit,idCommande);

    }

    @Override
    public Boolean detectMatchByIdCommandeAndIdProduit(Long idCommande, Long idProduit) {

        return daodetailCommande.detectMatchByIdCommandeAndIdProduit(idCommande,idProduit);
    }
}
