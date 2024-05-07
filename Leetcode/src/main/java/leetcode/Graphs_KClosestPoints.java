package leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 973. K Closest Points to Origin
 * https://leetcode.com/problems/k-closest-points-to-origin/description/
 * 
 * Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane and an integer k, 
 * return the k closest points to the origin (0, 0).
 * The distance between two points on the X-Y plane is the Euclidean distance (i.e., √(x1 - x2)2 + (y1 - y2)2).
 * You may return the answer in any order. The answer is guaranteed to be unique (except for the order that it is in).
 * 
 * Answer:
 *  * Since this is a (K) closest/smallest/biggest problems, we use a Priority Queue; since we want 
 *  points closest based on eucludian distance, we will use a max heap where distances furthest
 *  away can easily be popped and removed.
 * 
 * Priority Queue: O(n log k) - PQ is O(log n) insertion, and we loop through all (n) points, so this is O(n log k)
 * Storage: O(k+1) since we only store up to (k) elements.
 * 
 */
public class Graphs_KClosestPoints {
    public int[][] kClosest(int[][] points, int k) {
        
        //a max heap where the highest distance is always at the head so it may be easily removed 
        //for constraint purposes
        PriorityQueue<EucludianPoint> topKPoints = new PriorityQueue<>(new Comparator<EucludianPoint>() {
            @Override
            public int compare(EucludianPoint o1, EucludianPoint o2) {
                return o2.distance - o1.distance;
            }            
        });
        
        //loop through all points calculating distance from (0,0)
        for(int[] point : points) { 
            //eucludian distance:
            int x = point[0];
            int y = point[1];   
            //double distance = Math.sqrt( Math.pow(x, 2) + Math.pow(y, 2) );
            EucludianPoint epoint = new EucludianPoint((x*x) + (y*y), point);
            topKPoints.offer(epoint);                

            //ensure that our priority queue doesn't exceed K elements.
            if(topKPoints.size() > k) {
                topKPoints.poll();  //remove the biggest entry
            }
        }
        
        //now spit out the top k elements
        int[][] results = new int[topKPoints.size()][2];
        int i = 0;
        while(!topKPoints.isEmpty()) { 
            int[] point = topKPoints.poll().point;
            results[i] = point;
            i++;            
        }
        return results;
    }
    
    class EucludianPoint  {
        int distance;
        int[] point;
        
        public EucludianPoint(int distance, int[] point) {  
            this.distance = distance;
            this.point = point;
        }        
    }
}
