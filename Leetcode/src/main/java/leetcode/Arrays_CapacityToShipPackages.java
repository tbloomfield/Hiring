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
 * in (d) days. We can iterate through this by creating a range from (max
 * package weight) ... total weight.  A ship has to at least be able to carry our heaviest package.
 */
public class Arrays_CapacityToShipPackages {
    
    public int shipWithinDays(int[] weights, int days) {
        int totalWeightToShip    = 0;
        int highestPackageWeight = 0;
        
        for(int i = 0; i < weights.length; i++) {
            int packageWeight = weights[i];
            totalWeightToShip += packageWeight;
            highestPackageWeight = Math.max(packageWeight, highestPackageWeight);
        }
        
        //do quicksort to recursively call to see if we can ship 
    }
}
