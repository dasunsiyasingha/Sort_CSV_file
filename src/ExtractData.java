 
import java.io.BufferedReader; 
import java.io.File; 
import java.io.FileReader; 
import java.util.ArrayList; 
import java.util.Arrays; 
import java.util.List; 
 
public class ExtractData { 
 
    File file; 
    int index; 
    double[] numericValues; 
    boolean isFirstRow = true; 
    public ExtractData(File file, int index) { 
        this.file = file; 
        this.index = index; 
 
        this.numericValues = extractNumericColumn(file, index); 
    } 
 
    public double[] extractNumericColumn(File csvFile, int columnIndex) { 
        List<Double> values = new ArrayList<>(); 
 
        try (BufferedReader br = new BufferedReader(new 
FileReader(csvFile))) { 
 
            String line; 
            while ((line = br.readLine()) != null) { 
                if (isFirstRow) { 
                    // Skip header row 
                    isFirstRow = false; 
                    continue; 
                } 
                String[] parts = line.split(","); 
 
//                System.out.println(Arrays.toString(parts)); 
 
                if (columnIndex < parts.length) { 
                    try { 
                        
values.add(Double.parseDouble(parts[columnIndex])); 
                    } catch (NumberFormatException e) { 
                        System.out.println("Invalid numeric value: " + 
parts[columnIndex]); 
                    } 
                } 
            } 
 
        } catch (Exception e) { 
            e.printStackTrace(); 
        } 
 
        // Convert List<Double> → double[] 
        double[] arr = new double[values.size()]; 
        for (int i = 0; i < values.size(); i++) { 
            arr[i] = values.get(i); 
        } 
        System.out.println(Arrays.toString(arr)); 
        return arr; 
    } 
 
}  

