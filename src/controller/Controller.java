package controller;

import javafx.collections.ObservableList;
import model.domain.*;
import model.facade.Service;

import java.util.ArrayList;

public class Controller implements Observer {
    private static Controller single_instance = null;
    private final Service service;
    private int questionNumber = 0;
    private boolean refreshTable;
    private boolean testKlaar;

    private Controller() {
        refreshTable = false;
        testKlaar = false;
        service = new Service();
        service.addObserver(this);
    }
    public static Controller getInstance() {
        if (single_instance == null) {
            synchronized (Controller.class) {
                if (single_instance == null) {
                    single_instance = new Controller();
                }
            }
        }
        return single_instance;
    }

    public Service getService() {
        return this.service;
    }

    public ArrayList<Category> getCategories() {
        return service.getCategories();
    }

    public void addCategory(Category category) {
        refreshTable = true;
        this.service.addCategory(category);
    }
    public ObservableList getCategoryNames() {
        return service.getCategoryNames();
    }
    public void addQuestion(Question q) {
        service.addQuestion(q);
    }

    public Question getQuestion(String s) {
        return service.getQuestion(s);
    }
    public ArrayList<Question> getQuestions(){
        return this.service.getQuestions();
    }


    public Evaluation getEvaluation(){
        return service.getEvaluation();
    }

    public void newTest(){
        service.newTest();
    }

    public Score getScoreFeedback(){
        return service.getScore();
    }

    public void resetQuestionNumber(){
        this.questionNumber = 0;
    }

    public int getQuestionNumber(){
        return this.questionNumber;
    }

    public Score getScore(){
        testKlaar = true;
        return service.getScore();
    }

    public String getNextQuestion(){
        String question = "";
        if(!getQuestions().isEmpty()){
            if(questionNumber < getQuestions().size()){
                question = getQuestions().get(questionNumber).getQuestion();
                questionNumber++;
            }
        }
        return question;
    }

    @Override
    public void update() {

    }
}
