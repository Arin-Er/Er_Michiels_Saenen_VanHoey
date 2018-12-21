package view.panels;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.db.DbService;
import model.domain.Question;

public class TestPane extends GridPane {
	private Label questionField;
	private Button submitButton;
	private ToggleGroup statementGroup;
	private DbService dbService;
	private int counter = 0;
	private RadioButton answerRadioButton;
	private ArrayList<Question> questionsToAsk;

	public TestPane (DbService dbService){
		this.dbService = dbService;

		questionsToAsk = dbService.getQuestions();

		this.setPrefHeight(300);
		this.setPrefWidth(750);
		
		this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);

        setItems();

	}

	public void setItems(){
		//get Random question
		int random = getRandomNumberInRange(0, questionsToAsk.size() - 1);
		Question question = questionsToAsk.get(random);
		//shuffle answers
		Collections.shuffle(question.getAnswers());
		//show question on screen
		questionField = new Label(question.getQuestion());
		add(questionField, 0, 0, 1 ,1);
		//show answer options
		statementGroup = new ToggleGroup();
		for(CharSequence s : question.getAnswers()){
			//new RadioButton with value from answers
			RadioButton rb = new RadioButton(s.toString());
			rb.setToggleGroup(statementGroup);
			add(rb, 0, 1 + counter, 1, 1);
			counter += 1; // counter added so answers arent on top of eachother
		}

		//add submit button
		submitButton = new Button("Submit");
		add(submitButton, 0, 1 + counter, 1, 1); // also has counter otherwise will be in answer
		submitButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				questionsToAsk.remove(question);
				RadioButton answerButton = (RadioButton) statementGroup.getSelectedToggle();
				String answer = answerButton.getText();
				
				if(answer.equals(question.getCorrectAnswer())){
					//score needs to be increaser per category
					System.out.println("score + 1");
				}
				if(questionsToAsk.isEmpty()){
					Stage stage = (Stage) submitButton.getScene().getWindow();
					stage.close();
				}
				else{
					reset();
					setItems();
				}
			}
		});

	}

	public void reset(){
		this.getChildren().clear();
		this.counter = 0;
	}

	//voor het selecteren van een random vraag
	private static int getRandomNumberInRange(int min, int max) {
		if (min > max) {
			throw new IllegalArgumentException("max must be greater than min");
		}
		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}

	public List<String> getSelectedStatements() {
		 List<String> selected = new ArrayList<String>();
		if(statementGroup.getSelectedToggle()!=null){
			selected.add(statementGroup.getSelectedToggle().getUserData().toString());
		}
		return selected;
	}
}


