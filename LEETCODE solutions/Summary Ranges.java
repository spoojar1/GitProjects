public class Solution {
    public List<String> summaryRanges(int[] nums) {
        /*Logic:
        Pretty starighforward: Traverse through the array and keep track of contiguous elements
        Dont forget to handle the edge case at the end
        */
        
        List<String> list = new ArrayList<String>();
        
        if(nums.length==0)
            return list;
            
        int start=nums[0], end=nums[0];
        for(int i=1; i<nums.length; i++){
            if(nums[i] == nums[i-1] + 1)
                end = nums[i];
            else{
                if(start==end)
                    list.add(String.valueOf(start));
                else
                    list.add(String.valueOf(start)+"->"+String.valueOf(end));
                start = nums[i];
                end = nums[i];
            }
        }
        
        if(start==end)
            list.add(String.valueOf(start));
        else
            list.add(String.valueOf(start)+"->"+String.valueOf(end));        
        return list;
    }
}