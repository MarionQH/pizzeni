package fr.eni.pizzeni.dao;

import fr.eni.pizzeni.bo.Client;
import fr.eni.pizzeni.bo.Commande;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class DAOCommandeMySQL implements IDAOCommande {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    static final RowMapper<Commande> COMMANDE_ROW_MAPPER = new RowMapper<Commande>() {

        @Override
        public Commande mapRow(ResultSet rs, int rowNum) throws SQLException {
            Commande commande = new Commande();
            commande.setId(rs.getLong("id_commande"));
            // getTimesTamp
            commande.setDateHeureLivraison(rs.getObject("date_heure_livraison", LocalDateTime.class));
            commande.setLivraison(rs.getBoolean("livraison"));
            commande.setPrixTotal(rs.getLong("prix_total"));
            commande.setEstPaye(rs.getBoolean("est_paye"));
            commande.setIdEtat(rs.getLong("ETAT_id_etat"));

            Client client = new Client();
            client.setId(rs.getLong("id_client"));
            client.setNom(rs.getString("nom"));
            client.setPrenom(rs.getString("prenom"));
            client.setVille(rs.getString("ville"));
            client.setRue(rs.getString("rue"));
            client.setVille(rs.getString("ville"));
            commande.setClient(client);

            return commande;
        }
    };



    @Override
    public List<Commande> selectCommandes() {

        String sql = "SELECT c.id_commande, c.date_heure_livraison, c.livraison, c.ETAT_id_etat, c.prix_total, c.est_paye, cl.id_client as CLIENT_id_client  FROM commande c JOIN client cl ON c.CLIENT_id_client = cl.id_client";

        return jdbcTemplate.query(sql, COMMANDE_ROW_MAPPER);

    }


    @Override
    public Commande selectCommandeById(Long id) {
        String sql = "SELECT c.id_commande," +
                " c.date_heure_livraison," +
                " c.livraison, " +
                "c.ETAT_id_etat, " +
                "c.prix_total," +
                " c.est_paye," +
                " cl.id_client " +
                "FROM commande c " +
                "JOIN client cl ON c.CLIENT_id_client = cl.id_client WHERE id_commande = :idCommande";

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("idCommande", id);


        return namedParameterJdbcTemplate.queryForObject(sql, map, COMMANDE_ROW_MAPPER);
    }

    @Override
    public void saveCommande(Commande commande) {

        // 1. Insérer le produit dans la table produit
        String sql = "INSERT INTO commande (date_heure_livraison, livraison, ETAT_id_etat, prix_total,est_paye,CLIENT_id_client ) VALUES (:dateHeureLivraison,:livraison,:idEtat,:prixTot,:paye,:CLIENT_id_client)";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("dateHeureLivraison", commande.getDateHeureLivraison());
        mapSqlParameterSource.addValue("livraison", commande.isLivraison());
        mapSqlParameterSource.addValue("idEtat", commande.getIdEtat());
        mapSqlParameterSource.addValue("prixTot", commande.getPrixTotal());
        mapSqlParameterSource.addValue("paye", commande.isEstPaye());
        mapSqlParameterSource.addValue("CLIENT_id_client",commande.getClient().getId());

        // Exécuter la requête pour insérer le produit
        namedParameterJdbcTemplate.update(sql, mapSqlParameterSource);


    }

    @Override
    public Long getIdLastCommandeEnregistreeBDD() {


        // 2. Récupérer l'id du film récemment inséré (si auto-incrémenté par la base de données)
       Long lastIdCommande = namedParameterJdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", new MapSqlParameterSource(), Long.class);
       return lastIdCommande;
    }

    @Override
    public void updateCommande(Commande commande) {

        //PS Return dans un void retourne pas de valeur mais stop le code
           String sql = "UPDATE commande SET date_heure_livraison = :dateHeureLivraison, CLIENT_id_client  = :id_client, prix_total = :prix_total,ETAT_id_etat = :idEtat WHERE id_commande = :id";
MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
mapSqlParameterSource.addValue("id", commande.getId());
mapSqlParameterSource.addValue("dateHeureLivraison", commande.getDateHeureLivraison());
mapSqlParameterSource.addValue("id_client",commande.getClient().getId());
mapSqlParameterSource.addValue("prix_total",commande.getPrixTotal());
mapSqlParameterSource.addValue("idEtat",commande.getIdEtat());

    }


}
