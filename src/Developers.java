import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.*;
import java.io.FileInputStream;
import java.net.URL;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

class Developers {

    void meetDevelopers(Stage primaryStage) {
        primaryStage.setResizable(true);
        primaryStage.setFullScreen(false);

        StaticSection section = new StaticSection();
        GridPane grid = section.pane();
        MenuBar menuBar = section.toolbar(primaryStage);
        primaryStage.setTitle(section.getName());
        primaryStage.getIcons().add(new Image("file:logo.png"));

        Text title = new Text("Meet the Developers");
        title.setId("Title");
        grid.add(title,0,0,2,1);
        GridPane.setHalignment(title, HPos.CENTER);

        grid.add(addImage("Japkeerat.jpg",350,325),0,1);
        grid.add(addImage("Raghav.png",350,300),1,1);

        Text name = new Text("Japkeerat Singh");
        name.setId("Name");
        grid.add(name,0,2);
        GridPane.setHalignment(name, HPos.CENTER);

        name = new Text("Raghav Mittal");
        name.setId("Name");
        grid.add(name,1,2);
        GridPane.setHalignment(name, HPos.CENTER);

        HBox iconBox = addIcon(0);
        grid.add(iconBox,0,3);
        GridPane.setHalignment(iconBox, HPos.CENTER);

        iconBox = addIcon(1);
        grid.add(iconBox,1,3);
        GridPane.setHalignment(iconBox, HPos.CENTER);

        Scene scene = new Scene(new VBox(), 1000, 700);
        scene.getStylesheets().add(Window.class.getResource("Styling.css").toExternalForm());
        ((VBox) scene.getRoot()).getChildren().addAll(menuBar, grid);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private ImageView addImage(String picAddress, int height, int width) {
        Image image;
        Logger logger = Logger.getLogger("ErrorLog");
        FileHandler handle;
        try {
            handle = new FileHandler("ErrorLog.log");
            logger.addHandler(handle);
            SimpleFormatter formatter = new SimpleFormatter();
            handle.setFormatter(formatter);
            image = new Image(new FileInputStream(picAddress));
            ImageView view = new ImageView(image);
            view.setFitHeight(height);
            view.setFitWidth(width);
            view.setPreserveRatio(false);
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

    private HBox addIcon(int index) {
        HBox box = new HBox(10);
        ImageView view1 = addImage("linkedin.png",30,50);
        ImageView view2 = addImage("github.png",30,30);
        view1.setCursor(Cursor.HAND);
        view2.setCursor(Cursor.HAND);
        view1.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Logger logger = Logger.getLogger("ErrorLog");
                FileHandler handle;
                try {
                    handle = new FileHandler("ErrorLog.log");
                    logger.addHandler(handle);
                    SimpleFormatter formatter = new SimpleFormatter();
                    handle.setFormatter(formatter);
                    if (index == 0) {
                        Desktop.getDesktop().browse(new URL("https://www.linkedin.com/in/japkeeratsingh").toURI());
                    }
                    else {
                        Desktop.getDesktop().browse(new URL("https://www.linkedin.com/in/raghav-mittal-4a8098132").toURI());
                    }
                }
                catch (Exception ex) {
                    logger.info(String.valueOf(ex));
                    Error error = new Error();
                    Stage s = new Stage();
                    try {
                        error.errorDisplay(s);
                    } catch (Exception e) {
                        logger.info(String.valueOf(e));
                    }
                }
            }
        });
        view2.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Logger logger = Logger.getLogger("ErrorLog");
                FileHandler handle;
                try {
                    handle = new FileHandler("ErrorLog.log");
                    logger.addHandler(handle);
                    SimpleFormatter formatter = new SimpleFormatter();
                    handle.setFormatter(formatter);
                    if (index == 0) {
                        Desktop.getDesktop().browse(new URL("https://www.github.com/Japkeerat").toURI());
                    }
                    else {
                        Desktop.getDesktop().browse(new URL("https://www.github.com/ragna211").toURI());
                    }
                }
                catch (Exception ex) {
                    logger.info(String.valueOf(ex));
                    Error error = new Error();
                    Stage s = new Stage();
                    try {
                        error.errorDisplay(s);
                    } catch (Exception e) {
                        logger.info(String.valueOf(e));
                    }
                }
            }
        });
        box.getChildren().addAll(view1,view2);
        box.setAlignment(Pos.CENTER);
        return box;
    }
}
