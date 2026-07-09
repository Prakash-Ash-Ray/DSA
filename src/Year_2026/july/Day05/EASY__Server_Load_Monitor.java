/*
            Question 1 (Easy)
Scenario: Daily Server Load Monitor

You're building a monitoring dashboard for Axis.

Each day, the monitoring system records the server load percentage.

For every day, determine how many days you must wait until a higher server load occurs.

If no future day has a higher load, return 0 for that day.
*/

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class EASY__Server_Load_Monitor {
    
    /**
     * Day        : 005
     * Date       : 09-Jul-2026
     * Difficulty : Easy
     * Topic      : Stack
     * Pattern    : monotonic stack (Next Greater Element))
     * Why does the stack work?
     *      Because once a larger future load is found, that previous day's answer is finalized forever.
     *      It never needs to be revisited. 
     * 
     * Recognition Clues:
     *      next greater element
     *      future element
     *      First Large element
     *      O(n) expected
     * 
     * Invariant:
     *       The stack always stores indices whose next greater load has not yet been found.
     *       The stack stores unresolved indices, and their corresponding values are monotonic.
     *       Bottom → Top: decreasing (for Next Greater)
     *       Top → Bottom: increasing
     * 
     * Time Taken : Nan
     * Status     : Solved
     *
     * Time Complexity  : O(N)
     * Space Complexity : O(N)
     *
     * Learning:
     * - Store indices instead of values when the answer depends on distance.
     * - A monotonic decreasing stack helps find the next greater element in O(n).
     * - Every element is pushed and popped at most once.
     *
     * Mistakes:
     * - Initially wanted to store values instead of indices.
     * - Forgot that indices are needed to calculate waiting days.
     */

    public static void main(String[] args) {

        // Test Cases

        System.out.println(Arrays.toString(daysUntilHigherLoad(new int[] {30, 40, 35, 50}))); // [1, 2, 1, 0]
        System.out.println(Arrays.toString(daysUntilHigherLoad(new int[] {80, 70, 60}))); // [0, 0, 0]
        System.out.println(Arrays.toString(daysUntilHigherLoad(new int[] {10}))); // [0]
        System.out.println(Arrays.toString(daysUntilHigherLoad(new int[] {10, 20}))); // [1, 0]
        System.out.println(Arrays.toString(daysUntilHigherLoad(new int[] {20, 10}))); // [0, 0]
        System.out.println(Arrays.toString(daysUntilHigherLoad(new int[] {10, 20, 30, 40}))); // [1, 1, 1, 0]
        System.out.println(Arrays.toString(daysUntilHigherLoad(new int[] {40, 30, 20, 10}))); // [0, 0, 0, 0]
        System.out.println(Arrays.toString(daysUntilHigherLoad(new int[] {30, 60, 40, 70}))); // [1, 2, 1, 0]
        System.out.println(Arrays.toString(daysUntilHigherLoad(new int[] {70, 60, 80}))); // [2, 1, 0]
        System.out.println(Arrays.toString(daysUntilHigherLoad(new int[] {20, 30, 25, 35, 15, 40}))); // [1, 2, 1, 2, 1, 0]
        System.out.println(Arrays.toString(daysUntilHigherLoad(new int[] {50, 50, 50}))); // [0, 0, 0]
        System.out.println(Arrays.toString(daysUntilHigherLoad(new int[] {30, 30, 40}))); // [2, 1, 0]
        System.out.println(Arrays.toString(daysUntilHigherLoad(new int[] {40, 30, 30, 50}))); // [3, 2, 1, 0]
        System.out.println(Arrays.toString(daysUntilHigherLoad(new int[] {90, 80, 70, 100}))); // [3, 2, 1, 0]
        System.out.println(Arrays.toString(daysUntilHigherLoad(new int[] {10, 50, 20, 30, 60}))); // [1, 3, 1, 1, 0]
        System.out.println(Arrays.toString(daysUntilHigherLoad(new int[] {60, 10, 20, 30, 40}))); // [0, 1, 1, 1, 0]
        System.out.println(Arrays.toString(daysUntilHigherLoad(new int[] {20, 10, 30, 25, 40}))); // [2, 1, 2, 1, 0]
        System.out.println(Arrays.toString(daysUntilHigherLoad(new int[] {35, 45, 25, 55, 30, 60}))); // [1, 2, 1, 2, 1, 0]
    }

    public static int[] daysUntilHigherLoad(int[] loads) {

        int[] daysUntilHigherServerLoad  = new int[loads.length];

        Deque<Integer> stack = new ArrayDeque<>();

        for(int i=0; i<loads.length; i++) {

            while(!stack.isEmpty() 
                && loads[stack.peek()] < loads[i]){

                int previousDay = stack.pop(); 

                daysUntilHigherServerLoad[previousDay] = i - previousDay;
            }

            stack.push(i);
        }


        return daysUntilHigherServerLoad;
    }
}