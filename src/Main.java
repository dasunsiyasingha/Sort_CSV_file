import javafx.application.Application;
import javafx.geometry.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
//import javafx.geometry.Pos;

import javax.swing.text.Position;
import java.awt.*;
import java.io.File;
import java.util.Arrays;

public class Main extends Application {

    FileUploadValidate fileUploadValidate = new FileUploadValidate();
    boolean fileValidatedOk;
    boolean fileUploadOk, numericalColumn;

    private TextArea textAreaInsertion;
    private TextArea textAreaBubble;
    private TextArea textAreaMerge;
    private TextArea textAreaQuick;
    private TextArea textAreaHeap;

    private Label execTimeInsertion;
    private Label execTimeBubble;
    private Label execTimeMerge;
    private Label execTimeQuick;
    private Label execTimeHeap;

    private ComboBox<String> columnSelector = new ComboBox<>();

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Sorting Algorithm Performance");

        // --- File Selection Row ---
        Label selecFile = new Label("Select File");
        selecFile.setStyle("-fx-background-color: #A9CCF9; -fx-padding: 6; -fx-font-weight: bold;");
        selecFile.setMaxWidth(Double.MAX_VALUE);
        Button btnBrowse = new Button("Click to browse file");
        btnBrowse.setPrefWidth(250);

        Label selecCol = new Label("Select Column");
        selecCol.setStyle("-fx-background-color: #A9CCF9; -fx-padding: 6; -fx-font-weight: bold;");
        selecCol.setMaxWidth(Double.MAX_VALUE);




        Button btnSort = new Button("To Sort");
        btnSort.setStyle("-fx-background-color: #4AA3FF; -fx-text-fill: white; -fx-font-weight: bold;");

        HBox topRow = new HBox(10, selecFile, btnBrowse, selecCol);
        topRow.setPadding(new Insets(10));
        topRow.getChildren().add(columnSelector);
        topRow.getChildren().add(btnSort);

        // --- Error Message ---
        Label errorMsg = new Label("Csv file or selected column has error display it after click 'To Sort' button");
        errorMsg.setStyle("-fx-text-fill: red; -fx-background-color: #F9D6D5; -fx-padding: 8; -fx-border-radius: 5; -fx-background-radius: 5;");
        errorMsg.setVisible(false);


        textAreaBubble = new TextArea();
        textAreaInsertion = new TextArea();
        textAreaMerge = new TextArea();
        textAreaQuick = new TextArea();
        textAreaHeap = new TextArea();

        execTimeInsertion = new Label("execution time ");
        execTimeBubble  = new Label("execution time ");
        execTimeMerge = new Label("execution time ");
        execTimeQuick = new Label("execution time ");
        execTimeHeap = new Label("execution time ");

        // --- Sorting Results Blocks ---
        VBox block1 = createSortBlock("Insertion Sort", textAreaInsertion, execTimeInsertion);
        VBox block2 = createSortBlock("Bubble Sort", textAreaBubble, execTimeBubble);
        VBox block3 = createSortBlock("Merge Sort", textAreaMerge, execTimeMerge);
        VBox block4 = createSortBlock("Quick Sort", textAreaQuick, execTimeQuick);
        VBox block5 = createSortBlock("Heap Sort", textAreaHeap, execTimeHeap);

        VBox chartContainer = new VBox();
        chartContainer.setPadding(new Insets(20));
        chartContainer.setSpacing(10);

        GridPane results = new GridPane();
        results.setHgap(20);
        results.setVgap(20);
        results.setPadding(new Insets(10));

        results.add(block1, 0, 0);
        results.add(block2, 1, 0);
        results.add(block3, 0, 1);
        results.add(block4, 1, 1);
        results.add(block5, 0, 2);
        results.add(chartContainer, 1,2);



        // --- Best Algorithm Section ---
        Label bestAlgo = new Label("Identify and display the best-performing algorithm based on the shortest execution time.");
        bestAlgo.setStyle("-fx-background-color: #A8F5A2; -fx-padding: 12; -fx-background-radius: 10; -fx-font-size: 14;");

