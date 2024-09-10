package fr.eni.pizzeni.dao;

import fr.eni.pizzeni.bo.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
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

//            TypeProduit typeProduit = new TypeProduit();
//            typeProduit.setTitre(rs.getString("libelle"));
//            typeProduit.setId(rs.getLong("id_type_produit"));
        }
    }



    @Override
    public List<Produit> selectProduits() {
        return List.of();
    }

    @Override
    public Produit selectProduitById(Long id) {
        return null;
    }

    @Override
    public void saveProduit(Produit produit) {

    }
}
