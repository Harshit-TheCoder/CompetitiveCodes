# Bit Manipulation Tricks

## 1. Swap Two Numbers (Using XOR)
```cpp
a = a ^ b;
b = a ^ b;
a = a ^ b;
```

## 2. Check if k-th Bit is Set or Not
```cpp
if ((n & (1 << k)) != 0)
    // k-th bit is set
else
    // k-th bit is not set
```

## 3. Set the i-th Bit
```cpp
n = n | (1 << i);
```

## 4. Clear the i-th Bit
```cpp
n = n & ~(1 << i);
```

## 5. Toggle the i-th Bit
```cpp
n = n ^ (1 << i);
```

## 6. Remove the Rightmost Set Bit
```cpp
n = n & (n - 1);
```

## 7. Check if a Number is a Power of Two
```cpp
if ((n & (n - 1)) == 0)
    // n is a power of 2
else
    // n is not a power of 2
```

## 8. Count Number of Set Bits
```cpp
int count = 0;
while (n > 0) {
    count += (n & 1);
    n >>= 1;
}
```

## 9. Bit Shifts for Power of 2 Multiplication/Division
```cpp
n * 2^x == n << x
n / 2^x == n >> x
```

## 10. Minimum Bit Flips to Convert x to y
```cpp
int num = x ^ y;
int count = 0;
while (num > 0) {
    count += (num & 1);
    num >>= 1;
}
```

## 11. Power Set using Bitmasking
```cpp
vector<vector<int>> res;
int n = nums.size();
for (int i = 0; i < (1 << n); i++) {
    vector<int> subset;
    for (int j = 0; j < n; j++) {
        if ((i & (1 << j)) != 0) {
            subset.push_back(nums[j]);
        }
    }
    res.push_back(subset);
}
```

## 12. XOR of Numbers in a Range [L, R]
```cpp
int xorUpto(int n) {
    if (n % 4 == 0) return n;
    if (n % 4 == 1) return 1;
    if (n % 4 == 2) return n + 1;
    return 0;
}

int xorRange(int L, int R) {
    return xorUpto(R) ^ xorUpto(L - 1);
}
```

## 13. Divide Two Numbers Without Using / or %
```cpp
int divide(int a, int b) {
    if (a == b) return 1;
    bool sign = true;
    if ((a < 0 && b > 0) || (a > 0 && b < 0)) sign = false;
    long long n = abs((long long)a);
    long long d = abs((long long)b);
    long long ans = 0;

    while (n >= d) {
        int cnt = 0;
        while (n >= (d << (cnt + 1))) cnt++;
        ans += (1LL << cnt);
        n -= d * (1LL << cnt);
    }

    if (ans > INT_MAX) return sign ? INT_MAX : INT_MIN;
    return sign ? ans : -ans;
}
```

---

# Prime Factorization in O(sqrt(N))
```cpp
vector<int> primeFactors(int n) {
    vector<int> res;
    while (n % 2 == 0) {
        res.push_back(2);
        n /= 2;
    }
    for (int i = 3; i * i <= n; i += 2) {
        while (n % i == 0) {
            res.push_back(i);
            n /= i;
        }
    }
    if (n > 2)
        res.push_back(n);
    return res;
}
```