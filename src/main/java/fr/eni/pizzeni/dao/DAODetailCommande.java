package fr.eni.pizzeni.dao;

import fr.eni.pizzeni.bo.Client;
import fr.eni.pizzeni.bo.Commande;
import fr.eni.pizzeni.bo.DetailCommande;
import fr.eni.pizzeni.bo.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class DAODetailCommande implements IDAODetailCommande{

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    static final RowMapper<DetailCommande> DETAIL_COMMANDE_ROW_MAPPER = new RowMapper<DetailCommande>() {

        @Override
        public DetailCommande mapRow(ResultSet rs, int rowNum) throws SQLException {
            DetailCommande detailCommande = new DetailCommande();
            detailCommande.setQuantite(rs.getInt("quantite"));

            Commande commande = new Commande();
            commande.setId(rs.getLong("id_commande"));
            detailCommande.setCommande(commande);

            Produit produit = new Produit();
            produit.setId(rs.getLong("id_produit"));
            detailCommande.setProduit(produit);

            return detailCommande;
        }
    };

    @Override
    public List<DetailCommande> selectDetailsCommandes() {

        String sql = "select * from detail_commande";
        return jdbcTemplate.query(sql, DETAIL_COMMANDE_ROW_MAPPER);
    }

    @Override
    public DetailCommande selectDetailCommandeByIdCommande(Long id) {
        String sql = "SELECT dc.quantite, dc.commande_id_commande as id_commande, dc.produit_id_produit as id_produit,p.nom as nom_produit FROM detail_commande dc JOIN commande c ON dc.commande_id_commande = c.id_commande JOIN produit p ON dc.produit_id_produit = p.id_produit WHERE c.id_commande = :idCommande";

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("idCommande", id);

        return namedParameterJdbcTemplate.queryForObject(sql, map, DETAIL_COMMANDE_ROW_MAPPER);
    }

    @Override
    public void saveDetailCommande(DetailCommande detailCommande) {

    }


}