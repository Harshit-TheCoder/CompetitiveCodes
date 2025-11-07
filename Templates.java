import java.util.*;
class Templates{
    public static void main(String[] args) {
        System.out.println("Hello World");
    }
}
class DisjointSet{
    List<Integer> parent = new ArrayList<>();
    List<Integer> size = new ArrayList<>();
    List<Integer> rank = new ArrayList<>();
    DisjointSet(int n){
        for(int i=0;i<n;i++){
            parent.add(i);
            size.add(1);
            rank.add(1);
        }
    }
    int findUPar(int node){
        if(parent.get(node) == node) return node;
        int ulp = findUPar(parent.get(node));
        parent.set(node, ulp);
        return parent.get(node);
    }
    void unionBySize(int u, int v){
        int ulp_u = findUPar(u);
        int ulp_v = findUPar(v);
        if(ulp_u == ulp_v) return;
        if(size.get(ulp_u) < size.get(ulp_v)){
            parent.set(ulp_u, ulp_v);
            size.set(ulp_v, size.get(ulp_u) + size.get(ulp_v));
        }
        else{
            parent.set(ulp_v, ulp_u);
            size.set(ulp_u, size.get(ulp_u) + size.get(ulp_v));
        }
    }
    void unionByRank(int u, int v){
        int ulp_u = findUPar(u);
        int ulp_v = findUPar(v);
        if(ulp_u == ulp_v) return;
        if(rank.get(ulp_u) < rank.get(ulp_v)){
            parent.set(ulp_u, ulp_v);
        }
        else if(rank.get(ulp_u) > rank.get(ulp_v)){
            parent.set(ulp_v, ulp_u);
        }
        else{
            parent.set(ulp_v, ulp_u);
            rank.set(ulp_u, rank.get(ulp_u) + 1);
        }
    }
}


class SegmentTree{
    int n;
    int seg[];
    SegmentTree(int n){
        this.n = n;
        seg = new int[4*n];
    }
    public void build(int ind, int low, int high, int arr[]){
        if(low == high){
            seg[ind] = arr[low];
            return;
        }
        int mid = (low + high) >> 1;
        build(2*ind + 1, low, mid, arr);
        build(2*ind + 2, mid +1, high, arr);
        seg[ind] = Math.min(seg[2*ind + 1], seg[2*ind + 2]);
    }
    public int query(int ind, int low, int high, int l, int r){
        if(r < low || l > high) return Integer.MAX_VALUE;
        if(l <= low && high <= r) return seg[ind];

        int mid = (low + high) >> 1;
        int left = query(2*ind + 1, low, mid, l, r);
        int right = query(2*ind + 2, mid + 1, high, l, r);
        return Math.min(left, right);
    }
    public void update(int ind, int low, int high, int i, int val){
        if(low == high){
            seg[ind] = val;
            return;
        }
        int mid = (low + high) >> 1;
        if(i <= mid){
            update(2*ind + 1, low, mid, i, val);
        }
        else update(2*ind + 1, mid +1 ,high, i ,val);
        seg[ind] = Math.min(seg[2*ind + 1], seg[2*ind + 2]);
    }
}

class FenwickTree{
    int N;
    int fen[];
    FenwickTree(int size){
        N = size + 2;
        fen = new int[N];
    }
    void update(int i, int val){
        while(i<N){
            fen[i] += val;
            i += (i & -i);
        }
    }
    int sum(int i){
        int s = 0;
        while(i>0){
            s += fen[i];
            i -= (i & -i);
        }
        return s;
    }
    int rangeSum(int l, int r){
        return sum(r) - sum(l-1);
    }

    // smallest index such that prefix sum >= k
    int prefixSum(int k){
        int curr = 0, prevSum = 0;
        for(int i=0;i<N;i++){
            int next = curr + (1 << i);
            if(next < N && prevSum + fen[next] < k){
                prevSum += fen[next];
                curr = next;
            }
        }
        return curr + 1;
    }
}

class LRUCache{
    class Node{
        int key, val;
        Node next, prev;
        Node(int key, int val){
            this.key = key;
            this.val = val;
            this.next = null;
            this.prev = null;
        }
    }

