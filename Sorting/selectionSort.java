package Sorting;

public class selectionSort {
    public static void main(String[] args) {
        int arr[] = {1,2,3,4,5};
        int n = arr.length;
        for(int i=0;i<n-1;i++){
            int mini = i;
            for(int j=i+1;j<n;j++){
                if(arr[mini] > arr[j]) mini = j;
            }
            arr[mini] = arr[mini] ^ arr[i];
            arr[i] = arr[mini] ^ arr[i];
            arr[mini] = arr[mini] ^ arr[i];
        }
    }
}
