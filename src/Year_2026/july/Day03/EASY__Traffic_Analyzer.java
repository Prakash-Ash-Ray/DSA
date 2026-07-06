/**
 *                  Question 1 (Easy)
Scenario: Website Traffic Analyzer

You're building analytics for Axis.

Every minute, the website records how many users are currently active.

The analytics team wants to know the maximum number of users that were active during any consecutive k minutes.
 */

public class EASY__Traffic_Analyzer {

    /**
     * Day        : 003
     * Date       : 06-Jul-2026
     * Difficulty : Easy
     * Topic      : Arrays
     * Pattern    : Calculated fixed sum + Fixed sliding window.
     * Recognition Clues:
     *      Consecutive elements
     *      Fixed window size (k)
     *      Maximum/Minimum/Sum
     *      O(n) expected
     * Why Sliding Window?
     *  Because consecutive subarrays overlap.
     *  Instead of recalculating every window from scratch,
     *  reuse the previous window by removing one element and adding one element.
     *
     * Time Taken : Nan
     * Status     : Solved 
     *
     * Time Complexity  : O(n)
     * Space Complexity : O(1)
     *
     * Learning:
     * - Compute first window once, then slide the fixed window, by subtracting first element and adding the next upcoming element
     * - Avoid unnecessary special-case handling if the general algorithm already covers it.
     * Mistakes:
     * - written unnecessary code for handling special case
     */

    public static void main(String[] args) {

        // Test Cases

        System.out.println(maxActiveUsers(new int[] {5, 3, 8, 2, 7, 4}, 3));
        System.out.println(maxActiveUsers(new int[] {10}, 1));
        System.out.println(maxActiveUsers(new int[] {1, 2, 3, 4}, 4));  
        System.out.println(maxActiveUsers(new int[] {5, 1, 9, 2}, 1));
        System.out.println(maxActiveUsers(new int[] {0, 0, 0, 0}, 2));
        System.out.println(maxActiveUsers(new int[] {5, 5, 5, 5}, 3));
        System.out.println(maxActiveUsers(new int[] {9, 8, 1, 1, 1}, 2));
        System.out.println(maxActiveUsers(new int[] {1, 1, 1, 9, 8}, 2));

    }

    public static int maxActiveUsers(int[] activeUsers, int k) {

        // No need of this case, below algorithm covers it
        // if(activeUsers.length == k) { 
        //     int totalUsers = 0; 
        //     for(int Users: activeUsers) { 
        //         totalUsers += Users; 
        //     } 
        //     return totalUsers; 
        // }

        int totalUsers = 0;

        for(int i=0; i<k; i++) {
            totalUsers += activeUsers[i];
        }
        
        int maxTotalUsers = totalUsers; 

        // int startMinute = 0;
        // int endMinute = k-1;

        // while(endMinute + 1 < activeUsers.length) {
        //     totalUsers = (totalUsers - activeUsers[startMinute]) + activeUsers[endMinute+1];
        //     maxTotalUsers = Math.max(maxTotalUsers, totalUsers);
            
        //     startMinute ++;
        //     endMinute ++;
        // }

        for(int end = k; end < activeUsers.length; end++) {
            totalUsers -= activeUsers[end-k];
            totalUsers += activeUsers[end];
            maxTotalUsers = Math.max(maxTotalUsers, totalUsers);
        }
        

        return maxTotalUsers;
    }
}
