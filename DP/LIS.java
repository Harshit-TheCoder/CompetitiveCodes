package DP;
public class LIS {
    //Memoization
    public static int lis(int arr[], int idx, int prev, int n, int dp[][]){
        if(idx >= n) return 0;
        if(dp[idx][prev+1] != -1) return dp[idx][prev+ 1];

        int leave = lis(arr, idx+1, prev, n, dp);
        int pick = 0;
        if(prev == -1 || arr[prev + 1] < arr[idx]){
            pick = 1 + lis(arr, idx+1, idx, n, dp);
        }
        return dp[idx][prev+1] = Math.max(pick, leave);
    }
    public static void main(String[] args) {
        int arr[] = {10, 9, 2, 5, 3, 7, 101, 18};
        int n = arr.length;
        int dp[][] = new int[n+1][n+1];
        // for(int i=0;i<=n;i++) for(int j=0;j<=n;j++) dp[i][j] = -1;
        // int result = lis(arr, 0, -1, n, dp);
        // System.out.println(result);

        //Tabulation
        for(int i=n-1;i>=0;i--){
            for(int j=i-1;j>=-1;j--){
                int leave = dp[i+1][j+1];
                int pick = 0;
                if(j == -1 || arr[j] < arr[i]){
                    pick = 1 + dp[i+1][i+1];
                }
                dp[i][j+1] = Math.max(pick, leave);
            }
        }

        //Space Optimization
        int next[] = new int[n+1];
        int curr[] = new int[n+1];
        for(int i=n-1;i>=0;i--){
            for(int j=i-1;j>=-1;j--){
                int leave = next[j+1];
                int pick = 0;
                if(j == -1 || arr[j] < arr[i]){
                    pick = 1 + next[i+1];
                }
                curr[j+1] = Math.max(pick, leave);
            }
            next = curr;
        }


    }
}
