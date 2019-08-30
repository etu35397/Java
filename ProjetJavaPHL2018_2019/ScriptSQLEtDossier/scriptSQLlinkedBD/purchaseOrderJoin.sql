SELECT 
com.id as NumeroCommande,
com.nomReservation as nomReservation,
com.dateCommande as dateCommande,
com.dateLivraison as dateLivraison,
com.rue as rueLivraison,
com.numRue as numRueLivraison,
com.boite as boiteLivraison,
loc_cli.codePostal as codePostalLivraison,
loc_cli.name as villeLivraison,
piz.rue as ruePizzeria,
piz.numRue as numRuePizzeria,
loc_piz.codePostal as codePostalPizzeria,
loc_piz.name as villePizzeria
FROM commande com
LEFT JOIN localite loc_cli on loc_cli.id = com.idLocalite
LEFT JOIN pizzeria piz on piz.id = com.idPizzeria
LEFT JOIN localite loc_piz on loc_piz.id = piz.idLocalite

#WHERE com.dateCommande BETWEEN (  to_date( '12-DEC-2014', 'DD-MON-YYYY' ); AND ? ) AND (estPaye == ?)
;


/*SELECT per.name as name,
per.prenom as prenom, 
per.estHomme as estHomme,
IFNULL(per.rue, tit.rue) as rue, 
IFNULL(per.NumRue, tit.NumRue) as NumRue,
IFNULL(per.numgsm, tit.numgsm) as numgsm,
IFNULL(per.numtelephone, tit.numtelephone) as numtelephone, 
IFNULL(per.boite, tit.boite) as boite, 
IFNULL(loc.codePostal, loc_tit.codePostal) as codePostal, 
IFNULL(loc.name, loc_tit.name) as localite,
IFNULL(per.nbpoint, tit.nbpoint) as nbpoint
FROM Personne per
LEFT JOIN localite loc ON loc.id = per.idLocalite
LEFT JOIN personne tit ON tit.id = per.idTitulaire
LEFT JOIN localite loc_tit ON loc_tit.id = tit.idLocalite
where per.id = ?;*/