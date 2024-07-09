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
![Uploading image.png…]()

En suite on a essayé d’ajouter quelque compte dans note base de donnée a partie de faire cereer une fonction qui retourne un objet de type Commande line runner et sans oublier de faire noter cette fonction avec la notation @Bean pour  comme suite  : 

Remarque : 
Dans cette fois je il faut noter que il faut regarder est ce que le Tomcat est bien démarre et dans quel port a été démarré  lorsque on lance  (On démarre) l’application , aussi en peut voir aussi le  temps pour que l’application se démarre tel que dans le cas normal le temps pour qu’un micro service se démarre (le boot d’une application Spring) il va prendre  2, 3  , 4 ou bien 5  secondes et pas plus .
Mais le plus important c’est que il y a un moyen pour créer des applications native  comme GralVm
Qui est une solution de IBM on l’utilise comme une solution universel pour faire démarrer une application  dans un temps rapide   (en générale c’est pour faire concevoir des applications natives) .
Alors jusqu’à maintenant on nous voulons exposions les défirent fonctionnalité de notre micro service pour le monde extérieurs   c’est-à-dire faire connecter notre micro service qui permet au autre front end da faire accéder au micro service 

Alors là on a le choix de faire passer via soit : 
Les Web service basé sur : Soap ,  Wsdl, Rest, GraphQL , GRPC  tout ça c’est en mode synchrone
Alors ces quatre web service sont appelle aussi des connecteurs.

Dans un permeir temps en va commancer par celui quand vousconnaicez c’est  rest : 

 C’est pour cela on va créer un package Web  , dont  laquel on va créer une classe quand on va l’appelé AcountRestController , ici puis ce luis la c’est un web Service RestFul on va utiliser directement l’annotation  @RestController  voici comment on va faire ca :
