public class Solution {
    public void rotate(int[] nums, int k) {

        int n = nums.length;
        k = k%n;
        if(k==0 || n==1)
            return;
        
        //LOGIC for O(n) solution with O(1)
        int pos = n - k;
        rotate(nums, 0, pos-1);
        rotate(nums, pos, n-1);
        rotate(nums, 0, n-1);
    }
    
    public void rotate(int[] nums, int start, int end){
        while(start<end){
            int t = nums[start];
            nums[start] = nums[end];
            nums[end] = t;
            
            start++;
            end--;
        }
    }
}