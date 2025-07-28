class Solution {
    public int[] singleNumber(int[] nums) {
        int xr = 0;
        for (int num : nums) {
            xr ^= num;
        }

        // Get rightmost set bit
        int mask = xr & (-xr);

        int[] result = new int[2];
        for (int num : nums) {
            if ((num & mask) == 0) // mask-th bit of num is different
                result[0] ^= num;
            else // mask-th bit of num is same
                result[1] ^= num;
        }

        return result;
    }
}

//Similar to find Repeating and Missing Number