package fr.eni.pizzeni.bll;

import fr.eni.pizzeni.bo.Client;
import fr.eni.pizzeni.dao.IDAOClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientManager implements IClientManager {

    @Autowired
    IDAOClient daoClient;

    @Override
    public List<Client> getClients() {

        List<Client> clients = daoClient.selectClients();

        return clients;
    }

    @Override
    public Client getClientById(Long id) {

        Client client = daoClient.selectClientById(id);

        return client;
    }

    @Override
    public void saveClient(Client client) {

        daoClient.saveClient(client);

    }
}
