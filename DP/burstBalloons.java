package DP;

import java.util.ArrayList;
import java.util.Arrays;

public class burstBalloons {
    static int maxCoins(int i, int j, ArrayList<Integer> a, int[][] dp) {
        if (i > j) return 0;
        if (dp[i][j] != -1) return dp[i][j];
        int maxi = Integer.MIN_VALUE;
        for (int ind = i; ind <= j; ind++) {
            int cost = a.get(i - 1) * a.get(ind) * a.get(j + 1)
                       + maxCoins(i, ind - 1, a, dp) + maxCoins(ind + 1, j, a, dp);
            maxi = Math.max(maxi, cost);
        }
        return dp[i][j] = maxi;
    }

    // Wrapper function to calculate the maximum coins
    static int maxCoins(ArrayList<Integer> a) {
        int n = a.size();
        a.add(1);
        a.add(0, 1);
        int[][] dp = new int[n + 2][n + 2];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return maxCoins(1, n, a, dp);
    }
}
