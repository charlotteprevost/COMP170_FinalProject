
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

//import javafx.*;

public class COMP170_FinalProject {

//    private static final String[] filePaths = {"./EasyWords.txt", "./MediumWords.txt", "./HardWords.txt"};
    private static final List<String> filePaths = new ArrayList<>();
    private static final List<String> wordsList = new ArrayList<>();
    private static int guessesLeft = 6;
    private static int lettersLeftToGuess;
    private static List<Character> wordToGuess = new ArrayList<>();
    private static List<Character> letterGuesses = new ArrayList<>();
    private static List<Character> userGuess = new ArrayList<>();

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
     * Count the number of Words.txt files in the current directory:
     * This is to make the code scalable if the user wishes to add their own word files
     */
    public static void setUpWordFiles() {
            File currentDir = new File("."); // Current directory
            File[] files = currentDir.listFiles();  // List all files in the current directory
            if (files != null) {
                for (int i = 0; i < files.length; i++){
                    if (files[i].isFile() && files[i].getName().toLowerCase().endsWith("words.txt")) {
                        filePaths.add(files[i].getName());
                    }
                }
            }
        System.out.println(filePaths);
    }

    public static void printFileSelection(){
        System.out.println("Please select your word file: ");
        for (int i = 0; i < filePaths.size(); i++){
            System.out.println("\tEnter "+i+" to select \""+ filePaths.get(i) +"\"");
        }
        System.out.print("\n==> ");
    }

    /** Select words file
     *  - Set up the filePaths ArrayList of Strings containing the paths to the files in current directory
     *  - Display file options and how to select a file
     *  - Forever loop until user inputs a valid file option
     */
    public static int chooseFile() {
        setUpWordFiles();
        printFileSelection();

//        int fileNum = -1; // Initialize value outside acceptable range

        Scanner console = new Scanner(System.in);

        while (true) {
            String input = console.nextLine().trim();
            if (input.matches("^\\d+$")) { // Check if input is a positive integer
                int fileNum = Integer.parseInt(input);
                if (fileNum >= 0 && fileNum < filePaths.size()) {
                    System.out.println("\nFile selected:\t\""+ filePaths.get(fileNum) +"\"");
                    return fileNum; // Valid file index input, exit loop
                }
            }
            System.out.print("Invalid input.\nPlease enter a valid file number as listed above: ");
        }

//        while (true) {
//            if (console.hasNext("\\d+")) { // "\\d+" matches one or more digits
//                fileNum = Integer.parseInt(console.next());
//                if (fileNum >= 0 && fileNum < filePaths.size()) {
//                    break; // Valid file index input, exit loop
//                } else {
//                    System.out.print("Invalid input. ");
//                }
//            } else {
//                System.out.print("Invalid input. ");
//                console.next(); // Consume invalid input
//            }
//            System.out.print("Please enter a valid file number as listed above: ");
//        }



//        while (true) { // Forever loop until valid input is entered
//            if (console.hasNextInt()) {
//                fileNum = console.nextInt();
//                if (fileNum >= 0 && fileNum < filePaths.size()) {
//                    break; // Valid file index input, exit loop
//                } else {
//                    System.out.print("Invalid file number. ");
//                }
//            } else {
//                System.out.print("Invalid input. ");
//                console.next();
//            }
//            printFileSelection();
//        }

//        System.out.println("File selected: \""+ filePaths.get(fileNum) +"\"");
//        return fileNum;
    }

    /**
     * Import words from a text file and store then into an ArrayList of Strings:
     * IMPORTANT:
     *  - Text files MUST contain one word per line.
     *  - Text files MUST be located in current directory.
     *  - Additional text files may be added below by modifying the switch statement below
     *      as well as the filePaths array.
     * @param fileNum - The index place of the selected file in the filePaths ArrayList
     * @throws FileNotFoundException - For good practice
     */
    public static void importWordsList(int fileNum)
            throws FileNotFoundException {
        Scanner input = new Scanner(new File("./"+filePaths.get(fileNum)));
        while (input.hasNextLine()) {
            wordsList.add(input.nextLine());
        }
    }

    /** Prompt for difficulty and fetch a random word from the appropriate word list.
     *      - Set difficulty
     *      - Fetch word list based on difficulty
     *      - Store random word from list
     *      - Show user how many letter places they must guess
     */
    public static void setWord()
            throws FileNotFoundException {

        int file = chooseFile();   // Ask user to select text file of words
        importWordsList(file);  // Import word list corresponding to selected file

        Random rand = new Random();
        int index = rand.nextInt(wordsList.size());         // Generate a random index within the file's line range
        String word = wordsList.get(index).toUpperCase();   // Get a word from imported words list

        for (int i = 0; i < word.length(); i++) {
            wordToGuess.add(word.charAt(i));    // Store word's Characters in an ArrayList
            userGuess.add('_');                 // Placeholder underscores for hidden letters
        }

        System.out.println("Debug_wordToGuess: "+ wordToGuess);
        printArrayList(userGuess, " ");
    }

    /**
     * Print the contents of a List<Character> array separated by a comma
     * @param array - an ArrayList of Characters
     */
    public static void printArrayList(List<Character> array, String separator) {
        System.out.print(array.getFirst());
        for (int i = 1; i < array.size(); i++) {
                System.out.print(separator + array.get(i));
        }
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

    public static void printEnd() {
        Drawings drawings = new Drawings();
        System.out.println(drawings.getEnd(guessesLeft));
    }

    public static void checkGuess(char letter) {
        boolean wordContainsLetter = false;
        for (int i = 0; i < wordToGuess.size(); i++) {
            if (wordToGuess.get(i) == letter) {
                userGuess.set(i, letter);
                wordContainsLetter = true;
            }
        }
        if (!wordContainsLetter) {
            guessesLeft--;
        }
    }

    /**
     * Prompt user for letter guess
     * - Loops until user enters valid input
     * - Stores the user's letter guess
     * - Checks the letter guess by calling checkGuess(letter)
     * - Prints an illustration based on guesses left
     * - Prints the letters left to guess
     * - Prints the letters previously guessed (right or wrong)
     */
    public static void guessWord() {
        Scanner console = new Scanner(System.in);
        System.out.print("\nYour letter guess: ");
        // Use RegEx matching to ensure we're getting one letter from the user
        while(!console.hasNext("[A-Za-z]")){
            System.out.print("Invalid input, please enter a single letter: ");
            console.next();
        }
        char letterGuess = console.next().toUpperCase().charAt(0);
        letterGuesses.add(letterGuess); // Update list of previous guesses (whether right or wrong)
        checkGuess(letterGuess);
        printDrawing();
        System.out.print("Word to guess:\t");
        printArrayList(userGuess, " ");
        System.out.print("\nPrevious letter guesses:\t");
        printArrayList(letterGuesses, ", ");
    }

    public static void main(String[] args)
            throws FileNotFoundException {
        welcomeUser();
        setWord();
        // While the user's guess so far is not equal to the word to
        // guess, and while the user still has chances, keep playing
        while (!wordToGuess.equals(userGuess) && guessesLeft != 0){
            guessWord();
        }
        printEnd();
    }
}
