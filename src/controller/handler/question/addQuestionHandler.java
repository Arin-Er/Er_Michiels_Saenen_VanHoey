package controller.handler.question;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.db.DbService;
import model.domain.Question;

public class addQuestionHandler implements EventHandler<ActionEvent> {
    DbService dbService;
    TextArea statementsArea;
    TextField questionField, statementField, feedbackField;
    ComboBox categoryField;

    String statement, question, feedback, category;
    ObservableList<CharSequence> statements;


    public addQuestionHandler(DbService dbService, TextArea statementsArea, TextField questionField, TextField statementField, TextField feedbackField, ComboBox categoryField){
        this.dbService = dbService;
        this.statementField = statementField;
        this.questionField = questionField;
        this.statementsArea = statementsArea;
        this.categoryField = categoryField;
        this.feedbackField = feedbackField;
    }

    @Override
    public void handle(ActionEvent e){
        if(!statementsArea.getText().isEmpty() || !questionField.getText().isEmpty() || !statementField.getText().isEmpty() || !feedbackField.getText().isEmpty() || !categoryField.getValue().toString().isEmpty()){
            statement = statementField.getText();
            feedback = feedbackField.getText();
            question = questionField.getText();
            category = categoryField.getValue().toString();
            statements = statementsArea.getParagraphs();

            dbService.addQuestion(new Question(question, statement, statements, category, feedback));

            statementField.clear();
            feedbackField.clear();
            questionField.clear();
            statementsArea.clear();
            ((Node)(e.getSource())).getScene().getWindow().hide();
        }
    }
}
