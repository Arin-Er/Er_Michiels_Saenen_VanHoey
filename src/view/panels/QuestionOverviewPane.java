package view.panels;

import controller.handler.question.toAddQuestionHandler;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.db.DbService;
import model.db.QuestionDB;
import model.domain.Question;

public class QuestionOverviewPane extends GridPane {
	private TableView table;
	private Button btnNew;

	DbService dbService;
	
	public QuestionOverviewPane(DbService dbService) {
		this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);

        this.dbService = dbService;

        
		this.add(new Label("Questions:"), 0, 0, 1, 1);
		
		table = new TableView<Question>();
		table.setItems(FXCollections.observableArrayList(dbService.getQuestions()));
		table.setPrefWidth(REMAINING);

        TableColumn nameCol = new TableColumn<>("Question");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("question"));
        table.getColumns().add(nameCol);
        TableColumn descriptionCol = new TableColumn<>("Category");
        descriptionCol.setCellValueFactory(new PropertyValueFactory("category"));
        table.getColumns().add(descriptionCol);
		this.add(table, 0, 1, 2, 6);
		
		btnNew = new Button("New");
		setNewAction(new toAddQuestionHandler(dbService));
		this.add(btnNew, 0, 11, 1, 1);
	}


	
	public void setNewAction(EventHandler<ActionEvent> newAction) {
		btnNew.setOnAction(newAction);
	}
	
	public void setEditAction(EventHandler<MouseEvent> editAction) {
		table.setOnMouseClicked(editAction);
	}

	public void showQuestionAddScreen(){
		Stage stage = new Stage();
		QuestionDetailPane qdp = new QuestionDetailPane();

		Scene scene = new Scene(qdp, 300, 250);
		stage.setScene(scene);
		stage.show();
	}

}
