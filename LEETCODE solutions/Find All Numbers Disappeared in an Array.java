public class Solution {
    /*Logic:
    for every ith element m, Negate the element at mth position (m-1 incase of 0 indexed array)
    Traverse the array. The indices corresponding to elements that are non-negative weren't present in the array
    */
    
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> list = new ArrayList<Integer>();
        for(int i=0;i<nums.length;i++){
            if(nums[Math.abs(nums[i])-1]>0)
                nums[Math.abs(nums[i])-1] = -nums[Math.abs(nums[i])-1];
        }
        for(int i=0;i<nums.length;i++)
            if(nums[i]>0)
                list.add(i+1);
        return list;
    }
}