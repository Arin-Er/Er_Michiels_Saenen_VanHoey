package model.domain;

import java.util.ArrayList;

import javafx.collections.ObservableList;
import model.strategyQuestion.YesNo;

public class YesNoQuestion extends Question {

    public YesNoQuestion(String question, String correctAnswer, ObservableList<CharSequence> answers, String category, String feedback) {
        super(question, correctAnswer, answers, category, feedback);
        questionType = new YesNo();
    }
}
