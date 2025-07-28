class NCR {

    static final int MAX = 1000001;
    static final int MOD = 1_000_000_007;
    
    static long[] fact = new long[MAX];
    static long[] invFact = new long[MAX];

    // Static block to precompute factorials and inverse factorials
    static {
        fact[0] = invFact[0] = 1;
        // Compute factorials modulo MOD
        for (int i = 1; i < MAX; i++) {
            fact[i] = (fact[i - 1] * i) % MOD;
        }
        // Compute inverse factorial of MAX - 1 using Fermat's Little Theorem
        invFact[MAX - 1] = power(fact[MAX - 1], MOD - 2);
        // Compute inverse factorials in reverse
        for (int i = MAX - 2; i >= 1; i--) {
            invFact[i] = (invFact[i + 1] * (i + 1)) % MOD;
        }
    }
    // Function to compute a^b mod MOD
    static long power(long a, long b) {
        long res = 1;
        a %= MOD;
        while (b > 0) {
            if ((b & 1) == 1)
                res = (res * a) % MOD;
            a = (a * a) % MOD;
            b >>= 1;
        }
        return res;
    }
    // Function to compute nCr mod MOD
    static long nCr(int n, int r) {
        if (r < 0 || r > n) return 0;
        return fact[n] * invFact[r] % MOD * invFact[n - r] % MOD;
    }
}
