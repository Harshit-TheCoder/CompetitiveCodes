class Solution {
    int func(int cookies[], int k, int n, int idx, int children[]){
        if(idx >= n){
            int maxi = 0;
            for(int num: children) maxi = Math.max(maxi, num);
            return maxi;
        }

        int unfairness = Integer.MAX_VALUE;
        for(int i=0;i<k;i++){
            children[i] += cookies[idx];
            unfairness = Math.min(unfairness, func(cookies, k, n, idx+1, children));
            children[i] -= cookies[idx];
        }
        return unfairness;
    }
    public int distributeCookies(int[] cookies, int k) {
        int n = cookies.length;
        int children[] = new int[n];
        int result = func(cookies, k, n, 0, children);
        return result;
    }
}

class UnfairnessDistribution{
    public static void main(String[] args) {
        
    }
}
// This is a DP pattern of minimizing the pattern. Although you solved yourself, Keep in mind.