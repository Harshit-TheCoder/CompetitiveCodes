package Sorting;

public class mergeSort {
    public static void merge(int arr[], int low, int mid, int high){
        int temp[] = new int[high - low + 1];
        int left = low, right = mid + 1, k=0;
        while(left <= mid && right <= high){
            if(arr[left] <= arr[right]){
                temp[k++] = arr[left];
                left++;
            }
            else{
                temp[k++] = arr[right];
                right++;
            }
        }

        while(left <= mid){
            temp[k++] = arr[left];
            left++;
        }

        while(right <= high){
            temp[k++] = arr[right];
            right++;
        }

        for(int i=low;i<=high;i++) arr[i] = temp[i-low];
    }
    public static void mergesort(int arr[], int n, int low, int high){
        if(low >= high) return;
        int mid = (low + high) >> 1;
        mergesort(arr, n, low, mid);
        mergesort(arr, n, mid+1, high);
        merge(arr, low, mid, high);
    }
    public static void main(String[] args) {
        int arr[] = {1,2,3,4,5};
        int n = arr.length;
        mergesort(arr, n, 0, n-1);
    }
}
