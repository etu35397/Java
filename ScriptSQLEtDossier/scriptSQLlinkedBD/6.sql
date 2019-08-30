SELECT 
piz.id as idPizza, 
piz.nom as nomPizza, 
sau_comp.nom as nomSauce, 
pat_comp.nom as nomPate,
sum(ingr_comp.prix)/COUNT(DISTINCT sau_comp.id) + pat_comp.prix + tai.prix + sau_comp.prix as prixUnitaire 
FROM appartenance app
left outer JOIN pizza piz on app.idPizza = piz.id 
left outer JOIN ingredient ingr on piz.id = ingr.idPizza 
left outer JOIN taille tai on tai.id = piz.idTaille 
left outer JOIN sauce sau on sau.idPizza = piz.id 
left outer JOIN composant ingr_comp on ingr_comp.id = ingr.idComposant 
left outer JOIN composant sau_comp on sau_comp.id = sau.idComposant 
left outer JOIN composant pat_comp on pat_comp.id = piz.idPate 
left outer JOIN commande bon on bon.id = app.idCommande 
WHERE bon.id = 2
group by nomPizza