/**\
                    Question 2 (Medium)
Scenario: Cloud Backup Capacity

You're building a backup service for Axis.

Each database has a storage size (in GB).

Backups must be completed within D days.

Each day, you choose a maximum backup capacity (GB/day).

Databases must be backed up in order. You cannot split a single database across multiple days.

Find the minimum daily backup capacity required to finish all backups within D days.
 */

public class MEDIUM__Cloud_Backup_Capacity {

    /**
     * Day        : 004
     * Date       : 07-Jul-2026
     * Difficulty : Medium
     * Topic      : Arrays
     * Pattern    : Binary Search on Answer(Solution search space/range).
     * Pattern Recognition Clues:
     *      - Search for minimum feasible answer
     *      - "Within D days"
     *      - Monotonic condition
     *      - Binary Search on Answer
     * 
     * Time Taken : Nan
     * Status     : Solved
     *
     * Time Complexity  : O(N log(sum)), search space size = sum(Databases) - max(Databases) + 1
     * Space Complexity : O(1)
     *
     * Learning:
     * - Binary Search can be applied to the answer space, not only sorted arrays.
     * - Convert an optimization problem into a decision problem.
     * - If a capacity works, every larger capacity also works (monotonic property).
     *
     * Mistakes:
     * - Initially assumed the problem was partitioning the array. Focused on partition points instead of searching the answer.
     * - Forgot that D can be any value, not always 2,then which I blindly went for doing binary search to split array into two.
     * - and some small mistakes, like condition instead of <= used == and little stumbled on little logic at totalDays count.
     */

    public static void main(String[] args) {

        // Test Cases

                System.out.println("------");
                System.out.println(minimumBackupCapacity(new int[] {10, 20, 30, 40}, 2)); // 60

                System.out.println("------");
                System.out.println(minimumBackupCapacity(new int[] {5, 5, 5, 5}, 4)); // 5

                System.out.println("------");
                System.out.println(minimumBackupCapacity(new int[] {100}, 1)); // 100

                System.out.println("------");
                System.out.println(minimumBackupCapacity(new int[] {10, 20, 30, 40}, 1)); // 100

                System.out.println("------");
                System.out.println(minimumBackupCapacity(new int[] {10, 20, 30, 40}, 4)); // 40

                System.out.println("------");
                System.out.println(minimumBackupCapacity(new int[] {7, 2, 5, 10, 8}, 2)); // 18

                System.out.println("------");
                System.out.println(minimumBackupCapacity(new int[] {7, 2, 5, 10, 8}, 3)); // 14

                System.out.println("------");
                System.out.println(minimumBackupCapacity(new int[] {1, 2, 3, 4, 5}, 2)); // 9

                System.out.println("------");
                System.out.println(minimumBackupCapacity(new int[] {1, 2, 3, 4, 5}, 3)); // 6

                System.out.println("------");
                System.out.println(minimumBackupCapacity(new int[] {9, 8, 7, 6, 5}, 2)); // 18

                System.out.println("------");
                System.out.println(minimumBackupCapacity(new int[] {1, 1, 1, 1, 1}, 5)); // 1

                System.out.println("------");
                System.out.println(minimumBackupCapacity(new int[] {1, 1, 1, 1, 1}, 1)); // 5

                System.out.println("------");
                System.out.println(minimumBackupCapacity(new int[] {100, 1, 1, 1}, 2)); // 100

                System.out.println("------");
                System.out.println(minimumBackupCapacity(new int[] {1, 100, 1, 1}, 2)); // 101

                System.out.println("------");
                System.out.println(minimumBackupCapacity(new int[] {50, 50, 50, 50}, 2)); // 100

                System.out.println("------");
                System.out.println(minimumBackupCapacity(new int[] {10, 50, 10, 50, 10}, 3)); // 60

                System.out.println("------");
                System.out.println(minimumBackupCapacity(new int[] {3, 2, 2, 4, 1, 4}, 3)); // 6

    }

    public static int minimumBackupCapacity(int[] databases, int days) {

        // Below two are small optimizations
        if(days >= databases.length) {
            return findMaxStorage(databases);
        }

        if(days == 1){
            return findTotalStorage(databases);
        }

        int minCapacity = findMaxStorage(databases);
        int maxCapacity = findTotalStorage(databases);
 
        // System.out.println("minCapacity: " + minCapacity + " maxCapacity: " + maxCapacity);

        while(minCapacity < maxCapacity) {
            int midCapacity = minCapacity + (maxCapacity - minCapacity)/2;
            int requiredDays = daysTaken(databases, midCapacity);

            if(requiredDays <= days) {
                maxCapacity = midCapacity;
                // System.out.println("MinCapacity: " + minCapacity);
                // System.out.println("MaxCapacity: " + maxCapacity);
            }
            else if(requiredDays > days) {
                minCapacity = midCapacity + 1;
            }
        }

        // System.out.println("f MinCapacity: " + minCapacity);
        // System.out.println("f MaxCapacity: " + maxCapacity);
        
        return minCapacity;
    }

    public static int daysTaken(int[] databases, int capacity) {

        int totalDays = 0;
        int sum = 0;

        // for(int i=0; i<databases.length; i++) { //10, 20, 30, 40
        //     sum += databases[i];
        //     if(sum == capacity){
        //         totalDays++;
        //         sum = 0;
        //     }
        //     else if(sum > capacity) {
        //         totalDays++;
        //         sum = databases[i];
        //     }
        // }

        for(int i=0; i<databases.length; i++){
            if(sum + databases[i] > capacity) {
                totalDays++;
                sum = 0;
            }

            sum += databases[i];
        }

        if(sum > 0) {
            totalDays++;
        }

        // System.out.println("Capacity: " + capacity +  "   Total days: " + totalDays);

        return totalDays;
    }
    
    public static int findMaxStorage(int[] databases) {

        int maxStorage = 0;
        for(int storage: databases) {
            maxStorage = Math.max(maxStorage, storage);
        }

        return maxStorage;
    }

    public static int findTotalStorage(int[] database) {
        int totalStorage = 0;
        for(int storage: database) {
            totalStorage += storage;
        }
        return totalStorage;
    }

}