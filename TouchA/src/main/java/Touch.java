import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;



public class Touch extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        //creating a Group object
        Group group = new Group();

        //Creating a Scene by passing the group object, height and width
        Scene scene = new Scene(group, 600, 300);

        Text in = new Text("inn");
        Text out = new Text("out");
        in.setX(100);
        in.setY(100);
        out.setX(100);
        out.setY(50);

        group.getChildren().add(in);

        //setting color to the scene
        scene.setFill(Color.BROWN);

        //Setting the title to Stage.
        primaryStage.setTitle("Sample Application");

        //Adding the scene to Stage
        primaryStage.setScene(scene);

        //Displaying the contents of the stage
        primaryStage.show();

        scene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                default: in.setText(in.getText() + e.getText());
            }
        });

    }

    public static void main(String args[]) {
        launch(args);
    }
}