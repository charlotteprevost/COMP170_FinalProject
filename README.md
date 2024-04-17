COMP 170-005 – Spring 2024 – Final Project

<div align="center">

# $${\color{darkred}The\ Training\ Gibbet}$$

$${\color{grey}Alena\ Eshaya\ and\ Charlotte\ Prevost}$$	

</div>

---
<div align="center">
	
# $${\color{teal}I.\ Project\ Proposal}$$

</div>

## Definition
Our Training Gibbet is designed to hone a user’s Hangman word guessing skills. The user starts by selecting their difficulty (easy, medium, hard), then a word is randomly chosen by our code. One letter at a time, the user enters their guess.
- If they guess correctly, their screen displays the letters where they belong in the word. 
- If the user completes the computer’s word before their six attempts run out, they win.
- If they guess incorrectly, then their remaining guesses appear on screen through illustrations.
- If their six guesses are up, they lose this round.

The user is then able to play or try again.

### _Traditional Hangman Rules_
Hangman is a word based guessing game typically played by two people, a drawer and a guesser. The rules are as follows:\
The drawer draws a gibbet (a hanging structure).\
The drawer thinks of a word, then next to the gibbet draws an underscore for each letter in the word.\
_Ex_: “Drawer: `CHICKEN`” → “Drawer: `_ _ _ _ _ _ _`”
  
The guesser must guess, a single letter at a time, whether a letter is included in the word or not.\
**Note**: The guesser is authorized to write down the letter to keep track of previous guesses.\
If a guess is correct, the drawer writes in the letter in the places where the letter belongs.\
_Ex cont’d_: “Guesser: `C`” → “Drawer: `C _ _ C _ _ _`”).

&rarr; If the guesser guesses all letters before their guesses expire, they win, and it is their turn to choose a word for the other person to guess.\
&rarr; If a guess is wrong, the drawer progressively draws a stick figure hanging from the gibbet (6 guesses: a head, a torso, four limbs).\
&rarr; If the guesser expires all their guesses and the drawer has drawn a full stick figure, they lose. The drawer may extend the gibbet and choose another word.


## Team

|              | Alena Eshaya | Charlotte Prevost |
| :----------: | :---         | :---              |
| Planning / Coordination / Management | <ul><li>Scheduling meetings and rooms</li><li>Scheduling time frame of deliverables</li><li>Documenting progress</li><li>Choosing, creating, stylizing final deliverable slides</li></ul>|<ul><li>Editing of deliverables with clarity, coherence, and professionalism in mind</li><li>Suggesting suitable task repartition in concordance with each team member’s skill set and schedule</li></ul>|
| Code |<ul><li>Creating the Class file of accessible visuals & Creating all visuals</li><li>Creating three text files of words each of varying difficulty</li><li>Handling logic of importing files as lists</li><li>Handling random word selection</li></ul>|<ul><li>Drafting pseudo-code</li><li>Structuring of code (logic flow, method selection, refactoring)</li><li>Handling IO logic (user guess input and computer response/output logic)</ul></li>|


## Design

### Key Components
- Three text files, each containing a list of words of varying difficulty in the same directory as the project file:\
“EasyWords.txt”, “MediumWords.txt”, “HardWords.txt”.
- A variety of display visuals: a start visual, hangman visuals, a win visual, a game over visual, and a try/play again visual. We plan to incorporate stylization and colors into text. For example, using “ASCII art” for the drawings, using green hues when the user wins, or red hues when the user loses. These visuals will need to be stored in a “Drawings.java” file in the same directory as the project.
	- Six hangman drawings (one per wrong guess)
	- Optional: Elaborate "Game Over" visual
	- "You Won!" visual for when the user has correctly guessed the word without "dying"
	- Optional: “Try again?” / “Play again?” visuals
	- Time-permitting, visuals will be colorful and stylized.


### Input/Output 
- User inputs a single digit (1, 2, or 3) for difficulty selection
- Computer outputs explanations and prompts user for input
- Computer outputs an illustration and letters left to guess in the word (_Optional_: display previous attempted guesses of letters)
- User inputs a single letter guess until they win or lose


