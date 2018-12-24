package view.panels;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import controller.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.domain.Category;
import model.domain.Question;

public class TestPane extends GridPane {
	private Label questionField;
	private Button submitButton;
	private ToggleGroup statementGroup;
	private Controller controller = Controller.getInstance();
	private ArrayList<Question> questionsToAsk;
	private ArrayList<Category> categories;
	private int counter = 0;
	private int score = 0;

	
	public TestPane (Controller controller){
		this.controller = controller;

		questionsToAsk = controller.getQuestions();
		categories = controller.getCategories();
		this.setPrefHeight(300);
		this.setPrefWidth(750);
		
		this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);

        questionField = new Label();
        questionField.setText(controller.getNextQuestion());
        add(questionField, 0, 0, 1,1);

        statementGroup = new ToggleGroup();

		ArrayList<String> answers = getAnswersAsList(controller.getQuestion(questionField.getText()).getAnswers());

        Collections.shuffle(answers);
		for(CharSequence s : answers){
			RadioButton rb = new RadioButton(s.toString());
			rb.setUserData(s.toString());
			rb.setToggleGroup(statementGroup);
			add(rb, 0, 1 + counter);
			counter ++;
		}

		submitButton = new Button("Submit");
		add(submitButton, 0, 1+ counter, 1, 1);
	}

	public ArrayList<String> getAnswersAsList(ObservableList<CharSequence> list){
		ArrayList<String> answers = new ArrayList<String>();

		for(CharSequence s : list){
			answers.add(s.toString());
		}

		return answers;
	}


	public String getAnswer(){
		String answer = "";
		try{
			answer = statementGroup.getSelectedToggle().getUserData().toString();
		}catch(NullPointerException e){
			System.out.println(e.getMessage());
		}
		return answer;
	}

	public void setProcessAnswerAction(EventHandler<ActionEvent> processAnswerAction) {
		submitButton.setOnAction(processAnswerAction);
	}
	public void reset(){
		this.getChildren().clear();
		this.counter = 0;
	}

	public List<String> getSelectedStatements() {
		 List<String> selected = new ArrayList<String>();
		if(statementGroup.getSelectedToggle()!=null){
			selected.add(statementGroup.getSelectedToggle().getUserData().toString());
		}
		return selected;
	}
	private static int getRandomNumberInRange(int min, int max) {
		if (min > max) {
			throw new IllegalArgumentException("max must be greater than min");
		}
		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}

}
