import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class FileUploadValidate {
    private File selectedCsvFile;

    public boolean fileUpload(Stage primaryStage, Label errorMsg){
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        File file = fc.showOpenDialog(primaryStage);

        if (file != null) {
            selectedCsvFile = file;
            System.out.println("Selected CSV: " + file.getAbsolutePath());
            errorMsg.setVisible(false);
            return true;
        }
        return false;
    }

}