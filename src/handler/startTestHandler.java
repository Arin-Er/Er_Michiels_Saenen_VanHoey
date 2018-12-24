package handler;

import controller.Controller;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import view.panels.TestPane;

import javafx.event.*;

public class startTestHandler implements EventHandler<ActionEvent> {
    Controller controller = Controller.getInstance();
    Stage stage;
    TestPane testPane;

    public startTestHandler(){}

    @Override
    public void handle(ActionEvent e){
        controller.resetQuestionNumber();
        testPane = new TestPane(controller);
        if(controller.getQuestions().isEmpty()){
            Scene scene = new Scene(new GridPane());
            showAlert(Alert.AlertType.ERROR, scene.getWindow(), "Error", "No questions in database");
        }else{
            controller.newTest();
            this.stage = new Stage();
            Scene scene = new Scene(testPane);
            stage.setScene(scene);
            stage.show();
            testPane.setProcessAnswerAction(new processAnswerHandler(testPane, stage));
        }
    }
    private void showAlert(Alert.AlertType alertType, Window window, String title, String message){
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(window);
        alert.show();
    }
}
