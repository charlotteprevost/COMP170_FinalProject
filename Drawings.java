import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Drawings {

    // Alena TO-DO: Use printf() to stylize with color
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
                    ___|___      -- --
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
                    ___|___      --
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
                    ___|___
                    """
    );

    public String getDrawings(int guessesLeft) {
        return drawings.get(guessesLeft);
    }
}






