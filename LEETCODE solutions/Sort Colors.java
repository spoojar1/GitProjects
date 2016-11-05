public class Solution {
	/*LOGIC
	consider the half sorted array 0,0,1,1,2,2,0,1,2
	To place 0 at its correct position, 1 will have to move a position, and same goes for 2.
	llrly, To place 1 at its correct position, 2 will have to move a position
	In this lies the logic, store border of 0,1 and 2 groups
	*/
	
    public void sortColors(int[] nums) {
        int pos0=0, pos1=0, pos2=0;
        for(int i=0;i<nums.length;i++){
            if(nums[i]==0){
                nums[pos2++]=2;
                nums[pos1++]=1;
                nums[pos0++]=0;
            }else if(nums[i]==1){
                nums[pos2++]=2;
                nums[pos1++]=1;
            }
            else if(nums[i]==2){
                nums[pos2++]=2;
            }
        }
    }
}