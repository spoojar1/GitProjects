import java.util.*;

public class Solution {
    public int[] twoSum(int[] nums, int target) {
        Hashtable<Integer,Integer> table = new Hashtable<Integer,Integer>();
        table.put(nums[0], 0);
        
        for(int i=1;i<nums.length;i++){
            if(table.get(target-nums[i])==null){
                table.put(nums[i], i);                                                    
            }else{
                int arr[] = new int[2];
                arr[0] = table.get(target-nums[i]);
                arr[1] = i;
                return arr;
            }
        }
        
        return new int[2];
    }
}