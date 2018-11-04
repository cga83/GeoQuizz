# GeoQuiz
Created with [lisagg](http://www.github/lisagg) and [julielelong](http://www.github.com/julielong).

![Alt text](geoquiztitle.png?raw=true "Title")

## Introduction
Geoquiz will help you improve your geography knowledge ! Once you are connected to the server, you will be able to log in (or sign in if you do not have an account yet). Then, once you are logged in, you will be able to see your score, to compare yourself to the other players, or to play a new game. Every game consists of eight randomly chosen questions about French geography or capital cities from the world. When your answer is correct (you have four choices every time), you get one point.

![Alt text](geoquiz1.png?raw=true "Accueil du jeu")
Page d'accueil du jeu

## Code
### User Interface
We used Javafx to design GeoQuiz.
Here is an example of our code (along with the result) :
![Alt text](geoquiz2.png?raw=true "User Interface")
![Alt text](geoquiz3.png?raw=true "Javafx code")

### Open data
The questions (and answers) are randomly chosen from CSV files we downloaded on data.gov.fr and sql.sh.
![Alt text](geoquiz4.png?raw=true "Data")

### Server - players (clients)
Before beginning to play, the user has to connect to the server (the server must be running).
![Alt text](geoquiz5.png?raw=true "Page connexion server")

### Design patterns
The server and the player are both singletons.


