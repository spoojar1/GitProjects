public class Solution {
    public int maxSubArray(int[] nums) {
        int max_so_far = nums[0], max_total = nums[0];
        for(int i=1;i<nums.length;i++){
            if(max_so_far < 0)
                max_so_far = 0;
            
            max_so_far += nums[i];
            if(max_so_far > max_total)
                max_total = max_so_far;
        }
        
        return max_total;
    }
}