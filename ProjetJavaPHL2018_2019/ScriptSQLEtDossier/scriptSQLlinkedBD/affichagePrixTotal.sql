SELECT 
piz.id as idPizza, 
piz.nom as nomPizza, 
sum(ingr_comp.prix)/COUNT(DISTINCT sau_comp.id) + pat_comp.prix + tai.prix as prixTotal, 
pat_comp.nom as nomPate, 
sau_comp.nom as nomSauce 
FROM pizza piz 
JOIN ingredient ingr on piz.id = ingr.idPizza 
JOIN taille tai on tai.id = piz.idTaille 
JOIN sauce sau on sau.idPizza = piz.id 
JOIN composant ingr_comp on ingr_comp.id = ingr.idComposant 
JOIN composant sau_comp on sau_comp.id = sau.idComposant 
JOIN composant pat_comp on pat_comp.id = piz.idPate 
WHERE piz.id = 2 
;