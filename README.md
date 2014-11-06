LostPets
========
Projet de concéption d'une application mobile android de gestion d'animaux perdus

Promotion : 5A ESIEA

Groupe : KIRSZ Arthur PORCHERON David

Cette application, que nous avons développe , permet de signaler l’ensemble des animaux perdus
dans le monde.
Le principe est simple, si j’ai pérdu mon animal domestique, je crée une fiche de perte grace à
l’application en donnant l’ensemble des informations me concernant et concernant mon animal.
Cette fiche est sauvegardée sur le web (webservice lostpets) et sera automatiquement supprimée
au bout de 30 jours d’activité.
Si j’ai trouvé un animal pérdu, je peux regarder la liste des animaux perdus ou regarde sur la carte
quels sont les animaux déclarés perdus au tour de ma position.
Lorsque je trouve la fiche de l’animal en question, je peux le prendre en photo pour la partager
soit avec mes contacts soit avec le propriétaire de l’animal
(si celui-ci a renseigne son numéro de téléphone portable).
Enfin, quelques informations utiles sont renseignés dans l’application
comme « qui contacter en cas de parte/ de couverte d’un animal ? ».

Librairies externes :
- Android Support : com.android.support :support-v4 :20.0.0 (Licensed under the Apache License, Version 2.0)
- AsyncHttpRequest : com.loopj.android :android-async-http :1.4.4 (Licensed under the Apache License, Version 2.0)
- Picasso : com.squareup.picasso :picasso :2.3.4 (Licensed under the Apache License, Version 2.0)
- Google Maps et Location : com.google.android.gms :play-services :6.1.11 (Licensed under Apache License, Version 2.0)

Architecture du projet :

La documentation du projet se trouve dans app/doc au format docx et pdf
Les tests unitaires sont dans le répertoire app/src/main/java/fr/esiea/mobile/lostpets/test
Ces tests ne portent que sur la partie modèle sachant que le context ne peut se charger en mode test.

Explications des packages :

- /src/main/java/fr/esiea/mobile/lostpets/activity : contient l'ensemble des activity de l'application
- /src/main/java/fr/esiea/mobile/lostpets/adapter : contient la classe PetAdapter qui permet de gérer
la liste des animaux perdus
- /src/main/java/fr/esiea/mobile/lostpets/dao : contient les classes permettant de gérer les objets
provenant de la base de donnée et du webservice
- /src/main/java/fr/esiea/mobile/lostpets/fragment : contient l'ensemble des fragments de l'application
- /src/main/java/fr/esiea/mobile/lostpets/model : contient l'ensemble des classes représentant les
objets modèles de l'application (Pets, Pet et User)
- /src/main/java/fr/esiea/mobile/lostpets/sql : contient la classe permettant d'interfacer avec la
base de donnée
- /src/main/java/fr/esiea/mobile/lostpets/test : contient l'ensemble des tests des objets modèles Pet
- /src/main/java/fr/esiea/mobile/lostpets/util : contient la classe permettant de gérer la création
des images lors d'une capture photo.
