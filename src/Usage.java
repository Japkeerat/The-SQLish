import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

class Usage {

    void tellUsingWay(Stage primaryStage) {
        primaryStage.setTitle("NL Interface for DBMS");
        primaryStage.setResizable(true);
        primaryStage.setFullScreen(false);

        StaticSection section = new StaticSection();
        GridPane grid = section.pane();
        MenuBar menuBar = section.toolbar(primaryStage);

        Text title = new Text("Usage");
        title.setId("Title");
        grid.add(title,0,0,2,1);
        GridPane.setHalignment(title, HPos.CENTER);

        TextArea area = new TextArea();
        area.setEditable(false);
        area = settingUsageText(area);
        grid.add(area,0,1,2,13);

        Scene scene = new Scene(new VBox(), 1000, 700);
        scene.getStylesheets().add(Window.class.getResource("Styling.css").toExternalForm());
        ((VBox) scene.getRoot()).getChildren().addAll(menuBar, grid);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private TextArea settingUsageText(TextArea area) {
        File file = new File("Usage.txt");
        area.setWrapText(true);
        Logger logger = Logger.getLogger("ErrorLog");
        FileHandler handle;
        try {
            handle = new FileHandler("ErrorLog.log");
            logger.addHandler(handle);
            SimpleFormatter formatter = new SimpleFormatter();
            handle.setFormatter(formatter);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String s;
            while((s=br.readLine())!=null) {
                area.appendText(s);
                area.appendText("\n");
            }
        }
        catch(Exception ex) {
            logger.info(String.valueOf(ex));
            Error error = new Error();
            Stage s = new Stage();
            try {
                error.errorDisplay(s);
            } catch (Exception e) {
                logger.info(String.valueOf(e));
            }
        }
        return area;
    }
}
