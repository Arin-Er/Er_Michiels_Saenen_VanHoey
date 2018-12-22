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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.db.CategoryDB;
import model.domain.Category;
import model.domain.Observer;



public class CategoryOverviewPane extends GridPane implements Observer {
	private TableView table;
	private Button btnNew;
	private Controller controller = Controller.getInstance();
	
	public CategoryOverviewPane() {
		this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);
        
		this.add(new Label("Categories:"), 0, 0, 1, 1);
		
		table = new TableView<Category>();
		table.setItems(FXCollections.observableArrayList(controller.getCategories()));
		table.setPrefWidth(REMAINING);

        TableColumn<Category, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory("title"));
        table.getColumns().add(nameCol);
        nameCol.setCellValueFactory(new PropertyValueFactory<Category, String>("title"));

        TableColumn descriptionCol = new TableColumn<>("Description");
        descriptionCol.setCellValueFactory(new PropertyValueFactory<Category, String>("description"));
        table.getColumns().add(descriptionCol);
		this.add(table, 0, 1, 2, 6);
		
		btnNew = new Button("New");
		setNewAction(new toAddCategoryHandler());
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
		table.setItems(FXCollections.observableArrayList(controller.getCategories()));
	}

	class toAddCategoryHandler implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent e){
			Stage stage = new Stage();
			CategoryDetailPane categoryDetailPane = new CategoryDetailPane();
			Scene scene = new Scene(categoryDetailPane, 250, 150);
			stage.setScene(scene);
			stage.show();
		}
	}

}