### Some Example Functions
`displayInstructions()`\
`get/setDifficulty()` (re-prompts until user inputs a valid digit)\
`importWords()`\
`setWordToGuess()`\
`displayHangmanDrawing()`\
`displayLettersLeft()`\
`userGuess()` (re-prompt until valid user input)\
`playTryAgain()`\
_Optional_: `displayPreviousGuesses()` to show user previous letter guess attempts\
_Optional_: `checkDuplicate()` to prevent duplicate word guess selection in a single session 


### NOTE
&rarr; A Java console access is required to play.\
&rarr; Scanner input validation of the game must be solid.\
&rarr; Player must ensure all required files are present in their game directory.\


### Interface Draft
Using `printf()`, hopefully something like this (example below for 3 guesses left):
```
			                 ____________
		3 guesses left!   	|           |
		               		|       (.  _  .)
		           		|        —|...|
		                	|      /  |...|
		                	|     O   |...|
		                	|
		                	|
		     	      	     ___|___

	Attempted letters: R, C, T, M		Word to guess:	 C _ _ C _ _ _

Your letter guess: [awaiting user input]
```

### Further Research Needed
- Using `printf()` can be tricky
- Choosing best type of object to store a list of imported words
- Sanitizing multiple types of user inputs without being redundant


## Testing
Errors may arise in multiple cases: when importing and consuming tokens contained in a text file, when processing user input, when outputting a user’s guess result, or when outputting visuals to the user. Additional errors are sure to arise.
- **File access**: An Exception should be thrown if any required files cannot be accessed.
- **Difficulty level**: The game will have three difficulty levels (Easy →  1, Medium → 2, Hard → 3), the user will at first be prompted to input the corresponding digit. If the user is to input anything other than 1, 2, or 3, our program will keep prompting the user until they have input a valid digit.
- **Letter guess**: Once the game has started and the user is prompted to guess a letter, if the user inputs anything but a single letter, they will be prompted to do so until they do.


Ultimately, handling the Scanner will be paramount. For edge cases that we have not thought of, we will request help from test-users such as our TA Erik, as well as friends to play the game and help us catch any bugs we’ve missed.

## Schedule / Progression Guide

||**GOAL**|
|:---  |:---                    |
| Week 10 | Proposal Done and Turned In. (Group Meeting at 1:30 WED) |
| Week 11 | Drawing of the Hangman and Checking user inputs. |
| Week 12 | Strings of difficulties. (Group Meeting at 1:30 WED) &rarr; Create lists of easy, medium, and hard words in 3 separate files. |
| Week 13 | Start screen, Game Over screen, Win! screen |
| Week 14 | Testing logic and one last meeting. (Final Group Meeting 1:30 WED) |
| Week 14 | Create a Google Slide for the Project. |
| Week 15 | Presentation. |

---

<div align="center">

# $${\color{teal}II.\ Midpoint\ Deliverables}$$

</div>

## Requirement Updates
Still considering:
- Multiple ways to import and store word lists
- Multiple ways to manipulate an evolving userGuess String


## Program Structure
Please refer to the [COMP170_FinalProject.java](https://github.com/charlotteprevost/COMP170_FinalProject/blob/main/COMP170_FinalProject.java) file

## Key Data
Please refer to the [COMP170_FinalProject.java](https://github.com/charlotteprevost/COMP170_FinalProject/blob/main/COMP170_FinalProject.java) file

## Issues / Successes
_"Question or problems you are facing right?"_
 
---
---
<div align="center">
	
# $${\color{teal}Debugging\ Log}$$

_Keep track of feedback and bugs reported by test-users here._

</div>


| Date (YYYY-MM-DD) | Report (Who and What) | Notes/Solutions (Problem Solving Procedure)| 
|:---               | :---                  |:---                                        |
|  |  |  |
|  |  |  |
|  |  |  |
|  |  |  |
|  |  |  |