![image](https://github.com/ayoubbenlahcen/TP6--Spring---App-Micro-service---SD--Master--MIAAD--FSM--2024/assets/152870306/b80c128a-7b73-4165-85e3-24b67f6f1ecb)


Ici et pour le moment on a pas utilisé la couche DTO  Alors ca c’est une mauvaise pratique mais après on va essayer de  l’intègre (je parle sur la couche DTO)

###### Remarque : 
Pour utiliser les services web Rest full il y quelque norme qu’il faut respecter l’un de ces règle et la suivant : 
Si une rest API permer ,apr exemple de faire recoupere  un liste des bankAccounts il faut soicifier "/bankAccounts" comme URL  avec le s  a la fin comme il est montré dans le code au dessue c’est-à-dire faire utiliser:  @GetMapping("/bankAccounts")


######         Remarque : 
Je répète une cors une fois que dans Rest les donne par defaut sont en format Json .
Et faite attentient quand onpose la question :
Est-ce que on obliger d’utiliser Json ? 
La réponse est  Non
Alors par exemple Soap forcement c’est  XML 
Pour Rest c’est ca dépend de client quand le client en vois la requête http il peut spécifier dans  un header qui s’appel accepte  si  accepte est application json le web service il vous retourne json   , par contre si vous mettez dans ce header application XML il vous retourne le résultat ou ---> Alors pour Rest le client qui choisie comment il veut recevoir les donne a partir de web service soit XML si il a spécifier dans le un header (qui s’appel accepte) que il veut application XML  ou bien JSON si il a choisie dans le header  application JSON
--> Mais généralement c’est le format JSON qui est par défaut  utiliser .

Pour GraphQL  lui-même il utlise JSON 
Pour GRPC il utilisee un protocole qui s’appelle : protocole Buffer tel que ce dernier est boucoups plus réduite que JSON  .

--> Alors  c’est vous voulez  vraiment être dans les performances d’échange  de donne le protocole Buffer  utiliser par GRPC c’est plus réduit , mais on verra que souvent GRPC  généralement n’est pas  utiliser pour la partie front .
--> Alors le GRPC est bien et aussi et plus pratique pour communiquer entre les services backend  par contre en frontend en reste toujours sur JSON car ce lui la est intéressant.
Alors afin de faire tester notre RestControlleur et comme en a dit que les resulat retourner par ce dernier il vont etre sous forme  json
En va ajouter une autre Rest API pour faire sauvegarder et un autre pour supprimer et autre pour faire modifier un  bankAccount voici on a fait ca : 

Alors pour faire tester ca voici les défirent testes quand a fait pour notre restController on utlisant l’outil Postman : 
1-	La recouperation de toute les compte : 
![image](https://github.com/ayoubbenlahcen/TP6--Spring---App-Micro-service---SD--Master--MIAAD--FSM--2024/assets/152870306/25b7945b-7bc3-41a8-aac7-bcb86a673fea)

2-	La récupération d’un compte en utilisant le id : 
![image](https://github.com/ayoubbenlahcen/TP6--Spring---App-Micro-service---SD--Master--MIAAD--FSM--2024/assets/152870306/6ba3498b-7f0e-4391-b73b-906017408948)
  
3-	L’ajoute d’un compte dans la base de donne : 
Donc pour faire  ajouter un  compte enutlisant le PostMan au premier lieu il faut specifier : 
    ==> Dans le champs header comment en veut envoyer et aussi recouper les données  comme suite : 
![image](https://github.com/ayoubbenlahcen/TP6--Spring---App-Micro-service---SD--Master--MIAAD--FSM--2024/assets/152870306/4d22a1e7-9bfa-43a6-a996-033fd1e7c183)

--> En suite en va spécifier le cops de la requête c’est-à-dire mettre les données de compte que je veux sauvegarder comme suite dans la partie Body: 
![image](https://github.com/ayoubbenlahcen/TP6--Spring---App-Micro-service---SD--Master--MIAAD--FSM--2024/assets/152870306/d75e08fb-1c44-4ca2-b092-f4d49e4f441b)

Alors comme on vois on a ici les donne de notre compte il sont sous forme Json   
    ==> Alors on rencontrer ici un seul problème c’est on pas traiter les cas ou le id du compte que je veux sauvegarder n’est pas specifier dans le corps de  la requête et ca il ne lance une exception voici cette exception :  
![image](https://github.com/ayoubbenlahcen/TP6--Spring---App-Micro-service---SD--Master--MIAAD--FSM--2024/assets/152870306/6c0d53e4-b7af-4347-9480-798b1be2edfe)

    ==> Alors pour resoudre ce petit probleme on effectuer une petit modification dans notre rest Api « save » afin  de faire généré un id dans le cas ou  le id n’est pas spécifier dans le corps  de la requête voici la modification quand on faire : 
![image](https://github.com/ayoubbenlahcen/TP6--Spring---App-Micro-service---SD--Master--MIAAD--FSM--2024/assets/152870306/fd6194d1-0c36-4ff6-8868-a1cbe78d919a)

    ==> Apres l’ajoute de cette instruction qui est en réalité c’été l’un des trucs de la couche métier on  a trouver le résultat quand on a : 
![image](https://github.com/ayoubbenlahcen/TP6--Spring---App-Micro-service---SD--Master--MIAAD--FSM--2024/assets/152870306/719f13fc-a5ad-4e4b-b28c-22f62a56cc60)
Afin de faire enregistrer le donne ,  notre rest Api  il nous a retourné les informations de nouveau compte enregistre .

4-	Mise a jours d’un compte : 
Par exemple je dois changer les information du compte enregistrer :
![image](https://github.com/ayoubbenlahcen/TP6--Spring---App-Micro-service---SD--Master--MIAAD--FSM--2024/assets/152870306/5ebd22ac-6a8a-4dcd-8ee5-113f53b31a41)
  ==> ca est l’etat  du compte avant ma mise a jours 
  
Apres la modification : 
![image](https://github.com/ayoubbenlahcen/TP6--Spring---App-Micro-service---SD--Master--MIAAD--FSM--2024/assets/152870306/8ec4acba-69ab-4568-b6ba-5c22da53b128)

5-	On peut aussi tester la suppression si en veut

Alors jusqu’à maintenant on peut dire que on a uns idée  c’est quoi  un web service rest full
Et alors mon Rest peux communiquer avec le monde extérieur  via un Rest Api .

Remarque : 
Dans la pratique le rest Api n’est pas documente  alors la meilleur façon pour document les rest Api   en utilisant Spring c’est de faire la documentation swagger  alors pour faire ca on est besoin de faire ajouter une dépendance  qui s’apple sping boot openapi comme suite : 
![image](https://github.com/ayoubbenlahcen/TP6--Spring---App-Micro-service---SD--Master--MIAAD--FSM--2024/assets/152870306/106d8be3-d52b-417c-8769-97427bd153da)

Juste a savoir que  dans cette documentation a comme nom dans la premeir version  1 et 2 swagger et dans la version 3 il a pris comme nom  openapi  . c’est-à-dire la documentation openApi.

Alors si je veux accéder a cette documentation je dois faire dans le URL : 
localhost:8081/swagger-ui/index.html
![image](https://github.com/ayoubbenlahcen/TP6--Spring---App-Micro-service---SD--Master--MIAAD--FSM--2024/assets/152870306/2c7fb779-d560-417d-98be-4f6242f07b36)

Alors voici qu’est-ce que j’ai comme résultat afin de faire taper ça dans URL : 
 
Alors en realite c’est quoi la doccumentation OpenApi ?

Si on on a cliquer sur lien : /v3/api-docs 
On voir comme resulats ca : 
 ![image](https://github.com/ayoubbenlahcen/TP6--Spring---App-Micro-service---SD--Master--MIAAD--FSM--2024/assets/152870306/4d036077-ad94-4926-ad96-e559eb905f99)

Alors comme on vois que une cette page contient un ensemble des données JSON  qui décrit nos rest Api  tel que il contient le path vers  ces rests api aussi le nom de la methode qui va être appeler et aussi les out put …

Alors dans ce document on trouve les déférent int point et qu’il sont les inputs et aussi les outputs pour chaque rest Api .

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























