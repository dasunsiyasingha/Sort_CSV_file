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

    public static double[] mergeSort(double[] arr) {
        double[] a = arr.clone();
        mergeSortRec(a, 0, a.length - 1);
        return a;
    }

    private static void mergeSortRec(double[] a, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;

            mergeSortRec(a, left, mid);
            mergeSortRec(a, mid + 1, right);
            merge(a, left, mid, right);
        }
    }

    private static void merge(double[] a, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        double[] L = new double[n1];
        double[] R = new double[n2];

        for (int i = 0; i < n1; i++) L[i] = a[left + i];
        for (int i = 0; i < n2; i++) R[i] = a[mid + 1 + i];

        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) a[k++] = L[i++];
            else a[k++] = R[j++];
        }

        while (i < n1) a[k++] = L[i++];
        while (j < n2) a[k++] = R[j++];
    }

    public static double[] quickSort(double[] arr) {
        double[] a = arr.clone();

        quickSortRec(a, 0, a.length - 1);

        return a;
    }

    private static void quickSortRec(double[] a, int low, int high) {
        if (low < high) {
            int pivot = partition(a, low, high);
            quickSortRec(a, low, pivot - 1);
            quickSortRec(a, pivot + 1, high);
        }
    }

    private static int partition(double[] a, int low, int high) {
        double pivot = a[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (a[j] < pivot) {
                i++;
                double temp = a[i];
                a[i] = a[j];
                a[j] = temp;
            }
        }

        double temp = a[i + 1];
        a[i + 1] = a[high];
        a[high] = temp;

        return i + 1;
    }

    public static double[] heapSort(double[] arr) {
        double[] a = arr.clone();   // Do not modify original array

        int n = a.length;

        // 1. Build Max Heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(a, n, i);
        }

        // 2. Extract elements one by one
        for (int i = n - 1; i >= 0; i--) {
            // Move current root (max) to end
            double temp = a[0];
            a[0] = a[i];
            a[i] = temp;

            // Call heapify on the reduced heap
            heapify(a, i, 0);
        }
        return a;
    }

    private static void heapify(double[] a, int n, int i) {
        int largest = i;       // root index
        int left = 2 * i + 1;  // left child
        int right = 2 * i + 2; // right child

        if (left < n && a[left] > a[largest]) {
            largest = left;
        }

        if (right < n && a[right] > a[largest]) {
            largest = right;
        }

        // If largest is not root → swap
        if (largest != i) {
            double temp = a[i];
            a[i] = a[largest];
            a[largest] = temp;

            // Recursively heapify the affected subtree
            heapify(a, n, largest);
        }
    }

//    public static void main(String[] args) {
//
//        double[] test_bubbleSort_arr = SortAlgorithms.bubbleSort(new double[]{78, 3, 6, 2, 5, 25});
//        double[] test_insertionSort_arr = SortAlgorithms.insertionSort(new double[]{78, 3, 6, 2, 5, 25});
//        System.out.println(Arrays.toString(test_bubbleSort_arr));
//        System.out.println(Arrays.toString(test_insertionSort_arr));
//    }
}

