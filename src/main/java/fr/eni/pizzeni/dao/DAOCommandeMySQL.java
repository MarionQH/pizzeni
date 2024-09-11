package fr.eni.pizzeni.dao;

import fr.eni.pizzeni.bo.Commande;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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
            commande.setDateHeureLivraison(rs.getObject("date_heure_livraison", LocalDate.class));
            commande.setLivraison(rs.getBoolean("livraison"));
            commande.setPrixTotal(rs.getLong("prix_total"));
            commande.setEstPaye(rs.getBoolean("est_paye"));
            commande.setIdEtat(rs.getLong("ETAT_id_etat"));

            return commande;
        }
    };



    @Override
    public List<Commande> selectCommandes() {

        String sql = "SELECT id_commande, date_heure_livraison, livraison, ETAT_id_etat, prix_total,est_paye  FROM commande ";

        return jdbcTemplate.query(sql, COMMANDE_ROW_MAPPER);

    }


    @Override
    public Commande selectCommandeById(Long id) {
        String sql = "SELECT id_commande, date_heure_livraison, livraison, ETAT_id_etat, prix_total,est_paye  FROM commande WHERE id_commande = :idCommande";

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("idCommande", id);


        return namedParameterJdbcTemplate.queryForObject(sql, map, COMMANDE_ROW_MAPPER);
    }

    @Override
    public void saveCommande(Commande commande) {

        // 1. Insérer le produit dans la table produit
        String sql = "INSERT INTO commande (date_heure_livraison, livraison, ETAT_id_etat, prix_total,est_paye ) VALUES (:dateHeureLivraison,:livraison,:idEtat,:prixTot,:paye)";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("dateHeureLivraison", commande.getDateHeureLivraison());
        mapSqlParameterSource.addValue("livraison", commande.isLivraison());
        mapSqlParameterSource.addValue("idEtat", commande.getIdEtat());
        mapSqlParameterSource.addValue("prixTot", commande.getPrixTotal());
        mapSqlParameterSource.addValue("paye", commande.isEstPaye());

        // Exécuter la requête pour insérer le produit
        namedParameterJdbcTemplate.update(sql, mapSqlParameterSource);

    }
}
