public class Solution {
    public int rob(int[] nums) {
        /*LOGIC:
        In a circular arrangement, oyu can either take the first element and not take the last element OR take the last element and not take the first element
        */
        
        int n = nums.length;
        if(n==0)
            return 0;
        
        if(n==1)
            return nums[0];
        if(n==2)
            return Math.max(nums[0],nums[1]);
            
        return Math.max(helper(nums,0,n-2), helper(nums,1,n-1));
    }
    
    public int helper(int nums[], int l, int r){
        int n = r-l+1;
        int dp[] = new int[n];
        
        dp[n-1] = nums[r];
        dp[n-2] = Math.max(nums[r],nums[r-1]);
        
        int max = dp[n-2];
        n = n-3;
        for(int i=r-2;i>=l;i--){
            dp[n] = Math.max(max, nums[i] + dp[n+2]);
            max = dp[n--];
        }
        
        return max;
    }
}