    Node head = new Node(0,0);
    Node tail = new Node(0,0);
    Map<Integer, Node> map = new HashMap<>();
    int capacity;

    LRUCache(int capacity){
        this.capacity = capacity;
        head.next = tail;
        tail.prev = head;
    }

    public void insert(Node node){
        node.next = head.next;
        head.next.prev = node;
        node.prev = head;
        head.next = node;
    }

    public void remove(Node node){
        node.next.prev = node.prev;
        node.prev.next = node.next;
    }

    public int  get(int key){
        if(map.containsKey(key)){
            Node node = map.get(key);
            remove(node);
            insert(node);
            return node.val;
        }
        else return -1;
    }

    public void put(int key, int val){
        if(map.containsKey(key)){
            Node node = map.get(key);
            node.val = val;
            remove(node);
            insert(node);
            return;
        }
        else if(map.size() >= capacity){
            map.remove(tail.prev.key);
            remove(tail.prev);  
        }
        Node node = new Node(key, val);
        insert(node);
        map.put(key, node);
    }
}



class LFUCache{
    
    class Node{
        int key,val, freq;
        Node next, prev;
        Node(int key, int val){
            this.key = key;
            this.val = val;
            this.next = null;
            this.prev = null;
            freq = 1;
        }
    }

    public class LRUList{
        Node head = new Node(0, 0);
        Node tail = new Node(0,0);
        int size;

        public LRUList(){
            this.size = 0;
            head.next = tail;
            tail.prev = head;
        }

        public void insert(Node node){
            node.next = head.next;
            head.next.prev = node;
            node.prev = head;
            head.next = node;
            size++;
        }

        public void remove(Node node){
            node.next.prev = node.prev;
            node.prev.next = node.next;
            size--;
        }

        public Node removeLRU(){
            Node lru = tail.prev;
            remove(lru);
            return lru;
        }
        public boolean empty(){
            return size == 0;
        }
    }

    Map<Integer, Node> map = new HashMap<>();
    Map<Integer, LRUList> freqlist = new HashMap<>();
    int capacity, minfreq;

    public LFUCache(int capacity){
        this.capacity = capacity;
        this.minfreq = 0;
    }

    public int get(int key){
        if(map.containsKey(key)){
            Node node = map.get(key);
            updateFreq(node);
            return node.val;
        }
        else return -1;
    }

    public void put(int key, int val){
        if (capacity == 0) return;
        if(map.containsKey(key)){
            Node node = map.get(key);
            node.val = val;
            updateFreq(node);
            return;
        }
        else if(map.size() >= capacity){
            LRUList minLRU = freqlist.get(minfreq);
            Node lruNode = minLRU.removeLRU();
            map.remove(lruNode.key);
        }
        Node node = new Node(key, val);
        map.put(key, node);
        freqlist.computeIfAbsent(1, k -> new LRUList()).insert(node);
        minfreq = 1;
    }

    public void updateFreq(Node node){
        int f = node.freq;
        LRUList oldList = freqlist.get(f);
        oldList.remove(node);
        if(f == minfreq && oldList.empty()) minfreq++;
        node.freq++;
        freqlist.computeIfAbsent(node.freq, k -> new LRUList()).insert(node);
    }
}


class MinStack{
    Stack<Long> stack = new Stack<>();
    long mini;
    MinStack(){
        this.mini = Long.MAX_VALUE;
    }
    public void push(long val){
        if(stack.isEmpty()){
            stack.push(val);
            mini = val;
        } 
        else{
            if(val < mini){
                stack.push((2*val) - mini);
                mini = val;
            }
            else{
                stack.push(val);
            }
        }
    }

    public void pop(){
        if(stack.isEmpty()) return;
        else{
            Long val = stack.pop();
            if(val < mini){
                mini = (2*mini) - val;
            }
        }
    }

    public long top(){
        if(stack.isEmpty()) return -1;
        else{
            Long val = stack.peek();
            if(val < mini){
                return mini;
            }
            return val;
        }
    }

