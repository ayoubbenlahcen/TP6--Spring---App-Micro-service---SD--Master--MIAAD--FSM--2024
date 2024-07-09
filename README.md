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
##### Après avoir créé le projet Spring, nous allons organiser notre application en créant les packages suivants :
  - Entities : Pour stocker les différentes entités JPA.
  - Enums : Pour les classes énumérées.
  - Repositories : Pour les repositories JPA.
Voici comment notre application va se présenter après la création de ces différents packages :
![image](https://github.com/ayoubbenlahcen/TP6--Spring---App-Micro-service---SD--Master--MIAAD--FSM--2024/assets/152870306/aab0b246-fc84-4ecf-ac09-54c4d9e4bcbc)


***************************************************************************************************
Dans l'architecture, nous avons :
  - Une entité nommée BankAccount dans le package entities.
![image](https://github.com/ayoubbenlahcen/TP6--Spring---App-Micro-service---SD--Master--MIAAD--FSM--2024/assets/152870306/4721ed1f-11c5-49c7-8a5f-c52f3798e8a7)
  - Une classe énumérée AccountType dans le package enums.
  ![image](https://github.com/ayoubbenlahcen/TP6--Spring---App-Micro-service---SD--Master--MIAAD--FSM--2024/assets/152870306/f7697927-5208-4948-a5e3-d4e6bf2d279c)
  - Une interface BankAccountRepository dans le package repositories.
![image](https://github.com/ayoubbenlahcen/TP6--Spring---App-Micro-service---SD--Master--MIAAD--FSM--2024/assets/152870306/5278cb24-1b0d-4ceb-b493-fff198c6a088)

Ensuite, nous avons tenté de configurer notre application dans le fichier application.properties comme suit pour utiliser la base de données H2 :
![image](https://github.com/ayoubbenlahcen/TP6--Spring---App-Micro-service---SD--Master--MIAAD--FSM--2024/assets/152870306/d6607bb2-d0e9-42c8-8d12-af161283409b)

Ensuite, nous avons essayé d'ajouter quelques comptes dans notre base de données en créant une fonction qui retourne un objet de type Commande line runner, et nous n'avons pas oublié d'annoter cette fonction avec @Bean, comme suit : 
![Uploading image.png…]()

Remarque : 
Cette fois-ci, il est important de vérifier si Tomcat démarre correctement et sur quel port il est démarré lorsque nous lançons l'application. On peut également observer le temps nécessaire pour que l'application démarre. Normalement, le démarrage d'un microservice Spring prendra entre 2, 3, 4 ou 5 secondes, mais pas plus.

Le point crucial est qu'il existe des moyens pour créer des applications natives, comme GralVm d'IBM, une solution universelle permettant un démarrage rapide des applications (généralement utilisée pour concevoir des applications natives).

À ce stade, nous voulons exposer les différentes fonctionnalités de notre microservice au monde extérieur, c'est-à-dire permettre à d'autres frontends d'accéder à notre microservice.

Nous avons le choix de passer par les Web services basés sur SOAP, WSDL, REST, GraphQL, et gRPC, tous fonctionnant de manière synchrone. Ces quatre types de Web services sont également appelés connecteurs.


Dans un premier temps, nous allons commencer par celui que vous connaissez : REST

C'est pourquoi nous allons créer un package Web, dans lequel nous allons créer une classe appelée AccountRestController. Ici, cette classe représente un service Web RESTful, et nous allons utiliser directement l'annotation @RestController. Voici comment nous allons procéder :
![image](https://github.com/ayoubbenlahcen/TP6--Spring---App-Micro-service---SD--Master--MIAAD--FSM--2024/assets/152870306/b80c128a-7b73-4165-85e3-24b67f6f1ecb)

Ici, pour le moment, nous n'avons pas utilisé la couche DTO, ce qui est une mauvaise pratique. Cependant, nous prévoyons de l'intégrer ultérieurement dans notre architecture (je parle de la couche DTO).

###### Remarque : 
Pour utiliser les services web RESTful, il y a quelques normes à respecter. L'une de ces règles est la suivante : si une API REST permet par exemple de récupérer une liste de comptes bancaires, il faut spécifier "/bankAccounts" comme URL avec un 's' à la fin, comme illustré dans le code ci-dessus, c'est-à-dire en utilisant @GetMapping("/bankAccounts").


######         Remarque : 
Je répète, une fois de plus, que dans REST, les données par défaut sont au format JSON. Il est important de noter que lorsque l'on pose la question : 'Est-ce qu'on est obligé d'utiliser JSON ?', la réponse est non. Par exemple, pour SOAP, le format est forcément XML.

Pour REST, le format dépend du client. Lorsque le client envoie une requête HTTP, il peut spécifier dans un en-tête appelé 'Accept' le format qu'il préfère recevoir en réponse : s'il spécifie 'application/json', le service web retournera JSON, tandis que s'il spécifie 'application/xml', il recevra XML en réponse.

En général, c'est le format JSON qui est le plus couramment utilisé par défaut.

Pour GraphQL, il utilise lui-même le format JSON. En revanche, pour gRPC, il utilise un protocole appelé Protocol Buffers, qui est beaucoup plus compact que JSON.

Si vous visez des performances d'échange de données optimales, le protocole Buffer utilisé par gRPC est plus réduit. Cependant, il est souvent observé que gRPC n'est généralement pas utilisé pour les interactions avec la partie frontend.

gRPC est particulièrement efficace et pratique pour la communication entre services backend. En revanche, en frontend, JSON reste largement utilisé en raison de sa simplicité et de sa flexibilité.

Afin de tester notre RestController et comme convenu que les résultats retournés seront sous forme JSON, nous avons ajouté plusieurs autres API REST pour effectuer les opérations suivantes : sauvegarder, supprimer et modifier un compte bancaire. Voici comment nous avons procédé :
Pour tester ces fonctionnalités avec notre RestController en utilisant l'outil Postman :
1-  Récupération de tous les comptes : 
![image](https://github.com/ayoubbenlahcen/TP6--Spring---App-Micro-service---SD--Master--MIAAD--FSM--2024/assets/152870306/25b7945b-7bc3-41a8-aac7-bcb86a673fea)

2-	Récupération d'un compte en utilisant l'ID : 
![image](https://github.com/ayoubbenlahcen/TP6--Spring---App-Micro-service---SD--Master--MIAAD--FSM--2024/assets/152870306/6ba3498b-7f0e-4391-b73b-906017408948)
  
3-	Ajout d'un compte dans la base de données : 
Donc, pour ajouter un compte en utilisant Postman, il est d'abord nécessaire de spécifier :
==> Dans le champ "Headers" comment nous voulons envoyer et recevoir les données, comme suit :
![image](https://github.com/ayoubbenlahcen/TP6--Spring---App-Micro-service---SD--Master--MIAAD--FSM--2024/assets/152870306/4d22a1e7-9bfa-43a6-a996-033fd1e7c183)

--> Ensuite, nous spécifierons le corps de la requête, c'est-à-dire nous ajouterons les données du compte que nous voulons sauvegarder dans la partie "Body".
![image](https://github.com/ayoubbenlahcen/TP6--Spring---App-Micro-service---SD--Master--MIAAD--FSM--2024/assets/152870306/d75e08fb-1c44-4ca2-b092-f4d49e4f441b)

Alors comme on peut le voir, les données de notre compte sont ici sous forme JSON.

==> Cependant, nous rencontrons ici un problème : nous ne traitons pas les cas où l'ID du compte que je veux sauvegarder n'est pas spécifié dans le corps de la requête, ce qui entraîne une exception. Voici cette exception :
![image](https://github.com/ayoubbenlahcen/TP6--Spring---App-Micro-service---SD--Master--MIAAD--FSM--2024/assets/152870306/6c0d53e4-b7af-4347-9480-798b1be2edfe)

==> Alors, pour résoudre ce petit problème, nous avons effectué une petite modification dans notre API REST "save" afin de générer un ID dans le cas où l'ID n'est pas spécifié dans le corps de la requête. Voici la modification que nous avons apportée :
![image](https://github.com/ayoubbenlahcen/TP6--Spring---App-Micro-service---SD--Master--MIAAD--FSM--2024/assets/152870306/fd6194d1-0c36-4ff6-8868-a1cbe78d919a)

==> Après l'ajout de cette instruction, qui relève en réalité d'un des aspects de la couche métier, voici les résultats obtenus lorsque nous avons : 
![image](https://github.com/ayoubbenlahcen/TP6--Spring---App-Micro-service---SD--Master--MIAAD--FSM--2024/assets/152870306/719f13fc-a5ad-4e4b-b28c-22f62a56cc60)
Afin d'enregistrer les données, notre API REST nous a retourné les informations du nouveau compte enregistré.

4-	Mise a jours d’un compte : 
Par exemple, pour modifier les informations du compte enregistré :
![image](https://github.com/ayoubbenlahcen/TP6--Spring---App-Micro-service---SD--Master--MIAAD--FSM--2024/assets/152870306/5ebd22ac-6a8a-4dcd-8ee5-113f53b31a41)
  ==> Ca est l’etat  du compte avant ma mise a jours 
  
Apres la modification : 
![image](https://github.com/ayoubbenlahcen/TP6--Spring---App-Micro-service---SD--Master--MIAAD--FSM--2024/assets/152870306/8ec4acba-69ab-4568-b6ba-5c22da53b128)

5-	On peut aussi tester la suppression si nécessaire.

Jusqu'à présent, nous pouvons dire que nous avons une idée de ce qu'est un service web RESTful. Mon REST peut communiquer avec le monde extérieur via une API REST.

Remarque : 
En pratique, la documentation des APIs REST n'est pas toujours documentée. La meilleure façon de documenter les APIs REST avec Spring est d'utiliser Swagger. Pour cela, nous devons ajouter une dépendance appelée spring-boot-starter-<VERSION>-openapi comme suit :
![image](https://github.com/ayoubbenlahcen/TP6--Spring---App-Micro-service---SD--Master--MIAAD--FSM--2024/assets/152870306/106d8be3-d52b-417c-8769-97427bd153da)

Il est bon de savoir que dans les premières versions 1 et 2, la documentation s'appelait Swagger, tandis que dans la version 3, elle est renommée en OpenAPI, c'est-à-dire la documentation OpenAPI.

Alors si je veux accéder a cette documentation je dois faire dans le URL : 
localhost:8081/swagger-ui/index.html
![image](https://github.com/ayoubbenlahcen/TP6--Spring---App-Micro-service---SD--Master--MIAAD--FSM--2024/assets/152870306/2c7fb779-d560-417d-98be-4f6242f07b36)

Pour accéder à la documentation générée par Swagger/OpenAPI dans votre application Spring Boot, vous devez généralement ajouter /swagger-ui.html ou /swagger-ui/index.html à l'URL de votre application. Voici comment cela pourrait ressembler : 
 
Alors en realite c’est quoi la documentation OpenApi ?

Si on on a cliquer sur lien : /v3/api-docs 
On voir comme resulats ca : 
 ![image](https://github.com/ayoubbenlahcen/TP6--Spring---App-Micro-service---SD--Master--MIAAD--FSM--2024/assets/152870306/4d036077-ad94-4926-ad96-e559eb905f99)

Dans cette page, vous trouverez un ensemble de données JSON qui décrit nos APIs REST, y compris le chemin vers ces APIs, le nom des méthodes qui seront appelées, ainsi que les sorties (outputs).

Ce document détaille les différents points d'entrée, les inputs nécessaires, et les outputs pour chaque API REST.

Avec ca on peut tester facilement notre rest .

On peut tester aussi nos rest api  sous Postman ou d’autre  alors
Et comme que rest api (application programming interface) signifier que vous n’êtes pas besoins que d’une interface pour que vous puissiez  communiquer avec nos rest Api

D’où  notre service a comme rôle est de faire générer la documentation qui est en format Json  comme suite : 

      {
          "id": "a71f50a9-70a3-4fca-9cad-393820a4176a",
          "createdAt": "2024-07-08T12:08:39.493+00:00",
          "balance": 97756.37054611369,
          "currency": "USD",
          "type": "SAVING_ACCOUNT"
      }
      
Remarque : 
  - Pour rest on besoin des donne au fomat  -->   JSON /XML selon le choix d’utilisateur
  - Pour Soap        -->  on besoin d’un WSDL
  - Pour GraphQl     -->  on est besoin de schéma
  - Pour GRPC        -->  on est besoin d’un protophile (un fichier qui est on format proto , un fichier texte   qui a une format défirent  c’est pas de JSON)

On rappel un autre  fois quand on a pas encore utiliser la couche DTO.

Remarque : 
Si on veux faire un rest controlleut seulement pour faire lla modification et la suppression et l’ajoute et la recouperation en peux le faire a l’aide de : 
SpringDataRest   
Alors si on veux l’utiliser on est besoin de faire intégrer une dépendance qui est la suivant   :
![image](https://github.com/ayoubbenlahcen/TP6--Spring---App-Micro-service---SD--Master--MIAAD--FSM--2024/assets/152870306/df9dd3d2-10df-46de-bdd6-c7946039fcb3)

Alors après la joute de cette dépendance  Spring va nous  créerons un web service générique
C’est que ce service web il va fonctionner avec n’importe qu’elle entite , 
Finalement  notre controlleur quand on fait on  est aps besoin de le créer .

Alors comment on va faire ca ?
C’est en utilisant SpringDataRest  Alors pour mieux comprendre en va faire utiliser l’annotation 
@RepositoryRestRessource et voici comment on va faire ca : 
![image](https://github.com/ayoubbenlahcen/TP6--Spring---App-Micro-service---SD--Master--MIAAD--FSM--2024/assets/152870306/01017d1d-9589-443f-84d2-4dfd685d45ee)

Alors seulement pour tester notre webservice générer par Spring soit on va ajouter l’annotation @RequestPath(‘’/path’’) de dans @RestController créer par nous même .
Ou bien on peut tous simplement faire supprimer/commenter  l’annotation  @RestController 
Avec le web service générer on peut ajouter/supprimer /modifier/recouperer sans aucun problème voici les défèrent résultat quand on a : 
![image](https://github.com/ayoubbenlahcen/TP6--Spring---App-Micro-service---SD--Master--MIAAD--FSM--2024/assets/152870306/c12b5471-815f-4c32-96f4-b57e45d7da2f)
 
Comme on voix que Spring Data Rest pour la premier fois quand on fait interaction avec l’application tel qua on ne spécifier aucun Url d’une reste API il nous retourne le résultat en haut tel que  il nous afficher des information et aussi des lien pour savoir qu’est ce quand peur faire pour faire accéder et manipuler les données .

Alors en suite on peux consulter toutes les compte comme on va voir maintenant dans image suivant: 
![image](https://github.com/ayoubbenlahcen/TP6--Spring---App-Micro-service---SD--Master--MIAAD--FSM--2024/assets/152870306/708949ff-6e5e-47bc-b15a-5343cab9cc95)

On peux voir clearement que la forme de resultat retourner par le web service generer par Spring Data Rest est défirent de celle retourner par notre web service créer par nous et pour voir ca on clear voici le resultat retourner par notre web service cereer : 
![image](https://github.com/ayoubbenlahcen/TP6--Spring---App-Micro-service---SD--Master--MIAAD--FSM--2024/assets/152870306/abcec4f7-5251-426d-a175-1d5794c39dbe)

Comme on voix que le resultat retourner par Spring Data Rest est un _embedded  par contre celui  retourner par notre Web service crée est une liste des Json .

Alors en peux faire aussi la pagination  en utilisant le Spring Data Rest  comme suite : 
![image](https://github.com/ayoubbenlahcen/TP6--Spring---App-Micro-service---SD--Master--MIAAD--FSM--2024/assets/152870306/ea3ab323-0b98-48dd-9bfb-d5994f216f51)

On peux voir clearement que dans le resultat retourner on a de information sur les la number qui correspond au numero de  la page et aussi le nombre des  comptes  présent dans la basze de donne aussi le nobre de page total quand on a  et aussi  le size de la page .

On peux  voir aussi quand on la possibilité de faire utiliser une fonction de  notre repository  toute en faite l’appel de cette dernier a partir de URL , par exemple faire une recherche comme site : 
![image](https://github.com/ayoubbenlahcen/TP6--Spring---App-Micro-service---SD--Master--MIAAD--FSM--2024/assets/152870306/b0cca1f9-28b9-421d-8693-3c495aa23597)

Voici l’appel de la methode  findByType() a partir de le URL : 
![image](https://github.com/ayoubbenlahcen/TP6--Spring---App-Micro-service---SD--Master--MIAAD--FSM--2024/assets/152870306/0cc74fdd-d70a-4093-b6d0-75ccb6f3a76c)
 
Remarque : 
Vous pouvez remarquer que Spring Data Rest in ne retourne pas les id des enregitrement alors pour cela on a quelque chose qui s’appel les projection c’est-à-dire faire specifier qu’il sont les information que je veux retourner   pour les enregistrements   , 
Donc les projections on va les utiliser comme astuce pour faire  retourne les chose (les informations) quand on veux a partir de la base de donnée   et ces information peut inclue les id des enregistrements  .

Voici comment on va faire ca : 
Créer une interface  quand va l’appel par exemple : AccountProjection
![image](https://github.com/ayoubbenlahcen/TP6--Spring---App-Micro-service---SD--Master--MIAAD--FSM--2024/assets/152870306/1d5aaf8a-8fc5-470e-b787-49e24d3651f7)
 
En suite on va redimarre le serveur 
Puis faire tester la projection : 
![image](https://github.com/ayoubbenlahcen/TP6--Spring---App-Micro-service---SD--Master--MIAAD--FSM--2024/assets/152870306/f353e867-3a5b-46f2-8cb7-efc48a02148e)
 
On peux voir que dans la projection on les ids des compte et aussi le type comme on a spécifier dans l’interface AccountProjection .

Remarque : 
Alors comme on voit que c’est vrai que  le Data Rest il ne donne pas l’accès a tous les attribue mais avec le mécanisme  de projection  on a résoudre ce problème .
Alors le dernier point sur Sprind Data rest c’est que  c’est pas professionnel de faire appele la méthode findByType() par exemple dans le url  par ce nom , alors il est possible de faire : 
utiliser l’annotation @RestResource(path = "/byType") par exemple  et aussi en peut spécifier l’annotation @Parem("t") ,  voici comment on va faire ca :
![image](https://github.com/ayoubbenlahcen/TP6--Spring---App-Micro-service---SD--Master--MIAAD--FSM--2024/assets/152870306/ed059cf0-e1ff-4b19-884b-7f7d3a80fb88)
 
Comme on peut faire la projection sur ces resulta pour obtenier par exemple  selment le id et le type comme suite : 
![image](https://github.com/ayoubbenlahcen/TP6--Spring---App-Micro-service---SD--Master--MIAAD--FSM--2024/assets/152870306/32957da4-f3c3-4e35-bafe-ca91fbfe8461)

Alors jusqu’à maintenant on a pas respecter les normes  alors pour etre dans les normes  il faut utliser les DTO et il faut utiliser la couche service alors pour ce la on va créer  la couche service   et aussi la couche DTO  une un autre couche mapper (pour faire les conversion d’un objet vers un autre).























