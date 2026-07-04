
import java.util.Arrays;

/**  Question 1 (Easy)
Scenario: Log Cleanup Service

You're building a log processing service for Axis.

After logs are sorted by timestamp, duplicate log IDs appear next to each other because multiple services accidentally recorded the same event.

To reduce storage usage, the infrastructure team wants you to remove duplicates in-place.

Return the number of unique log IDs.
*/

public class EASY__Cleanup_Service {

    /**
     * Day        : 002
     * Date       : 04-Jul-2026
     * Difficulty : Easy
     * Topic      : Arrays
     * Pattern    : two pointer
     * Why this pattern?
     *          The array is sorted, so duplicates are adjacent.
     *          A read pointer can scan the array while a write pointer compacts unique elements in place using O(1) extra space.
     *
     * Time Taken : Nan
     * Status     : Solved
     *
     * Time Complexity  : O(n)
     * Space Complexity : O(1)
     *
     * Learning:
     * - To check the boundary conditions and edge cases.
     * - understand the constraints before solving the problem
     * - Simplify the logic instead of preserving unnecessary data (duplicates don't need to be kept).
     * - Identify the appropriate pattern from the clues like Sorted, in-place, O(1)
     *
     * Mistakes:
     * - didn't understand the constraints properly; 
     * - didnt check for edge cases. 
     * - check for the boundaries violation properly, making simple logic complex
     */

    public static void main(String[] args) {

        // Test Cases

        System.out.println(removeDuplicates(new int[] {101,101,105,110,110,120}));
        System.out.println(removeDuplicates(new int[] {5,5,5}));
        System.out.println(removeDuplicates(new int[] {7})); 
        System.out.println(removeDuplicates(new int[] {110,110,110,110,120,130,130,140,150,160,160,160,160,170,170,180,180,190,200,200,200,200}));
        System.out.println(removeDuplicates(new int[] {}));
        System.out.println(removeDuplicates(new int[] {1,2}));
        System.out.println(removeDuplicates(new int[] {1,2,3}));
        System.out.println(removeDuplicates(new int[] {1,2,3,4,5}));
        System.out.println(removeDuplicates(new int[] {1,2,3,4,5,6,7,8,9,10}));
    }

    public static int removeDuplicates(int[] logIds) {

        if(logIds.length < 1) {
            return 0;
        }
        
        int write = 0;
        int read = 1;


        while(read < logIds.length) {
            if( logIds[write] == logIds[read]) {
                read++;
            }                
            else{
                if(write+1 != read){                   
                    logIds[write+1] = logIds[read];
                }
                write += 1;
                read += 1;
            } 
        }

        System.out.println(Arrays.toString(logIds));

        return write+1;
    }
}