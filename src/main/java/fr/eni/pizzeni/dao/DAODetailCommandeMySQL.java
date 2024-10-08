package fr.eni.pizzeni.dao;


import fr.eni.pizzeni.bo.DetailCommande;
import fr.eni.pizzeni.bo.Produit;
import fr.eni.pizzeni.bo.TypeProduit;
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

            // Matcher les alias
            Produit produit = new Produit();
            produit.setId(rs.getLong("id_produit"));
            produit.setNom(rs.getString("nom"));
            produit.setDescription(rs.getString("description"));
            produit.setPrix(rs.getLong("prix"));
            produit.setImageUrl(rs.getString("image_url"));

            TypeProduit typeProduit = new TypeProduit();
            typeProduit.setId(rs.getLong("id_type_produit"));
            typeProduit.setLibelle(rs.getString("libelle"));

            produit.setTypeProduit(typeProduit);

            detailCommande.setProduit(produit);

            return detailCommande;
        }
    };

    @Override
    public List<DetailCommande> selectDetailsCommandes() {

        String sql = "SELECT dc.quantite, " +
                "dc.commande_id_commande, " +
                "p.id_produit, " +
                "p.nom , " +
                "p.description, " +
                "p.prix, " +
                "p.image_url, " +
                "tp.id_type_produit, " +
                "tp.libelle " +
                "FROM detail_commande dc " +
                "JOIN produit p " +
                "ON dc.produit_id_produit = p.id_produit " +
                "JOIN type_produit tp " +
                "ON p.type_produit_id_type_produit = tp.id_type_produit ";


        return jdbcTemplate.query(sql, DETAIL_COMMANDE_ROW_MAPPER);
    }

    @Override
    public List<DetailCommande> selectDetailsCommandeByIdCommande(Long idCommande) {
        String sql = "SELECT dc.quantite, " +
                "dc.commande_id_commande, " +
                "p.id_produit, " +
                "p.nom , " +
                "p.description, " +
                "p.prix, " +
                "p.image_url, " +
                "tp.id_type_produit, " +
                "tp.libelle " +
                "FROM detail_commande dc " +
                "JOIN produit p " +
                "ON dc.produit_id_produit = p.id_produit " +
                "JOIN type_produit tp " +
                "ON p.type_produit_id_type_produit = tp.id_type_produit " +
                "WHERE dc.commande_id_commande = :idCommande";

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("idCommande", idCommande);

        return namedParameterJdbcTemplate.query(sql, map, DETAIL_COMMANDE_ROW_MAPPER);
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
    public void updateDetailCommande(int quantite, Long idProduit, Long idCommande) {

        //TODO à terminer (ajouter l'updade en fonction de l'ID commande

        String sql = "UPDATE detail_commande SET quantite = :quantite WHERE produit_id_produit = :idProduit  AND commande_id_commande = :idCommande";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("idProduit",idProduit);
        mapSqlParameterSource.addValue("quantite",quantite);
        mapSqlParameterSource.addValue("idCommande",idCommande);


        namedParameterJdbcTemplate.update(sql, mapSqlParameterSource);
    }

    @Override
    public Boolean detectMatchByIdCommandeAndIdProduit(Long idCommande, Long idProduit) {

       String sql = "SELECT COUNT(*) FROM db_pizzeni.detail_commande WHERE PRODUIT_id_produit = :idProduit AND COMMANDE_id_commande = :idCommande";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("idProduit",idProduit);
        map.addValue("idCommande",idCommande);

        Boolean hasDetailCommandeMatch = false;

       int resultSQLQuery = namedParameterJdbcTemplate.queryForObject(sql, map, Integer.class);

        if (resultSQLQuery > 0) {
            hasDetailCommandeMatch = true;
        }

        return hasDetailCommandeMatch;
    }




}
