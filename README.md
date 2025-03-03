# Projet Fullstack

Ce projet a été fait par :\
• ETIENNE Hugo : hugo.etienne4@etu.univ-lorraine.fr | [hetienne1](https://github.com/hetienne1) \
• KAZANTSEV Daniel : daniel.kazantsev1@etu.univ-lorraine.fr | [ash-hsa](https://github.com/ash-hsa)\
• LAHMADI Sonny : sonny.lahmadi1@etu.univ-lorraine.fr | [soni57](https://github.com/soni57)

Il est disponible sur le [github](https://github.com/ash-hsa/projet_fullstack). 

⚠️ **Attention** : Certains de nos commits ont été fait ensemble, si vous souhaitez noter le nombres de commits, certain push fait par **ash-hsa** comportent des co-authors. (Ces pushs ont été fait durant les cours, nous codions en live share)

## Faire fonctionner le projet

Premièrement, vous devez clone le projet : 
```sh
git clone https://github.com/ash-hsa/projet_fullstack.git
cd projet_fullstack
```

Ensuite, vous devez allumer le backend : 
```sh
cd backend
gradle build
```

Une fois que le build est passé et qu'il n'a pas de problème, faites

```sh
gradle run
```


Ensuite, allumer le frontend :
```sh
cd ../frontend
ng serve
```

Le projet est ensuite disponible sur http://localhost:4200/ \
et le backend est sur http://localhost:8080/ 

## Specialité

Pour commencer, vous serez rediriger vers une page de login, des utilisateurs sont créer par defaut dans la base de donnée, dans App.java (les identifiants sont marqué)

Une fois connecté, vous serez rediriger vers la pge d'accueil selon votre role.

### Super Admin
Les supers admins peuvent gérer tous les centres et tous les admins. Pour cela, vous avez une page d'accueil ayant 2 boutons, une pour gérer les admins, l'autres pour gérer les centres.
Dans chacune des pages, vous pouvez supprimer aussi bien les admins et les centres. 

⚠️ **Attention** : Vous ne pouvez pas supprimer un centre ou un admin si il est impliqué dans d'autres tables. Par exemple, vous ne pouvez pas supprimer un centre si il a un rendez vous de prévus dans ce centre.

### Admin
Un admin gère un centre. Il peut donc voir les médecins qui sont dans son centre, en ajouter, et il peut voir aussi les rendez qui sont dans son centre et les annuler.

### User 
Pour un utilisateur qui est un docteur, il sera, une fois connecté, redirigé vers une page d'accueil pour chercher un patient et dire qu'il est vacciné.

Pour un utilisateur classique, il sera redirigé vers une page pour prendre un rendez vous (le rdv sera pris avec le premier medecin de ce patient), voir ses rendez-vous et une page pour modifier ses informations


