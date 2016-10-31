public class Solution {
    public int threeSumSmaller(int[] nums, int target) {
        /*
        Logic: Brute force is trivial.
        For O(n^2) solution, sort the array, then for each i pointer move, push ith and (i+1)th element into the stack. Then start traversing the array from (i+2)th position, check with top of the stack and if it satifies the condition, it'll mean that it should satify the condition with all elements below that element in the stack(Since the array is sorted, all sum in the stack will be less than the top of the stack). In case where top of the stack does not satify the condition, pop till you reach a sum that does.
        */
        
        /*if(nums.length==0)
            return 0;
        int count=0;
        for(int i=0;i<=nums.length-3;i++){
            int sum = nums[i] + nums[i+1];
            for(int j=i+2;j<=nums.length-1;j++){
                if(target - nums[j] > sum)
                    count++;
            }
        }
        return count;
        */
        Arrays.sort(nums);
        Deque<Integer> s = new ArrayDeque<Integer>();
        int count=0;
        for(int i=0;i<=nums.length-3;i++){
            s.clear();
            s.push(nums[i] + nums[i+1]);
            for(int j=i+2;j<=nums.length-1;j++){
                while(s.size()!=0 && (target - nums[j] <= s.peek()))
                    s.pop();
                if(s.size()!=0){
                    count += s.size();
                    s.push(nums[i] + nums[j]);
                }
            }
        }
        return count;
    }
}