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
public class DAOUtilisateurMySQL implements IDAOUtilisateur {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    static final RowMapper<Utilisateur> UTILSATEUR_ROW_MAPPER = new RowMapper<Utilisateur>() {

        @Override
        public Utilisateur mapRow(ResultSet rs, int rowNum) throws SQLException {
            Utilisateur utilisateur = new Utilisateur();
            utilisateur.setId(rs.getLong("id_utilisateur"));
            utilisateur.setNom(rs.getString("nom"));
            utilisateur.setPrenom(rs.getString("prenom"));
            utilisateur.setEmail(rs.getString("email"));
            utilisateur.setPassword(rs.getString("mot_de_passe"));


            return utilisateur;
        }
    };


    @Override
    public List<Utilisateur> selectUtilisateurs() {

        return jdbcTemplate.query("SELECT id_utilisateur,nom,prenom,email,mot_de_passe FROM utilisateur", UTILSATEUR_ROW_MAPPER);

    }

    @Override
    public Utilisateur selectUtilisateursById(Long id) {

        List<Utilisateur> utilisateurs = jdbcTemplate.query("SELECT id_utilisateur,nom,prenom,email,mot_de_passe FROM utilisateur WHERE id_utilisateur=? ", UTILSATEUR_ROW_MAPPER, id);
        if (utilisateurs.size() == 0) {
            return null;
        }
        //0 correspond à l'index de la liste (premier élément)
        return utilisateurs.get(0);

    }

    @Override
    public void saveUtilisateur(Utilisateur utilisateur) {


        String sql = "INSERT INTO utilisateur (nom,prenom,email,mot_de_passe) VALUES (:nom,:prenom,:email,:password)";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("prenom", utilisateur.getPrenom());
        mapSqlParameterSource.addValue("nom", utilisateur.getNom());
        mapSqlParameterSource.addValue("email", utilisateur.getEmail());
        mapSqlParameterSource.addValue("password", utilisateur.getPassword());



        namedParameterJdbcTemplate.update(sql, mapSqlParameterSource);

        return;

    }
}
