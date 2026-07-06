
/**
 *              Question 2 (Medium)
Scenario: Security Audit

You're building a security monitoring service for Axis.

Every login attempt is represented by a character:

S = Success
F = Failure

The security team wants to find the longest continuous period during which there were at most k failed logins.

Return the length of that longest period.
 */

public class MEDIUM_Security_Audit {

    /**
     * Day        : 003
     * Date       : 06-Jul-2026
     * Difficulty : Medium
     * Topic      : Arrays
     * Pattern    : resizing the sliding windows dynamically, by shrinking and expanding. (Variable sliding window)
     * Recognition Clues:
     *      Continuos subarray/substring
     *      at most K
     *      longest/Shortest
     *      O(N) expected
     * Invariant:
     *   State what is always true about your window after each iteration.
     * 
     * Time Taken : Nan
     * Status     : Solved
     *
     * Time Complexity  : O(N)
     * Space Complexity : O(1)
     *
     * Learning:
     * I don't need to remember the positions of every interesting element.
     *   Often, maintaining a simple property of the current window (like the count of failures) is enough.
     * Expand -> Fix the window if needed -> Measure.
     * undersating good invariant makes the code elegant.
     *
     * Mistakes:
     * - misunderstood/calculated how to track the failure and move the startpointer/left
     * - bad choice of when to update the parameters, which is direct result of bad code style/invariant.
     * - bad invariant/code-style , correctness is there, but elegence, or better writing is missed.
     */

    public static void main(String[] args) {

        // Test Cases

        System.out.println(longestSecurePeriodV1("SSFSFFSSS", 2)); // 6
        System.out.println(longestSecurePeriodV1("FFFF", 1)); // 1
        System.out.println(longestSecurePeriodV1("SSSSS", 1)); // 5
        System.out.println(longestSecurePeriodV1("FSFSFSFSF", 2)); // 5
        System.out.println(longestSecurePeriodV1( "SSFSFSFSFS", 2)); // 6
        System.out.println(longestSecurePeriodV1("FFFFF", 2)); //2
        System.out.println(longestSecurePeriodV1("SSSSFFFF", 2)); //6

    }

    public static int longestSecurePeriod(String logins, int k) {

        // Process the string sequentially
        // maintain/store the index of first failure "F"
        // When k+1th failure hits, max (current max, endpointer - startpointer) 
        // The startpointer should be moved to first failure position + 1
        // Time Complexity : O(N), Space Complexity : O(1)

       //  "SSFSFSFSFS", 2
       /*
    0-> S
        SS
        SSF
        SSFS
        SSFSF
        SSFSFS
        SSFSFSF x, longest = 6
    3-> SFSF
        SFSFS
        SFSFSF x, longest = max(6,5) = 6
    5-> SFSF
        SFSFS, longest = max(6,5) = 6

       */

       int startPtr = 0;
       int endPtr = 0;
       int maxSecurePeriod = Integer.MIN_VALUE;
       int currentFailures = 0;

       while(endPtr < logins.length()) { // FFFF, 1
            if(logins.charAt(endPtr) == 'F'){
                currentFailures++;
            }

            if(currentFailures == k+1) { // if currentfailures hit extra than the limit
                maxSecurePeriod = Math.max(maxSecurePeriod, endPtr - startPtr);

                while(logins.charAt(startPtr) == 'S'){
                    startPtr++;
                }
                startPtr++; //startPtr starts the from the next pos to the latest first failure.
                currentFailures--; // the last first failure is removed.
            }

            endPtr++;
       }

       if(endPtr == logins.length()) {
                maxSecurePeriod = Math.max(maxSecurePeriod, endPtr - startPtr);
        }

        return maxSecurePeriod;
    }

    public static int longestSecurePeriodV1(String logins, int k) {

        /*
            You simply say:
                Expand.
                Fix the window if needed.
                Measure.
            It's easier to read and prove correctness.
        */

        int left = 0;
        int failures = 0;
        int maxSecurePeriod = 0;

        int right = 0;

        while(right < logins.length()) {
            if(logins.charAt(right) == 'F') {
                failures++;
            }

            while(failures > k) {
                if(logins.charAt(left) == 'F') {
                    failures--;
                }
                left++;
            }
            int length = right - left + 1;
            maxSecurePeriod = Math.max(maxSecurePeriod, length);

            right++;
        }

        return maxSecurePeriod;
    }
    
}
