class Solution {
    public int minimumDeleteSum(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        int dp[][] = new int[m+1][n+1];
        dp[0][0] = 0;
        for(int i=1;i<=m;i++) dp[i][0] = dp[i-1][0] + (int)s1.charAt(i-1);
        for(int j=1;j<=n;j++) dp[0][j] = dp[0][j-1] + (int)s2.charAt(j-1);
        for(int i=1;i<=m;i++){
            for(int j=1;j<=n;j++){
                if(s1.charAt(i-1) == s2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                } 
                else{
                    dp[i][j] = Math.min((int)s1.charAt(i-1) + dp[i-1][j], (int)s2.charAt(j-1) + dp[i][j-1]);
                } 
            }
        }
        return dp[m][n];
    }
}
class minimumDeleteSum{
    public static void main(String[] args) {
        
    }
}

// Variation of LCS, but here you have to find the lowest cost of deleting characters from s1 and s2 to make them equal.
// problem with LCS is for LCS of same length, there will be different LCS that will be lexographically smaller or larger.
// to solve this add the ascii value of characters instead of 0 or 1.