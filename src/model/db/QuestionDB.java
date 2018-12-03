package model.db;

import model.domain.Question;

import java.util.ArrayList;

public interface QuestionDB {
    void addQuestion(Question question);
    void deleteQuestion(Question question);
    Question getQuestion(String question);
    int getSizeQuestionDB();
    ArrayList<Question> getQuestions();
}
