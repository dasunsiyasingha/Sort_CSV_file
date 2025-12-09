import javafx.application.Application; 
import javafx.geometry.Insets; 
import javafx.scene.Scene; 
import javafx.scene.control.Button; 
import javafx.scene.control.Label; 
import javafx.scene.control.TextArea; 
import javafx.scene.layout.GridPane; 
import javafx.scene.layout.HBox; 
import javafx.scene.layout.VBox; 
import javafx.stage.Stage; 
 
public class Main extends Application { 
 
    // Sorting block references 
    private SortBlock insertionUI; 
    private SortBlock shellUI; 
    private SortBlock mergeUI; 
    private SortBlock quickUI; 
    private SortBlock heapUI; 
 
    @Override 
    public void start(Stage primaryStage) { 
        primaryStage.setTitle("Sorting Algorithm Performance"); 
 
        // --- File Selection Row --- 
        Button btnBrowse = new Button("Click to browse file"); 
        btnBrowse.setPrefWidth(250); 
 
        Button btnShowColumns = new Button("display the available 
columns"); 
        btnShowColumns.setPrefWidth(250); 
 
        Button btnSort = new Button("To Sort"); 
        btnSort.setStyle("-fx-background-color: #4AA3FF; -fx-text-fill: 
white; -fx-font-weight: bold;"); 
 
        HBox topRow = new HBox(10, btnBrowse, btnShowColumns, btnSort); 
        topRow.setPadding(new Insets(10)); 
 
        // --- Error Message --- 
        Label errorMsg = new Label("Csv file or selected column has error 
display it after click 'To Sort' button"); 
        errorMsg.setStyle("-fx-text-fill: red; -fx-background-color: 
#F9D6D5; -fx-padding: 8;"); 
        errorMsg.setVisible(false); 
 
        // --- Create Sorting Result Blocks --- 
        insertionUI = createSortBlock("Insertion Sort"); 
        shellUI     = createSortBlock("Shell Sort"); 
        mergeUI     = createSortBlock("Merge Sort"); 
        quickUI     = createSortBlock("Quick Sort"); 
        heapUI      = createSortBlock("Heap Sort"); 
 
        GridPane results = new GridPane(); 
        results.setHgap(20); 
        results.setVgap(20); 
        results.setPadding(new Insets(10)); 
 
        results.add(insertionUI.block, 0, 0); 
        results.add(shellUI.block,     1, 0); 
        results.add(mergeUI.block,     2, 0); 
        results.add(quickUI.block,     3, 0); 
        results.add(heapUI.block,      4, 0); 
 
        // --- Best Algorithm Section --- 
        Label bestAlgo = new Label("best-performing algorithm"); 
        bestAlgo.setStyle("-fx-background-color: #A8F5A2; -fx-padding: 
12; -fx-background-radius: 10; -fx-font-size: 14;"); 
 
        VBox root = new VBox(20, topRow, errorMsg, results, bestAlgo); 
        root.setPadding(new Insets(20)); 
 
        Scene scene = new Scene(root, 1200, 750); 
        primaryStage.setScene(scene); 
        primaryStage.show(); 
 
        // --- Sorting Button Action --- 
        btnSort.setOnAction(e -> runSorting()); 
    } 
 
    // --- Sorting Block Object (To Store References) --- 
    private static class SortBlock { 
        VBox block; 
        TextArea output; 
        Label execTime; 
    } 
 
    // Create a UI block for one sorting method 
    private SortBlock createSortBlock(String title) { 
        SortBlock ui = new SortBlock(); 
 
        Label lblTitle = new Label(title); 
        lblTitle.setStyle("-fx-font-weight: bold; -fx-font-size: 14;"); 
 
        ui.output = new TextArea(); 
        ui.output.setPrefSize(200, 150); 
 
        ui.execTime = new Label("Execution Time: -"); 
        ui.execTime.setStyle("-fx-background-color: #A9CCF9; -fx-padding: 
6; -fx-font-weight: bold;"); 
        ui.execTime.setMaxWidth(Double.MAX_VALUE); 
 
        ui.block = new VBox(5, lblTitle, ui.output, ui.execTime); 
        ui.block.setPadding(new Insets(10)); 
        ui.block.setStyle("-fx-background-color: #D9E8F7; -fx-padding: 
15; -fx-background-radius: 8;"); 
 
        return ui; 
    } 
 
 
    private void runSorting() { 
 
        // Example sorted data for demonstration 
        String exampleSorted = "1, 5, 9, 11, 25"; 
 
        // Example execution times 
        insertionUI.output.setText(exampleSorted); 
        insertionUI.execTime.setText("Execution Time: 2 ms"); 
 
        shellUI.output.setText(exampleSorted); 
        shellUI.execTime.setText("Execution Time: 3 ms"); 
 
        mergeUI.output.setText(exampleSorted); 
        mergeUI.execTime.setText("Execution Time: 1 ms"); 
 
        quickUI.output.setText(exampleSorted); 
        quickUI.execTime.setText("Execution Time: 2 ms"); 
 
        heapUI.output.setText(exampleSorted); 
        heapUI.execTime.setText("Execution Time: 4 ms"); 
    } 
 
    public static void main(String[] args) { 
        launch(args); 
    } 
} 
