public class kthCharacter2 {
    
}
class Solution {
    public char kthCharacter(long k, int[] operations) {
        
        return solve(k,operations,operations.length);
    }

   private char solve(long k, int[] operations, int n) {
    char result = 'a';

    while (n > 0) {
        long left = 1;
        long right = (long) Math.pow(2, n);
        long mid = left + (right - left) / 2;
        int flip = operations[n - 1];

        if (k <= mid) {
            // go to left half, just reduce n
            n--;
        } else {
            // right half
            k = k - mid;
            if (flip == 1) {
                // simulate character increment
                result = result == 'z' ? 'a' : (char)(result + 1);
            }
            n--;
        }
    }

    return result;
   }

}
// because of constraints brute force wont work
// MEMORIZE THIS TECHNIQUE AT ALL COSTS.
// Here we will do reverse engineering to find kth character pos.
// we know length of answer string will always be 2^n
// take low = 0, high = 2^n - 1 , mid = (low + high) >> 1
// now see if k <= mid => in the string from which we obtained this current string , kth is in first half so just reduce n
// else it means in the string from which we obtained this current string , kth is in second half. So do k = k-mid to get original position of k in the previuos string 
// here if nums[n-1] == 1 means we have to do operation 2 so make result = result + 1 whre result = 'a' at the beginning of loop and do n--
// at last return result