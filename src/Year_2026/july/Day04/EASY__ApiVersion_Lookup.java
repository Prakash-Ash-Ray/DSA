
/**
 *              Question 1 (Easy)
Scenario: API Version Lookup

You're maintaining the deployment system for Axis.

The deployed API versions are stored in ascending order.

Given a version number, determine whether it has been deployed.

Return its index if found; otherwise return -1.
 * EASY__ApiVersion_Lookup
 */

public class  EASY__ApiVersion_Lookup {

    /**
     * Day        : 004
     * Date       : 07-Jul-2026
     * Difficulty : Easy
     * Topic      : Arrays
     * Pattern    : Binary Search
     * Recognition Clues:
     *    Sorted array
     *    Fast lookup
     *    O(log n)
     *    Search for exact value
     * 
     *
     * Time Taken : Nan
     * Status     : Solved
     *
     * Time Complexity  : O(log N)
     * Space Complexity : O(1)
     *
     * Learning:
     * - When the input is sorted and the problem requires searching for an exact value in O(log n), 
     *    Binary Search is usually the first pattern to consider.
     * - To remember: for mid, use left + (right - left)/2, instead of (left + right)/2.
     * 
     * Invariant:
     *      If the target exists, it is always within the current search range [left, right].
     *
     * Mistakes:
     * - I already know this so, no mistakes made for this one.
     */

    public static void main(String[] args) {

        // Test Cases

        System.out.println(findVersion(new int[] {101, 105, 110, 118, 125}, 110));
        System.out.println(findVersion(new int[] {101,105,110}, 120));
        System.out.println(findVersion(new int[] {}, 100));
        System.out.println(findVersion(new int[] {200}, 200));

    }

    public static int findVersion(int[] versions, int target) {

        // Brute Force: Search through all the elements sequentially and check for each item in the array, Time Complexity: O(N)
        // The array is already sorted.
        // Write a binary search Alogorithm, Time Complexity: O(log N), Space Complxity: O(1)
        
        int left = 0;
        int right = versions.length-1;

        while(left <= right) {
            int mid = left + (right - left)/2;

            if(versions[mid] == target) {
                return mid;
            }
            else if(versions[mid] < target) {
                left = mid + 1;
            }
            else {
                right = mid - 1;
            } 
        }

        return -1;
    }
}