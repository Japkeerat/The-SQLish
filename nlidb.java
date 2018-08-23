import javafx.application.Application;
import javafx.stage.Stage;

/**
 * @author Japkeerat Singh
 *
 * This class is responsible for launching of the application.
 * */
public class nlidb extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Window window = new Window();
        window.createMainFrame(primaryStage);
    }
}
