package DP;

public class printLCS {
    public static void main(String[] args) {
        String a = "abcdgfefiiwhrue", b = "abcdffr";
        int n = a.length();
        int m = b.length();
        int dp[][] = new int[n][m];
        for(int i=0;i<=n;i++) dp[i][0] = 0;
        for(int i=0;i<=m;i++) dp[0][i] = 0;
        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                if(a.charAt(i-1) == b.charAt(j-1)) dp[i][j] = 1 + dp[i-1][j-1];
                else dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }

        int i=n, j=m;
        StringBuilder sb = new StringBuilder();
        while(i>0 && j>0){
            if(a.charAt(i-1) == b.charAt(j-1)){
                sb.append(a.charAt(i-1));
                i--;
                j--;
            }
            else if(dp[i-1][j] > dp[i][j-1]) i--;
            else j--;
        }

        sb.reverse();
        System.out.println(sb.toString());
    }
}
