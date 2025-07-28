import java.util.*;
class Solution {
   
    class DisjointSet{
        List<Integer> parent = new ArrayList<>();
        List<Integer> size = new ArrayList<>();
        DisjointSet(int n){
            for(int i=0;i<n;i++){
                parent.add(i);
                size.add(i);
            }
        }
        int findUPar(int node){
            if(node == parent.get(node)) return node;
            int ulp = findUPar(parent.get(node));
            parent.set(node, ulp);
            return parent.get(node);
        }
        void unionBySize(int u, int v){
            int ulp_u = findUPar(u);
            int ulp_v = findUPar(v);
            if (ulp_u == ulp_v) return;
            if(size.get(ulp_u) < size.get(ulp_v)){
                parent.set(ulp_u, ulp_v);
                size.set(ulp_v , size.get(ulp_u) + size.get(ulp_v));
            }
            else{
                parent.set(ulp_v, ulp_u);
                size.set(ulp_u , size.get(ulp_u) + size.get(ulp_v));
            }
        }
    }
    public boolean[] distanceLimitedPathsExist(int n, int[][] edgeList, int[][] queries) {
        Arrays.sort(edgeList, (a, b) -> Integer.compare(a[2], b[2]));
        int q = queries.length;
        int[][] indexedQueries = new int[q][4]; 
        for (int i = 0; i < q; i++) {
            indexedQueries[i][0] = queries[i][0];
            indexedQueries[i][1] = queries[i][1];
            indexedQueries[i][2] = queries[i][2];
            indexedQueries[i][3] = i;
        }
        Arrays.sort(indexedQueries, (a, b) -> Integer.compare(a[2], b[2]));
        DisjointSet dsu = new DisjointSet(n);
        boolean[] result = new boolean[q];
        int edgeIndex = 0;
        for (int[] query : indexedQueries) {
            int p = query[0], qNode = query[1], limit = query[2], originalIndex = query[3];
            // Union all edges with weight < limit
            while (edgeIndex < edgeList.length && edgeList[edgeIndex][2] < limit) {
                dsu.unionBySize(edgeList[edgeIndex][0], edgeList[edgeIndex][1]);
                edgeIndex++;
            }
            // If p and qNode are connected, answer is true
            result[originalIndex] = (dsu.findUPar(p) == dsu.findUPar(qNode));
        } 
        return result;
    }
}



// Sort edgeList by weight (ascending).
// Add an index to each query and sort queries by limit.
// Initialize DSU with n nodes.
// For each query (in increasing order of limit):
// Union all edges with weight < limit using DSU.
// Then check: are u and v connected in DSU?
// If yes → true (a path with all edges < limit exists).
// Else → false.
// Store results in original order using the indexed queries.
public class edgeLengthLimitedPaths {
    public static void main(String[] args) {
        
    }
}
