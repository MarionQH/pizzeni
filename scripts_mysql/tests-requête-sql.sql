USE db_pizzeni;

UPDATE commande SET date_heure_livraison = '2024-09-13 19:26:00', CLIENT_id_client  = 2, prix_total = 10 ,ETAT_id_etat = 2 WHERE id_commande = 1;

SELECT dc.quantite, dc.commande_id_commande as id_commande, dc.produit_id_produit as id_produit,p.nom as nom_produit, p.prix as prix_produit FROM detail_commande dc JOIN commande c ON dc.commande_id_commande = c.id_commande JOIN produit p ON dc.produit_id_produit = p.id_produit WHERE c.id_commande = 1;

USE db_pizzeni;

UPDATE detail_commande SET quantite = 5 WHERE produit_id_produit = 3 AND commande_id_commande = 1;