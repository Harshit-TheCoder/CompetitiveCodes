package Sorting;

public class quickSort {
    public static int partitionIndex(int arr[], int low, int high){
        int pivot = arr[low];
        int i=low, j=high;
        while(i<j){
            while(i<high && arr[i] <= pivot) i++;
            while(j>low && arr[j] > pivot) j--;
            if(i < j){
                arr[i] = arr[i] ^ arr[j];
                arr[j] = arr[i] ^ arr[j];
                arr[i] = arr[i] ^ arr[j];
            }
        }

        arr[low] = arr[low] ^ arr[j];
        arr[j] = arr[low] ^ arr[j];
        arr[low] = arr[low] ^ arr[j];

        return j;
    }
    public static void quicksort(int arr[], int low, int high){
        if(low >= high) return;

        int pIndex = partitionIndex(arr, low, high);
        quicksort(arr, low, pIndex - 1);
        quicksort(arr, pIndex + 1, high);
    }
    public static void main(String[] args) {
        int arr[] = {1,2,3,4,5,6};
        int n = arr.length;
        quicksort(arr, 0, n-1);
    }
}
