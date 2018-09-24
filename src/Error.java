import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public class Error {
    public void errorDisplay(Stage primaryStage) throws Exception {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Some Error occured. Log file generated.", ButtonType.OK);
        alert.showAndWait();
        if(alert.getResult()==ButtonType.OK) {
            primaryStage.close();
        }
    }
}
