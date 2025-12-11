import java.util.Arrays;

public class SortAlgorithms {

    public static double[] bubbleSort(double[] arr) {
        double[] a = arr.clone();

        for (int i = 0; i < a.length - 1; i++) {
            for (int j = 0; j < a.length - i - 1; j++) {
                if (a[j] > a[j + 1]) {
                    double temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }
        return a;
    }

    public static double[] insertionSort(double[] arr) {
        double[] a = arr.clone();

        for (int i = 1; i < a.length; i++) {
            double key = a[i];
            int j = i - 1;

            while (j >= 0 && a[j] > key) {
                a[j + 1] = a[j];
                j--;
            }
            a[j + 1] = key;
        }

        return a;
    }
    public static void main(String[] args) {

        double[] test_bubbleSort_arr = SortAlgorithms.bubbleSort(new double[]{78, 3, 6, 2, 5, 25});
        double[] test_insertionSort_arr = SortAlgorithms.insertionSort(new double[]{78, 3, 6, 2, 5, 25});
        System.out.println(Arrays.toString(test_bubbleSort_arr));
        System.out.println(Arrays.toString(test_insertionSort_arr));
    }
}

