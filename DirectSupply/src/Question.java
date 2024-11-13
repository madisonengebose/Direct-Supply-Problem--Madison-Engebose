/*
 * Main Screen Controller Class
 * Name: Madison Engebose
 * Created: 11/12/24
 */

package src;

/**
 * This class creates the question objects
 */
public class Question {
    private String text;
    private String correctAnswer;

    /**
     * This method is the constructor for the question objects
     * @param text A string containing the question
     * @param correctAnswer A string containing the correct answer for the question
     */
    public Question(String text, String correctAnswer) {
        this.text = text;
        this.correctAnswer = correctAnswer;
    }

    /**
     * This method gets the question
     * @return A string containing the question
     */
    public String getText() {
        return text;
    }

    /**
     * This method gets the correct answer
     * @return A string containing the correct answer
     */
    public String getCorrectAnswer() {
        return correctAnswer;
    }
}