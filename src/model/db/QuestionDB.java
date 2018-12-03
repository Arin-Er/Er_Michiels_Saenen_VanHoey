package model.db;

import model.domain.Question;

import java.util.ArrayList;

public class QuestionDB {
    //gelijkaardig aan CategoryDB
    private static QuestionDB single_instance = null;

    private ArrayList<Question> questions;

    private QuestionDB (){
        questions = new ArrayList<>();
    }

    public static QuestionDB getInstance(){
        if(single_instance == null)
            single_instance = new QuestionDB();
        return single_instance;
    }

    public void addQuestion(Question question){
        if(question == null){
            throw new DbException("question can not be null");
        }
        else{
            for(Question x : this.questions){
                if(x.getQuestion().equals(question.getQuestion())){
                    throw new DbException("This Question already exists");
                }
            }
        }

        this.questions.add(question);
    }

    //ook hier twijfelen over een edit. staat nergens in stories

    public void deleteQuestion(Question question){
        this.questions.remove(question);
    }

    public Question getQuestion(String question){
        Question result = null;
        for(Question x:this.questions){
            if(x.getQuestion().equals(question)){
                result = x;
            }
        }
        if(result == null){
            throw new DbException("Question was not found");
        }else{
            return result;
        }
    }



    public int getSizeQuestion(){
        return this.questions.size();
    }

    public ArrayList<Question> getQuestions(){
        return this.questions;
    }
}
