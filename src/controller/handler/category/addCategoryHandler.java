package controller.handler.category;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import model.db.CategoryDB;
import model.domain.Category;



public class addCategoryHandler implements EventHandler<ActionEvent> {
    CategoryDB categoryDB;
    TextField titleField, descriptionField;

    public addCategoryHandler(CategoryDB categoryDB, TextField titleField, TextField descriptionField){
        this.categoryDB = categoryDB;
        this.descriptionField = descriptionField;
        this.titleField = titleField;
    }

    @Override
    public void handle(ActionEvent e){
        if(titleField != null || !titleField.getText().isEmpty() || descriptionField != null || !descriptionField.getText().isEmpty()){
            categoryDB.addCategory(new Category(titleField.getText(), descriptionField.getText()));
            titleField.clear();
            descriptionField.clear();
            ((Node)(e.getSource())).getScene().getWindow().hide();
        }
    }
}
