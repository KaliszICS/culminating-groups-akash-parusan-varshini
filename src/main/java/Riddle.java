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
    public Riddle(String question, String answer) {
        this.question = question;
        this.answer = answer.toLowerCase(); // Normalize for comparison
    }

    /**
     * Returns the riddle question.
     * 
     * @return the riddle question string
     */
    public String getQuestion() {
        return question;
    }

    /**
     * Checks if the player's input matches the correct answer.
     * Performs case-insensitive comparison and trims whitespace
     * for more forgiving user input.
     * 
     * @param input the player's attempted answer
     * @return true if the input matches the correct answer (case-insensitive),
     *         false otherwise
     */
    public boolean checkAnswer(String input) {
        return input.toLowerCase().trim().equals(answer);
    }
}