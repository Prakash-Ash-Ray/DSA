/*
                    Question 2 (Medium)
Scenario: Microservice Response Analyzer

You're monitoring response times of a chain of microservices.

Each service reports its response time in milliseconds.

For every service, determine the next service to the right whose response time is smaller.

Return the value of that response time.

If no such service exists, return -1 for that position.
*/

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class MEDIUM__Microservice_Response_Analyzer {

    /**
     * Day        : 005
     * Date       : 08-Jul-2026
     * Difficulty : Medium
     * Topic      : Stack
     * Pattern    : Monotonic Stack (Next Smaller Element)
     * Recognition clues
     *      Need next greater/smaller element
     *      Need O(n)
     *      Need information about previous unresolved elements
     *      Answer determined when current element arrives
     * Invariant:
     *       The stack always stores indices whose next smaller response has not yet been found.
     *       The stack stores unresolved indices, and their corresponding values are monotonic.
     *       Bottom → Top: increasing (for Next Smaller)
     *       Top → Bottom: decreasing
     * 
     * Time Taken : Nan
     * Status     : Solved
     *
     * 
     * Time Complexity  : O(N)
     * Space Complexity : O(N)
     *
     * Learning:
     * - The same monotonic stack pattern can solve both Next Greater and Next Smaller problems by 
     *   changing the comparison operator and what is stored in the answer
     * - the remaning elements in the stack needs to be processed, as the values need to -1 instead of 0 for which there 
     *   is no next smaller element.
     *
     * Mistakes:
     * - Since, it is same as the previous one, no mistakes done.
     * -
     */

    public static void main(String[] args) {

        // Test Cases

        System.out.println(Arrays.toString(nextSmallerResponse(new int[] {15, 12, 18, 10, 20}))); // [12, 10, 10, -1, -1]
        System.out.println(Arrays.toString(nextSmallerResponse(new int[] {5, 4, 3, 2}))); // [4, 3, 2, -1]
        System.out.println(Arrays.toString(nextSmallerResponse(new int[] {2, 3, 4, 5}))); // [-1, -1, -1, -1]
        System.out.println(Arrays.toString(nextSmallerResponse(new int[] {10}))); // [-1]
        System.out.println(Arrays.toString(nextSmallerResponse(new int[] {8, 6}))); // [6, -1]
        System.out.println(Arrays.toString(nextSmallerResponse(new int[] {6, 8}))); // [-1, -1]
        System.out.println(Arrays.toString(nextSmallerResponse(new int[] {10, 5, 8, 3, 6}))); // [5, 3, 3, -1, -1]
        System.out.println(Arrays.toString(nextSmallerResponse(new int[] {7, 9, 5, 8, 6}))); // [5, 5, -1, 6, -1]
        System.out.println(Arrays.toString(nextSmallerResponse(new int[] {20, 15, 25, 10, 30}))); // [15, 10, 10, -1, -1]
        System.out.println(Arrays.toString(nextSmallerResponse(new int[] {30, 20, 25, 15, 10}))); // [20, 15, 15, 10, -1]
        System.out.println(Arrays.toString(nextSmallerResponse(new int[] {4, 8, 5, 2, 25}))); // [2, 5, 2, -1, -1]
        System.out.println(Arrays.toString(nextSmallerResponse(new int[] {13, 7, 6, 12}))); // [7, 6, -1, -1]
        System.out.println(Arrays.toString(nextSmallerResponse(new int[] {1, 2, 1, 2, 1}))); // [-1, 1, -1, 1, -1]
        System.out.println(Arrays.toString(nextSmallerResponse(new int[] {9, 8, 7, 6, 5}))); // [8, 7, 6, 5, -1]
        System.out.println(Arrays.toString(nextSmallerResponse(new int[] {5, 7, 6, 8, 4}))); // [4, 6, 4, 4, -1]
        System.out.println(Arrays.toString(nextSmallerResponse(new int[] {11, 13, 21, 3}))); // [3, 3, 3, -1]
        System.out.println(Arrays.toString(nextSmallerResponse(new int[] {50, 40, 60, 30, 70}))); // [40, 30, 30, -1, -1]
        System.out.println(Arrays.toString(nextSmallerResponse(new int[] {10, 9, 8, 7, 11, 6}))); // [9, 8, 7, 6, 6, -1]

    }

    public static int[] nextSmallerResponse(int[] responseTimes) {

         int[] nextSmallerResponseTime = new int[responseTimes.length];

        Deque<Integer> stack = new ArrayDeque<>();

        for(int i=0; i<responseTimes.length; i++) {

            while(!stack.isEmpty() 
                && responseTimes[stack.peek()] > responseTimes[i]){

                int previousResponseIndex = stack.pop(); 

                nextSmallerResponseTime[previousResponseIndex] = responseTimes[i];
            }

            stack.push(i);
        }

        while(!stack.isEmpty()){
            nextSmallerResponseTime[stack.pop()] = -1;
        }


        return nextSmallerResponseTime;
    }
}