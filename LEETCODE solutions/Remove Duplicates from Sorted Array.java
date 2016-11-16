public class Solution {
    public int removeDuplicates(int[] nums) {
        int n = nums.length;
        if(n==0 || n==1)
            return n;
            
        int i = 0, j = i+1, prev = nums[i];
        while(j<n){
            if(nums[j]==prev)
                j++;
            else{
                i++;
                nums[i] = nums[j];
                prev = nums[j];
                j++;
            }
        }
        
        return i+1;
    }
}