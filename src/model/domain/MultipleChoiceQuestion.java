package model.domain;

import java.util.ArrayList;

import javafx.collections.ObservableList;
import model.strategyQuestion.MultipleChoice;

public class MultipleChoiceQuestion extends Question{

    public MultipleChoiceQuestion(String question, String correctAnswer, ObservableList<CharSequence> answers, String category, String feedback) {
        super(question, correctAnswer, answers, category, feedback);
        questionType = new MultipleChoice();
    }
}
