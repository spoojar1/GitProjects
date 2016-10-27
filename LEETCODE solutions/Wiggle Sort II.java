public class Solution {
    public void wiggleSort(int[] nums) {
        /*Logic:
        Sort the array. The array will have 2 parts cut from the mid. Left part should be larger for the solution to work.
        eg: [2,3,1,4,5,0,6] gives [0,1,2,3] and [4,5,6] which can be joined together starting from the END of the arrays giving [3,6,2,5,1,4,0].
        END or else the solution will fail for some cases like [5,4,5,6] which yields [4,5] and [5,6] and will lead to [4,5,5,6] which is wrong. Whereas if taken from the right, it yields [5,6,4,5]
        */
        
        Arrays.sort(nums);
        int div = nums.length%2==0?nums.length/2-1:nums.length/2;
        int i=div, j=nums.length-1;
        
        int result[] = new int[nums.length], k=-1; 
        boolean flag = true;
        
        while(i>=0 || j>div){
            if(flag){
                result[++k] = nums[i--];
                flag=!flag;
            }else{
                result[++k] = nums[j--];
                flag=!flag;
            }
        }
        for(int x=0; x<nums.length; x++)
            nums[x] = result[x];
    }
}