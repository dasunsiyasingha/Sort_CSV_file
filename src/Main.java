
import javafx.application.Application; 
import javafx.geometry.Insets; 
//import javafx.geometry.Pos; 
import javafx.scene.Scene; 
import javafx.scene.control.*; 
import javafx.scene.layout.*; 
import javafx.stage.Stage; 
 
public class Main extends Application { 
 
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
//        topRow.setAlignment(Pos.CENTER_LEFT); 
        topRow.setPadding(new Insets(10)); 
 
        // --- Error Message --- 
        Label errorMsg = new Label("Csv file or selected column has error 
display it after click 'To Sort' button"); 
        errorMsg.setStyle("-fx-text-fill: red; -fx-background-color: 
#F9D6D5; -fx-padding: 8; -fx-border-radius: 5; -fx-background-radius: 
5;"); 
        errorMsg.setVisible(false); 
 
        // --- TabPane with Sorting Result Blocks --- 
        TabPane tabPane = new TabPane(); 
        tabPane.getTabs().add(createSortTab("Insertion Sort")); 
        tabPane.getTabs().add(createSortTab("Shell Sort")); 
        tabPane.getTabs().add(createSortTab("Merge Sort")); 
        tabPane.getTabs().add(createSortTab("Quick Sort")); 
        tabPane.getTabs().add(createSortTab("Heap Sort")); 
 
        // Tabs cannot be closed 
        tabPane.getTabs().forEach(tab -> tab.setClosable(false)); 
 
        // --- Best Algorithm Section --- 
        Label bestAlgo = new Label("best-performing algorithm"); 
        bestAlgo.setStyle("-fx-background-color: #A8F5A2; -fx-padding: 
12; -fx-background-radius: 10; -fx-font-size: 14;"); 
 
        // --- Root Layout --- 
        VBox root = new VBox(20, topRow, errorMsg, tabPane, bestAlgo); 
        root.setPadding(new Insets(20)); 
 
        Scene scene = new Scene(root, 900, 750); 
        primaryStage.setScene(scene); 
        primaryStage.show(); 
    } 
 
    // Create a tab that contains a sorting block 
    private Tab createSortTab(String title) { 
        VBox block = createSortBlock(title); 
        Tab tab = new Tab(title, block); 
        tab.setClosable(false); 
        return tab; 
    } 
 
    // Create sorting result block 
    private VBox createSortBlock(String title) { 
        Label lblTitle = new Label(title); 
        lblTitle.setStyle("-fx-font-weight: bold; -fx-font-size: 14;"); 
 
        TextArea output = new TextArea(); 
        output.setPrefSize(350, 120); 
 
        Label execTime = new Label("execution time"); 
        execTime.setStyle("-fx-background-color: #A9CCF9; -fx-padding: 6; -fx-font-weight: bold;"); 
        execTime.setMaxWidth(Double.MAX_VALUE); 
 
        VBox box = new VBox(5, lblTitle, output, execTime); 
        box.setPadding(new Insets(10)); 
        box.setStyle("-fx-background-color: #D9E8F7; -fx-padding: 15; 
fx-background-radius: 8;"); 
        return box; 
    } 
 
    public static void main(String[] args) { 
        launch(args); 
    } 
} 