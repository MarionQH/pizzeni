package fr.eni.pizzeni.dao;

import fr.eni.pizzeni.bo.Client;


import java.util.List;

public interface IDAOClient {

    public List<Client> selectClients();

    public Client selectClientById(Long id);

    public void saveClient (Client client);
}
