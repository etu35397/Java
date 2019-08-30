UPDATE commande
SET nomReservation = 'Marbaix Romain',
dateCommande = '2018-05-13 19:34:00',
dateLivraison = '2020-05-13 19:34:00', 
rue = 'Rue de fer ++ ', 
numRue = 10, 
boite = 'Z', 
estPaye = 0, 
idLocalite = 583, 
idPersonne = 1, 
idPizzeria = 2
WHERE id = 1;

Select * FROM commande;