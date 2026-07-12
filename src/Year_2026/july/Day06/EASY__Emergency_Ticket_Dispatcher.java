
import java.util.Arrays;
import java.util.PriorityQueue;

/*
        Question 1 (Easy)
Scenario: Emergency Ticket Dispatcher

You're building the support system for Axis.

Customer support tickets arrive with a priority number.

A smaller number means higher priority.

Tickets are processed one by one.

Return the order in which tickets will be processed.
*/

public class EASY__Emergency_Ticket_Dispatcher {
    /**
     * Day        : 006
     * Date       : 09-Jul-2026
     * Difficulty : Easy
     * Topic      : Heap
     * Pattern    : Priority Queue(min-heap)
     * Recognition Clues
     *      Repeatedly extract minimum
     *      Highest priority first
     *      Dynamic ordering
     *      Efficient retrieval of smallest element
     *   
     * Time Taken : Nan
     * Status     : Solved
     *
     * Time Complexity  : O(nlogn)
     * Space Complexity : O(N)
     *
     * Learning:
     * - Use a Priority Queue when you repeatedly need access to the smallest or largest element while elements are continuously 
     *   added or removed. A heap maintains only the heap property, not a fully sorted order.
     *
     * Mistakes:
     * - Initially forgot the PriorityQueue API methods (offer, poll, peek) and had to look them up.
     */

    public static void main(String[] args) {

        System.out.println(Arrays.toString(processTickets(new int[] {4, 1, 3, 2})));
        System.out.println(Arrays.toString(processTickets(new int[] {4, 3, 2, 1})));
        System.out.println(Arrays.toString(processTickets(new int[] {100, 23, 1, 324324, 5343})));
        System.out.println(Arrays.toString(processTickets(new int[] {4556, 13243, 2323,242})));
        System.out.println(Arrays.toString(processTickets(new int[] {4})));
        System.out.println(Arrays.toString(processTickets(new int[] {})));

    }

    public static int[] processTickets(int[] priorities) {

        int[] orderedPriorities = new int[priorities.length];

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        for(int priority: priorities) {
            pq.offer(priority);
        }

        int i=0;
        while(!pq.isEmpty()){
            orderedPriorities[i++] = pq.poll();
        }

        return orderedPriorities;
    }
}