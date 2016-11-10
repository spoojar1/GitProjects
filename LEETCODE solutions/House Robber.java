public class Solution {
    public int rob(int[] nums) {
        /*LOGIC:
        start from end. Keep track of maximum profit you can gain while traversing backwards
        */
        
        int n = nums.length;
        if(n==0)
            return 0;
            
        int dp[] = new int[n];
        if(n==1)
            return nums[0];
        if(n==2)
            return Math.max(nums[0],nums[1]);  
            
        dp[n-1] = nums[n-1];
        dp[n-2] = Math.max(nums[n-1],nums[n-2]);    //IMP
        
        int max = dp[n-2];
        
        for(int i=n-3;i>=0;i--){
            dp[i] = Math.max(max, nums[i] + dp[i+2]);
            max = dp[i];
        }
        
        return max;
    }
}