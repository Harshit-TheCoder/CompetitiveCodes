public class FenwickTree {
    int[] fen;
    int N;
    FenwickTree(int size) {
        N = size + 2; // 1-based indexing, with a buffer
        fen = new int[N];
    }
    void update(int i, int val) { // Point update: add 'add' to index 'i'
        while (i < N) {
            fen[i] += val;
            i += (i & -i); // turn of the rightmost most, bitwise AND with original number and add it to the index.
        }
    }   
    int sum(int i) { // Prefix sum query: sum[1..i]
        int s = 0; // sum starting from given index till index 1.
        while (i > 0) {
            s += fen[i];
            i -= (i & -i);
        }
        return s;
    }    
    int rangeSum(int l, int r) { // Range sum query: sum[l..r]
        return sum(r) - sum(l - 1);
    } 
    int find(int k) { // Binary lifting: Find smallest index such that prefix sum ≥ k (lower bound)
        int curr = 0, prevSum = 0;
        for (int i = (int)(Math.log(N) / Math.log(2)); i >= 0; i--) {
            int next = curr + (1 << i);
            if (next < N && fen[next] + prevSum < k) {
                prevSum += fen[next];
                curr = next;
            }
        }
        return curr + 1;
    }
}
