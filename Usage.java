import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Usage {

    public void tellUsingWay(Stage primaryStage) {
        primaryStage.setTitle("NL Interface for DBMS");
        primaryStage.setResizable(true);
        primaryStage.setFullScreen(false);

        StaticSection section = new StaticSection();
        GridPane grid = section.pane();
        MenuBar menuBar = section.toolbar(primaryStage);

        Scene scene = new Scene(new VBox(), 1000, 700);
        scene.getStylesheets().add(Window.class.getResource("Styling.css").toExternalForm());
        ((VBox) scene.getRoot()).getChildren().addAll(menuBar, grid);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
