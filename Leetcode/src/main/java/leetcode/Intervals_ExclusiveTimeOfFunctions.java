package leetcode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

/**
 * 636. Exclusive Time of Functions
 * https://leetcode.com/problems/exclusive-time-of-functions/description/
 * 
 * On a single-threaded CPU, we execute a program containing n functions. Each
 * function has a unique ID between 0 and n-1.
 * 
 * Function calls are stored in a call stack: when a function call starts, its
 * ID is pushed onto the stack, and when a function call ends, its ID is popped
 * off the stack. The function whose ID is at the top of the stack is the
 * current function being executed. Each time a function starts or ends, we
 * write a log with the ID, whether it started or ended, and the timestamp.
 * 
 * Complexity: O(N) as we iterate through each log entry.
 * Storage: O(N) store each log entry in a queue.
 */
public class Intervals_ExclusiveTimeOfFunctions {
    
    public static void main(String[] args) { 
        Intervals_ExclusiveTimeOfFunctions test = new Intervals_ExclusiveTimeOfFunctions();
        int [] results = test.exclusiveTime(3, List.of("1:start:0","0:start:2","1:start:3","2:start:4","2:end:4","0:end:6","1:end:7","1:end:8"));
        System.err.println(Arrays.toString(results));
    }
    
    class JobSchedule {
        int jobId;
        int startTime;
        
        public JobSchedule(int jobId, int startTime) { 
            this.jobId = jobId;
            this.startTime = startTime;
        }
    }
    
    //start = inclusive
    //end = inclusive
    public int[] exclusiveTime(int n, List<String> logs) {        
        int[] runtimes = new int[n];
        
        if(n == 0 || logs.isEmpty()) { 
            return runtimes;
        }
        
        Deque<JobSchedule> jobs = new ArrayDeque<>();        
        
        //O(n) loop through each timestamp provided.
        for(String s: logs) {
            String[] entries = s.split(":");
            Integer id = Integer.valueOf(entries[0]);  //process id
            String type = entries[1]; //start/stop
            Integer timestamp = Integer.valueOf(entries[2]); //timestamp
            
            if(type.equals("start")) {
                
                //A new job is starting when there is a previous job still running
                //update the running time of the previous job.
                if(!jobs.isEmpty()) {
                    runtimes[jobs.peek().jobId] += (timestamp - jobs.peek().startTime);                  
                }
                
                //now add this new job
                JobSchedule newJob = new JobSchedule(id, timestamp);
                jobs.offer(newJob);                
            }
            //an end message must have a cooresponding start message
            else {
                //this end cooresponds to the current job which we're ending.
                JobSchedule job = jobs.pop();
                
                //jobs are inclusive of the specified time ended, so add one.
                runtimes[id] += timestamp + 1 - job.startTime;                    

                //if there's a previous entry in our stack, it would have been paused
                //until this job ended.  Update its start time to the end time:
                if(!jobs.isEmpty()) {                    
                    jobs.peek().startTime = timestamp + 1 ; //the previous entry will restart in the next 1 second                    
                }
            }            
        }
        
        return runtimes;
    }
}
