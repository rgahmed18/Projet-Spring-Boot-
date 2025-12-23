# Devoir N°1 : Architecture Microservices avec Spring Cloud

## 👥 Équipe de développement (Binôme)
   Étudiant 1 : Rguibi Ahmed
   Étudiant 2 : Zeftari Ihssane

## 🏗️ Architecture Technique
Ce projet implémente une architecture e-commerce complète basée sur Spring Cloud :
1. Frontend (React) : Interface utilisateur (Port 3000).
2. API-GATEWAY : Point d'accès unique (Port 8080) avec Circuit Breaker Resilience4j.
3. CONFIG-SERVER : Configuration centralisée via GitHub (Port 8888).
4. EUREKA-SERVER : Serveur de découverte (Port 8761).
5. COMMANDE-SERVICE : Microservice métier avec base H2 (Port 8083).
6. PRODUIT-SERVICE : Microservice métier (Port 8082).

## 🚀 Fonctionnalités Clés Implémentées

### 1. Gestion des Commandes (Étude de cas 1 & 2)
* CRUD complet sur l'entité `Commande` intégrant l'ID produit.
* Propriété personnalisée `mes-config-ms.commandes-last` gérée via Config Server.

### 2. Monitoring & Actuator
 Hot Reload : Modification dynamique de la configuration sans redémarrage via `/actuator/refresh`.
Health Check Custom : Le microservice passe en statut `DOWN` si aucune commande n'est présente en base de données.

### 3. Résilience & Documentation
Circuit Breaker : Mécanisme de fallback activé si le temps de réponse dépasse 3 secondes.
OpenAPI/Swagger : Documentation interactive disponible via la Gateway sur `/swagger-ui.html`.

## 🛠️ Instructions de lancement
1. Lancer **Config-Server**
2. Lancer **Eureka-Server**
3. Lancer les microservices **Produit** et **Commande**
4. Lancer **API-Gateway**
5. Lancer le **Frontend React**

#### Énoncé :  
Etude de cas (1) : 
Ajouter un « microservice-commandes » qui permet de réaliser les opérations CRUD sur une 
« COMMANDE » avec 0 ligne SQL : 
a. La version (1) de la table « COMMANDE » est composée » des colonnes suivantes [id, 
description, quantité, date, montant] 
b. La configuration du « microservice-commandes » doit être gérée au niveau Spring 
Cloud et github 
c. La configuration du « microservice-commandes » contient une propriété 
personnalisée « mes-config-ms.commandes-last » qui permet d’afficher les dernières 
commandes reçues. Dans notre cas : « mes-config-ms.commandes-last  = 10 » 
permet d’afficher les commandes reçues les 10 derniers jours. 
En se basant sur le service Actuator de spring, modifier cette propriété à 20 et 
réaliser un chargement à chaud pour que le « microservice-commandes » affiche les 
les commandes reçues les 20 derniers jours 
d. En se basant sur le service Actuator de spring, Implémenter la supervision la bonne 
santé du « microservice-commandes » : le statut à afficher « UP » 
e. Personnaliser la supervision de la bonne santé du « microservice-commandes » : 
dans notre cas, un « microservice-commandes » est en bonne santé lorsqu’il y’a des 
commandes dans la table « COMMANDE », dans ce cas, le statut est « UP » sinon le 
statut à afficher est « DOWN » 
Etude de cas (2):  
La version (2) de la table « COMMANDE » est composée » des colonnes suivantes [id, 
description, quantité, date, montant, id_produit] 
a. Présenter sur un schéma l’architecture de mise en place de cette application. 
b. Les microservice-commandes et microservice-produit doivent être enregistrés auprès 
d’Eureka 
c. Implémenter une Gateway (Zuul ou API Gateway) comme point d’accès unique à 
l’application. 
d. Implémenter les fonctionnalités CRUD du « microservice-commandes » 
e. Mettre en place le mécanisme de load balancing pour cette application. 
f. Simuler un Timeout d’un des deux microservices, et implémenter un mécanisme de 
de contournement pour protéger le microservice appelant avec Hystrix. 
g. Appliquer OpenAPI et Swagger. 
Livrables:  
a. Démonstration des travaux réalisés. 
b. Code sources des projets Maven (Etude de cas 1 et 2) à déposer sur github avec un 
fichier Readme dont lequel vous présentez la Team de développement et toutes autres 
informations qui vous semblent pertinentes pour la bonne exécution des projets. 
c. Screenshots de l’application (cas 1 et 2)
