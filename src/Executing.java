import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

class Executing {

    String executeCommand(String sql) {
        if(sql.trim().charAt(0)=='S') {
           // querySelect(sql);
        }
        else if(sql.trim().charAt(0)=='D') {
            querying(sql);
            return "Deleted";
        }
        else if(sql.trim().charAt(0)=='I') {
            querying(sql);
            return "Data Inserted";
        }
        else if(sql.trim().charAt(0)=='U') {
            querying(sql);
            return "Data Updated";
        }
        return " ";
    }

    private void querying(String sql) {
        Logger logger = Logger.getLogger("ErrorLog");
        FileHandler handle;
        try {
            handle = new FileHandler("ErrorLog.log");
            logger.addHandler(handle);
            SimpleFormatter formatter = new SimpleFormatter();
            handle.setFormatter(formatter);
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection("jdbc:sqlite:AppDB.sqlite");
            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            connection.commit();
            statement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException | IOException ex) {
            logger.info(String.valueOf(ex));
            logger.setLevel(Level.SEVERE);
            Error error = new Error();
            Stage s = new Stage();
            try {
                error.errorDisplay(s);
            } catch (Exception e) {
                logger.info(String.valueOf(e));
            }
        }
    }

//    private void querySelect(String sql) {
//        try {
//            Class.forName("org.sqlite.JDBC");
//            Connection connection = DriverManager.getConnection("jdbc:sqlite:AppDB.sqlite");
//            connection.setAutoCommit(false);
//            Statement statement = connection.createStatement();
//            String sqlArray[] = sql.split(" ");
//            ResultSet set = statement.executeQuery(sql);
//            connection.commit();
//            if(sqlArray[1].equals("*")) {
//                while(set.next()) {
//                    String name = set.getString("Name");
//                    String age = set.getString("Age");
//                    String salary = set.getString("Salary");
//                    String job = set.getString("Job");
//                }
//            }
//            else {
//                while(set.next()) {
//
//                }
//            }
//            statement.close();
//            connection.close();
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//        }
//    }
}
