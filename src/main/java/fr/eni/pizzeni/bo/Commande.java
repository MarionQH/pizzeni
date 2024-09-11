package fr.eni.pizzeni.bo;


import java.time.LocalDateTime;

public class Commande {

    private Long id;
    private LocalDateTime dateHeureLivraison;
    private boolean livraison;
    private Long prixTotal;
    private boolean estPaye;
    private Long idEtat;
    private Client client;



    public Commande() {
    }

    public Commande(Long id, boolean estPaye, Long prixTotal, boolean livraison, LocalDateTime dateHeureLivraison, Long idEtat) {
        this.id = id;
        this.estPaye = estPaye;
        this.prixTotal = prixTotal;
        this.livraison = livraison;
        this.dateHeureLivraison = dateHeureLivraison;
        this.idEtat = idEtat;
    }

    public Commande (Long id,boolean estPaye,Long prixTotal, boolean livraison,LocalDateTime dateHeureLivraison,Long idEtat,Client client) {
        this(id, estPaye, prixTotal, livraison, dateHeureLivraison,idEtat);
        this.client = client;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateHeureLivraison() {
        return dateHeureLivraison;
    }

    public void setDateHeureLivraison(LocalDateTime dateHeureLivraison) {
        this.dateHeureLivraison = dateHeureLivraison;
    }

    public boolean isLivraison() {
        return livraison;
    }

    public void setLivraison(boolean livraison) {
        this.livraison = livraison;
    }

    public Long getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(Long prixTotal) {
        this.prixTotal = prixTotal;
    }

    public boolean isEstPaye() {
        return estPaye;
    }

    public void setEstPaye(boolean estPaye) {
        this.estPaye = estPaye;
    }
    public Long getIdEtat() {
        return idEtat;
    }
    public void setIdEtat(Long idEtat) {
        this.idEtat = idEtat;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
