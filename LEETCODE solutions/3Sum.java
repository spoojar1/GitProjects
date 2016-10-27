public class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        /*Logic:
        Use the 2 pointer and hashmap technique to find the 3sum. Sorting the array will allow us to skip the repeating numbers
        */
        
        Set<List<Integer>> set = new HashSet<List<Integer>>();
        HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
        Arrays.sort(nums);
        
        for(int i=0; i<=nums.length-3; i++){
            if(i>0 && nums[i]==nums[i-1])
                continue;
                    
            for(int j=i+1; j<=nums.length-1;j++){
                if(map.get(nums[j])==null)
                    map.put(-(nums[i]+nums[j]) , nums[j]);
                else{
                    List<Integer> list=new ArrayList<Integer>();
                    list.add(nums[i]);
                    list.add(map.get(nums[j]));
                    list.add(nums[j]);
                    Collections.sort(list);     //sorting to take care of duplicates, since set does not allow duplicates
                    set.add(list);
                    map.remove(nums[j]);
                    map.put(-(nums[i]+nums[j]) , nums[j]);
                }
            }
            map.clear();        //do not forget this
        }
        
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        for(List<Integer> list: set){
            result.add(list);
        }
        
        return result;
    }
}