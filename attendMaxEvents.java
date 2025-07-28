import java.util.*;
class Solution {
    int[][] dp;

    int bs(int[][] events, int n, int[] event, int low, int high) {
        int ans = -1;
        while (low <= high) {
            int mid = (low + high) >> 1;
            if (events[mid][0] > event[1]) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    int func(int[][] events, int k, int c, int i, int n) {
        if (i >= n || k == c) return 0;
        if (dp[i][c] != -1) return dp[i][c];

        int pick = 0;
        int idx = bs(events, n, events[i], i + 1, n - 1);
        if (idx != -1) {
            pick = events[i][2] + func(events, k, c + 1, idx, n);
        } else {
            pick = events[i][2];  // Last event that can be picked
        }

        int leave = func(events, k, c, i + 1, n);
        return dp[i][c] = Math.max(pick, leave);
    }

    public int maxValue(int[][] events, int k) {
        Arrays.sort(events, (a, b) -> Integer.compare(a[0], b[0]));
        int n = events.length;
        dp = new int[n][k + 1];
        for (int[] row : dp) Arrays.fill(row, -1);
        return func(events, k, 0, 0, n);
    }
}


// TYPICAL DP QUESTION USING BINARY SEARCH
// Here's a detailed hint to guide you towards solving the problem. Let's make it fun! 😄
// Think of this problem as a variation of a standard question where either you choose or you don't choose. 🤔 Here, you have the power to select the events that maximize your profit. 💰
// To approach this problem:
// Keep track of the last index you chose, initially set to -1. 
//📝 If you're wondering why we need this, it's because we don't have any event chosen yet
// For each event, compare its start time with the end time of the last chosen index. 
//If the start time is greater, it means the event is compatible with the previously chosen events. 
//You can consider picking it, XD! Otherwise, skip it and move on to the next event. 👀
// Storing the last chosen index can be a bit "eww" when it comes to three-dimensional memoization. But no worries, we have a solution! 
// Let's invite binary search's upper_bound here! 
// When you choose an index, find the index via upper_bound where the start time is greater than the end time. 🎯 Then, call the recursion function on that index to process the remaining events. 🔄
// Don't forget to handle the base cases too! When k (the number of events you can attend) becomes less than or equal to 0, it means no more events can be chosen. Time to celebrate! 🎉 Also, remember to check if you've reached the end of the events vector.

public class attendMaxEvents {
    public static void main(String[] args) {
        
    }
}
