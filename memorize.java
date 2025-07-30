// import javax.sound.midi.Track;

public class memorize {
    String s = Integer.toBinaryString(2);

    int xr = 0;
    int mask = xr & (-xr); // Rightmost set bit

    // Track number of components instead. A DSU-based trick is: if number of unions = n - 1, the graph is connected.
    // Let’s add a countAlice and countBob to track how many successful unions were done (excluding redundant ones).
    //mEMORIZE COMMON PREFIX CONCEPT
        // Lets manually apply & operation on all elements present in the 
        // range 5 and 7 including themself.

        //             5 = 101
        //             6 = 110
        //             7 = 111

        // AND operations:
        //             0 & 0 = 0
        //             0 & 1 = 0
        //             1 & 0 = 0
        //             1 & 1 = 1

        // With the help of above operations lets perform AND operations.

        //             101(5) & 110(6) = 100(4)

        //             100(4) & 111(7) = 100(4)

        //     answer = 4


        // Now lets apply the method used in the code:

        // we will remove last digit from binary representation of both left
        // and right number until we reach a point where both have same 
        // element at the same position.
        // And will also keep track of how many times we removed elements 
        // by keeping a count of it.

        // lets apply it practically:

        // first loop:
        //             101 >> 1 = 10
        //             111 >> 1 = 11
        //             cnt = 1

        // Second loop:
        //             10 >> 1 = 1
        //             11 >> 1 = 1
        //             cnt = 2

        // Third loop:
        //             1 == 1 
        // So now since they are equal we will add those many 0's at the 
        // end of the current element we have by using left shift operator.

        //             1 << cnt  = 1 << 2  => 100(4) 


        //     Result = 4



}
