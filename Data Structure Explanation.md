The data structures used for this project were ArrayLists and Stacks.

Our team used ArrayLists to store player data for the game as well as the views. The player data includes the player objects themselves, the player's scores, and the cards in the player's hand.
Since in theory a game may have multiple views, the views were also stored by an ArrayList. This made it easy to access a specific player's data through their index.

We used Stacks to store the game deck and all the played cards. This was because both game components used the top card very heavily (ex. drawing a card and the top card of the played cards pile).
The Stacks built in peek (to see the top card of the played cards pile), pop (to draw the top card of the deck) and push (to play a card on top of the played cards pile) methods were very useful for our application.
