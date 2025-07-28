class Solution {
    public int maxGoodNumber(int[] nums) {
        String[] binaries = new String[3];
        for (int i = 0; i < 3; i++) {
            binaries[i] = Integer.toBinaryString(nums[i]); //memorize
        }

        String maxi = "";
        String[] perms = {
            binaries[0] + binaries[1] + binaries[2],
            binaries[0] + binaries[2] + binaries[1],
            binaries[1] + binaries[0] + binaries[2],
            binaries[1] + binaries[2] + binaries[0],
            binaries[2] + binaries[0] + binaries[1],
            binaries[2] + binaries[1] + binaries[0],
        };

        for (String p : perms) {
            if (maxi.compareTo(p) < 0) maxi = p;
        }

        return Integer.parseInt(maxi, 2);
    }
}
class sortStringsByBinary{
    public static void main(String[] args) {
        
    }
}

// in this question cant i sort on the basis  of descending order of bit count, if bit count then in ascending order of value
// not working because strings must be sorted

//correct intuition is find all permutation by converting num to their binary String and concatenating and sort them lexographically