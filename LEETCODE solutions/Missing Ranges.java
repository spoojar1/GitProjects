public class Solution {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        /*Logic:
        Traverse the array. Keep track of ranges. Pay attention to edge cases. Numbers can be integers, hence substraction/addition can lead to overflow, hence type cast with long
        */
        
        List<String> list = new ArrayList<String>();
        
        //edge case
        if(nums.length==0){
            if(lower==upper)
                list.add(String.valueOf(lower));
            else
                list.add(String.valueOf(lower)+"->"+String.valueOf(upper));
            return list;
        }
        
        //edge case
        if(nums[0]!=lower){
            if((long)nums[0]-(long)lower==1)
                list.add(String.valueOf(lower));
            else
                list.add(String.valueOf(lower)+"->"+String.valueOf((long)nums[0]-1));
        }
        
        for(int i=1; i<nums.length; i++){
            if((long)nums[i]-(long)nums[i-1]<=1)
                continue;
            if((long)nums[i]-(long)nums[i-1]==2)
                list.add(String.valueOf(((long)nums[i]+(long)nums[i-1])/2));
            else
                list.add(String.valueOf((long)nums[i-1]+1)+"->"+String.valueOf((long)nums[i]-1));
        }
        
        //edge case
        if(upper-nums[nums.length-1]==1)
            list.add(String.valueOf(upper));
        if(upper-nums[nums.length-1]>=2)
            list.add(String.valueOf((long)nums[nums.length-1]+1)+"->"+String.valueOf(upper));
        
        return list;
    }
}