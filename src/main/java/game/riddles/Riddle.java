<<<<<<< HEAD
package game.riddles;

/**
 * The Riddle class represents a single riddle consisting of
 * a question and its corresponding answer.
 * It provides functionality to retrieve the riddle question
 * and validate a user's response.
 *
 * @author Akash K.
 * @author Parusan P.
 * @author Varshini B.
 * @version 1.0
 */
// [Classes]
public class Riddle {

    /** The riddle question */
    private String question;

    /** The correct answer to the riddle */
    private String answer;

    /**
     * Constructs a Riddle object with a specified question and answer.
     *
     * @param question the riddle question
     * @param answer the correct answer to the riddle
     */
    // [Classes]
=======
/**
 * Represents a riddle puzzle in the GridQuest RPG.
 * Used for interactive puzzle challenges where players must solve riddles
 * to progress, open chests, or earn rewards. Implements case-insensitive
 * answer checking for player-friendly interaction.
 * 
 * Demonstrates encapsulation and string manipulation concepts from ICS4U.
 * 
 * @author [Parusan]
 * @author [Akash]
 * @author [Varshini]
 * @version 1.0
 */
public class Riddle {

    /**
     * The riddle question presented to the player.
     */
    private String question;
    
    /**
     * The correct answer to the riddle, stored in lowercase
     * for case-insensitive comparison.
     */
    private String answer;

    /**
     * Constructs a new Riddle with the specified question and answer.
     * The answer is converted to lowercase to enable case-insensitive checking.
     * 
     * @param question the riddle question to present to players
     * @param answer the correct answer (case-insensitive)
     */
>>>>>>> e5a5c4dc231ee3fb8b8824259ad1490b7c593ccf
    public Riddle(String question, String answer) {
        this.question = question;
        this.answer = answer.toLowerCase(); // Normalize for comparison
    }

    /**
     * Returns the riddle question.
<<<<<<< HEAD
     *
     * @return the riddle question
=======
     * 
     * @return the riddle question string
>>>>>>> e5a5c4dc231ee3fb8b8824259ad1490b7c593ccf
     */
    public String getQuestion() {
        return question;
    }

    /**
<<<<<<< HEAD
     * Checks whether the provided input matches the riddle's answer.
     * Comparison is case-insensitive and ignores leading/trailing spaces.
     *
     * @param input the user's answer attempt
     * @return true if the answer is correct, false otherwise
     */
    // [Searching] (Sequential comparison)
=======
     * Checks if the player's input matches the correct answer.
     * Performs case-insensitive comparison and trims whitespace
     * for more forgiving user input.
     * 
     * @param input the player's attempted answer
     * @return true if the input matches the correct answer (case-insensitive),
     *         false otherwise
     */
>>>>>>> e5a5c4dc231ee3fb8b8824259ad1490b7c593ccf
    public boolean checkAnswer(String input) {
        return input.toLowerCase().trim().equals(answer);
    }
}
