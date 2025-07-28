public class moduloK 
{ // Remainder is following a paticular pattern for all numbers in a subsequence, track that pattern and extend the dp
    public int maximumLength(int[] nums, int k) 
    {
        // Step 1: Prepare a 2D DP table to track remainder transitions
        int[][] dp = new int[k][k]; // dp[prev_rem][curr_rem]
        int maxLength = 0;
        // Step 2: Loop through each number in the array
        for (int num : nums) 
        {
            int current_rem = num % k;
            // Step 3: Try to extend previous sequences
            for (int prev_rem = 0; prev_rem < k; prev_rem++) 
            {
                // Step 4: Update DP table to build longer valid subsequence
                dp[prev_rem][current_rem] = dp[current_rem][prev_rem] + 1;
                // Step 5: Keep track of the maximum length found
                if (dp[prev_rem][current_rem] > maxLength) maxLength = dp[prev_rem][current_rem];
            }
        }
        return maxLength;
    }
}

// Modular k operations often result in alternate (bi-drectional) or cyclical transitions/relationships of modulo.
// num % k can give remainders from 0 to k-1. so k*k dp to track subsequence length with last two elements as prev_rem and curr_rem
// so dp[prev_rem][curr_rem] = dp[curr_rem][prev_rem] + 1
