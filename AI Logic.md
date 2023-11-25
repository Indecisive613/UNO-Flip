AI Player Implementation

Terminology Clarifications:
- AI in this context refers to the attributes, methods, conditional statements, and algorithms that enables the UNO game
to perform actions on behalf of a specific player without requiring user input.
- Valid in the context refers to actions that are permitted as per the rules of UNO.

AI Implementation - Actions and States
When the user starts a new UNO game, they are prompted to select the number of players and input a name for each player.
The user is then prompted to select which of the players will be an AI player.

In this context, an AI player is a player whose actions in an UNO game including: 
- Play card, 
- Draw card, 
- Choose new game colour

are determined through the AiHelper class method using the current state of the UNO game including:
- Current top card,
- Current game colour, 
- Current game symbol,
- Current player hand

The methods in the Game model class use conditional statements to determine whether the current player is an AI player
and calls the AiHelper methods 

AI Implementation - Methods:
There are two principle AiHelper methods that automate two important game actions:
Select a valid card to play on the current top card
Select an appropriate game colour after playing a wild card

To select a valid card to play on the current top card, the AI will determine whether the hand of the current player
contains any valid cards that are not wild cards. 
- If there are valid cards that are not wild cards, the AI will then identify and play the highest valued valid card.
Value refers to the point value assigned to the card. This strategy will ensure that the other players will not have as
high a score.
- If there are no valid cards but there is at least one wild card, the AI will then play the first wild card in the hand.
The AI will choose the new game colour by identifying the colour that appears the most frequently with respect to the cards
in the current hand. 
- If there are no valid cards, wild or otherwise, then the AI will draw a card from the deck. If this drawn card is a
valid card, then the AI will play this card.
