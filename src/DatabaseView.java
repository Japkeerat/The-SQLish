import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

class DatabaseView {

    private Connection connection;
    private Statement statement;
    private TableView<Person> table = new TableView<>();
    private ObservableList<Person> data = FXCollections.observableArrayList();

    void createView(Stage primaryStage) {
        primaryStage.setTitle("NL Interface for DBMS");
        primaryStage.setResizable(true);
        primaryStage.setFullScreen(false);

        StaticSection section = new StaticSection();
        GridPane grid = section.pane();
        MenuBar menuBar = section.toolbar(primaryStage);

        Text title = new Text("Database");
        title.setId("Title");
        title.setTextAlignment(TextAlignment.CENTER);
        grid.add(title,0,0,2,1);

        table = getTable();
        table = addDataToTable(table);
        grid.add(table,0,1,2,10);

        Scene scene = new Scene(new VBox(), 1000, 700);
        scene.getStylesheets().add(Window.class.getResource("Styling.css").toExternalForm());
        ((VBox) scene.getRoot()).getChildren().addAll(menuBar, grid);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private TableView<Person> getTable() {
        table.setEditable(false);

        return table;
    }

    private TableView<Person> addDataToTable(TableView<Person> table) {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:AppDB.sqlite");
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            String sql = "SELECT * FROM APPLICATION";
            ResultSet set = statement.executeQuery(sql);
            connection.commit();
            while(set.next()) {
                String name = set.getString("Name");
                String age = set.getString("Age");
                String salary = set.getString("Salary");
                String job = set.getString("Job");
                //Person person = new Person(name,age,salary,job);
                data.add(new Person(name, age, salary, job));
            }
            table.setItems(data);
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
        TableColumn NameCol = new TableColumn("Name");
        NameCol.setCellValueFactory(new PropertyValueFactory("Name"));
        TableColumn AgeCol = new TableColumn("Name");
        AgeCol.setCellValueFactory(new PropertyValueFactory("Name"));
        TableColumn SalaryCol = new TableColumn("Name");
        SalaryCol.setCellValueFactory(new PropertyValueFactory("Name"));
        TableColumn JobCol = new TableColumn("Name");
        JobCol.setCellValueFactory(new PropertyValueFactory("Name"));
        table.getColumns().addAll(NameCol, AgeCol, SalaryCol, JobCol);
        return table;
    }
}

class Person {
    private String name;
    private String age;
    private String salary;
    private String job;

    public Person(String n, String a, String s, String j) {
        this.name = n;
        this.age = a;
        this.salary = s;
        this.job = j;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getSalary() {
        return salary;
    }

    public String getJob() {
        return job;
    }
}

