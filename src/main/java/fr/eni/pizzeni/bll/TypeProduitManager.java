package fr.eni.pizzeni.bll;

import fr.eni.pizzeni.bo.TypeProduit;
import fr.eni.pizzeni.dao.IDAOTypeProduit;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TypeProduitManager implements ITypeProduitManager{

    private IDAOTypeProduit daoTypeProduit;

    public TypeProduitManager(IDAOTypeProduit daoTypeProduit) {
        this.daoTypeProduit = daoTypeProduit;
    }


    @Override
    public List<TypeProduit> getTypesProduits() {
        return daoTypeProduit.findAll();
    }

    @Override
    public TypeProduit getTypeProduitById(Long id) {
        return daoTypeProduit.findById(id);
    }
}
