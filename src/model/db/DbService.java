package model.db;

import model.domain.Category;
import model.domain.Question;

import java.util.ArrayList;

public class DbService {
    private CategoryDB categoryDB;
    private QuestionDB questionDB;

    public DbService(){
        categoryDB= CategoryDBlocal.getInstance();
        questionDB= QuestionDBlocal.getInstance();
    }


    public void addCategory(Category category){
        this.categoryDB.addCategory(category);
    }
    public void deleteCategory(Category category){
        this.categoryDB.deleteCategory(category);
    }
    public Category getCategory(String title){
        return this.categoryDB.getCategory(title);
    }
    public int getSizeCategoryDB(){
        return this.categoryDB.getSizeCategoryDB();
    }
    public ArrayList<Category> getCategories(){
        return  this.categoryDB.getCategories();
    }




    public void addQuestion(Question question){
        this.questionDB.addQuestion(question);
    }
    public void deleteQuestion(Question question){
        this.questionDB.deleteQuestion(question);
    }
    public Question getQuestion(String question){
        return this.questionDB.getQuestion(question);
    }
    public int getSizeQuestionDB(){
        return this.questionDB.getSizeQuestionDB();
    }
    public ArrayList<Question> getQuestions(){
        return this.questionDB.getQuestions();
    }

}
