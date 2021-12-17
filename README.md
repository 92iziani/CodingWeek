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


## Built With

Le programme utilise les technologies suivantes :

* [Java SE 17](https://www.oracle.com/java/)
* [JavaFX 17](https://openjfx.io/)
* [SQLite](https://www.sqlite.org/)


## Comment lancer l'application ?

On va détailler ...

Comment nous lançons l'application :
1. Ouvir le répertoire "project-grp26" (le répertoire dans lequel ce readme est) dans [Visual Studio Code](https://code.visualstudio.com/).
2. Dans Visual Studio Code, cliquer sur le fichier Boot.java (/ProfRDV/src/Boot.java).
Visual Studio Code charge alors le projet Java.
3. Regarder dans l'onglet "JAVA PROJECT" si les dépendances de javaFX et sqlite sont renseignées:
-Ouvrir le menu déroulant "JAVA PROJECT"
-Ouvrir le menu déroulant "project-grp26"
-Ouvrir le menu déroulant "Referenced Libraries"
Si ce dossier est vide importer (bouton +) dans "Referenced Libraries" les dépendances .jar javaFX et sqlite, disponibles dans le dossier lib, ou téléchargeables ([javaFX](https://gluonhq.com/products/javafx/), [sqlite](http://www.java2s.com/Code/Jar/s/Downloadsqlitejdbc372jar.htm)).
4. Une fois les dépendances chargés, nous appuyons sur le bouton run (► run Java).
5. L'application démarre.

Comment vous pouvez lancer l'application : 
-Via le jar : 
  ```bash
  java --module-path ./lib --add-modules javafx.base,javafx.fxml,javafx.controls,sqlite.jdbc -jar project-grp26.jar
  ```
-De la même manière que nous


## Comment utiliser l'application ?

Pour utiliser l'application :

Nous n'arrivions pas initiallment à exécuter le jar. Nous avons trouvé une commande pour l'exécuter cependant un problème de path rend l'utilisation partielle. Il est cependant parfaitement exécutable via InteliJ en lançant le main /project/src/sample/Main.java

- Page de login
Login pour passer sur le profil étudiant :
id : charoy
pass : azerty
Pour le profil professeur :
Appuyer sur le bouton temporaire prof

- Page profil étudiant :
Pour ajouter un rendez-vous : bouton ajouter
Quitter : bouton quitter
La visualisation des rdv ne marche pas encore

- Page ajouter rdv étudiant :
Des données exemples sont générées
Selectionner Le prof : les horaires et jours disponibles sont mis à jour
Selectionner Le jour : les horaires disponibles sont mis à jour
Selectionner horaires
Motif optionnel
Bouton envoyer ne marche pas encore

- Page profil prof :
Bouton test fenêtre de gauche
Quitter : bouton quitter
La visualisation des rdv ne marche pas encore

<p align="right">(<a href="#top">back to top</a>)</p>
