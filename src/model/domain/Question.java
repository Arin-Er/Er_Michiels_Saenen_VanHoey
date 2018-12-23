package model.domain;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.strategyQuestion.QuestionType;

import java.util.ArrayList;

public class Question {
    //strategy question uitleg hoe ik het mss zou doen:

    //yesno question & multiplechoice question erven van question over ( question heeft een question type -> interface ) om Qtype te setten zeg maar da is in de constructor van de subklassen

    //Qtype heeft gwn een getter vr het type

    //de twee classes die Qtype implementeren implementeren deze getter op hun eigen manier, ( yes/no gaat gwn zeggen van " ik ben een yes/no vraag )

    private String question;
    private String correctAnswer;
    private ObservableList<CharSequence> answers = FXCollections.observableArrayList(new ArrayList<CharSequence>());
    private String category;
    private String feedback;
    public QuestionType questionType;

    public Question(String question, String correctAnswer, ObservableList<CharSequence> answers, String category, String feedback) {
        setQuestion(question);
        setCorrectAnswer(correctAnswer);
        setAnswers(answers);
        setCategory(category);
        setFeedback(feedback);
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        if(correctAnswer == null || correctAnswer.isEmpty()){
            throw new DomainException("Answer can not be empty");
        }
        this.correctAnswer = correctAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public ObservableList<CharSequence> getAnswers() {
        return answers;
    }

    public String getCategory() {
        return category;
    }


    public String getFeedback() {
        return feedback;
    }

    public void setQuestion(String question) {
        if (question == null || question.isEmpty()) {
            throw new DomainException("The question can not be empty");
        }
        this.question = question;
    }

    public void setCategory(String category) {
        if (category == null) {
            throw new DomainException("Category has to be set");
        }
        this.category = category;
    }

    public void setFeedback(String feedback) {
        if (feedback == null || feedback.isEmpty()) {
            throw new DomainException("Feedback is required");
        }
        this.feedback = feedback;
    }

    public void setAnswers(ObservableList<CharSequence> answers){
        if(answers.isEmpty() || answers.size() < 2){
            throw new DomainException("Every question needs at least 2 possible answers");
        }
        this.answers = answers;
    }

    public String getQuestionType(){
        return questionType.getQuestionType();
    }

    public void setQuestionType(QuestionType newQuestionType){
        if(newQuestionType == null){
            throw new DomainException("Type can not be empty");
        }
        this.questionType = newQuestionType;
    }
    public String format() {
        String result =  this.getCategory() + ";" + this.getAnswers() + ";" + this.getCorrectAnswer() + ";" + this.getFeedback() + ";";
        for (CharSequence q : answers){
            result += q + "/";
        }
        result = result.substring(0, result.length() - 1);
        return result;
    }
}
