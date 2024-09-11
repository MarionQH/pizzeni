package fr.eni.pizzeni.bll;

import fr.eni.pizzeni.bo.Client;


import java.util.List;

public interface IClientManager {

    public List<Client> getClients();

    public Client getClientById(Long id);
    public void saveClient(Client client);
}
