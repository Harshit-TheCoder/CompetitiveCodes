Dynamic Programming and Recursion Guide

Steps to Write a Recursive Solution

Identify number of possibilities in each state:

Determine the number of ways to transition to the next state.

The number of recursive calls equals the number of possible transitions.

Identify the base case:

Define when the recursion should stop.

Example: if (idx < 0 || idx >= n) return 0;

Use a non-void return type:

This allows easy aggregation of results from recursive calls.

Modify changing parameters in recursive calls directly:

Avoid updating state separately before the call.

Pass modified parameters inside the recursive function call.




Steps to Memoize a Recursive Function

Check the number of changing parameters:

The dimensions of the dp array should match the number of changing parameters.

Initialize the DP array:

Create a dp[n+1] array (or multidimensional array if needed).

Initialize all values to -1 (or a marker indicating unvisited state).

Store and return memoized values:

if (dp[i] != -1) return dp[i];
dp[i] = answer;
return dp[i];

Add memoization logic after base cases:

Check and return the memoized result if already computed.




Steps to Convert Memoization to Tabulation

**Determine changing parameters and dimension of **``

Same as memoization.

Copy base case:

If in memoization: if (idx >= n) return 0;

Then in tabulation: dp[n] = 0;

Set up iteration in reverse:

Loop from n-1 to 0 (or reverse order of recursion).

Copy recursive statements into loops:

Replace recursive calls with tabulated value access.

Return the appropriate DP value:

Often dp[0] or based on the initial state.




Steps to Convert Tabulation to Space Optimization

Check DP dependency:

If transition uses only previous row/states like dp[i-1][j] or dp[i][j], it can be optimized.

Use ** and ** arrays:

Replace dp[i-1][j] with prev[j]

Replace dp[i][j] with curr[j]

After transitions:

Set prev = curr for the next iteration.

**Return final result from the correct index of **``

Usually the value representing the full solution.