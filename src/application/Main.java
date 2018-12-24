package application;

import controller.Controller;
import handler.startTestHandler;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.db.CategoryDB;
import model.db.CategoryDBlocal;
import view.panels.AssesMainPane;
import view.panels.CategoryDetailPane;
import view.panels.CategoryOverviewPane;
import view.panels.MessagePane;
import view.panels.QuestionDetailPane;
import view.panels.QuestionOverviewPane;
import view.panels.TestPane;

public class Main extends Application {
	QuestionOverviewPane questionOverviewPane;
	CategoryOverviewPane categoryOverviewPane;
	MessagePane messagePane;

	@Override
	public void start(Stage primaryStage) {

		Controller controller = Controller.getInstance();

		try {
			questionOverviewPane = new QuestionOverviewPane();
			categoryOverviewPane = new CategoryOverviewPane();
			messagePane = MessagePane.getInstance();

			messagePane.setStartAction(new startTestHandler());

			controller.getService().addObserver(questionOverviewPane);
			controller.getService().addObserver(categoryOverviewPane);

			Group root = new Group();
			Scene scene = new Scene(root, 750, 400);

			BorderPane borderPane = new AssesMainPane(messagePane, categoryOverviewPane, questionOverviewPane);
			borderPane.prefHeightProperty().bind(scene.heightProperty());
			borderPane.prefWidthProperty().bind(scene.widthProperty());

			root.getChildren().add(borderPane);
			primaryStage.setScene(scene);
			primaryStage.sizeToScene();
			primaryStage.setTitle("Evaluation");

			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
