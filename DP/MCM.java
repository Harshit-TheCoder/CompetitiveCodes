package DP;

public class MCM {
    public static int func(int arr[], int i, int j, int dp[][]){
        if(i>j) return Integer.MAX_VALUE;
        if(i == j) return 0;
        if(dp[i][j] != -1) return dp[i][j];
        int mini = Integer.MAX_VALUE;
        for(int k=i;k<=j;k++){
            int ans = arr[i-1]*arr[k]*arr[j] + func(arr, i, k, dp) + func(arr, k+1, j, dp);
            mini = Math.min(mini, ans);
        }
        return dp[i][j] = mini;
    }
    public static void main(String[] args) {
        int arr[] = {10, 20, 30, 40, 50};
        int n = arr.length;
        int dp[][] = new int[n][n];
        // for(int i=0;i<n;i++) for(int j=0;j<n;j++) dp[i][j] = -1;
        // int result = func(arr, 1, n-1, dp);
        for(int i=n-1;i>=1;i--){
            for(int j=i+1;j<n;j++){
                int mini = Integer.MAX_VALUE;
                for(int k=i;k<=j;k++){
                    int ans = arr[i-1]*arr[k]*arr[j] + dp[i][k] + dp[k+1][j];
                    mini = Math.min(mini, ans);
                }
                dp[i][j] = mini;
            }
        }

        int result = dp[1][n-1];

    }
}
