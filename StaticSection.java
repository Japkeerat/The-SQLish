import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class StaticSection {

    public MenuBar toolbar(Stage primaryStage) {
        MenuBar bar = new MenuBar();

        Menu file = new Menu("File");

        MenuItem fileItem1 = new MenuItem("Home");
        fileItem1.setOnAction(event -> {
            Window window = new Window();
            window.createMainFrame(primaryStage);
        });

        MenuItem fileItem2 = new MenuItem("Database");
        fileItem2.setOnAction(event -> {
            DatabaseView view = new DatabaseView();
            view.createView(primaryStage);
        });

        MenuItem fileItem3 = new MenuItem("Exit");
        fileItem3.setOnAction(event -> System.exit(0));

        Menu help = new Menu("Help");

        MenuItem helpItem1 = new MenuItem("What is this?");
        helpItem1.setOnAction(event -> {
            About about = new About();
            about.showAboutThis(primaryStage);
        });

        MenuItem helpItem2 = new MenuItem("How to use?");
        helpItem2.setOnAction(event -> {
            Usage usage = new Usage();
            usage.tellUsingWay(primaryStage);
        });

        file.getItems().addAll(fileItem1, fileItem2, fileItem3);
        help.getItems().addAll(helpItem1, helpItem2);
        bar.getMenus().addAll(file, help);

        return bar;
    }

    public GridPane pane() {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(150);
        grid.setVgap(40);
        grid.setPadding(new Insets(25,25,25,25));
        return grid;
    }
}
