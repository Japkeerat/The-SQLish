import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Preloader {

    public void startLoading(Stage primaryStage) {

        StaticSection section = new StaticSection();
        primaryStage.setTitle(section.getName());

        GridPane grid = section.pane();

        Label title = new Label("The application is loading...");
        title.setId("PreloaderTitle");
        grid.add(title,0,0);
        GridPane.setHalignment(title, HPos.CENTER);

        grid.add(addImage("preloader.gif"),0,1);

        Scene scene = new Scene(grid, 400, 350);
        scene.getStylesheets().add(Window.class.getResource("Styling.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private ImageView addImage(String loaderAddress) {
        Image image;
        Logger logger = Logger.getLogger("ErrorLog");
        FileHandler handle;
        try {
            handle = new FileHandler("ErrorLog.log");
            logger.addHandler(handle);
            SimpleFormatter formatter = new SimpleFormatter();
            handle.setFormatter(formatter);
            image = new Image(new FileInputStream(loaderAddress));
            ImageView view = new ImageView(image);
            view.setFitHeight(150);
            view.setFitWidth(150);
            view.setPreserveRatio(false);
            GridPane.setHalignment(view, HPos.CENTER);
            return view;
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
        return null;
    }
}
