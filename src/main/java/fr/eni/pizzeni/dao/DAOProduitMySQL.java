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

            return produit;
        }
    };



    @Override
    public List<Produit> selectProduits() {
        return jdbcTemplate.query("SELECT p.id_produit, p.nom, p.description, p.prix, p.image_url, tp.id_type_produit as id_type_produit, tp.libelle FROM produit p JOIN type_produit tp ON p.type_produit_id_type_produit = tp.id_type_produit", PRODUIT_ROW_MAPPER);
    }

    @Override
    public Produit selectProduitById(Long id) {
        List<Produit> produits = jdbcTemplate.query("SELECT p.id_produit, p.nom, p.description, p.prix, p.image_url, tp.id_type_produit as id_type_produit, tp.libelle FROM produit p JOIN type_produit tp ON p.type_produit_id_type_produit = p.id_produit WHERE p.id_produit=?", PRODUIT_ROW_MAPPER, id);
        if (produits.size() == 0) {
            return null;
        }
        //0 correspond à l'index de la liste (premier élément)
        return produits.get(0);
    }

    @Override
    public void saveProduit(Produit produit) {
//
//        // 1. Insérer le film dans la table childrenmovie
//        String sql = "INSERT INTO childrenmovie (title,year,duration,synopsis,url,id_genre) VALUES (:titleMovie,:yearMovie,:durationMovie,:synopsisMovie,:urlMovie,:genreMovie)";
//        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
//        mapSqlParameterSource.addValue("titleMovie", movie.title);
//        mapSqlParameterSource.addValue("yearMovie", movie.year);
//        mapSqlParameterSource.addValue("durationMovie", movie.duration);
//        mapSqlParameterSource.addValue("synopsisMovie", movie.synopsis);
//        mapSqlParameterSource.addValue("urlMovie", movie.url);
//        mapSqlParameterSource.addValue("genreMovie", movie.getGenre().getId());
//
//        // Exécuter la requête pour insérer le film
//        namedParameterJdbcTemplate.update(sql, mapSqlParameterSource);
//
//        // 2. Récupérer l'id du film récemment inséré (si auto-incrémenté par la base de données)
//        Long childrenMovieId = namedParameterJdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", new MapSqlParameterSource(), Long.class);
//
//        // 3. Insérer la liste des participants (acteurs) dans la table acteurs
//        String sqlActeur = "INSERT INTO acteurs (id_film, id_participant) VALUES (:idMovie, :idParticipant)";
//        for (Participant participant : movie.getParticipants()) {
//            MapSqlParameterSource mapParticipantSource = new MapSqlParameterSource();
//            mapParticipantSource.addValue("idMovie",childrenMovieId);
//            mapParticipantSource.addValue("idParticipant", participant.getId()); // Utiliser l'ID du participant
//
//            namedParameterJdbcTemplate.update(sqlActeur, mapParticipantSource);
//        }

    }
}
