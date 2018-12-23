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
    private Evaluation evaluation;
    private Score score;

    public Service() {
        categoryDB= CategoryDBlocal.getInstance();
        questionDB= QuestionDBlocal.getInstance();
        evaluation = new Evaluation();
        observers = new ArrayList<Observer>();
    }

    public void addCategory(Category category){
        this.categoryDB.addCategory(category);
        this.notifyObservers();
    }


    public Evaluation getEvaluation(){
        return evaluation;
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

    public void addScore(Category category) {
        for(Category c : categoryDB.getCategories()) {
            if (c.equals(category)) {
                c.addToScore();
            }
        }
    }

    public int totalScore() {
        int max = 0;
        for(Category c : this.categoryDB.getCategories()) {
            max += c.getScore();
        }
        return max;
    }

    public int getTotalMaxScore() {
        int max = 0;
        for(Question q : questionDB.getQuestions()) {
            max += 1;
        }
        return max;
    }

    public int getMaxScore(Category c) {
        int max = 0;
        for(Question q :  questionDB.getQuestions()) {
            if(q.getCategory().equals(c)) {
                max += 1;
            }
        }
        return max;
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
