public class Solution {
    public void moveZeroes(int[] nums) {
        int n = nums.length;
        int pos1 = 0, pos2 = 0;
        
        while(pos1!=n && pos2!=n){
            while(pos1!=n && nums[pos1]!=0)
                pos1++;
                
            pos2 = pos1;    
            while(pos2!=n && nums[pos2]==0)
                pos2++;   
            
            if(pos1!=n && pos2!=n){
                int tmp = nums[pos1];
                nums[pos1] = nums[pos2];
                nums[pos2] = tmp;
            }    
        }
    }
}