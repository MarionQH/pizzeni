package fr.eni.pizzeni.ihm;

import fr.eni.pizzeni.bll.IClientManager;
import fr.eni.pizzeni.bo.Client;
import fr.eni.pizzeni.bo.TypeProduit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToClientConverter implements Converter<String, Client> {

    @Autowired
    IClientManager clientManager;

    @Override
    public Client convert(String idclient) {
        return clientManager.getClientById(Long.parseLong(idclient));
    }
}
