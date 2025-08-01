package DP;


public class countNumSquaresOne {
    public static int countSquares(int n, int m, int[][] arr) {
        int[][] dp = new int[n][m];

        // Initialize first row and first column
        for (int j = 0; j < m; j++) dp[0][j] = arr[0][j];
        for (int i = 0; i < n; i++) dp[i][0] = arr[i][0];

        // Fill the dp table
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (arr[i][j] == 1) {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j],
                                    Math.min(dp[i - 1][j - 1], dp[i][j - 1]));
                } else {
                    dp[i][j] = 0;
                }
            }
        }

        // Sum up all squares
        int sum = 0;
        for (int[] row : dp) {
            for (int val : row) {
                sum += val;
            }
        }

        return sum;
    }
}
