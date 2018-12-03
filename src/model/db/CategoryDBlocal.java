package model.db;

import model.domain.Category;

import java.util.ArrayList;

public class CategoryDBlocal implements CategoryDB {
    private static CategoryDBlocal single_instance = null;
    private ArrayList<Category> categories;

    private CategoryDBlocal(){
        this.categories = new ArrayList<Category>();
    }


    @Override
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

    @Override
    public void deleteCategory(Category c){
        this.categories.remove(c);
    }
    @Override
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
    @Override
    public int getSizeCategoryDB(){
        return this.categories.size();
    }
    @Override
    public ArrayList<Category> getCategories(){
        return this.categories;
    }

}