        VBox root = new VBox(20, topRow, errorMsg, results, bestAlgo);
        root.setPadding(new Insets(20));

        Scene scene = new Scene(root, 900, 750);
        primaryStage.setScene(scene);
        primaryStage.show();

        btnBrowse.setOnAction(e -> {
            fileUploadOk = fileUploadValidate.fileUpload(primaryStage, errorMsg);

            if(fileUploadOk){
                fileValidatedOk = fileUploadValidate.fileValidate(columnSelector, errorMsg);

                if(fileValidatedOk){
                    fileUploadValidate.loadCSVColumns(columnSelector, errorMsg);
                }
            }

        });

        btnSort.setOnAction(e -> {
            if(columnSelector.getSelectionModel().getSelectedIndex() != -1){
                errorMsg.setVisible(false);
                CSVFileData csvFileInfo = fileUploadValidate.validateNumericColumn(columnSelector.getSelectionModel().getSelectedIndex(), errorMsg);
                if(csvFileInfo != null){
                    ExtractData extractData = new ExtractData(csvFileInfo.file, csvFileInfo.Index);
                    arr = extractData.numericValues;
                    // System.out.println(Arrays.toString(arr));
                    // Run all sorting algorithms
                    double[] bubble = SortAlgorithms.bubbleSort(arr);
                    double[] insertion = SortAlgorithms.insertionSort(arr);
                    double[] merge = SortAlgorithms.mergeSort(arr);
                    double[] quick = SortAlgorithms.quickSort(arr);
                    double[] heap = SortAlgorithms.heapSort(arr);

                    Long bubbleTime = SortAlgorithms.bubbleTime;
                    Long insertionTime = SortAlgorithms.insertionTime;
                    Long mergeTime = SortAlgorithms.mergeTime;
                    Long quickTime = SortAlgorithms.quickTime;
                    Long heapTime = SortAlgorithms.heapTime;

                    execTimeBubble.setText("execution time " + bubbleTime.toString() + " ns");
                    execTimeInsertion.setText("execution time " + insertionTime.toString() + " ns");
                    execTimeMerge.setText("execution time " + mergeTime.toString() + " ns");
                    execTimeQuick.setText("execution time " + quickTime.toString() + " ns");
                    execTimeHeap.setText("execution time " + heapTime.toString() + " ns");

                    // Convert array to text
                    String bubbleText = Arrays.toString(bubble);
                    String insertionText = Arrays.toString(insertion);
                    String mergeText = Arrays.toString(merge);
                    String quickText = Arrays.toString(quick);
                    String heapText = Arrays.toString(heap);

                    // Show in TextArea
                    textAreaBubble.setText(bubbleText);
                    textAreaInsertion.setText(insertionText);
                    textAreaMerge.setText(mergeText);
                    textAreaQuick.setText(quickText);
                    textAreaHeap.setText(heapText);
                }
            }else{
                errorMsg.setText("Please select a column...");
                errorMsg.setVisible(true);
            }

        });

    }

    private VBox createSortBlock(String title, TextArea textArea, Label execTime) {
        Label lblTitle = new Label(title);
        lblTitle.setStyle("-fx-font-weight: bold; -fx-font-size: 14;");

        textArea.setPrefSize(700, 400);
        textArea.setWrapText(true);

        execTime.setStyle("-fx-background-color: #A9CCF9; -fx-padding: 6; -fx-font-weight: bold;");
        execTime.setMaxWidth(Double.MAX_VALUE);

        VBox box = new VBox(5, lblTitle, textArea, execTime);
        box.setPadding(new Insets(10));
        box.setStyle("-fx-background-color: #D9E8F7; -fx-padding: 15; -fx-background-radius: 8;");
        return box;
    }

    public static void main(String[] args) {
        launch(args);


    }


}