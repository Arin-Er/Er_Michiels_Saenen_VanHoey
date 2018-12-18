package model.db;

import model.domain.Category;
import model.domain.Observer;
import model.domain.Question;
import model.domain.Subject;

import java.util.*;

public class DbService implements Subject {
    private CategoryDB categoryDB;
    private QuestionDB questionDB;
    public List<Observer> observers;

    public DbService(){
        categoryDB= CategoryDBlocal.getInstance();
        questionDB= QuestionDBlocal.getInstance();
        observers = new ArrayList<Observer>();
    }


    public void addCategory(Category category){
        this.categoryDB.addCategory(category);
        notifyObservers();
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
    public CategoryDB getCategoryDB(){return this.categoryDB;}




    public void addQuestion(Question question){
        this.questionDB.addQuestion(question);
        notifyObservers();
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
    public QuestionDB getQuestionDB(){ return this.questionDB;}

    @Override
    public void addObserver(Observer o){
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o){
        observers.remove(o);
    }

    @Override
    public void notifyObservers(){
        for(Observer o : observers){
            o.update();
        }
    }

}
