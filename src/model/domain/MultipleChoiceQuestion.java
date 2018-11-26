package model.domain;

import java.util.ArrayList;
import model.strategyQuestion.MultipleChoice;

public class MultipleChoiceQuestion extends Question{

    public MultipleChoiceQuestion(String question, String correctAnswer, ArrayList<String> answers, String category, String feedback) {
        super(question, correctAnswer, answers, category, feedback);
        questionType = new MultipleChoice();
    }
}
