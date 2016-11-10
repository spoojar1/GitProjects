public class Solution {
    public int missingNumber(int[] nums) {
        int sum = 0, arr_sum = 0, max = Integer.MIN_VALUE;
        boolean flag0 = false;
        for(int i=0;i<nums.length;i++){
            max = Math.max(max, nums[i]);
            arr_sum += nums[i];
            if(nums[i]==0)
                flag0 = true;
        }
        
        if(!flag0)
            return 0;
            
        for(int i=0;i<=max;i++)
            sum += i;
            
        if(arr_sum==sum)
            return max+1;
        
        return sum - arr_sum;
    }
}