public class Solution {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> q = new PriorityQueue<Integer>();
        for(int i=0;i<nums.length;i++){
            q.add(nums[i]);
            if(q.size()==k+1)
                q.poll();
        }
        return q.peek();
    }
}