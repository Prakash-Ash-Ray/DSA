import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


/**
Question 2 (Medium)
Scenario: Bank Transaction Analyzer

You are working on Axis.

Every transaction has a positive or negative amount.

The finance team wants to quickly detect whether there exists a continuous sequence of transactions whose total amount equals a target value.

If such a sequence exists, return its starting and ending indices.

Otherwise return [-1, -1].
 */

public class MEDIUM__Transaction_Analyzer {

    /**
     * Day        : 001
     * Date       : 03-Jul-2026
     * Difficulty : Medium
     * Topic      : Arrays
     * Pattern    : Prefix Sum + Hashing
     * Optimization Path: Brute Force → Running Sum → Prefix Sum + HashMap
     * Data Structures  : HashMap
     * 
     * Recognition Clues:
     *   - Continuous subarray
     *   - Sum equals target
     *   - Negative numbers present (so sliding window won't work)
     *   - O(n) expected
     *
     * Time Taken : 1hr min (need to improve)
     * Status     : Solved with mix of Independent + Hint.
     *
     * Time Complexity  : O(n^3) -> O(n^2) -> O(n)
     * Space Complexity : O(1) -> O(1) -> O(n)
     *
     * Learning:
     * - Prefix + two sum -> to find sub array sum equal to target
     * - to find the subarray sum in O(n), use Prefix sum + HashMap, if need indices, or else Prefix sum + HashSet
     * - It is always better to initialize hashmap with a default value say 0 or custom default value.
     * - For prefix sum problems, initialize the HashMap with (0, -1) to represent a prefix sum of 0 before the array starts. This correctly handles subarrays that begin at index 0.
     *
     * Mistakes:
     * - in the first iteration, have done duplicated calculations, which also cause the final indexes swapping issue.
     * - Could find the the opitimized solution but got the first step correct as prefix sum couldnt get the second step, I would say decent intution.
     * - Missed the edge case where the required subarray starts from index 0. Initializing the prefix map with (0, -1) correctly handles these cases.
     * - Created an uneeded prefixsum array
     */

    public static void main(String[] args) {

        // Test Cases
        
        System.out.println(Arrays.toString(findTransactionRange(new int[] {120, -20, 50, 30, -10, 40}, 70)));
        System.out.println(Arrays.toString(findTransactionRange(new int[] {120, -20, 50, 40, -10}, 70)));
        System.out.println(Arrays.toString(findTransactionRangeV2(new int[] {50, -20, -30, -500, 20}, -50)));
        System.out.println(Arrays.toString(findTransactionRange(new int[] {10, 20, 30}, 100)));

        System.out.println("------------");

        System.out.println(Arrays.toString(findTransactionRangeV1(new int[] {120, -20, 50, 30, -10, 40}, 70)));
        System.out.println(Arrays.toString(findTransactionRangeV1(new int[] {120, -20, 50, 40, -10}, 70)));
        System.out.println(Arrays.toString(findTransactionRangeV2(new int[] {50, -20, -30, -500, 20}, -50)));
        System.out.println(Arrays.toString(findTransactionRangeV1(new int[] {10, 20, 30}, 100)));

        System.out.println("------------");

        System.out.println(Arrays.toString(findTransactionRangeV2(new int[] {120, -20, 50, 30, -10, 40}, 70)));
        System.out.println(Arrays.toString(findTransactionRangeV2(new int[] {120, -20, 50, 40, -10}, 70)));
        System.out.println(Arrays.toString(findTransactionRangeV2(new int[] {50, -20, -30, -500, 20}, -50)));
        System.out.println(Arrays.toString(findTransactionRangeV2(new int[] {10, 20, 30}, 100)));
    }

    public static int[] findTransactionRange(int[] transactions, int target) {

        // Brute Force: find every possible substring and calculate sum, when equals target return, Time Complexity: O(n^3)
        
        for(int i=0; i<transactions.length; i++) {
            for(int j=i; j<transactions.length; j++){ //corrected j=0 to j=1 to avoid duplicate calcs like i=0,j=4 and i=4,j=0, also wrong indices order might occur
                int sum = 0;
                for(int k=i; k<=j; k++) {
                    sum = sum + transactions[k];
                }
                if(sum == target) {
                    return new int[] {i,j};
                }
            }
        }

        return new int[] {-1,-1};
    }

    public static int[] findTransactionRangeV1(int[] transactions, int target) {

        // Running Sum: No Need of thrid loop, Time Complexity: O(n^2)
        
        for(int i=0; i<transactions.length; i++) {
            int sum = 0;
            for(int j=i; j<transactions.length; j++) {
                sum += transactions[j];
                if(sum == target) {
                    return new int[] {i,j};
                }
            }
        }
         

        return new int[] {-1,-1};
    }

        public static int[] findTransactionRangeV2(int[] transactions, int target) {

        int[] prefixsum = new int[transactions.length];

        int sum = 0;
        for(int i=0; i<transactions.length; i++) {
            sum += transactions[i];
            prefixsum[i] = sum;
        }

        Map<Integer, Integer> mp = new HashMap<>();

        mp.put(0,-1); // Represents a prefix sum of 0 before index 0.
                          // Allows detection of subarrays that start at index 0.

        for(int i=0; i<prefixsum.length; i++) {
            if(mp.containsKey(prefixsum[i] - target)) {
                return new int[] {mp.get(prefixsum[i] - target)+1,i};
            }

            mp.put(prefixsum[i], i);
        }

        /**
         * Prefix sum array is not needed, just calculate as you go: (Also improved naming)
         * Map<Integer, Integer> prefixMap = new HashMap<>();
         * prefixMap.put(0,-1);
         * int runningprefix = 0;
         * for(int i=0; i<prefixsum.length; i++) {
         *      runningprefix += transactions[i]
         *      if(prefixMap.containsKey(sum - target)) {
         *          return new int[] {prefixMap.get(runningprefix - target)+1,i};
         *      }
         *      prefixMap.put(runningprefix, i);
         * }
         */

        return new int[] {-1,-1};
    }


}

