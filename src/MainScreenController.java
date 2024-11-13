/*
 * Main Screen Controller Class
 * Name: Madison Engebose
 * Created: 11/12/24
 */
package src;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * This class sets up the methods for my main screen
 */
public class MainScreenController {
    private QuestionScreenController otherController;
    private Stage otherStage;

    @FXML
    private Pane mainPane;

    /**
     * When the user clicks the start button it closes the current stage and
     * opens the questions pane.
     */
    @FXML
    private void goToQuestions() {
        if (otherStage != null) {
            Stage currentStage = (Stage) mainPane.getScene().getWindow();
            currentStage.close();

            if (!otherStage.isShowing()) {
                otherStage.show();
            } else {
                System.out.println("The question stage is already visible.");
            }
        } else {
            System.out.println("Other stage is not set. Unable to navigate to questions.");
        }
    }

    /**
     * This method closes the user interface when the user clicks the exit button
     */
    @FXML
    private void exit(){
        System.exit(0);
    }

    public void setOtherStage(Stage stage) {
        this.otherStage = stage;
    }

    public void setOtherController(QuestionScreenController controller) {
        this.otherController = controller;
    }

    public void setMainGrid(Pane grid) {
        this.mainPane = grid;
    }
}
