package BinarySearch;

import java.util.ArrayList;

public class kthElementSortedArrays {
    public static int kthElement(ArrayList<Integer> a, ArrayList<Integer> b, int m, int n, int k) {
        if (m > n) return kthElement(b, a, n, m, k);

        int left = k; // length of left half

        // apply binary search:
        int low = Math.max(0, k - n), high = Math.min(k, m);
        while (low <= high) {
            int mid1 = (low + high) >> 1;
            int mid2 = left - mid1;
            // calculate l1, l2, r1, and r2
            int l1 = Integer.MIN_VALUE, l2 = Integer.MIN_VALUE;
            int r1 = Integer.MAX_VALUE, r2 = Integer.MAX_VALUE;
            if (mid1 < m) r1 = a.get(mid1);
            if (mid2 < n) r2 = b.get(mid2);
            if (mid1 - 1 >= 0) l1 = a.get(mid1 - 1);
            if (mid2 - 1 >= 0) l2 = b.get(mid2 - 1);

            if (l1 <= r2 && l2 <= r1) {
                return Math.max(l1, l2);
            }

            // eliminate the halves:
            else if (l1 > r2) high = mid1 - 1;
            else low = mid1 + 1;
        }
        return 0; // dummy statement
    }
}
