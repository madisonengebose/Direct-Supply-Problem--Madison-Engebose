/*
 * Main Screen Controller Class
 * Name: Madison Engebose
 * Created: 11/12/24
 */

package src;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * This class launches the user interface
 */
public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader mainLoader = new FXMLLoader();
        Parent root = mainLoader.load(getClass().getResource("mainScreen.fxml").openStream());
        stage.setTitle("Trivia Quiz");
        Scene mainScene = new Scene(root);
        stage.setScene(mainScene);
        stage.show();
        MainScreenController mainController = mainLoader.getController();
        mainController.setMainGrid((Pane) root);
        FXMLLoader secondaryLoader = new FXMLLoader();
        Stage secondaryStage = new Stage();
        Parent secondaryRoot = secondaryLoader.load(getClass().getResource(
                "questionScreen.fxml").openStream());
        secondaryStage.setTitle("Trivia Quiz");
        Scene secondaryScene = new Scene(secondaryRoot);
        secondaryStage.setScene(secondaryScene);
        secondaryStage.hide();

        // Set communication between controllers
        QuestionScreenController secondaryController = secondaryLoader.getController();
        mainController.setOtherStage(secondaryStage);
        mainController.setOtherController(secondaryController);
        secondaryController.setMainStage(stage);
        secondaryController.setMainController(mainController);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
