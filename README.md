# [PROJET_JAVA_2018-2019](https://github.com/etu32010/PROJET_JAVA_2018-2019#dossier-code)

## Dossier (papier)
- [x] Page de garde
- [x] domaine d'application 
- [x] Schema conceptuel **(Schema de BD)**
- [x] Schema logique des tables
- [x] Documentation des tables
- [x] ennoncer les **fonctionnalités** *(recherche, inserion et faire du blah-blah la dessus...)*
  - Enoncer toutes les **entrees** *(ex: JComboBox, JTextField, JRadioButton...)*
  - Ennoncer les **sorties** (énumérez les colonnes de la JTable qui affichera les résultats de la recherche)

## Dossier (CODE)

- [x] SUPPRESSION
- [x] LISTING
- [x] INSERTION
- [x] MODIFICATION

### Buisness
- [x]

### Controller
- [x]

### DAO
- [x]

### DataAccess
- [x] liaison BD

### Exception
- [x] Prix
- [x] gout (enum)
- [x] codePostal (NNNN)
- [x] numGSM(04NN/NN.NN.NN)
- [x] numTel (0NN/NN.NN.NN)
- [x] dateComparaison(naissance)
- [x] points negatifs (titulaire)

### Model
- [x] Classe : Pizza
- [x] Classe : Taille
- [x] Classe : Composant
- [x] Classe : Commande
- [x] Classe : Personne (super)
- [x] Classe : MembreFamille (sous)
- [x] Classe : Titulaire (sous)
- [x] Classe : pizzeria
- [x] Classe : Localite

### View

### Enumeration
- [x] Gout

## A verifier constamment :
- [ ] Fautes d'ortho.
- [ ] faire du code propre *(CleanCode)*

## A VERIFIER JUSTE AVANT DE RENDRE
- [ ] Verfier les getters/setters inutiles

__Modifications Alex:__
j'ai créé la classe personne, dateComparaisonException, NumGsmException
j'ai ajouté le regex au numGSM

__Modifications PH:__

__idées:__
Faire une classe static Numero qui permettrait de save les pattern des numeros style telephone et gsm et de pouvoir comparer avec une constante "regex" pour les type de numeros (gsm et tel)-> comme ça si le format des numeros change on change juste la et ça change partout dans le programme

## Consignes pour les corrections de juin :

- [x] Revoir la taille des fenêtres et le layout afin de rentre l'interface plus conviviale
- [x] Ne pas laisser la possibilité à l'utilisateur d'ouvrir plusieurs fois la même fenêtre

Pour la commande :

	- [x] permettre la commande d'une même pizza en plusieurs exemplaires => ajout d'une quantité
	- [x] l'adresse doit être facultative dans certains cas
	- [x] le numéro de maison n'est pas entier
	- [x] prévoir un listing de la table pour vérification de l'insertion, des modifications et des suppression

- [x] La suppression ne fonctionne pas (pop-up d'erreur, cast booléen)
- [x] Permettre la modification du contenu de la commande, des pizzas choisies et de leur quantité aussi
- [x] Les recherches doivent absolument mener à une jointure entre 3 tables
- [x] Faire quelques tests unitaires
- [x] Checks dans le script de la BD
- [x] Revoir la classe contenant la connexion et le design pattern singleton

- Revoir l'orthographe
- Refaire un dossier correct


·         Pour Pierre-Henri :

- [x] insertion et modification d'une commande
- [x] recherche : nom, prénom et date de commande (sorties) des clients ayant commandé une pizza donnée (critère d'entrée) entre 2 dates données (critères d'entrées)
- [x] une tâche métier : afficher pour une pizzéria choisie (critère d'entrée) le prix moyen des commandes effectuées entre 2 dates données (critères d'entrée)