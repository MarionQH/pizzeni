package fr.eni.pizzeni.dao;

import fr.eni.pizzeni.bo.TypeProduit;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;



@Repository
public class DAOTypeProduitMySQL implements IDAOTypeProduit{

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public DAOTypeProduitMySQL(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate ) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    static final RowMapper<TypeProduit> TYPE_PRODUIT_ROW_MAPPER = new RowMapper<TypeProduit>() {

        @Override

        public TypeProduit mapRow(ResultSet rs, int rowNum) throws SQLException {

            TypeProduit typeProduit = new TypeProduit();

            typeProduit.setId(rs.getLong("id_type_produit"));
            typeProduit.setLibelle(rs.getString("libelle"));

            return typeProduit;

        }


    };


    @Override
    public List<TypeProduit> findAll() {


        String sql = "SELECT id_type_produit, libelle FROM type_produit";

        return jdbcTemplate.query(sql, TYPE_PRODUIT_ROW_MAPPER);

    }

    @Override
    public TypeProduit findById(Long id) {

        String sql = "SELECT id_type_produit, libelle FROM type_produit WHERE id = :idTypeProduit";


        MapSqlParameterSource map = new MapSqlParameterSource();


       map.addValue("idTypeProduit", id);


        return namedParameterJdbcTemplate.queryForObject(sql, map, TYPE_PRODUIT_ROW_MAPPER);
    }
}
