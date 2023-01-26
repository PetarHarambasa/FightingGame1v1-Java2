# FightingGame1v1-Java2

Java FX application/game that is based on a fighting game 1v1 where players take turns and pick moves to deal damage to another player until someone has 0 health points.

You can choose between multiplayer and single-player.

In singleplayer you choose your name and class for you and another player. Once you choose a class and name you begin to take turns and pick moves. 
Moves are randomly added to your character every turn and also you can't play the same move you played the last turn. Damage is also random so you can't win all the 
time and every class has its damage scale as well as health points. You can also save the game in the middle of the fight, which is made by serializing player class into
a file and you can play that saved game by reopening the game and clicking on the button "Load save game". After someone has 0 health points game is going to display a message with
the victory player and take you to the screen where you can choose to play replay to see every move that was played in the game by both players. Or you can choose to play
another game with the same class and same player to seek a rematch🐱‍👤. Also, you can choose to go to a new scene where it is displayed all moves by all games.

In multiplayer you have all functionalities like in singleplayer but in this case, you have to start 2 instances of the same project as well as a server so that you 
can simulate multiplayer. You also have to start the RMI server because there is functionality for chatting between 2 instances. How does that work? The server 
is listening on a port and once instances are started they connect to the port of the server. Once they are connected to the server the game can start and functionalities
are the same and the business logic of the game is the same.
