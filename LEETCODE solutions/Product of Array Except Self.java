public class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int l_prod[] = new int[n], r_prod[] = new int[n];
        
        l_prod[0] = 1;
        r_prod[n-1] = 1;
        
        for(int i=1;i<n;i++)
            l_prod[i] = l_prod[i-1]*nums[i-1];
        
        for(int i=n-2;i>=0;i--)
            r_prod[i] = r_prod[i+1]*nums[i+1];
        
        for(int i=0;i<n;i++){
            l_prod[i] *= r_prod[i];
        }
        
        return l_prod;
    }
}