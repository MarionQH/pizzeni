package fr.eni.pizzeni.dao;

import fr.eni.pizzeni.bo.Client;
import fr.eni.pizzeni.bo.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
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

        List<Client> clients = jdbcTemplate.query("SELECT id_client,nom,prenom,rue,code_postal,ville FROM client WHERE id_client=?", CLIENT_ROW_MAPPER, id);
        if (clients.size() == 0) {
            return null;
        }
        //0 correspond à l'index de la liste (premier élément)
        return clients.get(0);

    }

    @Override
    public void saveClient(Client client) {

        // 1. Insérer le client dans la table client
        String sqlclient = "INSERT INTO client (prenom,nom,rue,code_postal,ville) VALUES (:prenom,:nom,:rue,:code_postal,:ville)";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("prenom", client.getPrenom());
        mapSqlParameterSource.addValue("nom", client.getNom());
        mapSqlParameterSource.addValue("rue", client.getRue());
        mapSqlParameterSource.addValue("code_postal", client.getCodePostal());
        mapSqlParameterSource.addValue("ville", client.getVille());

        // Exécuter la requête pour insérer le client
        namedParameterJdbcTemplate.update(sqlclient, mapSqlParameterSource);

        // 2. Insérer l'utilisateur dans la table utilisateur

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNom(client.getNom());
        utilisateur.setPrenom(client.getPrenom());
        utilisateur.setEmail(client.getEmail());  // Il doit y avoir un email dans l'objet client ou un moyen de le récupérer
        utilisateur.setPassword(client.getPassword());  // Il doit y avoir un password à définir

        String sqlUtilisateur = "INSERT INTO utilisateur (nom, prenom, email, mot_de_passe) VALUES (:nom, :prenom, :email, :password)";
        MapSqlParameterSource utilisateurParams = new MapSqlParameterSource();
        utilisateurParams.addValue("nom", utilisateur.getNom());
        utilisateurParams.addValue("prenom", utilisateur.getPrenom());
        utilisateurParams.addValue("email", utilisateur.getEmail());
        utilisateurParams.addValue("password", utilisateur.getPassword());

        // Exécuter la requête pour insérer l'utilisateur
        namedParameterJdbcTemplate.update(sqlUtilisateur, utilisateurParams);

    }
}
