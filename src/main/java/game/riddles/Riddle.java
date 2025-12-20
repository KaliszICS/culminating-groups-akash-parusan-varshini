package game.riddles;
// Classes
// Represents a riddle with a question and answer.
public class Riddle {

    private String question;
    private String answer;

    public Riddle(String question, String answer) {
        this.question = question;
        this.answer = answer.toLowerCase();
    }

    public String getQuestion() {
        return question;
    }

    public boolean checkAnswer(String input) {
        return input.toLowerCase().trim().equals(answer);
    }
}