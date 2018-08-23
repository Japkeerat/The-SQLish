import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 * @author Japkeerat Singh
 *
 * This class creates the interface where user inputs data and it is changed to its SQL
 * version and an ouput is shown.
 * In brief, this class is responsible for all of the GUI of the application.
 * TODO: Program buttons 'correct' and 'incorrect'.
 * TODO: Connect to Database.
 * */
public class Window {

    private String command;

    public void createMainFrame(Stage primaryStage) {
        primaryStage.setTitle("NL Interface for DBMS");
        primaryStage.setResizable(true);
        primaryStage.setFullScreen(false);

        StaticSection section = new StaticSection();
        GridPane grid = section.pane();
        MenuBar menuBar = section.toolbar(primaryStage);

        Text title = titleText();
        grid.add(title,0,0,2,1);

        Label fieldLabel = new Label("Enter command");
        fieldLabel.setId("Label");
        grid.add(fieldLabel,0,1);

        TextArea field = textField();
        grid.add(field,1,1);

        Button enterButton = new Button("Enter");
        Button clearButton = new Button("Clear");
        HBox enterButtonBox = buttonBox(clearButton, enterButton);
        enterButton.setCursor(Cursor.HAND);
        clearButton.setCursor(Cursor.HAND);
        grid.add(enterButtonBox,1,2);

        Label label1 = new Label("SQL of above command");
        label1.setVisible(false);
        label1.setId("Label");
        grid.add(label1,0,3);

        Label sqlVersion = new Label();
        grid.add(sqlVersion,1,3);

        Label label2 = new Label("Output");
        label2.setVisible(false);
        label2.setId("Label");
        grid.add(label2,0,4);

        Label dbOutput = new Label();
        grid.add(dbOutput,1,4);

        Label label3 = new Label("Is the outut correct?");
        label3.setVisible(false);
        label3.setId("Label");
        grid.add(label3,0,5);

        Button correctButton = new Button("Correct");
        Button incorrectButton = new Button("Incorrect");
        HBox correctionBox = buttonBox(correctButton,incorrectButton);
        correctionBox.setVisible(false);
        correctButton.setCursor(Cursor.HAND);
        incorrectButton.setCursor(Cursor.HAND);
        grid.add(correctionBox,1,5);

        enterButton.setOnAction(event -> {
            command = field.getText();
            SQLVersion sql = new SQLVersion();
            String sequel = sql.convertToSQL(command);
            label1.setVisible(true);
            sqlVersion.setText(sequel);
            label2.setVisible(true);
            dbOutput.setText("This is temporary output");
            label3.setVisible(true);
            correctionBox.setVisible(true);
        });

        clearButton.setOnAction(event -> field.clear());

        Scene scene = new Scene(new VBox(), 1000, 700);
        scene.getStylesheets().add(Window.class.getResource("Styling.css").toExternalForm());
        ((VBox) scene.getRoot()).getChildren().addAll(menuBar, grid);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Text titleText() {
        Text text = new Text();
        text.setText("Natural Language Interface for Database Processing");
        text.setId("Title");
        text.setTextAlignment(TextAlignment.CENTER);
        return text;
    }

    private TextArea textField() {
        TextArea field = new TextArea();
        field.setPromptText("Enter command");
        field.setCursor(Cursor.TEXT);
        field.setWrapText(true);
        field.setMaxSize(375,200);
        return field;
    }

    /*Because there can be variable number of buttons in a HBox*/
    private HBox buttonBox(Button... button) {
        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.BOTTOM_LEFT);
        buttonBox.getChildren().addAll(button);
        return buttonBox;
    }
}
