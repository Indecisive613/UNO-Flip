# UNO-Flip (Milestone 2)

## Description
This project simulates a game of UNO-Flip played solely with cards from the light side. In this game, players are trying to be the first to get rid of all the cards in their hand. In turn order, players play cards to the draw pile matching by either color, number, or special action. Players have the capability to view their drawn cards, place cards, draw additional cards, execute special card actions, and observe the resulting state of the cards. 

Milestone 1 implemented the basic UNO game using a text based interface. Milestone 2 converted the text-based interface into to a graphical user interface. The final milestones will integrate the dark side of the deck and add AI player capabilities.

## Contents
This project contains the following deliverables:
* Source code
* UNO-Flip executable
* UML Sequence diagrams
* UML class diagram
* Design explanation

## Installation
Install Java version 8 or ensure your java version can run java version 8.

## Usage
Navigate to the project folder in the command prompt then enter:
> java -jar UNO-Flip.jar

Pick how many players are playing:
![image](https://github.com/Indecisive613/UNO-Flip/assets/83597131/3282a4cc-d917-41db-884c-f399c9d23ee3)

Enter a unique name for each of the players: 
![image](https://github.com/Indecisive613/UNO-Flip/assets/83597131/0379b218-3e32-485b-a3bf-169f106078b6)

Choose a card from the current player's hand matching the color or symbol of the pile of cards in the middle:
![image](https://github.com/Indecisive613/UNO-Flip/assets/83597131/3afeec97-9364-4597-9596-2d6911e5fc2f)

When you click the card, the drawpile will update immediately. Click the "END TURN" button to progress to the next player's turn:
![image](https://github.com/Indecisive613/UNO-Flip/assets/83597131/371bd822-421d-48b9-9d7d-f5442c3c6e2d)

To draw a card, click the "DRAW +" button:
![image](https://github.com/Indecisive613/UNO-Flip/assets/83597131/5960e5b2-200c-477d-82b5-051fdca41d0f)

When you play a WILD card, you will be prompted to choose a colour:
![image](https://github.com/Indecisive613/UNO-Flip/assets/83597131/9fa43350-d413-4027-9637-984b23781dce)

The direction of play, the current color, and the remaining cards can be seen in a panel at the top of the interface:
![image](https://github.com/Indecisive613/UNO-Flip/assets/83597131/8fab1561-8467-4c1c-aa41-fa16d5c8b8f3)

The current player is shown above the "END TURN" Button, as well as in a magenta highlight above the drawpile:
![image](https://github.com/Indecisive613/UNO-Flip/assets/83597131/c5668ea1-f4b1-4ec4-a9a5-5d29a3219531)

Players do not need to say UNO when they only have 1 card left:
![image](https://github.com/Indecisive613/UNO-Flip/assets/83597131/1191297f-6adb-49aa-bdc4-b06ab9d1421d)

Once a player plays their last card, the round will end and the players scores, which are found at the top of the frame, will be updated:
Someone fix end game!!

## Assumptions
* If the starting card is not a normal card, ignore the effect but can still play on the color/pick any color if it is a wild
* If the first card flipped from the deck is a wild card, the starting player can play a card of any color
* When a player places their last card, their score is displayed, the deck is reshuffled, new hands are dealt, and the game restarts
* If you draw a valid card you may choose to end your turn without playing it
* Choosing player count, player names, and WILD cards colours are mandatory
* You may play WILD cards any time
* Players do not need to say UNO

## Credits
Milestone 1 Completion: October 22, 2023  
Milestone 2 Completion: November 14, 2023  

Authors: 
* Anand Balaram - 101217776
* Fiona Cheng - 101234672
* Jackie Smolkin-Lerner - 101184457
* Jake Siushansian - 101226956

## Class Diagram
TBD

## Sequence Diagrams
### Initialize
TBD

### Start Game
TBD

### Playing Game
TBD
