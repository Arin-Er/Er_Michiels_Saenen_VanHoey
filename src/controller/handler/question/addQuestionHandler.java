package controller.handler.question;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.db.DbService;

public class addQuestionHandler implements EventHandler<ActionEvent> {
    DbService dbService;

    public addQuestionHandler(DbService dbService){
        this.dbService = dbService;
    }

    @Override
    public void handle(ActionEvent e){

    }
}
