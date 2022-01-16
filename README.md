# poker
Poker game using command line input

Game: contains main class, creates cards and deck, players, calls methods in other classes to complete steps of the game

Card: creates card objects with getter methods for rank, suit, and value. Contains method compare values between two cards.

Deck: Creates an array 'Deck' filled with card objects. Contains methods to check if deck isEmpty, deal the next card, and shuffle the deck

Player: Creates player objects with 2 card objects, money, status if player is out of game, name, current hand strength, current bet, if all in, if folded. Contains methods to order hand by value and print cards to console

Rank: Contains methods to search for player hand rank(highcard - royal flush). Searches using both one or two player cards and if community cards contains that hand rank on its own.

Search: contains methods that finds player hand strength at the flop and turn intervals.

Compare: If two or more players have the same hand strength, contains methods that compare the two players hands to determine which is the winner. If players have equal hands, pot is split between the tied players

Move: Contains methods for users move and for opponents move using probabilities based upon current hand strength.

Side: Contains methods to create side pots in the event a player bets all of their money.