    public long getMin(){
        return mini;
    }
}


class NCR{
    static final long MOD = 1000000007;
    static final int MAX = 100001;
    static final long fact[] = new long[MAX];
    static final long invFact[] = new long[MAX];
    static{
        fact[0] = invFact[0] = 1;
        for(int i=1;i<MAX;i++){
            fact[i] = (fact[i-1]*i)%MOD;
        }
        invFact[MAX - 1] = pow(fact[MAX - 1], MOD - 2);
        for(int i=MAX - 2;i>=1;i--){
            invFact[i] = (invFact[i+1]*(i+1))%MOD;
        }
    }

    public static long pow(long a, long b){
        a %= MOD;
        long res = 1;
        while(b > 0){
            if((b & 1) == 1) res = (res * a)%MOD;
            a = (a * a)%MOD;
            b >>= 1;
        }
        return res;
    }

    public long nCr(int n, int r){
        if(r < 0 || r > n) return 0;
        return (((fact[n] * invFact[r]) % MOD) * invFact[n - r]) % MOD;
    }
}

class MatrixExponentiation{
    long [][] multiply(long A[][], long B[][], int MOD){
        int n = A.length;
        long res[][] = new long[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                for(int k=0;k<n;k++){
                    res[i][j] = (res[i][j] + A[i][k]*B[k][j])%MOD;
                }
            }
        }
        return res;
    }

    long[][] matrixPower(long A[][], long power, int MOD){
        int n = A.length;
        long[][] res = new long[n][n];
        for(int i=0;i<n;i++) res[i][i] = 1;
        while(power > 0){
            if((power & 1) == 1){
                res = multiply(A, res, MOD);
            }
            A = multiply(A, A, MOD);
            power >>= 1;
        }

        return res;
    }
}

class SeiveOfEratosThenes{
    static int sof[];
    static boolean isPrime[];
    static int n;
    SeiveOfEratosThenes(int size){
        n = size;
        sof = new int[n+1];
        isPrime = new boolean[n+1];
        for(int i=0;i<=n;i++){
            sof[i] = i;
            isPrime[i] = true;
        } 

        isPrime[0] = false;
        isPrime[1] = false;
        for(int i=2;i<=n;i++){
            if(isPrime[i]){
                for(int j=i*i;j<=n;j += i){
                    isPrime[j] = false;
                }
            }
        }

        sof[0] = 0;
        sof[1] = 1;
        for(int i=2;i<=n;i++){
            if(sof[i] == i){
                for(int j=i*i;j<=n;j += i){
                    if(sof[j] == j) sof[j] = i;
                }
            }
        }
    }

    boolean checkPrime(int val){
        return isPrime[val];
    }

    List<Integer> getPrimeDivisors(int val){
        List<Integer> divisors = new ArrayList<>();
        int temp = val;
        while(temp > 1){
            int d = sof[temp];
            divisors.add(d);
            temp /= d;
        }
        return divisors;
    }
}


class Trie{
    static class Node{
        Node children[] = new Node[26];
        boolean eow = false;
        public void put(char ch, Node node){
            children[ch - 'a'] = node;
        }
        public boolean containsKey(char ch){
            return children[ch - 'a'] != null;
        }
        public Node get(char ch){
            return children[ch - 'a'];
        }
        public void setEnd(){
            eow = true;
        }
        public boolean isEnd(){
            return eow;
        }
    }

    private Node root;
    Trie(){
        root = new Node();
    }

    public void insert(String word){
        Node node = root;
        for(char ch: word.toCharArray()){
            if(!node.containsKey(ch)){
                node.put(ch, new Node());
            }
            node = node.get(ch);
        }
        node.setEnd();
    }

    public boolean startsWith(String prefix){
        Node node = root;
        for(char ch: prefix.toCharArray()){
            if(!node.containsKey(ch)) return false;
            node = node.get(ch);
        }
        return true;
    }

    public boolean search(String word){
        Node node = root;
        for(char ch: word.toCharArray()){
            if(!node.containsKey(ch)) return false;
            node = node.get(ch);
        }
        return node.isEnd();
    }
}


