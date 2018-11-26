package model.db;

import model.domain.Category;

import java.util.ArrayList;

public class CategoryDB {
    private ArrayList<Category> categories;
    public CategoryDB(){
        this.categories = new ArrayList<Category>();
    }
    public void addCategory(Category c){
        //fouten -> als het null is en als die al in de lijst is ( category gelijk wanneer titel hetzelfde is? momenteel op deze manier geimplementeerd)
        if(c == null){
            throw new DbException("Categories can not be empty");
        }
        else{
            for(Category x : this.categories){
                if(x.getTitle().equalsIgnoreCase(c.getTitle())){
                    throw new DbException("This category already exists");
                }
            }
        }
        this.categories.add(c);
    }

    //editCategory() toevoegen? niet zeker of dit nodig is. bespreken in de les..

    public void deleteCategory(Category c){
        this.categories.remove(c);
    }
    public Category getCategory(String title){
        Category result = null;
        for (Category x : this.categories){
            if(x.getTitle().equalsIgnoreCase(title.toLowerCase())){
                result = x;
            }
        }
        if(result == null){// als de for each niks heeft gevonden
            throw new DbException("Given Category was not found");
        } else{
            return result;
        }
    }
    public int getSizeCategoryDB(){
        return this.categories.size();
    }
    public ArrayList<Category> getCategories(){
        return this.categories;
    }

}
