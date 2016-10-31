public class Solution {
    public int threeSumClosest(int[] nums, int target) {
        /*
        Logic: Brute force is trivial
        Make use of sorting to reduce the complexity from O(n^3) to O(n^2)
        2 pointer technique:
        1st loop goes from 0 to len-3. Second loop is using 2 pointers j and k on either end of arrays.
        The sum can be less or greater than target. If less, then j is incremented, else k is decremented.
        */
        
        /*int min = Integer.MAX_VALUE, result = 0;
        for(int i=0;i<=nums.length-3;i++){
            for(int j=i+1;j<=nums.length-2;j++){
                for(int k=j+1;k<=nums.length-1;k++){
                    int sum = nums[i] + nums[j] + nums[k];
                    if(Math.abs(target-sum) < min){
                        min = Math.abs(target-sum);
                        result = sum;
                    }
                }
            }
        }
        return result;*/
        
        Arrays.sort(nums);
        int result = 0, min = Integer.MAX_VALUE;
        for(int i=0;i<=nums.length-3;i++){
            int j=i+1, k=nums.length-1;
            while(j<k){
                int sum = nums[i] + nums[j] + nums[k];
                if(sum == target)
                    return sum;
                if(Math.abs(target-sum)<min){
                    min = Math.abs(target-sum);
                    result = sum;
                }
                if(sum < target)
                    j++;
                else
                    k--;
            }
        }
        return result;
    }
}