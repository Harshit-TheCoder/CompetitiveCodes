package DP;

public class LCS {
    //Memoization
    public static int lcs(String a, String b, int n, int m, int dp[][]){
        if(n <= 0 || m <= 0) return 0;
        if(dp[n][m] != -1) return dp[n][m];
        if(a.charAt(n-1) == b.charAt(m-1)) return dp[n][m] = 1 + lcs(a, b, n-1, m-1, dp);
        else return dp[n][m] = Math.max(lcs(a, b, n-1, m, dp), lcs(a, b, n, m-1, dp));
    }
    public static void main(String[] args) {
        String a = "abcdgfefiiwhrue", b = "abcdffr";
        int n = a.length();
        int m = b.length();
        int dp[][] = new int[n+1][m+1];
        // for(int i=0;i<=n;i++) for(int j=0;j<=m;j++) dp[i][j] = -1;
        // int result = lcs(a, b, n, m, dp);
        // System.out.println(result);


        //Tabulation
        for(int i=0;i<=n;i++) dp[i][0] = 0;
        for(int i=0;i<=m;i++) dp[0][i] = 0;
        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                if(a.charAt(i-1) == b.charAt(j-1)) dp[i][j] = 1 + dp[i-1][j-1];
                else dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }

        // int result = dp[n][m];
        // System.out.println(result);
        
        

    }
}
