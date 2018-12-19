package model.db;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.domain.Category;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;

public class CategoryDBlocal implements CategoryDB {
    private static CategoryDBlocal single_instance = null;
    private ArrayList<Category> categories;

    private CategoryDBlocal(){
        this.categories = new ArrayList<Category>();
    }

    public static CategoryDBlocal getInstance(){
        if(single_instance == null)
            single_instance = new CategoryDBlocal();
        return single_instance;
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
        try {
            FileWriter writer = new FileWriter("testdatabase/groep.txt",true);
            writer.write(System.getProperty( "line.separator" ));
            writer.write(c.getTitle());
            writer.write(";");
            writer.write(c.getDescription());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteCategory(Category c) {
        File file = new File("testdatabase/groep.txt");
        try {
            Scanner scannerFile = new Scanner(file);
            categories.clear();
            while(scannerFile.hasNextLine()) {
                Scanner scannerLijn = new Scanner(scannerFile.nextLine());
                scannerLijn.useDelimiter(";");
                String categoryLijn = scannerLijn.next();
                String description = scannerLijn.next();
                Category category = new Category(categoryLijn,description);
                categories.add(category);
                ListIterator<Category> iter = categories.listIterator();
                while(iter.hasNext()){
                    if(iter.next().getDescription().equals(c.getDescription())){
                        iter.remove();
                    }
                }
            }
            FileWriter writer = new FileWriter("testdatabase/groep.txt",false);
            writer.write(this.schrijf());
            writer.flush();
            writer.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String schrijf() {
        String result = "";
        for(Category cat : this.categories){
            result += cat.format() + "\n";
        }
        return result;
    }
    public Category getCategory(String title) {
        File file = new File("testdatabase/groep.txt");
        categories.clear();
        try {
            Scanner scannerFile = new Scanner(file);
            while (scannerFile.hasNextLine()) {
                Scanner scannerLijn = new Scanner(scannerFile.nextLine());
                scannerLijn.useDelimiter(";");
                String categoryLijn = scannerLijn.next();
                String description = scannerLijn.next();
                Category category = new Category(categoryLijn, description);
                categories.add(category);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Category result= null;
        for(Category cat : this.categories){
            if (cat.getTitle().equals(title)) {
                result = cat;
            }
        }
        if(result == null){
            throw new DbException("Category was not found");
        }else {
            return result;
        }
    }

    public int getSizeCategoryDB(){
        File file = new File("testdatabase/groep.txt");
        categories.clear();
        try {
            Scanner scannerFile = new Scanner(file);
            while (scannerFile.hasNextLine()) {
                Scanner scannerLijn = new Scanner(scannerFile.nextLine());
                scannerLijn.useDelimiter(";");
                String name = scannerLijn.next();
                String description = scannerLijn.next();
                Category category = new Category(name, description);
                categories.add(category);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int count = 0;
        for(Category cat : this.categories){
            count++;
        }
        return count;
    }
    public ArrayList<Category> getCategories(){
        File file = new File("testdatabase/groep.txt");
        categories.clear();
        try {
            Scanner scannerFile = new Scanner(file);
            while (scannerFile.hasNextLine()) {
                Scanner scannerLijn = new Scanner(scannerFile.nextLine());
                scannerLijn.useDelimiter(";");
                String name = scannerLijn.next();
                String description = scannerLijn.next();
                Category category = new Category(name, description);
                categories.add(category);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return categories;
    }

    @Override
    public ObservableList<String> getCategoryNames(){
        ArrayList<String> names = new ArrayList<>();
        File file = new File("testdatabase/groep.txt");
        names.clear();
        try {
            Scanner scannerFile = new Scanner(file);
            while (scannerFile.hasNextLine()) {
                Scanner scannerLijn = new Scanner(scannerFile.nextLine());
                scannerLijn.useDelimiter(";");
                String name = scannerLijn.next();
                scannerLijn.next();

                names.add(name);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return FXCollections.observableArrayList(names);

    }

}
