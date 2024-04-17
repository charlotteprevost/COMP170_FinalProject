
/*
* * * * * * * * * * * * * * * Instructions * * * * * * * * * * * * * * *

• You must structure your code into separate functions that show good design and information handling. An individual
    project should have at least 7 functions with good division of work between them, and team projects should likely
    have more.
• Each function must have preceding pseudocode and possibly pre- and post-conditions and use appropriate programming
    style as shown in class. Inline comments are strongly encouraged.
• You need to have a way to run and demonstrate your code. Inputs and outputs must be understandable to others. If your
    program requires the user to understand how to play a game or how to run the program you must not assume that they
    know that, you have to inform them what to do and what is expected.
• You must not embed significant numbers of Strings in your program, but instead read collections of Strings from files.
    For example, a program introduction should be placed in a file that is read and printed instead of its Strings being
    made part of the program source code. Use a while loop as shown in Chapter 5.
• You need to use standard concepts already taught or about to be taught in COMP 170: sequential statements, decision
    logic (if/else), loops (for/while/do-while), boolean logic and variables, methods with parameters and return values,
    object methods (especially String), random numbers, libraries, console I/O via Scanner170 and
    System.out.print/println/printf, arrays to hold multiple values that your program uses, possibly throwing
    exceptions, concepts like sentinels, fencepost algorithms, and cumulative sum/product, etc. You don’t have to use
    all of these, but your program should not be too simple (like no loops, or no String methods). Use as much of Java
    as makes sense.
• You may use multiple programs/classes in your project, and you may use concepts beyond what we teach in the course,
    but you must be able to explain how those work and how you learned about them – do not copy solutions from the web
    or ChatGPT!


* * * * * * * * * * * * * * * Custom Project Proposal Assignment * * * * * * * * * * * * * * *

You must complete the proposal following the outline to get full points. No extra credit opportunity with this
assignment. You will define, create, invent, program, test, and review a java program to do something you pick! You can
use all the java features we have covered to date. What will you do (some possible ideas will be provided in class)?


* * * * * Logistics * * * * *

1. Teamwork: optional, you may be alone or teams of up to 4. Teams must do more work than individuals. If a team you
    will need to define how to divide the work.
2. Progress Reports: may be required during work time on the project. See the schedule in Sakai including the due date
    for the full project.
3. You present and demonstrate your final project to the class.


* * * * * Technical Requirements * * * * *

1. You must structure your code into separate methods or functions that show good design and information handling. An
    individual project should have at about 7 functions with good division of work between them.
2. You may divide your code into separate classes but not required. Separate classes are especially useful when multiple
    people are working together (each person does their part in a separate class and you agree on the functions you
    provide for others to use).
3. Each method must have pseudo code and use appropriate programming style as used in class.
4. You may use Java features we have not yet covered in class if you know them or are ahead. Arrays, text files, and
    Object Oriented Design can be used. However, you can do interesting projects with none of these.
5. You need to have a way to run and demonstrate your code. Inputs and outputs must be understandable to others.


* * * * * Grading Rubric * * * * *

1. This proposal (20 points) – points for each item in the outline (below); minus up to 5 points for any missing section
    or incomplete details.
2. Midpoint deliverables (10 points) – see details in the assignment; minus up to 5 points for any missing section or
    incomplete details. The goal of this is NOT to fill out the outline, but to have some progress to show on your
    project.
3. Project demo, presentation, and code (40 points) – see details on project completion; minus 10 points for no
    presentation materials; minus 20 points for no presentation; minus up to 5 points for missing topics in the
    presentation; you can add other topics as you wish. For project code, review technical requirements in the project
    proposal assignment. Minus a minimum of 10 points for lack of pseudo code / clear design and division of code. Minus
    up to 10 points for poor programming style. Minus up to 10 points for any missing technical requirements.
4. No points for code which does not compile and run in the standard tools used in class (compile and run your code
    often, not just when you are finished).
5. Do NOT COPY your project from materials on the web; to do so is a violation of the course academic integrity policy.
    If you want to use something you find elsewhere or adapt some existing code, ASK AHEAD OF TIME.


* * * * * Structure of Proposal * * * * *

Follow this outline for completing this assignment. Turn in a suitable text file in word, or pdf.
- Project Title (make it interesting)
- Project Definition
- A paragraph or two describing what the app does and why it’s interesting. Some also call this your “elevator pitch” -
    if you happen to bump into someone in the elevator and you have 30 seconds before they get out, how do you sell them
    on your great idea for an app.
*/

