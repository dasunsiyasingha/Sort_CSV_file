import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.*;



public class PerformanceReport {
    
    public void showExecutionTimeChart(
            Long bubbleTime,
            Long insertionTime,
            Long mergeTime,
            Long quickTime,
            Long heapTime,
            VBox chartContainer
    ) {
        chartContainer.getChildren().clear(); // clear old charts

        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();

        xAxis.setLabel("Sorting Algorithms");
        yAxis.setLabel("Execution Time (ns)");

        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Sorting Algorithm Execution Times");
        barChart.setLegendVisible(false);

        XYChart.Series<String, Number> series = new XYChart.Series<>();

        series.getData().add(new XYChart.Data<>("Bubble", bubbleTime));
        series.getData().add(new XYChart.Data<>("Insertion", insertionTime));
        series.getData().add(new XYChart.Data<>("Merge", mergeTime));
        series.getData().add(new XYChart.Data<>("Quick", quickTime));
        series.getData().add(new XYChart.Data<>("Heap", heapTime));

        barChart.getData().add(series);

        barChart.setPrefWidth(500);
        barChart.setPrefHeight(350);

        chartContainer.getChildren().add(barChart);
    }

}