import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Drawings {

    public List<String> drawings = Arrays.asList(
            """
                    
                    0 guesses left! Dead!
                       ____________
                       |           |
                       |        {* _ *}
                       |       --|...|--
                       |      /  |...|  \\
                       |         |...|
                       |         -----
                       |         || ||
                       |         || ||
                       |         -- --
                    ___|___
                    """,
            """
                    
                    1 guess left!
                       ____________
                       |           |
                       |        {* _ *}
                       |       --|...|--
                       |      /  |...|  \\
                       |         |...|
                       |         -----
                       |         ||
                       |         ||
                       |         --
                    ___|___
                    """,
            """
                    
                    2 guesses left!
                       ____________
                       |           |
                       |        {* _ *}
                       |       --|...|--
                       |      /  |...|  \\
                       |         |...|
                       |
                       |
                       |
                       |
                    ___|___
                    """,
            """
                    
                    3 guesses left!
                       ____________
                       |           |
                       |        {* _ *}
                       |       --|...|
                       |      /  |...|
                       |         |...|
                       |
                       |
                       |
                       |
                    ___|___
                    """,
            """
                    
                    4 guesses left!
                       ____________
                       |           |
                       |        {* _ *}
                       |         |...|
                       |         |...|
                       |         |...|
                       |
                       |
                       |
                       |
                    ___|___
                    """,
            """
                    
                    5 guesses left!
                       ____________
                       |           |
                       |        {* _ *}
                       |
                       |
                       |
                       |
                       |
                       |
                       |
                    ___|___
                    """,
            """
                    
                    6 guesses left!
                       ____________
                       |           |
                       |
                       |
                       |
                       |
                       |
                       |
                       |
                       |
                    ___|___
                    """
    );

    public static String youWin =
        """
        
        
          _____           __  __ ______  __           __   _____  ___   ___
         / ____|   /\\    |  \\/  |  ____| \\ \\         / /  / ___ \\| \\ \\  | |
        | |  __   /  \\   | \\  / | |__     \\ \\   ^   / /  | /   \\ | |\\ \\ | |
        | | |_ | / /\\ \\  | |\\/| |  __|     \\ \\/ ^ \\/ /   | |   | | | \\ \\| |
        | |__| |/ ____ \\ | |  | | |____     \\  / \\  /    | \\___/ | |  \\ | |
         \\_____/_/    \\_\\|_|  |_|______|     \\/   \\/      \\_____/|_|   \\__|

        """;

    public static String youLose =
        """
        
        
          _____           __  _________    ______      ________ _____
         / ____|   /\\    |  \\/  |  ____|  / __ \\ \\    / /  ____|  __ \\
        | |  __   /  \\   | \\  / | |__    | |  | \\ \\  / /| |__  | |__) |
        | | |_ | / /\\ \\  | |\\/| |  __|   | |  | |\\ \\/ / |  __| |  _  /
        | |__| |/ ____ \\ | |  | | |____  | |__| | \\  /  | |____| | \\ \\
         \\_____/_/    \\_\\|_|  |_|______|  \\____/   \\/   |______|_|  \\_\\
        
        
        \tThe answer was:\t%1$s
                
        """;

    public String getDrawings(int guessesLeft) {
        return drawings.get(guessesLeft);
    }
    public String getEnd(int guessesLeft) { return guessesLeft == 0 ? youLose : youWin; }
}






