import javafx.stage.Stage;

import java.io.*;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

class SQLVersion {

    private Logger logger = Logger.getLogger("ErrorLog");
    private FileHandler handle;

    String convertToSQL(String command) {
        String sql;
        tempStoreCommand(command);
        try {
            handle = new FileHandler("ErrorLog.log");
            logger.addHandler(handle);
            SimpleFormatter formatter = new SimpleFormatter();
            handle.setFormatter(formatter);
            Process process = Runtime.getRuntime().exec("python3 TheModel.py");
        } catch (IOException ex) {
            logger.setLevel(Level.SEVERE);
            logger.info(String.valueOf(ex));
            Error error = new Error();
            Stage s = new Stage();
            try {
                error.errorDisplay(s);
            } catch (Exception e) {
                logger.info(String.valueOf(e));
            }
        }
        sql = tempReadCommand();
        deleteTempFile();
        return sql;
    }

    private void tempStoreCommand(String command) {
        try {
            handle = new FileHandler("ErrorLog.log");
            logger.addHandler(handle);
            SimpleFormatter formatter = new SimpleFormatter();
            handle.setFormatter(formatter);
            FileWriter writer = new FileWriter("temp.txt");
            writer.write(command);
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private String tempReadCommand() {
        String sql = "";
        try {
            handle = new FileHandler("ErrorLog.log");
            logger.addHandler(handle);
            SimpleFormatter formatter = new SimpleFormatter();
            handle.setFormatter(formatter);
            BufferedReader br = new BufferedReader(new FileReader("temp.txt"));
            sql = br.readLine();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sql;
    }

    private void deleteTempFile() {
        File file = new File("temp.txt");
        if(file.exists()) {
            file.delete();
        }
    }
}