int binarySearch(int low, int high, int arr[], int key){
    while(low <= high){
        int mid = (low + high) >> 1;
        if(arr[mid] == key) return mid;
        else if(arr[mid] < key) low = mid + 1;
        else high = mid -1;
    }
    return -1;
}

int ternarySearch(int low, int high, int key, int arr[]) {
    while (low <= high) {
        int mid1 = low + (high - low) / 3;
        int mid2 = high - (high - low) / 3;

        if (arr[mid1] == key) return mid1;
        if (arr[mid2] == key) return mid2;

        if (key < arr[mid1]) {
            high = mid1 - 1;
        } else if (key > arr[mid2]) {
            low = mid2 + 1;
        } else {
            low = mid1 + 1;
            high = mid2 - 1;
        }
    }
    return -1;
}


class SegmentTree {
    int n;
    int[] seg, lazy;

    SegmentTree(int[] arr) {
        n = arr.length;
        seg = new int[4 * n];
        lazy = new int[4 * n];
        build(1, 0, n - 1, arr);
    }

    void build(int ind, int low, int high, int[] arr) {
        if (low == high) {
            seg[ind] = arr[low];
            return;
        }
        int mid = (low + high) >>> 1;
        build(ind << 1, low, mid, arr);
        build(ind << 1 | 1, mid + 1, high, arr);
        seg[ind] = Math.min(seg[ind << 1], seg[ind << 1 | 1]);
    }

    void push(int ind, int low, int high) {
        if (lazy[ind] == 0) return;
        seg[ind] += lazy[ind];
        if (low != high) {
            lazy[ind << 1] += lazy[ind];
            lazy[ind << 1 | 1] += lazy[ind];
        }
        lazy[ind] = 0;
    }

    void updateRange(int ind, int low, int high, int l, int r, int val) {
        push(ind, low, high);
        if (r < low || high < l) return;
        if (l <= low && high <= r) {
            lazy[ind] += val;
            push(ind, low, high);
            return;
        }
        int mid = (low + high) >>> 1;
        updateRange(ind << 1, low, mid, l, r, val);
        updateRange(ind << 1 | 1, mid + 1, high, l, r, val);
        seg[ind] = Math.min(seg[ind << 1], seg[ind << 1 | 1]);
    }

    int query(int ind, int low, int high, int l, int r) {
        push(ind, low, high);
        if (r < low || high < l) return Integer.MAX_VALUE;
        if (l <= low && high <= r) return seg[ind];
        int mid = (low + high) >>> 1;
        return Math.min(
            query(ind << 1, low, mid, l, r),
            query(ind << 1 | 1, mid + 1, high, l, r)
        );
    }

    // --- Public API ---
    void rangeAdd(int l, int r, int val) { 
        updateRange(1, 0, n - 1, l, r, val); 
    }
    int queryMin(int l, int r) { 
        return query(1, 0, n - 1, l, r); 
    }
    void pointSet(int i, int val) { 
        rangeAdd(i, i, val - queryMin(i, i)); 
    }
}


class SparseTable {
    int[][] st;
    int[] log;

    // Build Sparse Table for Range Minimum Query (RMQ)
    SparseTable(int[] arr) {
        int n = arr.length;
        int K = (int)(Math.log(n) / Math.log(2)) + 1;

        st = new int[K][n];
        log = new int[n + 1];

        // Precompute logs
        for (int i = 2; i <= n; i++) log[i] = log[i / 2] + 1;

        // Initialize level 0
        for (int i = 0; i < n; i++) st[0][i] = arr[i];
        
        // Build Sparse Table
        for (int k = 1; k < K; k++) {
            for (int i = 0; i + (1 << k) <= n; i++) {
                st[k][i] = Math.min(st[k - 1][i], st[k - 1][i + (1 << (k - 1))]);
            }
        }
    }

    // Query minimum in range [L, R]
    int queryMin(int L, int R) {
        int len = R - L + 1;
        int k = log[len];
        return Math.min(st[k][L], st[k][R - (1 << k) + 1]);
    }
}





