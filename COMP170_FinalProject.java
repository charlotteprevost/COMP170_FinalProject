/**
 * * * * * * * * * * * * * * * * * * * * * * HANGMAN * * * * * * * * * * * * * * * * * * * *
 * Pre-Requisites:
 * - Text files containing lists of words in the current directory. The file names must end
 *      with "words.txt" and each file must contain a single word per line.
 * - Visual outputs must be contained in the "Drawings.class" file. Visuals must include:
 *      • 6 illustrations for 6 guesses
 *      • "Game Over" illustration (can be grouped with 0 guesses left illustration)
 *      • "You Won!" illustration
 * Game:
 *  - Welcome user, display instructions
 *  - Prompt user for text file selection.
 *  - Randomly select word from chosen text file.
 *  - Display to user the number of letter slots that need to be guessed.
 *  - Prompt user for a character input (case-insensitive).
 *  - Scan each character of the current word,
 *      • if no characters match the input character, increment the hangman count.
 *      • if any character matches the input character, fill in the appropriate slots.
 *  - If all slots have been filled before guesses expire, display "You Won!" visual.
 *  - If 6 wrong guesses, display "Game Over!" visual along with the answer.
 *  - Prompt user to play again or quit.
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class COMP170_FinalProject {

    private static final List<String> fileNames = new ArrayList<>();    // Names of the files containing guess words
    private static final List<String> wordsList = new ArrayList<>();    // Imported guess words
    private static List<Character> wordToGuess = new ArrayList<>();     // Randomly selected word to guess
    private static List<Character> letterGuesses = new ArrayList<>();   // Previous letter guesses
    private static List<Character> userGuess = new ArrayList<>();       // Slots the user has guessed so far
    private static int guessesLeft = 6;

    /** Welcome message and Instructions **/
    public static void welcomeUser() {
        System.out.print("""
                
                \t#########################################
                \t##          Hello and Welcome!         ##
                \t#########################################
                
                This mini-game is designed to hone your Hangman skills.
                
                Instructions:
                 - Select word file (word will be randomly chosen from selected file)
                 - Letters of the word you need to guess are denoted as space-separated underscores
                 - Enter your letter guesses as prompted
                 - If your 6 tries expire before you guess the whole word, you lose!
                 
                """);
    }

    /**
     * Store the names of all "words.txt" files situated in the current directory.
     * NOTE: Additional text files may be added by the user:
     *      IMPORTANT:
     *      - Text files MUST contain one word per line.
     *      - Text files MUST be located in current directory.
     *      - Text files MUST end with "words.txt" (case-insensitive)
     */
    public static void setUpWordFiles() {
        File currentDir = new File(".");  // Current directory
        File[] files = currentDir.listFiles();     // List all files in the CD
        if (files != null) {                       // If there are files in the CD
            for (File file : files) {              // Check for files that end with "words.txt"
                if (file.isFile() && file.getName().toLowerCase().endsWith("words.txt")) {
                    fileNames.add(file.getName()); // Store files that end with "words.txt"
                }
            }
        }
        System.out.println("Available word files: "+fileNames+"\n");
    }

    /**
     * Display all detected "*words.txt" files and prompt for selection
     */
    public static void printFileSelection(){
        System.out.println("Please select your word file: ");
        for (int i = 0; i < fileNames.size(); i++){
            System.out.println("\tEnter "+i+" to select \""+ fileNames.get(i) +"\"");
        }
        System.out.print("\n==> ");
    }

    /**
     * Select file containing list of words
     *  - Set up the fileNames ArrayList of Strings containing the paths to the files in current directory
     *  - Display file options and how to select a file
     *  - Forever loop until user inputs a valid file option
     * @return an integer representing the index of the selected file in the fileNames array
     */
    public static int chooseFile() {
        printFileSelection();
        Scanner console = new Scanner(System.in);
        while (true) {
            String input = console.nextLine().trim();
            if (input.matches("^\\d+$")) { // Check if input is a positive integer
                int fileIndex = Integer.parseInt(input);
                if (fileIndex >= 0 && fileIndex < fileNames.size()) {
                    System.out.println("File selected:\t\t\""+ fileNames.get(fileIndex) +"\"\n");
                    return fileIndex; // Valid file index input, exit loop
                }
            }
            System.out.print("Invalid input.\nPlease enter a valid file number as listed above: ");
        }
    }

    /**
     * Import words from a text file and store then into an ArrayList of Strings:
     * @param fileIndex - The index place of the selected file in the fileNames ArrayList
     * @throws FileNotFoundException - For good practice
     */
    public static void importWordsList(int fileIndex)
            throws FileNotFoundException {
        Scanner input = new Scanner(new File("./"+fileNames.get(fileIndex)));
        while (input.hasNextLine()) {
            wordsList.add(input.nextLine());
        }
    }

    /**
     * Prompt for difficulty and fetch a random word from the appropriate word list.
     *  - Set difficulty
     *  - Fetch word list based on difficulty
     *  - Store random word from list
     *  - Show user how many letter places they must guess
     */
    public static void setWord() {
        Random rand = new Random();
        int index = rand.nextInt(wordsList.size());         // Generate a random index within the file's line range
        String word = wordsList.get(index).toUpperCase();   // Get a word from imported words list

        for (int i = 0; i < word.length(); i++) {
            wordToGuess.add(word.charAt(i));    // Store word's Characters in an ArrayList
            userGuess.add('_');                 // Placeholder underscores for hidden letters
        }

        System.out.printf("""
                [==========================================================]
                [   Word to guess:\t%1$s                                   ]
                [==========================================================]
                """, userGuess);
    }

    /**
     * Print hangman illustration
     * A different image is displayed based on the number of guesses the user has left.
     * If 0 guesses are left, drawings.getDrawings(0) is called and a full hangman is displayed.
     * NOTE: All illustrations are in the Drawings.java file
     */
    public static void printDrawing() {
        Drawings drawings = new Drawings();
        System.out.println(drawings.getDrawings(guessesLeft));
    }

    /**
     * Check whether the letter guessed by the user is in the hidden word:
     * - If it is, assign the characters to the slots where they belong.
     * - If it isn't, decrement available guesses.
     * @param letter - a single character guess inputted by the player
     */
    public static void checkGuess(char letter) {
        boolean wordContainsLetter = false;     // Sentinel boolean
        for (int i = 0; i < wordToGuess.size(); i++) {
            if (wordToGuess.get(i) == letter) { // If letter guess matches a character of the hidden word
                userGuess.set(i, letter);       // Assign that character to the slot index where it belongs
                wordContainsLetter = true;      // Success, the letter guessed is in the word
            }
        }
        if (!wordContainsLetter) {  // Letter was not in the word
            guessesLeft--;          // One less chance left
        }
    }

    /**
     * Prompt user for letter guess:
     * - Loop until user enters valid input using RegEx
     * - Store the user's letter guess in an ArrayList of Characters
     * - Check the letter guess by calling checkGuess(letter)
     * - Print an illustration based on guesses left
     * - Print the letters left to guess
     * - Print all letters previously guessed (right or wrong)
     */
    public static void guessWord() {
        Scanner console = new Scanner(System.in);
        System.out.print("\n\tYour letter guess ==> ");
        // Use RegEx matching to ensure we're getting one letter from the user
        while(!console.hasNext("[A-Za-z]")){
            System.out.print("Invalid input, please enter a single letter: ");
            console.next();
        }
        char letterGuess = console.next().toUpperCase().charAt(0);  // Only grab the very first character
        letterGuesses.add(letterGuess); // Update list of previous guesses (whether right or wrong)
        checkGuess(letterGuess);
        printDrawing();
        System.out.print("Word to guess:\t\t"+userGuess.toString());
        System.out.print("\nPrevious letter guesses:\t"+letterGuesses.toString());
    }

    /**
     * Print end message
     * - If user has guessed the whole word and has guesses left, print won illustration
     * - If user has expired their guesses before completing the word, print lost illustration
     */
    public static void printEnd() {
        Drawings drawings = new Drawings();
        System.out.printf(drawings.getEnd(guessesLeft), wordToGuess);
    }

    /**
     * Check if user wishes to play again:
     * - Prompt for Y/N answer using RegEx
     * - If yes, variables are reset and game restarts from file selection point
     * - If not, see you next time!
     */
    public static void reset() {
        Scanner console = new Scanner(System.in);
        System.out.printf("\n\t%1$s again? (Y/N)\n==> ", guessesLeft == 0 ? "Try" : "Play");
        while(!console.hasNext("[YyNn]")){
            System.out.print("Invalid input, please enter 'Y' or 'N': ");
            console.next();
        }
        char yesOrNo = console.next().toUpperCase().charAt(0);
        if (yesOrNo == 'Y'){
            // Clear lists and reset variables for a new game
            wordToGuess.clear();
            letterGuesses.clear();
            userGuess.clear();
            guessesLeft = 6;
            
            play();
        } else {
            System.out.println("\n Bye! See you soon! :)\n");
        }
    }

    /**
     * Start the game
     * - Ask user to select text file of words
     * - Import word list corresponding to selected file
     * - Randomly select a word from the list
     * - While the user's guess so far is not equal to the word to guess, and
     *      while the user still has chances, keep playing
     * - When game ends, print end screen
     * - Then ask user if they want to try/play again
     */
    public static void play() {
        try {
            importWordsList(chooseFile());  // Select new word file
            setWord();                      // Set new word
        } catch (FileNotFoundException e){
            System.out.println("Error: "+e.getMessage());
        }

        while (!wordToGuess.equals(userGuess) && guessesLeft != 0){
            guessWord();
        }
        printEnd();
        reset(); // Call reset() to prompt for another game after this one ends
    }

    public static void main(String[] args) {
        welcomeUser();
        setUpWordFiles();
        play();
    }
}
