public class Solution {
    public int maxProduct(int[] nums) {
        /*LOGIC:
        This is similar to maximum sum subarray but here we cannot follow the <0 so set 0 concept. We cannot reset as we never know when the min -ve value can be multiplied by a -ve number to yield a +ve number. Hence, we keep track of both +ve and -ve products.
        Now, depending on the sign of the current number, we will update +ve or -ve product.
        Solve for simple examples -2,4,-2 and -2,4,2
        */
        
        int n = nums.length;
            
        int min[] = new int[n], max[] = new int[n], total_max = 0;
        
        total_max = min[0] = max[0] = nums[0];
        
        for(int i=1;i<n;i++){
            if(nums[i]<0){
                max[i] = Math.max(nums[i], nums[i]*min[i-1]);
                min[i] = Math.min(nums[i], nums[i]*max[i-1]);
            }else{
                max[i] = Math.max(nums[i], nums[i]*max[i-1]);
                min[i] = Math.min(nums[i], nums[i]*min[i-1]);
            }
            
            total_max = Math.max(total_max, max[i]);
        }
        
        return total_max;
    }
}