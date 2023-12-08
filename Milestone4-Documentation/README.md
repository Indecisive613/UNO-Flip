# UNO-Flip (Milestone 4)

## Description
This project simulates a game of UNO-Flip played with cards from the light side and cards from the dark side. In this game, players are trying to be the first to get rid of all the cards in their hand. In turn order, players play cards to the draw pile matching by either color, number, or special action. Players have the capability to view their drawn cards, place cards, draw additional cards, execute special card actions, and observe the resulting state of the cards.

Milestone 1 implemented the basic UNO game using a text based interface. Milestone 2 converted the text-based interface into to a graphical user interface. Milestone 3 intergrates the dark side of the deck and added AI player capabilities. Milestone 4 adds undo/redo, replay, and saving/loading functionality.

## Contents
This project contains the following deliverables:
* Source code
* UNO-Flip executable
* UML sequence diagrams
* UML class diagram

## Installation
This project was compiled with Java 14, so make sure you run it with Java version 14 or ensure your Java version can run Java version 14.

## Usage
Navigate to the project folder in the command prompt then enter:
> java -jar UNO-Flip.jar

## Extensibility
This project was built to keep coupling low and cohesion high using a few different design patterns and techniques, 
which helped in the development of the 4 milestones, and also make it easy to extend the game with new cards, features, and more. 

The cards follow the strategy pattern, where there is a base *Card* class which each card extends and overrides. 
To add a new card, simply create a new class that extends *Card*, and override its *cardAction* method with calls to Game. 

MVC was used to separate UNO Game logic (the models) from GUI components (views and controllers), 
which lowered coupling making it easier to add new features to the game. The *GameView* interface contained many methods that Game
would call when an action was taken, such as *handleDrawCard* or *handleNewTurn*. Every view in the game implements *GameView* 
decoupling it from game and allowing each view to update differently based on actions from *Game*. In the future extra views could
be simply added by having them extend *GameView* and adding a new controller class to interact with the models if user interaction 
is required.

### Implementations of GameView
Below is a list of the views that implement the *GameView* interface, and their function
- GameEndView: Popup on game end game
- HandViewPanel: Display players hand, and contains action buttons
- InfoViewPanel: Display game info
- NewGameView: Popup on new game to select players, names, and AIs
- TableViewPanel: Displays current player, top card, and deck info
- WildView: Popup when a wild card is played

These can be copied and modified to add new views to the game.

## Assumptions
* If the starting card is not a normal card, ignore the effect but can still play on the color/pick any color if it is a wild
* If the first card flipped from the deck is a wild card, the starting player can play a card of any color
* When a player places their last card, their score is displayed, the deck is reshuffled, new hands are dealt, and the game restarts
* If you draw a valid card you may choose to end your turn without playing it
* Choosing player count, player names, and WILD cards colours are mandatory
* You may play WILD cards any time
* Players do not need to say UNO
* A new round begins with player 1
* The max number of players is still 4
* Any number of the players can be AI as long as there is still at least 1 non-AI player
* If a player is playing the last card in their hand, the card's effect will take place before the end of a round
* The game allows a player to play any valid card from their hand after they draw, not just the drawn card
* **NEW:** Restarting the game through the menu will redo the round, not the entire game, to save scores

## Credits
Milestone 1 Completion: October 22, 2023  
Milestone 2 Completion: November 14, 2023  
Milestone 3 Completion: November 26, 2023
Milestone 3 Completion: December 8, 2023

Authors:
* Anand Balaram - 101217776
* Fiona Cheng - 101234672
* Jackie Smolkin-Lerner - 101184457
* Jake Siushansian - 101226956

## Class Diagram


## Sequence Diagrams
### 1. Undo Action
![Milestone 4 Sequence Diagram Undo Action.png](Milestone%204%20Sequence%20Diagram%20Undo%20Action.png)

### 2. Redo Action
![Milestone 4 Sequence Diagram Redo Action.png](Milestone%204%20Sequence%20Diagram%20Redo%20Action.png)

### 3. Save Game
![Milestone 4 Sequence Diagram Save Game.png](Milestone%204%20Sequence%20Diagram%20Save%20Game.png)

### 4. Save Game
![Milestone 4 Sequence Diagram Load Game.png](Milestone%204%20Sequence%20Diagram%20Load%20Game.png)