package view.panels;

import java.util.ArrayList;
import java.util.List;

import controller.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import model.domain.Question;

public class TestPane extends GridPane {
	private Label questionField;
	private Button submitButton;
	private ToggleGroup statementGroup;
	private Controller controller = Controller.getInstance();
	private int counter = 0;
	
	public TestPane (){
		this.setPrefHeight(300);
		this.setPrefWidth(750);
		
		this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);

        //test:
		Question q = controller.getQuestion("Welk patroon definieert een familie van algorimtes, kapselt ze in en maakt ze verwisselbaar?");
		questionField = new Label();
		questionField.setText(q.getQuestion());
		add(questionField, 0, 0, 1, 1);
		
		statementGroup = new ToggleGroup();
		//vragen moeten random worden gesteld, en mogen niet meer dan 1 keer worden gesteld

		//antwoorden moeten nog gerandomized worden, zodat ze niet altijd in dezelfde volgorde komen
		for(CharSequence s : q.getAnswers()){
			//voor elk antwoord moet er een radiobutton worden aangemaakt, die dan aan de toggleGroup moeten worden toegevoegd
			RadioButton r = new RadioButton(s.toString());
			r.setToggleGroup(statementGroup);
			add(r,0, 1 + counter, 1, 1);
			counter += 1; // counter zorgt ervoor dat de vragen niet op elkaar komen te staan
		}


		submitButton = new Button("Submit");
		add(submitButton, 0, 1 + counter,1, 1 );
	}
	
	public void setProcessAnswerAction(EventHandler<ActionEvent> processAnswerAction) {
		submitButton.setOnAction(processAnswerAction);
	}

	public List<String> getSelectedStatements() {
		 List<String> selected = new ArrayList<String>();
		if(statementGroup.getSelectedToggle()!=null){
			selected.add(statementGroup.getSelectedToggle().getUserData().toString());
		}
		return selected;
	}
}
