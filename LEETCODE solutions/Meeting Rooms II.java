/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
public class Solution {
    public int minMeetingRooms(Interval[] intervals) {
        /*LOGIC:
        Greedy approach. Sort the array based on start times. Keep track of end times in ordered manner. If new entry start time is >= then the lowest end time available, then it can be fit in without an extra room and the end time needs to be updated for that room, else one more room needed. In any case, need to add the new end time to the heap
        */
        
        int n = intervals.length;
        if(n==0 || n==1)
            return n;
            
        Arrays.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval i1, Interval i2) {
                return i1.start - i2.start;
            }
        });
    
        //System.out.println(intervals[2].start +" "+ intervals[2].end);
        PriorityQueue<Integer> q = new PriorityQueue<Integer>();
        q.add(intervals[0].end);
        
        int count = 1;
        for(int i=1;i<n;i++){
            int start = intervals[i].start, end = intervals[i].end;
            if(start<q.peek())
                count++;
            else
                q.remove();
            q.add(end);
        }
        return count;
    }
}