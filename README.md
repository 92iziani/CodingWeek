<div id="top"></div>

<br />
<div align="center">
  <a href="https://gitlab.telecomnancy.univ-lorraine.fr/codingweek2k21/project-grp26">
    <img src="ProfRDV/src/photo/logo.png" alt="Logo" width="300" height="200">
  </a>
</div>


## À propos

Dépôt du groupe 26 de le Coding Week - TELECOM Nancy - Lundi 13 Décembre ~ Vendredi 17 Décembre 2021

Les encadrants :
* Mme Martine GAUTIER

Les contributeurs :
* Kamal ALLOUCHE (Groupe 1)
* Michael ENESCU (Groupe 2)
* Axel GOARANT (Groupe 2)
* Ahmed ZIANI (Groupe 2)

## Vidéo de démonstration

Cette vidéo non répertoriée sur Youtube présente les fonctionnalités de notre application : 

[https://www.youtube.com/watch?v=12XWNQvzRRU](https://www.youtube.com/watch?v=12XWNQvzRRU)


## Built With

Le programme utilise les technologies suivantes :

* [Java SE 17](https://www.oracle.com/java/)
* [JavaFX 17](https://openjfx.io/)
* [SQLite](https://www.sqlite.org/)


## Comment lancer l'application ?

Comment nous lançons l'application :

1. Ouvir le répertoire "project-grp26" (le répertoire dans lequel ce readme est) dans [Visual Studio Code](https://code.visualstudio.com/).

2. Dans Visual Studio Code, cliquer sur le fichier Boot.java (/ProfRDV/src/Boot.java).
Visual Studio Code charge alors le projet Java.

3. Regarder dans l'onglet "JAVA PROJECT" si les dépendances de javaFX et sqlite sont renseignées:
-Ouvrir le menu déroulant "JAVA PROJECT"
-Ouvrir le menu déroulant "project-grp26"
-Ouvrir le menu déroulant "Referenced Libraries"
Si ce dossier est vide importer (bouton +) dans "Referenced Libraries" les dépendances .jar javaFX et sqlite, disponibles dans le dossier lib, ou téléchargeables ([javaFX](https://gluonhq.com/products/javafx/), [sqlite](http://www.java2s.com/Code/Jar/s/Downloadsqlitejdbc372jar.htm)).

4. Modifier dans le fichier "launch.json" les lignes :
```bash
  "vmArgs": " --module-path %path_to_javafx/lib% --add-modules javafx.controls,javafx.fxml",
  ```
En remplaçant "%path_to_javafx/lib%" par le chemin vers le dossier où javaFX est installé.

5. Une fois les dépendances chargés, nous appuyons sur le bouton run (► run Java) pour lancer Boot.java.

6. Il se peut que celà ne marche pas. Si celà est le cas, vérifier le fichier "launch.json". Si une nouvelle configuration est apparue, rajouter la commande :
```bash
  "vmArgs": " --module-path %path_to_javafx/lib% --add-modules javafx.controls,javafx.fxml",
  ```
  au dessus de la ligne commençant pas "mainClass". Reappuyer sur run.

7. L'application démarre.
Comment vous pouvez lancer l'application : 
-Via le jar : 
  ```bash
  java --module-path ./lib --add-modules javafx.base,javafx.fxml,javafx.controls,sqlite.jdbc -jar project-grp26.jar
  ```
-De la même manière que nous

8. 
**NOTE IMPORTANTE AUX ENCADRANTS** :
La compilation du .jar connaît une erreur qu'on a pas pu régler. (QuantumRenderer et No tool kit found !)
**REMARQUE** : Une compilation sur IntelliJ avec Java SE 8 (sdk 1.8) marche directement.


## Fonctionnalités principales de l'application

Pour utiliser l'application :

Nous n'arrivions pas initiallment à exécuter le jar. Nous avons trouvé une commande pour l'exécuter cependant un problème de path rend l'utilisation partielle. Il est cependant parfaitement exécutable via IntelliJ (en utilisation la version 1.8 de Java) en lançant le main /project/src/sample/Main.java

- **Se connecter en tant qu'administrateur** :
* Login : root
* Mot de passe : root
* L'admin pourra donc ajouter, modifier et supprimer un élève ou un professeur.

- **Se connecter en tant qu'étudiant** :
* Login : allouch21u (par exemple)
* Mot de passe : password
* L'étudiant peut afficher ses rendez-vous confirmés et ses rendez-vous en attente, il peut également faire une demande de rendez-vous avec un professeur selon ses disponibilités.

- **Se connecter en tant que professeur** :
* Login : oster21u (par exemple)
* Mot de passe : password
* Le professeur peut annuler les rendez-vous qui'il a confirmé auparavant, accepter ou refuser les nouvelles demande de rendez-vous qui a reçu (rendez-vous en attente), et il peut également définir ses disponibilités/indisponibilités habituelles et inhabituelles.

<p align="right">(<a href="#top">back to top</a>)</p>
