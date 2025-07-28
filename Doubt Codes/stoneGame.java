class Solution {
    int func(int[] piles, int n, int i, int j, int turn, int dp[][][]){
        if(i>j){
            return 0;
        }
        if(dp[i][j][turn] != -1) return dp[i][j][turn];
        int alice = 0, bob = 501; // bob = 501, because we will find min and 1 <= piles[i] <= 500
        if(turn == 1){
            alice = Math.max(piles[i] + func(piles, n, i+1, j, 1-turn, dp), piles[j] + func(piles, n, i, j-1, 1-turn, dp));
        }
        else{
            bob = Math.min(-piles[i] + func(piles, n, i+1, j, 1-turn, dp), -piles[j] + func(piles, n, i, j-1, 1-turn, dp));
        }
        return dp[i][j][turn] = Math.max(alice, bob);
    }
    public boolean stoneGame(int[] piles) {
        int n = piles.length;
        int dp[][][] = new int[n][n][2];
        for(int i=0;i<n;i++) for(int j=0;j<n;j++) for(int k=0;k<2;k++) dp[i][j][k] = -1;
        int result = func(piles, n, 0, n-1, 1, dp);
        if(result > 0) return true;
        return false;
    }
}
class stoneGame{
    public static void main(String[] args) {
        
    }
}

// This is a dp problem to maximize the minimum.
// when turn == 1 alice chooses maximum of (front, back)
// when turn == 0, bob's goal is to minimize what alice has, so he find min because if abs(piles[i]) > abs(piles[j]) then -piles[i] < -piles[j] 
// at the end, return net advantage of alice over bob.