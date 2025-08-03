package Sorting;

public class insertionSort {
    public static void main(String[] args) {
        int arr[] = {1,2,3,4,5,6,7};
        int n = arr.length;
        for(int i=0;i<n;i++){
            int j=i;
            while(j>0 && arr[j-1] > arr[j]){
                arr[j-1] = arr[j-1] ^ arr[j];
                arr[j] = arr[j-1] ^ arr[j];
                arr[j-1] = arr[j-1] ^ arr[j];
                j--;
            }
        }

    }
}
