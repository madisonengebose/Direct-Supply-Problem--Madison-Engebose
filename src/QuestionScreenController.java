/*
 * Main Screen Controller Class
 * Name: Madison Engebose
 * Created: 11/12/24
 */

package src;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.scene.control.Label;

/**
 * This class sets up the methods for my question screen
 */
public class QuestionScreenController {
    private Stage mainStage;
    private MainScreenController mainController;
    private int currentQuestionIndex = 0;
    private GetQuiz quiz;
    private int currentScore = 0;
    private static final int TOTAL_QUESTIONS = 5;
    @FXML
    private TextArea questionTextArea;
    @FXML
    private Label questionNumberLabel;
    @FXML
    private Button trueButton;
    @FXML
    private Button falseButton;
    /**
     * This method closes the window when the user clicks the exit button.
     */
    @FXML
    private void exit(){
        System.exit(0);
    }

    /**
     * This method goes back to the main screen when the user clicks the restart button
     */
    @FXML
    private void restart(){
        initializeQuiz();
    }

    /**
     * This method sets up the quiz for the user
     */
    @FXML
    public void initialize() {
        initializeQuiz();
    }

    private void initializeQuiz() {
        quiz = new GetQuiz();
        currentQuestionIndex = 0;
        currentScore = 0;
        displayNextQuestion();
        trueButton.setDisable(false);
        falseButton.setDisable(false);
    }

    private void displayNextQuestion() {
        if (quiz.hasMoreQuestions()) {
            Question currentQuestion = quiz.getCurrentQuestion();
            currentQuestionIndex += 1;
            questionTextArea.setText(currentQuestion.getText());
            questionNumberLabel.setText(
                    "Question " + currentQuestionIndex + " / " + TOTAL_QUESTIONS);
        } else {
            displayFinalMessage();
        }
    }

    private void displayFinalMessage() {
        questionTextArea.setText(
                "Quiz complete! Your score: " + currentScore + " / " + TOTAL_QUESTIONS);
        questionNumberLabel.setText("End of quiz");
        trueButton.setDisable(true);
        falseButton.setDisable(true);
    }


    @FXML
    private void handleTrue() {
        processAnswer("True");
    }

    @FXML
    private void handleFalse() {
        processAnswer("False");
    }

    private void processAnswer(String userAnswer) {
        if (quiz.checkAnswer(userAnswer)) {
            currentScore++;
        }
        displayNextQuestion();
    }

    public void setMainStage(Stage stage) {
        this.mainStage = stage;
    }

    public void setMainController(MainScreenController controller) {
        this.mainController = controller;
    }

}
