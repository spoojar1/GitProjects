public class Solution {
    public int findPeakElement(int[] nums) {
        return helpFindPeakElement(nums, 0, nums.length-1);
    }
    public int helpFindPeakElement(int[] nums, int start, int end){
        if(start == end)
            return start;
            
        int mid = (start + end)/2;
        if(start == mid){
            if(nums[start] > nums[end])
                return start;
            else
                return end;
        }
            
        if(nums[mid] > nums[mid+1] && nums[mid] > nums[mid-1])
            return mid;
        else{
            if(nums[mid] < nums[mid+1])
                return helpFindPeakElement(nums, mid+1, end);
            else
                return helpFindPeakElement(nums, start, mid-1);
        }
    }
}