package model.strategyQuestion;

public class YesNo implements QuestionType {
    @Override
    public String getQuestionType() {
        return "Yes/No question";
    }
}
