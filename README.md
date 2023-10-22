# UNO-Flip

## Description
This project simulates a game of UNO-Flip played solely with cards from the light side. In this game, players are trying to be the first to get rid of all the cards in their hand. In turn order, players play cards to the draw pile matching by either color, number, or special action. Players have the capability to view their drawn cards, place cards, draw additional cards, execute special card actions, and observe the resulting state of the cards presented in text format. 

Milestone 2 will incorporate a feature letting players say UNO when they have one card. It will also convert the current text-based format to a graphical user interface. The final milestones will integrate the dark side of the deck and add AI player capabilities.

## Contents
This project contains the following deliverables:
* Source code
* UNO-Flip executable
* Sequence diagrams
* UML diagram
* Data structure explanation

## Installation
Install Java version 8 or ensure your java version can run java version 8.

## Usage
Navigate to the project folder in the command prompt then enter:
> java -jar UNO-Flip.jar

Follow the instructions in the command prompt to continue playing the UNO game.

## Assumptions
* If the starting card is not a normal card, ignore the effect but can still play on the color/pick any color if it is a wild
* If the first card flipped from the deck is a wild card, the starting player can play a card of any color
* When a player places their last card, their score is displayed, the deck is reshuffled, new hands are dealt, and the game restarts
* Word-based user input (ie. the colours) are case insensitive

## Credits
Milestone 1 Completion: October 22, 2023
Authors: 
* Anand Balaram
* Fiona Cheng - 101234672
* Jackie Smolkin-Lerner - 101184457
* Jake Siushansian - 101226956
