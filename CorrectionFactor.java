import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CorrectionFactor {

    public void isCorrect(String command, String sql) {

    }

    public void isIncorrect(String command) {
        createDialogBox();
    }

    private void createDialogBox() {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(20);
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(25,25,25,25));

        Text title = new Text("Damn!!");
        title.setId("ErrorTitle");
        grid.add(title,0,0,2,1);

        Text error = new Text("We hate it when we are wrong. We are striving to do better.\n" +
                "Help us improve by answering the following question.");
        grid.add(error,0,1,2,1);

        Text question = new Text("What does the command you entered closely relate\n" +
                "to from the following options?");
        grid.add(question,0,2,2,1);

        RadioButton button1 = new RadioButton("Selecting from a database");
        RadioButton button2 = new RadioButton("Updating existing database");
        RadioButton button3 = new RadioButton("Inserting to a database");
        RadioButton button4 = new RadioButton("Deleting from a database");

        ToggleGroup radioGroup = new ToggleGroup();
        button1.setToggleGroup(radioGroup);
        button2.setToggleGroup(radioGroup);
        button3.setToggleGroup(radioGroup);
        button4.setToggleGroup(radioGroup);

        button1.setCursor(Cursor.HAND);
        button2.setCursor(Cursor.HAND);
        button3.setCursor(Cursor.HAND);
        button4.setCursor(Cursor.HAND);

        VBox box = new VBox(button1,button2,button3,button4);
        grid.add(box,0,3);

        Button submitButton = new Button("Submit");
        submitButton.setCursor(Cursor.HAND);
        HBox hBox = new HBox(10);
        hBox.getChildren().addAll(submitButton);
        grid.add(hBox,0,4);

        submitButton.setOnAction(event -> {
            RadioButton button = (RadioButton) radioGroup.getSelectedToggle();
        });

        Scene dialogScene = new Scene(grid,450,300);
        dialogScene.getStylesheets().addAll(CorrectionFactor.class.getResource("Styling.css").toExternalForm());
        dialog.setScene(dialogScene);
        dialog.show();
    }
}
