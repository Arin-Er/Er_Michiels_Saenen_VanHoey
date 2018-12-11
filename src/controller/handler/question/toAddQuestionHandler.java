package controller.handler.question;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.db.DbService;
import model.db.QuestionDB;


public class toAddQuestionHandler implements EventHandler<ActionEvent> {

    DbService dbService;

    public toAddQuestionHandler(DbService dbService){
        this.dbService = dbService;
    }

    @Override
    public void handle(ActionEvent e){

    }
}
