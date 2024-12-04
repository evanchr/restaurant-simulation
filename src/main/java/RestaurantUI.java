import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class RestaurantUI extends Application {

    private TextArea logArea;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Restaurant Simulation");

        logArea = new TextArea();
        logArea.setEditable(false);

        Button startButton = new Button("Lancer la simulation");
        startButton.setOnAction(e -> lancerSimulation());

        VBox layout = new VBox(10, logArea, startButton);
        layout.setPrefSize(600, 400);

        Scene scene = new Scene(layout);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void lancerSimulation() {
        new Thread(() -> {
            new Restaurant();
        }).start();
    }

    public void log(String message) {
        javafx.application.Platform.runLater(() -> logArea.appendText(message + "\n"));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
