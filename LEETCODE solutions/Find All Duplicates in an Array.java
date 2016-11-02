public class Solution {
    public List<Integer> findDuplicates(int[] nums) {
        /*Logic:
        2 hints : no negative numbers and maximum number possible is length of array which means each cell value has to be present as an index of the array.
        Take each number and negate the index it's value corresponds to
        for ith element 5, negate 5th element. Next time 5 is encountered, this 5th element will already be -ve and hence 5 will be a repeated number
        */
        List<Integer> list = new ArrayList<Integer>();
        for(int i=0;i<nums.length;i++){
            if(nums[Math.abs(nums[i])-1]>0)
                nums[Math.abs(nums[i])-1] = -nums[Math.abs(nums[i])-1];
            else
                list.add(Math.abs(nums[i]));
        }
        return list;
    }
}