package DP;

public class countLIS {
    public static void main(String[] args) {
        int arr[] = {10, 9, 2, 5, 3, 7, 101, 18};
        int n = arr.length;
        int dp[] = new int[n];
        for(int i=0;i<n;i++) dp[i]= 1;
        for(int i=0;i<n;i++) for(int j=0;j<i;j++) if(arr[j] < arr[i]) dp[i] = Math.max(dp[i], 1 + dp[j]);


        int count[] = new int[n];
        for(int i=0;i<n;i++) count[i] = 1;
        for(int i=0;i<n;i++){
            for(int j=0;j<i;j++){
                if(arr[j] < arr[i] && 1 + dp[j] > dp[i]){
                    dp[i] = 1 + dp[j];
                    count[i] = count[j];
                }
                else if(arr[j] < arr[i] && 1 + dp[j] == dp[i]){
                    count[i] = count[i] + count[j];
                }
            }
        }



    }

    
}
