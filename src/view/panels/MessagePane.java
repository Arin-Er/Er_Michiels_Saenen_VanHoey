package view.panels;

import java.io.IOException;
import java.util.Observer;

import controller.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MessagePane extends GridPane {
	private Button testButton;
	private Controller controller = Controller.getInstance();
	private Text l;
	public MessagePane () throws IOException {
	    setBorder(new Border(new BorderStroke(Color.BLACK, 
	            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

		this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);
        
		testButton = new Button("Evaluate");
		l = new Text();
		//this.showEvaluation();
		testButton.setOnAction(new EventHandler<ActionEvent>() { //TODO remove or generalize
			
			@Override
			public void handle(ActionEvent event) {
				//pagina openen waar vragen kunne gesteld worden

				Stage stage = new Stage();
				TestPane testPane = new TestPane(controller);
				Scene scene = new Scene(testPane, 500, 250);
				controller.newTest(); // zo wordt score bijgehouden aan begin van test denk ik
				stage.setScene(scene);
				stage.show();
			}
		});
		add(testButton, 0,1,1,1);
		setHalignment(testButton, HPos.CENTER);
	}

	public void showEvaluation() throws IOException {
		if (controller.getEvaluation().getPropertyValue("test").equals("false")) {
			l.setText("You never did this Test!");

		}
		if (controller.getEvaluation().getPropertyValue("test").equals("true")) {
			l.setText("You already did this Test!");

		}
		if (controller.getQuestions().size() > 0 && controller.getQuestionNumber() == controller.getQuestions().size()) {
			if (controller.getEvaluation().getPropertyValue("feedbackType").equals("score")) {
				l.setText(controller.getScoreFeedback().toString());
				controller.getEvaluation().setProperty("feedbackType", "score");
			}
			if (controller.getEvaluation().getPropertyValue("feedbackType").equals("feedback")) {
				l.setText(controller.getScoreFeedback().toStringFeedback());
				controller.getEvaluation().setProperty("feedbackType", "feedback");

			}

		}
	}

}
