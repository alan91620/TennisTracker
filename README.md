# TennisTracker

Vincent ETHEVE, Augustin LOLLIVIER, Alan MARTHINEAU

1- Listing des fonctionnalités
Les fonctionnalités suivantes ont été implémentés :
•	Récupération des coordonnés GPS avec affichage sur la carte ainsi que reverse Geocoding qui permet de récupérer l’adresse du match a laquelle on se trouve. (Intent implicite avec google Map
•	Prise d’une photo du match et de la tête des joueurs (Intent implicite avec l’appareil photo du téléphone.
•	Gestion du match en temps réel avec score du match, Jeu en cours, sets précédents. (Match en 3 sets gagnant.
•	Barre menu en bas de l’écran qui utilise les fragments.
•	Historique des 5 derniers matchs ace SQLlite
2- Architecture de l’application
Les sources de notre programme sont divisées en 2 dossiers : Le model et le controller. Le dossier model contient les classes qui servent à former les projets, il y en a trois : l’objet TennisGame, l’objet Player et l’objet HistoryMatch. L’objet TennisGame sert à créer un match il reçoit en paramètre deux objets joueurs. L’objet HistoryMatch sert à reconstruire un objet match à partir de la bdd SQLlite.
Le dossier Controller contient toutes les classes gérant les interactions entre le model et la view (ici les layouts). Ce dossier continent :
•	HistoriqueFrag : Gestion de l’historique
•	MenuFrag : Gestion du Menu Principal
•	ProgressFrag
•	SplashScreen : Ecran de démarrage
•	NewMatch : 
	LocationMatch : Récupération des coordonnées, affiche et récupération de l’adresse depuis les coordonnées.
	PhotoMatch : Prise de la photo du match.
	PlayerSelect : Renseignement des infos des joueurs, prise de leurs photos et création des objets Players. 
	SocreBoard2 : Création, gestion et affichage des scores du match en cours.
•	MainActivity : Activité principale.

3 – Améliorations possibles
Pour améliorer notre application nous aurions pu rendre cliquable chaque match de notre historique afin de pouvoir recharger toutes les informations du match sélectionné comme la localisation et la photo par exemple.


