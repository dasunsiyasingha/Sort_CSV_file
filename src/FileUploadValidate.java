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

    public boolean fileValidate(ComboBox<String> columnSelector, Label errorMsg){
        // 1. Check file selected
        if (selectedCsvFile == null) {
            errorMsg.setText("Error: Please select a CSV file!");
            errorMsg.setVisible(true);
            return false;
        }

        // 2. Check file extension
        if (!selectedCsvFile.getName().toLowerCase().endsWith(".csv")) {
            errorMsg.setText("Error: Selected file is not a CSV file.");
            errorMsg.setVisible(true);
            return false;
        }
        return true;
    }

    public CSVFileData validateNumericColumn(int columnIndex, Label errorMsg) {
        try (BufferedReader br = new BufferedReader(new FileReader(selectedCsvFile))) {

            br.readLine(); // skip header

            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");

                if (columnIndex >= parts.length) {
                    errorMsg.setText("Not valid Index. Please Select correct column");
                    errorMsg.setVisible(true);
                    return null;
                }

                if (!parts[columnIndex].matches("-?\\d+(\\.\\d+)?")) {
                    errorMsg.setText("Not have Numerical Data. Please select another column..");
                    errorMsg.setVisible(true);
                    return null;
                }
            }
            errorMsg.setVisible(false);
            CSVFileData csvFileData = new CSVFileData(selectedCsvFile, columnIndex);
            return csvFileData;

        } catch (Exception ex) {
            errorMsg.setText("Have a some errors. Try again...");
            errorMsg.setVisible(true);
            return null;

        }
    }




}