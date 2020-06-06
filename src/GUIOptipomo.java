import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import java.io.FileNotFoundException;

/**
 * @author Jan Olav Berg
 */
public class GUIOptipomo extends Application {
    private StackPane root = new StackPane();
    private Text mode = new Text("mode");
    private Text timer = new Text("timer");
    private Text instructions = new Text("instructions");
    private Text totalWork = new Text("totalWork");
    private Text totalPause = new Text("total pause");
    private Scene scene;


    private Parent createContent() {
        root.setPrefSize(600, 400);
        root.getChildren().addAll(mode, timer, instructions, totalWork, totalPause);
        setLayout();
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update(now);
            }
        };
        timer.start();
        return root;
    }
    private void setLayout() {
        root.setAlignment(mode, Pos.TOP_LEFT);
        root.setAlignment(timer, Pos.CENTER);
        root.setAlignment(instructions, Pos.BOTTOM_LEFT);
        root.setAlignment(totalWork, Pos.TOP_RIGHT);
        root.setAlignment(totalPause, Pos.BOTTOM_RIGHT);
    }

    private void update(long now) {
    }

    @Override
    public void start(Stage stage) throws Exception {
        scene = new Scene(createContent());
        stage.setTitle("Optipomo");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