//////////////////////////////////////////////////////////////////////////////////////////
/* * * * * * * * * * * * * * * * * * * * * HANGMAN * * * * * * * * * * * * * * * * * * * *

* * * PSEUDOCODE * * *

Preparation:
- WORDS: Three lists of X †words of varying difficulty. Can be manually chosen or imported.
- VISUALS: 
    • User has 6 guesses, each additional wrong guess requires a new "hangman" drawing.
    • "Game Over!" visual for when the user has expired their 6 guesses.
    • "You Won!" visual for when the user has correctly guessed the word without "dying."
- INSTRUCTIONS: Explain the rules of the game to the user and how to play.

Game:
- Welcome user, display instructions, and prompt user for game difficulty:
    1 -> Easy (<6 letters)
    2 -> Medium (6-8 letters)
    3 -> Hard (9+ letters)
- Randomly select word from chosen difficulty list.
- Display to user the number of slots that need to be guessed.
- Prompt user for a character input (case-insensitive).
- Scan each character of the current word,
    • if no characters match the input character, increment the hangman count.
    • if any character matches the input character, fill in the appropriate slots.
- If all slots have been filled, display "You Won!" visual.
- If 6 wrong guesses, display "Game Over!" visual along with the answer.

Optional:
- Prevent repeat words if they have been guessed previously.
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.*;

public class COMP170_FinalProject {

    private static final List<String> wordsList = new ArrayList<>();
    private static int guessesLeft = 6;
    private static int lettersLeftToGuess;
    private static String wordToGuess;
    private static char[] letterGuesses;
    private static String userGuess;


    public static void welcomeUser() {
        System.out.println("Welcome Message: ...\nHow to Play: ...");
    }

    public static int setDifficulty() {
        Scanner console = new Scanner(System.in);

        System.out.print(
                "1 -> Easy\n" +
                "2 -> Medium\n" +
                "3 -> Hard\n" +
                "Please select your difficulty (1, 2, or 3): "
        );

        int difficulty = 0; // Initialize value outside acceptable range

        while (!(difficulty >= 1 && difficulty <= 3)) {
            if (console.hasNextInt()) {
                difficulty = console.nextInt();
                if (!(difficulty >= 1 && difficulty <= 3)) {
                    System.out.print("Invalid input. Please enter a digit (1, 2, or 3): ");
                }
            } else {
                System.out.print("Invalid input. Please enter a digit (1, 2, or 3): ");
                console.next(); // Clear invalid input from scanner
            }
        }

        return difficulty;
    }

    public static void importWordsList(int difficulty)
            throws FileNotFoundException {

        Scanner input;
        // Fetch word list according to difficulty
        switch (difficulty) {
            case 1:
                System.out.println("Difficulty selected: Easy");
                input = new Scanner(new File("./EasyWords.txt"));
                break;
            case 2:
                System.out.println("Difficulty selected: Medium");
                input = new Scanner(new File("./MediumWords.txt"));
                break;
            case 3:
                System.out.println("Difficulty selected: Hard");
                input = new Scanner(new File("./HardWords.txt"));
                break;
            default:
                System.out.println("Error. Default difficulty set to Easy");
                input = new Scanner(new File("./EasyWords.txt"));
                break;
        }
        while (input.hasNextLine()) {
            wordsList.add(input.nextLine());
        }
    }

    // Count the number of unique characters in the String word
    public static int cntDistinct(String str) {
        // Set to store unique characters in the String
        HashSet<Character> s = new HashSet<Character>();
        for(int i = 0; i < str.length(); i++) {
            s.add(str.charAt(i));
        }
        return s.size();
    }

    // Fetch a random word from a set difficulty level.
    // Store the word and show the user how many letter places they must guess
    // Instantiate Character array of previously
    public static void setWord()
            throws FileNotFoundException {
        int difficulty = setDifficulty();
        importWordsList(difficulty);

        // Generate a random index to get a word from imported words list
        Random rand = new Random();
        int index = rand.nextInt(wordsList.size());
        wordToGuess = wordsList.get(index).toUpperCase();

        // Assign initial placeholder characters to user's wordGuess
        userGuess = "_".repeat(wordToGuess.length());
        System.out.println("Debug_Word to guess: "+ wordToGuess);
        // Instantiate char array that will hold previous letter guesses
        letterGuesses = "_".repeat(cntDistinct(wordToGuess)+guessesLeft).toCharArray();
        printLeftToGuess();
    }

    // Print letters left to guess
    // No return, no params
    public static void printLeftToGuess() {
        // CharacterIterator variable to iterate over the current characters of userGuess
        CharacterIterator itr = new StringCharacterIterator(userGuess);
        // while-loop to count remaining letters to guess
        while (itr.current() != CharacterIterator.DONE) {

            if (!Character.isLetter(itr.current())){
                lettersLeftToGuess++;
            }
            // Getting the next input from the user
            // using the next() method
            itr.next();
        }

        String[] leftToGuess = userGuess.split("");
        System.out.println("Word to guess: \t"+String.join(" ", leftToGuess));
    }

    // Add letter guess to letterGuesses
    public static void updateLetterGuesses(char guess) {
        for (int i = 0; i < letterGuesses.length; i++) {
            if (letterGuesses[i] == '_'){
                letterGuesses[i] = guess;
                break;
            }
        }
    }

    // Print letters already guessed
    // Optional: Add color to output (good guess green, bad guess red)
    public static void printLetterGuesses() {
        System.out.print("Previous guesses:\t");
        if (letterGuesses[0] != '_'){
            System.out.print(letterGuesses[0]);
        }
        for (int i = 1; i < letterGuesses.length; i++) {
            if (letterGuesses[i] != '_'){
                System.out.print(", "+letterGuesses[i]);
            } else {
                break;
            }
        }
    }

    // Print an illustration that represents how many guesses the user has left
    // If 0 guesses left, drawings.getDrawings(0)cis called and displays a dead man
    // Note: Drawings.java contains all hangman drawings.
    public static void printDrawing() {
        Drawings drawings = new Drawings();
        System.out.println(drawings.getDrawings(guessesLeft));
    }

    public static void checkGuess(char letter) {
        boolean wordContainsLetter = false;
        for (int i = 0; i < wordToGuess.length(); i++) {
            if (wordToGuess.charAt(i) == letter) {
                StringBuilder updatedGuess = new StringBuilder(userGuess);
                updatedGuess.setCharAt(i, letter);
                userGuess = updatedGuess.toString();
                wordContainsLetter = true;
            }
        }
        if (!wordContainsLetter) {
            guessesLeft--;
        }
    }

    // Prompt user for letter guess
    public static void guessWord() {
        Scanner console = new Scanner(System.in);
        System.out.print("\nYour letter guess: ");
        // Use RegEx matching to ensure we're getting a letter from the user
        while(!console.hasNext("[A-Za-z]")){
            System.out.println("Invalid input, please enter a single letter: ");
            console.next();
        }
        char letterGuess = console.next().toUpperCase().charAt(0);

        updateLetterGuesses(letterGuess);
        checkGuess(letterGuess);
        printDrawing();
        printLeftToGuess();
        printLetterGuesses();
    }

    public static void main(String[] args)
            throws FileNotFoundException {
        welcomeUser();
        setWord();
        // While the user's guess so far is not equal to the word to
        // guess, and while the user still has chances, keep playing
        while (!wordToGuess.contentEquals(userGuess) && guessesLeft != 0){
            guessWord();
        }
    }
}
