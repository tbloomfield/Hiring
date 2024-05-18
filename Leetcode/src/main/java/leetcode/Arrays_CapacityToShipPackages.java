package leetcode;

/**
 * 1011. Capacity to Ship Packages Within D Days
 * https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/description/
 * 
 * A conveyor belt has packages that must be shipped from one port to another
 * within days days.
 * 
 * The ith package on the conveyor belt has a weight of weights[i]. Each day, we
 * load the ship with packages on the conveyor belt (in the order given by
 * weights). We may not load more weight than the maximum weight capacity of the
 * ship.
 * 
 * Return the least weight capacity of the ship that will result in all the
 * packages on the conveyor belt being shipped within x days.
 * 
 * Answer:
 * 
 * We need to figure out the smallest capacity ship that can send (x) packages
 * in (d) days. Use a binary search
 * 
 * complexity: O(n log (weights)
 *  * 
 * The time complexity of this algorithm is O(n*log(sum of weights)) because we
 * do binary search in the range of [left, right], which has a size of log(sum
 * of weights), and for each mid value, we need to check whether we can ship all
 * the packages using a ship with mid capacity, which takes O(n) time. 
 * 
 * Space: O(1) because we only need to store a few variables.
 * 
 * 
 */
public class Arrays_CapacityToShipPackages {
    
    public int shipWithinDays(int[] weights, int days) {
        int maxPackageWeight = 0, totalCapacity = 0;
        
        // Find the minimum and maximum capacity required for the ship
        for (int w : weights) {
            maxPackageWeight = Math.max(maxPackageWeight, w); // minimum capacity should be able to handle the heaviest package
            totalCapacity += w;                               // maximum capacity should be able to handle all packages
        }
        
        // Use binary search to find the least weight capacity required for the ship
        while (maxPackageWeight < totalCapacity) {
            int mid = (maxPackageWeight + totalCapacity) / 2;
            int daysRequired = 1, currentCapacity = 0;
            
            // Count the number of days required to ship all packages with current mid capacity
            for (int w : weights) {
                if (currentCapacity + w > mid) { // current capacity can't handle the current package
                    daysRequired++;          // increment the number of days required
                    currentCapacity = 0; // reset the current capacity
                }
                currentCapacity += w; // add the current package to the current capacity
            }
            
            // If the number of days required is greater than the given number of days, increase the weight capacity
            if (daysRequired > days) {
                maxPackageWeight = mid + 1;
            } 
            // If the number of days required is less than or equal to the given number of days, decrease the weight capacity
            else {
                totalCapacity = mid;
            }
        }
        
        // Return the least weight capacity required to ship all packages within the given number of days
        return maxPackageWeight;
    }
}
