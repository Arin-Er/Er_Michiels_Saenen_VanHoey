package model.db;

import model.domain.Category;

import java.util.ArrayList;

public interface CategoryDB {
    void addCategory(Category c);

    void deleteCategory(Category c);

    Category getCategory(String title);

    int getSizeCategoryDB();

    ArrayList<Category> getCategories();
}
