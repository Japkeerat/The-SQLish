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

class About {

    void showAboutThis(Stage primaryStage) {
        primaryStage.setTitle("NL Interface for DBMS");
        primaryStage.setResizable(true);
        primaryStage.setFullScreen(false);

        StaticSection section = new StaticSection();
        GridPane grid = section.pane();
        MenuBar menuBar = section.toolbar(primaryStage);

        Text title = new Text("About");
        title.setId("Title");
        grid.add(title,0,0,2,1);

        TextArea area = new TextArea();
        area.setEditable(false);
        area = settingAboutText(area);
        grid.add(area,0,1,2,13);

        Scene scene = new Scene(new VBox(), 1000, 700);
        scene.getStylesheets().add(Window.class.getResource("Styling.css").toExternalForm());
        ((VBox) scene.getRoot()).getChildren().addAll(menuBar, grid);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private TextArea settingAboutText(TextArea area) {
        File file = new File("About.txt");
        area.setWrapText(true);

        try {
            BufferedReader br = new BufferedReader((new FileReader(file)));
            String s;
            while((s=br.readLine())!=null) {
                area.appendText(s);
                area.appendText("\n");
            }
        }
        catch(Exception ex) {
            System.out.println("Exception");
        }
        return area;
    }
}
