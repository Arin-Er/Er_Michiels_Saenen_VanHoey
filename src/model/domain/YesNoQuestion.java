package model.domain;

import java.util.ArrayList;
import model.strategyQuestion.YesNo;

public class YesNoQuestion extends Question {

    public YesNoQuestion(String question, String correctAnswer, ArrayList<String> answers, String category, String feedback) {
        super(question, correctAnswer, answers, category, feedback);
        questionType = new YesNo();
    }
}
