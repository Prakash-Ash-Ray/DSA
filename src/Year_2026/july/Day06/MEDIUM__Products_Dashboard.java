import java.util.Arrays;
import java.util.PriorityQueue;

/*
         Question 2 (Medium)
Scenario: Trending Products Dashboard

You're building the analytics dashboard for Axis Marketplace.

Each product has a popularity score.

The marketing team wants to display only the K most popular products.

Return the K largest scores in any order.
*/

class MEDIUM__Products_Dashboard {
    /**
     * Day        : 006
     * Date       : 09-Jul-2026
     * Difficulty : Medium
     * Topic      : Heap
     * Pattern    : Min-heap optimization
     * 
     * Recognition Clues
     *      Top K
     *      K largest
     *      K smallest
     *      Streaming data
     *      Better than full sorting
     *      Need only K elements, not the complete ordering.
     *
     * Time Taken : Nan
     * Status     : Solved
     *
     * Time Complexity  : O(N logK)
     * Space Complexity : O(K)
     * 
     * Invariant:
     *      The heap always contains the k largest elements seen so far.
     *      The root of the heap is the smallest among those K elements.
     *
     * Learning:
     * - For Top K problems, maintaining a min-heap of size K is more efficient than building a max-heap of all elements.
     * - The heap invariant ensures it always contains the current K largest elements seen so far.
     *
     * Mistakes:
     * - Initially used a max-heap, resulting in O(N log N).
     * - Initially replaced the heap's minimum unconditionally instead of checking whether the new element belonged in the top K.
     * 
     */

    public static void main(String[] args) {

        // Test Cases

        System.out.println(Arrays.toString(topKPopularProducts(new int[] {15, 7, 30, 10, 25}, 2)));
        System.out.println(Arrays.toString(topKPopularProducts(new int[] {5,1,8}, 1)));
        System.out.println(Arrays.toString(topKPopularProducts(new int[] {}, 1)));
        System.out.println(Arrays.toString(topKPopularProducts(new int[] {3,6,1,5,7,8}, 4)));
        System.out.println(Arrays.toString(topKPopularProducts(new int[] {3,5,2,6,6,3,7}, 5)));
        System.out.println(Arrays.toString(topKPopularProducts(new int[] {3,5,9,10}, 5)));

    }

    public static int[] topKPopularProducts(int[] popularity, int k) {

        // Say, If there are products of particular category and asked K top products
        if(popularity.length == 0 || k == 0) {
            return new int[0];
        }

        k = Math.min(popularity.length, k);

        int[] topKProducts = new int[k];

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(int score: popularity) {
            if(pq.size() < k){
                pq.offer(score);
            }
            else if(score > pq.peek()){
                    pq.poll();
                    pq.offer(score);
            }
        }
        
        while(!pq.isEmpty() && k > 0) {
            topKProducts[--k] = pq.poll();
        }

        return topKProducts;
    }
}