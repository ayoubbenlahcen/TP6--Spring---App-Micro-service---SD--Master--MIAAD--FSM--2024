# TP6--Spring---App-Micro-service---SD--Master--MIAAD--FSM--2024
L'architecture de microservices décompose les applications en petits services indépendants. En Java, plusieurs technologies facilitent la communication entre ces services :

  - REST : Utilise HTTP pour des interactions simples et stateless.
  - GraphQL : Offre des requêtes flexibles en définissant les données nécessaires.
  - GRPC : Utilise HTTP/2 pour des communications performantes et bidirectionnelles.
  - SOAP : Basé sur XML pour des communications sécurisées et transactionnelles.

Ces technologies offrent une flexibilité accrue, facilitent les mises à jour et permettent une scalabilité indépendante de chaque composant.

###### Remarque :
  - Jusqu'à maintenant, nous avons utilisé Spring MVC classique impératif.
  - Il existe un autre modèle appelé Spring Reactive (Spring WebFlux).
  - Nous verrons par la suite la différence entre eux.
##### Première étape : Créer un nouveau projet Spring
Pour commencer, nous allons créer un nouveau projet Spring. Vous pouvez le faire de deux manières : 
###### Avec Spring Initializr dans Google Chrome :
  - Rendez-vous sur Spring Initializr.
  - Configurez votre projet (choisissez les dépendances, le nom du projet, etc.).
  - Cliquez sur "Generate" pour télécharger le projet.
  - Importez le projet téléchargé dans votre IDE, comme IntelliJ.
###### Avec Start.Spring.Ayou sous IntelliJ :
  - Ouvrez IntelliJ.
  - Allez dans "File" > "New" > "Project".
  - Sélectionnez "Spring Initializr" et configurez votre projet.
  - Cliquez sur "Next" et ajoutez les dépendances nécessaires.
  - Cliquez sur "Finish" pour créer le projet.
###### Remarque : 
  - Il est important de savoir comment créer un projet Spring avec ces deux méthodes.

##### Ensuite, ajoutez les dépendances suivantes à votre projet :
![image](https://github.com/ayoubbenlahcen/TP6--Spring---App-Micro-service---SD--Master--MIAAD--FSM--2024/assets/152870306/f2459d02-cefb-4e9a-835f-0c094cab10f6)























