# BlackJack

## An electronic card game

Intended usage and more details:
- This application will allow the user to engage in a card game of Blackjack with the computer 
- Like most betting-driven card games, this application will have a simple betting system in place that starts the user
off with a specific amount of virtual currency, which is able to be saved upon closing the application
- This application is intended for casual players who would like to partake in a friendly card game

*Although this application will have no particular practical use, this project will be engaging to create*
*in terms of user interactivity.*

## User Stories
- As a user, I would like to select the amount of currency I would want to bet before the deal begins.
- As a user, I would like to have the option of hitting - adding a card to my hand - so I can increase my playing
hand's collective value
- As a user, I would like to have the option of holding - not receiving a card
- As a user, I would like to have the option of doubling down - doubling my bet whilst receiving a card during the 
round
- As a user, I would like to save my balance to file
- As a user, I would like to have the option of loading my saved balance when the game starts

## Instructions for Grader
- You can generate the first required event by clicking the "hit" button when the game prompts the user to
- You can generate the second required event by clicking the "double down" button when the game prompts the user to
- You can locate my visual component by viewing the icon on the save button
- You can save the state of my application by clicking the "save" button when the game prompts the user to
- You can reload the state of my application by pressing the "load" button when the program starts

## Phase 4: Task 2
- Under the persistence package, the save class has a robust design. The methods, saveBalance and loadBalance have a 
robust design due to the try and catch blocks that take care of the exceptions that the file object 
throws if the PrintWriter and Scanner cannot find the file.

## Phase 4: Task 3
- A lot of duplicate code was found in my UI package so I extracted the duplicate code and refactored it into a separate
method to reduce duplication and coupling. For instance, if the user busts while adding a card to their hand, I would
write the consequent code for that particular method. However, since there are multiple ways to add cards to your hand,
that would mean I would need to write the same consequent code for the same cause. Therefore, to reduce coupling and 
duplication, I extracted that code and refactored it. 
- In the model package, originally, the hand class would have been merged with the deck class. However, this would 
increase coupling as the player and computer's hand would be the same as a disparate "deck". Therefore, to reduce 
coupling, the hand class was created and all methods that was related to specifically the players' hands, would be 
instantiated there.

