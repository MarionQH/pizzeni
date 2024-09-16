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
    public DetailCommande getDetailCommandeByIdCommande(Long id) {
        DetailCommande detailCommande = daodetailCommande.selectDetailCommandeByIdCommande(id);
        return detailCommande;
    }

    @Override
    public void saveDetailCommande(DetailCommande detailCommande,Long idCommande) {

        daodetailCommande.saveDetailCommande(detailCommande,idCommande);

    }
}
