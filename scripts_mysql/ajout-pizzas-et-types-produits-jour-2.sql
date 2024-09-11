use db_pizzeni;

INSERT INTO type_produit(id_type_produit,libelle) VALUES (1,'pizza');
INSERT INTO type_produit(id_type_produit,libelle) VALUES (2,'boisson');
INSERT INTO type_produit(id_type_produit,libelle) VALUES (3,'dessert');


INSERT INTO produit(nom,description,prix,image_url,TYPE_PRODUIT_id_type_produit)
VALUES('Chakchouka','Sauce tomate\, mozzarella fior di latte\, poivrons cuisinés\, oignons cuisinés\, merguez\, œuf plein air\, après cuisson : huile d\’olive extra vierge\, coriandre fraîche.','9.5',"'https://www.pizzacosy.fr/wp-content/uploads/2024/06/vignette_produit-chakchouka.jpg'",1);

INSERT INTO produit(nom,description,prix,image_url,TYPE_PRODUIT_id_type_produit)
VALUES('Zucchina','Crème de courgettes aux zestes de citron\, mozzarella fior di latte\, courgettes poêlées\, après cuisson : ricotta\, menthe fraîche\, huile d\’olive extra vierge. ','8.5',"https://www.pizzacosy.fr/wp-content/uploads/2024/06/vignette_produit-zucchina.jpg",1);

