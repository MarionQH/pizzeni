package fr.eni.pizzeni.dao;


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
import java.util.List;

@Repository
public class DAODetailCommandeMySQL implements IDAODetailCommande{

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    static final RowMapper<DetailCommande> DETAIL_COMMANDE_ROW_MAPPER = new RowMapper<DetailCommande>() {

        @Override
        public DetailCommande mapRow(ResultSet rs, int rowNum) throws SQLException {
            DetailCommande detailCommande = new DetailCommande();
            detailCommande.setQuantite(rs.getInt("quantite"));


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
    public DetailCommande selectDetailCommandeByIdCommande(Long idCommande) {
        String sql = "SELECT dc.quantite, dc.commande_id_commande as id_commande, dc.produit_id_produit as id_produit,p.nom as nom_produit, p.prix as prix_produit FROM detail_commande dc JOIN commande c ON dc.commande_id_commande = c.id_commande JOIN produit p ON dc.produit_id_produit = p.id_produit WHERE c.id_commande = :idCommande";

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("idCommande", idCommande);

        return namedParameterJdbcTemplate.queryForObject(sql, map, DETAIL_COMMANDE_ROW_MAPPER);
    }

    @Override
    public void saveDetailCommande(DetailCommande detailCommande,Long idCommande) {

        // 1. Insérer le produit dans la table produit
        String sql = "INSERT INTO detail_commande (quantite,commande_id_commande,produit_id_produit) VALUES (:quantite,:id_commande,:id_produit)";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("quantite", detailCommande.getQuantite());
        mapSqlParameterSource.addValue("id_commande",idCommande);
        mapSqlParameterSource.addValue("id_produit", detailCommande.getProduit().getId());

        // Exécuter la requête pour insérer le produit
        namedParameterJdbcTemplate.update(sql, mapSqlParameterSource);

    }

    @Override
    public void updateDetailCommande(DetailCommande detailCommande, Long idProduit, Long idCommande) {

        //TODO à terminer (ajouter l'updade en fonction de l'ID commande

        String sql = "UPDATE detail_commande SET quantite = :quantite WHERE produit_id_produit = :idProduit  AND commande_id_commande = :idCommande";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("idProduit",idProduit);
        mapSqlParameterSource.addValue("quantite",detailCommande.getQuantite());
        mapSqlParameterSource.addValue("idCommande",idCommande);


    }

    @Override
    public Boolean detectMatchByIdCommandeAndIdProduit(Long idCommande, Long idProduit) {

        

        return null;
    }


}
