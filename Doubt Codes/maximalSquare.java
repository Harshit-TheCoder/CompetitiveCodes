public class maximalSquare {
    
}
class Solution {
    public int maximalSquare(char[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;

        int[][] dp = new int[n][m];
        int maxSide = 0;

        // Fill dp matrix
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == '1') {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;  // first row or first column
                    } else {
                        dp[i][j] = Math.min(
                            Math.min(dp[i - 1][j], dp[i][j - 1]),
                            dp[i - 1][j - 1]
                        ) + 1;
                    }
                    maxSide = Math.max(maxSide, dp[i][j]);
                }
            }
        }

        return maxSide * maxSide;
    }
}

//Okay the catch in this question is, every cell dp[i][j] must be treated as the bottom right corner of the square, 
// for corner elements ,that dp[i][j] where i ==0 || j == 0 must be 1 if mat[i][j] = '1' else 0,
// then from i=1 and j = 1, u will look at dp[i][j-1], dp[i-1][j], dp[i-1][j-1]
// if all three are equal means dp[i][j] will make a square
// if dp[i-1][j-1] = 1, dp[i-1][j] = 1, dp[i][j-1] = 2 then dp[i][j] = min of all + 1


