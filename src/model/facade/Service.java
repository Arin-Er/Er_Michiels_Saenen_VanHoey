package model.facade;

import javafx.collections.ObservableList;
import model.db.*;
import model.domain.*;

import java.util.ArrayList;
import java.util.List;

public class Service implements Subject, CategoryDB, QuestionDB {
    private CategoryDB categoryDB;
    private QuestionDB questionDB;
    public List<Observer> observers;
    private Score score;

    public Service() {
        categoryDB= CategoryDBlocal.getInstance();
        questionDB= QuestionDBlocal.getInstance();
        observers = new ArrayList<Observer>();
    }

    public void addCategory(Category category){
        this.categoryDB.addCategory(category);
        this.notifyObservers();
    }

    @Override
    public Category getCategory(String title){
        return this.categoryDB.getCategory(title);
    }

    @Override
    public int getSizeCategoryDB(){
        return this.categoryDB.getSizeCategoryDB();
    }

    public void deleteCategory(Category category){
        this.categoryDB.deleteCategory(category);
        this.notifyObservers();
    }

    public ArrayList<Category> getCategories(){
        return categoryDB.getCategories();
    }

    @Override
    public ObservableList<String> getCategoryNames(){
        return this.categoryDB.getCategoryNames();
    }

    public void addQuestion(Question question){
        this.questionDB.addQuestion(question);
        this.notifyObservers();
    }

    public void deleteQuestion(Question question){
        this.questionDB.deleteQuestion(question);
        this.notifyObservers();
    }

    @Override
    public Question getQuestion(String question){
        return this.questionDB.getQuestion(question);
    }

    @Override
    public int getSizeQuestionDB(){
        return this.questionDB.getSizeQuestionDB();
    }

    public ArrayList<Question> getQuestions(){
        return questionDB.getQuestions();
    }

    public void newTest() {
        score = new Score(this.getCategories(),this.getQuestions());

    }

    public Score getScore(){
        this.notifyObservers();
        return score;
    }

    @Override
    public void addObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for(Observer o : observers){
            o.update();
        }
    }
}
