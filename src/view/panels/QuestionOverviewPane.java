package view.panels;

import controller.Controller;
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
import model.db.QuestionDB;
import model.domain.Observer;
import model.domain.Question;

import java.util.Scanner;

public class QuestionOverviewPane extends GridPane implements Observer {
	private TableView table;
	private Button btnNew;

	private Controller controller = Controller.getInstance();
	
	public QuestionOverviewPane() {
		this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);

		this.add(new Label("Questions:"), 0, 0, 1, 1);
		
		table = new TableView<Question>();
		table.setItems(FXCollections.observableArrayList(controller.getQuestions()));
		table.setPrefWidth(REMAINING);

        TableColumn nameCol = new TableColumn<>("Question");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("question"));
        table.getColumns().add(nameCol);
        TableColumn descriptionCol = new TableColumn<>("Category");
        descriptionCol.setCellValueFactory(new PropertyValueFactory("category"));
        table.getColumns().add(descriptionCol);
		this.add(table, 0, 1, 2, 6);
		
		btnNew = new Button("New");
		setNewAction(new toAddQuestionHandler());
		this.add(btnNew, 0, 11, 1, 1);
	}


	
	public void setNewAction(EventHandler<ActionEvent> newAction) {
		btnNew.setOnAction(newAction);
	}
	
	public void setEditAction(EventHandler<MouseEvent> editAction) {
		table.setOnMouseClicked(editAction);
	}

	@Override
	public void update(){
		System.out.println("Update received");
		table.setItems(FXCollections.observableArrayList(controller.getQuestions()));
	}

	class toAddQuestionHandler implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent e){
			Stage stage = new Stage();
			QuestionDetailPane questionDetailPane = new QuestionDetailPane();
			Scene scene = new Scene(questionDetailPane, 250, 300);
			stage.setScene(scene);
			stage.show();
		}
	}

}
