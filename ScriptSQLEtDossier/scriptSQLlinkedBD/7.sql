SELECT *
FROM appartenance app  
left outer JOIN commande bon on bon.id = app.idCommande  
left outer JOIN pizzeria pzria on pzria.id = bon.idPizzeria   
left outer JOIN localite loc on loc.id = bon.idLocalite  
left outer JOIN localite loc_piz on loc_piz.id = pzria.idLocalite  
left outer JOIN personne per on per.id = bon.idPersonne  
LEFT outer JOIN personne tit ON tit.id = per.idTitulaire 
WHERE bon.id = 1