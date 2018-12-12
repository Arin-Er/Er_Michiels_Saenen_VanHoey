package controller.handler.question;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.db.DbService;
import model.db.QuestionDB;
import view.panels.QuestionDetailPane;


public class toAddQuestionHandler implements EventHandler<ActionEvent> {

    DbService dbService;

    public toAddQuestionHandler(DbService dbService){
        this.dbService = dbService;
    }

    @Override
    public void handle(ActionEvent e){
        Stage stage = new Stage();
        QuestionDetailPane questionDetailPane = new QuestionDetailPane(dbService);

        Scene scene = new Scene(questionDetailPane, 250, 300);
        stage.setScene(scene);
        stage.show();
    }
}
