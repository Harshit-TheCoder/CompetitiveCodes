# Dynamic Programming and Recursion Guide

## 🧠 Steps to Write a Recursive Solution

1. **Identify the number of possibilities in each state**

   - Determine how many choices or paths are possible from the current state.
   - The number of recursive calls will usually equal the number of possible transitions.
   - 🔍 *Key insight:* Try breaking down a problem into "choices" or "decisions" you can make at each step.

2. **Identify the base case**

   - Define the condition under which the recursion should stop.
   - Example: `if (idx < 0 || idx >= n) return 0;`
   - 🔍 *Key insight:* A correct base case prevents stack overflow and infinite recursion.

3. **Use a non-void return type**

   - You’ll often want to return values (like counts or sums) from subproblems.
   - Example: `int solve(int idx)`

4. **Modify changing parameters directly in the recursive call**

   - Avoid modifying state before calling the recursive function.
   - Instead, pass the new value as an argument inside the function call.

---

## 🧩 Steps to Memoize a Recursive Function

1. **Count changing parameters**

   - These determine the dimensions of your `dp` array.
   - Example: If `solve(i, j)` → use `dp[i][j]`.

2. **Initialize the DP array**

   - Use a sentinel value like `-1` to mark uncomputed states.
   - Example (Java): `int[][] dp = new int[n][m]; Arrays.fill(dp[i], -1);`

3. **Store and return memoized values**

   ```java
   if (dp[i] != -1) return dp[i];
   dp[i] = answer;
   return dp[i];
   ```

4. **Add memoization check after base case**

   - Always check if the state has been computed before going deeper.

---

## 📊 Steps to Convert Memoization to Tabulation

1. **Determine parameters and DP array size**

   - Same as in memoization; your table size must match your state.

2. **Implement base case explicitly**

   - For `if (idx >= n) return 0;` in recursion → `dp[n] = 0;` in tabulation.

3. **Reverse the order of computation**

   - Tabulation is bottom-up, so you typically loop backward (from `n-1` to `0`).

4. **Convert recursive logic into iteration**

   - Replace recursive calls with direct access into the DP table.

5. **Return the value for the initial state**

   - Commonly `dp[0]`, `dp[0][0]`, or another form based on the problem.

---

## 💾 Steps to Convert Tabulation to Space Optimization

1. **Check if current state only depends on previous**

   - If `dp[i][j]` only depends on `dp[i-1][...]`, you can use rolling arrays.

2. **Use **``** and **``** arrays**

   - Replace `dp[i-1][j]` with `prev[j]`
   - Replace `dp[i][j]` with `curr[j]`

3. **After each iteration**

   - Copy or assign: `prev = curr` or `prev[j] = curr[j]`

4. **Return the final answer from **``

   - Usually `prev[...]` contains the result after the last iteration.

---

> 🚀 **Pro Tip:** These steps provide a structured pipeline: start from brute recursion, add memoization, convert to tabulation, and then optimize space. This skillset is crucial in solving large-scale DP problems efficiently and shines in coding interviews and contests.

---

**Feel free to add visualizations or diagrams to reinforce transitions between steps.**

