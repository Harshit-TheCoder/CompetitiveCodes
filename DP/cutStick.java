package DP;
import java.util.*;
public class cutStick {
    static int f(int i, int j, ArrayList<Integer> cuts, int[][] dp) {
        // Base case
        if (i > j) {
            return 0;
        }

        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        int mini = Integer.MAX_VALUE;

        for (int ind = i; ind <= j; ind++) {
            int ans = cuts.get(j + 1) - cuts.get(i - 1) +
                      f(i, ind - 1, cuts, dp) +
                      f(ind + 1, j, cuts, dp);

            mini = Math.min(mini, ans);
        }

        return dp[i][j] = mini;
    }

    // Function to calculate the minimum cost
    static int cost(int n, int c, ArrayList<Integer> cuts) {
        // Modify the cuts array
        cuts.add(n);
        cuts.add(0);
        Collections.sort(cuts);

        int[][] dp = new int[c + 1][c + 1];

        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        return f(1, c, cuts, dp);
    }
}
