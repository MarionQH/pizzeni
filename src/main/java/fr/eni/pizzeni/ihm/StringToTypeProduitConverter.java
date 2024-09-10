package fr.eni.pizzeni.ihm;

import fr.eni.pizzeni.bll.ITypeProduitManager;
import fr.eni.pizzeni.bo.TypeProduit;
import org.springframework.core.convert.converter.Converter;

public class StringToTypeProduitConverter implements Converter<String, TypeProduit> {

    private ITypeProduitManager typeProduitManager;

    public StringToTypeProduitConverter(ITypeProduitManager typeProduitManager) {
        this.typeProduitManager = typeProduitManager;
    }



    @Override
    public TypeProduit convert(String idTypeProduit) {

        System.out.println("Conversion de idTypeProduit" + idTypeProduit);

        return typeProduitManager.getTypeProduitById(Long.parseLong(idTypeProduit));
    }


}
