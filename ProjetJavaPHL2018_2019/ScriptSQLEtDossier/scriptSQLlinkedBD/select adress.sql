SELECT 
loc.id as idLoc,
piz.rue as rue, 
piz.numRue as numRue, 
piz.boite as boite,
loc.nom as ville,
loc.codePostal as codePostal
FROM pizzeria piz 
JOIN localite loc on loc.id = piz.idLocalite
where loc.id = 20
;