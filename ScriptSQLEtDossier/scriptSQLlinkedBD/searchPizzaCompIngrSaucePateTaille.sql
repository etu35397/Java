SELECT 
piz.id as idPizza,
piz.nom as nomPizza,
pat_comp.nom as nomPate,
pat_comp.gout as gourPate,
ingr_comp.nom as nomIngredient,
ingr_comp.gout as goutIngredient,
sau_comp.nom as nomSauce,
sau_comp.gout as goutSauce,
sau_comp.prix as prixSauce
FROM pizza piz
JOIN ingredient ingr on piz.id = ingr.idPizza
JOIN taille tai on tai.id = piz.idTaille
JOIN sauce sau on sau.idPizza = piz.id
JOIN composant ingr_comp on ingr_comp.id = ingr.idComposant
JOIN composant sau_comp on sau_comp.id = sau.idComposant
JOIN composant pat_comp on pat_comp.id = piz.idPate
;