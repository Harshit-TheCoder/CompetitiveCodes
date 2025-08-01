public class rotateArrayDPlaces {
    void Reverse(int[] arr, int start, int end) {
        while (start <= end) {
        int temp = arr[start];
        arr[start] = arr[end];
        arr[end] = temp;
        start++;
        end--;
        }
    }
    void Rotateeletoright(int[] arr, int n, int k) {
        // Reverse first n-k elements
        Reverse(arr, 0, n - k - 1);
        // Reverse last k elements
        Reverse(arr, n - k, n - 1);
        // Reverse whole array
        Reverse(arr, 0, n - 1);
    }
    void Rotateeletoleft(int[] arr, int n, int k) {
        // Reverse first k elements
        Reverse(arr, 0, k - 1);
        // Reverse last n-k elements
        Reverse(arr, k , n - 1);
        // Reverse whole array
        Reverse(arr, 0, n - 1);
    }
}
