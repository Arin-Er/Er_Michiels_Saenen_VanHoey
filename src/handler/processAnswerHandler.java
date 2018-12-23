package handler;

import controller.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.panels.TestPane;

public class processAnswerHandler implements EventHandler<ActionEvent> {
    Controller controller = Controller.getInstance();
    Stage stage;
    TestPane testPane;

    public processAnswerHandler(TestPane testPane, Stage stage){
        this.testPane = testPane;
        this.stage = stage;
    }

    @Override
    public void handle(ActionEvent e){
        try{
            controller.getScore().controlAnwser(testPane.getAnswer());
            if(controller.getScore().isLastQuestion()){
                System.out.println(controller.getScore().toString());
                stage.close();
                controller.getEvaluation().setProperty("test", "true");
                controller.resetQuestionNumber();
            }else{
                stage.close();
                Stage stage = new Stage();
                TestPane testPane = new TestPane(controller);
                Scene scene = new Scene(testPane);
                stage.setScene(scene);
                stage.show();
                testPane.setProcessAnswerAction(new processAnswerHandler(testPane, stage));
            }
        }catch(Exception exception){
            System.out.println(exception.getMessage());
        }
    }
}
