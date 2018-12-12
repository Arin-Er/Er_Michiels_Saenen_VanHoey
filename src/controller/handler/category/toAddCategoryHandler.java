package controller.handler.category;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.db.CategoryDB;
import model.db.DbService;
import view.panels.CategoryDetailPane;

public class toAddCategoryHandler implements EventHandler<ActionEvent> {
    public DbService dbService;

    public toAddCategoryHandler(DbService dbService){
        this.dbService = dbService;
    }

    @Override
    public void handle(ActionEvent e){
        Stage stage = new Stage();
        CategoryDetailPane categoryDetailPane = new CategoryDetailPane(dbService);

        Scene scene = new Scene(categoryDetailPane, 250, 150);
        stage.setScene(scene);
        stage.show();
    }
}
