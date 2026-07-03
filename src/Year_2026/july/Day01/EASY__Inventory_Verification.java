
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/** 
        Question 1 (Easy)
Scenario: Warehouse Inventory Verification

You are building an inventory system for a warehouse.

Every product that arrives is scanned, and its product ID is recorded.

Due to a scanner malfunction, one product might be scanned twice.

Your task is to detect the first duplicate product ID as the inventory is processed. **/ 

public class EASY__Inventory_Verification {

/**
     * Day        : 001
     * Date       : 03-Jul-2026
     * Difficulty : Easy
     * Topic      : Arrays
     * Pattern    : Hash + lookups
     *              Directly implemented the Hashmap solution for O(1) lookups to find if the id is already present
     *              Updated - Set(HashSet) can be used for O(1) lookups and no need to store index value
     * Data Structure: HashMap/HashSet
     * Recognition Clues:
     *      - array
     *      - duplicates
     * 
     * Time Taken : 8 min
     * Status     : Solved Independently
     *
     * Time Complexity  : O(n)
     * Space Complexity : O(n)
     *
     * Learning:
     * Can use HashMap/HashSet to store the processed values and to find the values with instant O(1) lookups
     * To find the duplicates, need to store the proccessed values, preferrably HashSet/HashMap.
     * 
     * Mistakes:
     * (Not exactly a mistake):
     * Used HashMap instead of HashSet, Stored the index value too, but it is not required, taking a little extra space/memory.
     * And checking the condition if the array is processed every time and returning there adds extra n operations.
     */

    public static void main(String[] args) {

        // Test Cases

        System.out.println("The DuplicateId is : " + firstDuplicateV1(new int[] {15,42,31,15,99}));
        System.out.println("The DuplicateId is : " + firstDuplicateV1(new int[] {7, 9, 12, 20}));
        
    }

    public static int firstDuplicate(int[] productIds) {

        Map<Integer, Integer> products = new HashMap<>();
        for(int i=0; i<productIds.length; i++) {
            if (products.containsKey(productIds[i])){
                return productIds[i];
            }
            products.put(productIds[i], i);
            //Asumming that the output is -1 if no duplicates is present.
            if(i == productIds.length - 1) {
                return -1;
            }
        }

        return 0;
    }

    public static int firstDuplicateV1(int[] productIds) {
        
        Set<Integer> products = new HashSet<>();

        for(int Id: productIds) {
            if(products.contains(Id)) {
                return Id;
            }
            products.add(Id);
        }

        return -1;

    }

}