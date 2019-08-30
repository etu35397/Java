SELECT 
ingr_comp.id as id,
ingr_comp.nom as nom,
ingr_comp.gout as gout,
ingr_comp.prix as prix
FROM pizza piz
JOIN ingredient ingr on piz.id = ingr.idPizza
JOIN taille tai on tai.id = piz.idTaille
JOIN sauce sau on sau.idPizza = piz.id
JOIN composant ingr_comp on ingr_comp.id = ingr.idComposant
JOIN composant sau_comp on sau_comp.id = sau.idComposant
JOIN composant pat_comp on pat_comp.id = piz.idPate
WHERE piz.id = 2
;