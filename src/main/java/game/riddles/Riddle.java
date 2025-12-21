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
    public Riddle(String question, String answer) {
        this.question = question;
        this.answer = answer.toLowerCase(); // Normalize for comparison
    }

    /**
     * Returns the riddle question.
     *
     * @return the riddle question
     */
    public String getQuestion() {
        return question;
    }

    /**
     * Checks whether the provided input matches the riddle's answer.
     * Comparison is case-insensitive and ignores leading/trailing spaces.
     *
     * @param input the user's answer attempt
     * @return true if the answer is correct, false otherwise
     */
    // [Searching] (Sequential comparison)
    public boolean checkAnswer(String input) {
        return input.toLowerCase().trim().equals(answer);
    }
}
