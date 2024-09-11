package fr.eni.pizzeni.dao;

import fr.eni.pizzeni.bo.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class DAOClientMySQL implements IDAOClient {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    static final RowMapper<Client> CLIENT_ROW_MAPPER = new RowMapper<Client>() {

        @Override
        public Client mapRow(ResultSet rs, int rowNum) throws SQLException {
            Client client = new Client();
            client.setId(rs.getLong("id_client"));
            client.setNom(rs.getString("nom"));
            client.setPrenom(rs.getString("prenom"));
            client.setRue(rs.getString("rue"));
            client.setCodePostal(rs.getLong("code_postal"));
            client.setVille(rs.getString("ville"));

            return client;
        }
    };




    @Override
    public List<Client> selectClients() {

        return jdbcTemplate.query("SELECT id_client,nom,prenom,rue,code_postal,ville FROM client", CLIENT_ROW_MAPPER);
    }

    @Override
    public Client selectClientById(Long id) {
        return null;
    }

    @Override
    public void saveClient(Client client) {

    }
}
