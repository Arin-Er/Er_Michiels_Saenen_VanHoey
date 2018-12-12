package view.panels;

import controller.handler.category.toAddCategoryHandler;
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
import model.db.DbService;
import model.domain.Category;


public class CategoryOverviewPane extends GridPane {
	private TableView table;
	private Button btnNew;
	private DbService dbService;
	
	public CategoryOverviewPane(DbService dbService) {
		this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);

        this.dbService = dbService;
        
		this.add(new Label("Categories:"), 0, 0, 1, 1);
		
		table = new TableView<Category>();
		table.setItems(FXCollections.observableArrayList(dbService.getCategories()));
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
		setNewAction(new toAddCategoryHandler(dbService));
		this.add(btnNew, 0, 11, 1, 1);
	}
	
	public void setNewAction(EventHandler<ActionEvent> newAction) {
		btnNew.setOnAction(newAction);
	}
	
	public void setEditAction(EventHandler<MouseEvent> editAction) {
		table.setOnMouseClicked(editAction);
	}

}
