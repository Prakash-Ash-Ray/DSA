import java.util.Arrays;

/**     Question 2 (Medium)
Scenario: Customer Appointment Scheduler

You're building the appointment scheduler for Axis Bank.

Customers request appointment slots represented by:

[startTime, endTime]

Some appointments overlap.

Before showing the schedule to the branch manager, merge all overlapping appointments.
*/

public class MEDIUM__Appointment_Scheduler {

    /**
     * Day        : 002
     * Date       : 04-Jul-2026
     * Difficulty : Medium
     * Topic      : Arrays
     * Pattern    : Sorting + Linear Scan (Merge intervals)
     * Recognition Clues:
     *      Overlapping intervals
     *      Merge ranges
     *      Start and end values
     *      Scheduling or calendar problems
     *      Unsorted intervals → Sort first
     *
     * Time Taken : Nan
     * Status     : Solved
     *
     * Time Complexity  : O(NlogN)
     * Space Complexity : O(N)
     *
     * Learning:
     * - Always clarify whether intervals are sorted; if not, sort by the start time first.
     * - When merging intervals, the merged end time should be the maximum end seen so far.
     * - Handle edge cases explicitly (empty input, single interval).
     * - Allocate for the maximum possible result size, then trim it before returning.
     * - Think in terms of maintaining a current merged interval while traversing
     *
     * Mistakes:
     * - forgot to deal with single interval edge case
     * - merge logic failed some cases. fixed it.
     */

    public static void main(String[] args) {

        // Test Cases

        System.out.println(Arrays.deepToString(mergeAppointments(new int[][] {{1,3},{2,6},{8,10},{15,18}})));
        System.out.println(Arrays.deepToString(mergeAppointments(new int[][] {{1,4},{4,5}})));
        System.out.println(Arrays.deepToString(mergeAppointments(new int[][] {{1,3},{2,6},{4,9}})));
        System.out.println(Arrays.deepToString(mergeAppointments(new int[][] {{1,2},{3,4},{5,6}})));
        System.out.println(Arrays.deepToString(mergeAppointments(new int[][] {{1,10},{2,5}})));
        System.out.println(Arrays.deepToString(mergeAppointments(new int[0][0])));
    }

    public static int[][] mergeAppointments(int[][] appointments) {

        // {{1,3},{2,6},{8,10},{15,18}} -> {{1,6},{8,10},{15,18}}
        // {{1,4},{4,5}} -> {{1,5}}
        // {{1,3},{2,6},{4,9}} -> {{1,9}}
        // {{1,2},{3,4},{5,6}} -> {{1,2},{3,4},{5,6}}
        // Sort the appointments on start time, then merger the time intervals

        if(appointments.length == 0) {
            return new int[0][0];
        }

        if(appointments.length == 1) {
            return appointments;
        }

        int[][] ans = new int[appointments.length][appointments[0].length]; //max number of intervals will be same as the original
        
        Arrays.sort(appointments, (a,b) -> Integer.compare(a[0], b[0]));
        
        int startTime=appointments[0][0];
        int endTime=appointments[0][1];
        int resultindex = 0;

        for(int i=1; i<appointments.length; i++) { // {{1,10}, {2,5}}
            if(endTime >= appointments[i][0]){ //merge if greater than or equals to
                // if(endTime <= appointments[i][1]){
                //     endTime = appointments[i][1];
                // }
                endTime = Math.max(endTime, appointments[i][1]);
            }
            else{
                ans[resultindex++] = new int[] {startTime, endTime};
                startTime = appointments[i][0];
                endTime = appointments[i][1];
            }
        }

        ans[resultindex++] = new int[]{startTime, endTime};

        return Arrays.copyOf(ans, resultindex);
    }
}



/**
 *                              Tiny Challenge
 * - Instead of merging appointments, 
 *   return the minimum number of meeting rooms required so that no two overlapping appointments share the same room.
 *  eg1:
 *  [1,4]
 *  [2,5]
 *  [7,9]
 * 
 * - find the min startTime and max endTime,
 *   from startTime to endTime, go step by step, and check in that interval how many meetings are happening
 *   from eg1: 1, 9 -> (1,2), (2,3), (3,4) ...(5,6), (6,7), (7,8) (8,9)
 *   optimizatio: only look for active interval/interval where change occurs, skip the otherwise..
 *   from eg1: 1, 9 -> (1,2), (2,4), (4,5), (5,7), (7,9) 
 *   Algorithm: Sweep Line ALgorithm
 */
