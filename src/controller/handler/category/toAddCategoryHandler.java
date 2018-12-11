package controller.handler.category;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.db.CategoryDB;
import view.panels.CategoryDetailPane;

public class toAddCategoryHandler implements EventHandler<ActionEvent> {
    public CategoryDB categoryDB;

    public toAddCategoryHandler(CategoryDB categoryDB){
        this.categoryDB = categoryDB;
    }

    @Override
    public void handle(ActionEvent e){
        Stage stage = new Stage();
        CategoryDetailPane categoryDetailPane = new CategoryDetailPane();

        Scene scene = new Scene(categoryDetailPane, 250, 150);
        stage.setScene(scene);
        stage.show();
    }
}
