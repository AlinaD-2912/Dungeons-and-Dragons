# 🐉 Dungeons & Dragons – Java Console RPG
Un jeu de rôle textuel inspiré du célèbre jeu Donjons et Dragons, développé en Java. Le jeu se joue dans la console et intègre une mécanique de plateau, des combats au tour par tour, et une sauvegarde automatique des personnages dans une base de données.
 
## Objectif du projet
Reproduire l’esprit du jeu D&D dans un environnement Java console, avec :

  - Une structure orientée objet robuste

  - Un plateau de jeu simulé avec des cases à effets

  - Des interactions en temps réel via clavier

  - Une sauvegarde persistante des personnages créés dans une base de données MySQL
## Pages du site
- **Catalogue** : Affiche une liste dynamique d’objets produits (ex. téléphones, laptops). Chaque produit a un nom, une description, un prix et une quantité. Les objets sont manipulés directement en PHP.
- **Contact** : Formulaire permettant aux visiteurs de contacter l’entreprise. Les données saisies sont enregistrées dans un fichier texte (messages.txt), illustrant la gestion de formulaires en PHP.
- **Panier** : Panier simple utilisant les sessions PHP. L'utilisateur peut ajouter/modifier des produits au panier à partir du catalogue.
  

## Technologies utilisées
- Java 17+
- MySQL / JDBC
- IDE (IntelliJ, Eclipse ou NetBeans)
- Programmation orientée objet
- Console Java pour l’interface utilisateur
  
## Lancer le projet localement
### 1. Cloner le dépôt

```bash
git clone https://github.com/AlinaD-2912/Dungeons-and-Dragons.git
cd Dungeons-and-Dragons
``` 
### 2. Configuration base de données :
```bash
CREATE DATABASE dnd_game;
```
-- Exemple de table pour les personnages :
```bash
CREATE TABLE characters (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255),
  type VARCHAR(50),
  level INT,
  hp INT,
  attack INT
);
```
### 3. Compiler et exécuter :
```bash
javac Main.java
java Main
```
## Exemple de gameplay
   
<pre> ``` 
 --- Main Menu --- 
 1. Create character 
 2. Quit Choose: 
 
 > 1 
 
 Type (Wizard/Warrior): 
 
 > Wizard 
 
 Name: 
 
 > Leon 
 
 Connected to MySQL database! 
 Character saved with ID: 1 
 Disconnected from database. 
 
 --- Character Menu --- 
 1. Show character 
 2. Modify name 
 3. Start game 
 4. Back 
 
 > 3 
 
 Press Enter to roll the dice... 
 
 You rolled: 3 
 Your new position: 3 
 Press Enter to roll the dice... 
 
 You rolled: 1 
 Your new position: 4 
 You encountered a Evil Spirit! 
 
 -------- Fight Started! ----------- ⠀⠀⠀⠀⠀⠀⠀⠀⠀
          ⢀⣴⣿⣿⣿⣦⠀ ⠀⠀⠀⠀
        ⣰⣿⡟⢻⣿⡟⢻⣧ ⠀⠀⠀
      ⣰⣿⣿⣇⣸⣿⣇⣸⣿ ⠀⠀
    ⣴⣿⣿⣿⣿⠟⢻⣿⣿⣿ 
 ⣠⣾⣿⣿⣿⣿⣿⣤⣼⣿⣿⠇ 
 ⢿⡿⢿⣿⣿⣿⣿⣿⣿⣿⡿⠀ ⠀⠀
 ⠈⠿⠿⠋⠙⢿⣿⡿⠁⠀ 
 
 -------- ENEMY ----------- 
 Enemy: Evil Spirit 
 Attack Level: 7 
 Life Level: 15 
 
 -------- YOU ----------- 
 Attack Level: 13 
 Life Level: 3 
 
 --- Choice --- 
 1. Attack 
 2. Flee Choose: 
 ``` </pre>
