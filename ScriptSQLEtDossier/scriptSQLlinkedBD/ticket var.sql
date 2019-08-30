SELECT
bon.nomReservation as nomReservation, 
bon.dateCommande as dateCommande,
bon.dateLivraison as dateLivraison,
per.nbPoint as nbPoint,
pzria.numRue as numRuePizzeria,
pzria.rue as ruePizzeria,
loc_piz.codePostal as codePostalPizzeria,
loc_piz.nom as villePizzeria,
pzria.boite as boitePizzeria,
bon.numRue as numRueLivraison,
bon.rue as rueLivraison,
loc.codePostal as codePostalLivraison,
loc.nom as villeLivraison,
bon.boite as boiteLivraison
FROM appartenance app
left outer JOIN commande bon on bon.id = app.idCommande
left outer JOIN pizzeria pzria on pzria.id = bon.idPizzeria 
left outer JOIN localite loc on loc.id = bon.idLocalite
left outer JOIN localite loc_piz on loc_piz.id = pzria.idLocalite
left outer JOIN personne per on per.id = bon.idPersonne
WHERE bon.id = 2;