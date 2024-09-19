package fr.eni.pizzeni.dao;

import fr.eni.pizzeni.bo.Produit;
import fr.eni.pizzeni.bo.TypeProduit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class DAOProduitMySQL implements IDAOProduit {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /**
     * LE code qui permet de savoir comment convertir/mapper un resultat en SQL en Objet/Classe
     * Comment mapper un resultat SQL en Movie
     */
    static final RowMapper<Produit> PRODUIT_ROW_MAPPER = new RowMapper<Produit>() {

        @Override
        public Produit mapRow(ResultSet rs, int rowNum) throws SQLException {
            Produit produit = new Produit();
            produit.setId(rs.getLong("id_produit"));
            produit.setNom(rs.getString("nom"));
            produit.setDescription(rs.getString("description"));
            produit.setPrix(rs.getLong("prix"));
            produit.setImageUrl(rs.getString("image_url"));

            TypeProduit typeProduit = new TypeProduit();
            typeProduit.setLibelle(rs.getString("libelle"));
            typeProduit.setId(rs.getLong("id_type_produit"));

            produit.setTypeProduit(typeProduit);

            return produit;
        }
    };



    @Override
    public List<Produit> selectProduits() {
        return jdbcTemplate.query("SELECT p.id_produit, p.nom, p.description, p.prix, p.image_url, tp.id_type_produit, tp.libelle FROM produit p JOIN type_produit tp ON p.type_produit_id_type_produit = tp.id_type_produit", PRODUIT_ROW_MAPPER);
    }

    @Override
    public Produit selectProduitById(Long id) {
        List<Produit> produits = jdbcTemplate.query("SELECT p.id_produit, p.nom, p.description, p.prix, p.image_url, tp.id_type_produit, tp.libelle FROM produit p JOIN type_produit tp ON p.type_produit_id_type_produit = tp.id_type_produit WHERE p.id_produit=?", PRODUIT_ROW_MAPPER, id);
        if (produits.size() == 0) {
            return null;
        }
        //0 correspond à l'index de la liste (premier élément)
        return produits.get(0);
    }

    @Override
    public void saveProduit(Produit produit) {

        if (produit.getId() == null) {

        // 1. Insérer le produit dans la table produit
        String sql = "INSERT INTO produit (nom,description,prix,image_url, TYPE_PRODUIT_id_type_produit) VALUES (:nomProduit,:descriptionProduit,:prixProduit,:urlProduit,:typeProduit)";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("nomProduit", produit.getNom());
        mapSqlParameterSource.addValue("descriptionProduit", produit.getDescription());
        mapSqlParameterSource.addValue("prixProduit", produit.getPrix());
        mapSqlParameterSource.addValue("urlProduit", produit.getImageUrl());
        mapSqlParameterSource.addValue("typeProduit", produit.getTypeProduit().getId());

        // Exécuter la requête pour insérer le produit
        namedParameterJdbcTemplate.update(sql, mapSqlParameterSource);

        return;
        }

        if (produit.getId() != null) {

            if (produit.getId() != null) {
                String sql = "UPDATE produit " +
                        "SET nom = :nomProduit, " +
                        "description = :descriptionProduit, " +
                        "prix = :prixProduit, " +
                        "image_url = :urlProduit, " +
                        "TYPE_PRODUIT_id_type_produit = :typeProduit " +
                        "WHERE id_produit = :idProduit"; // Fix: use :idProduit as a placeholder

                MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
                mapSqlParameterSource.addValue("nomProduit", produit.getNom());
                mapSqlParameterSource.addValue("descriptionProduit", produit.getDescription());
                mapSqlParameterSource.addValue("prixProduit", produit.getPrix());
                mapSqlParameterSource.addValue("urlProduit", produit.getImageUrl());
                mapSqlParameterSource.addValue("typeProduit", produit.getTypeProduit().getId());
                mapSqlParameterSource.addValue("idProduit", produit.getId()); // Fix: add the idProduit value

                namedParameterJdbcTemplate.update(sql, mapSqlParameterSource);
            }

        }
    }

    @Override
    public void deleteProduitById(Long id) {



        String sql = "DELETE FROM produit WHERE id_produit=?";

        jdbcTemplate.update(sql, id);
    }


}