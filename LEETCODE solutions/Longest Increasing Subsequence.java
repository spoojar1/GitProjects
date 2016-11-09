public class Solution {
    public int lengthOfLIS(int[] nums) {
        /*LOGIC:
        Prefix array concept. For each i maintain a prefix value that holds the lis value till that point. for each i , traverse from 0 to i-1 to calculate the lis value for i. prefix[i] = max(prefix[i], prefix[j]+1) if num[i]>num[j]
        Follow up: Maintain a list of lis elements, if new element to be inserted into the list does not fit into the list, find the element in the list that it should replace using binary search.
        On every addition into the list(not replacement), update the max_lis count.
        */
        
        //O(n^2)
        /*int n = nums.length, lis_count = 1;
        if(n==1 || n==0)
            return n;
        
        int prefix[] = new int[n];
        Arrays.fill(prefix,1);
        
        for(int i=1;i<n;i++){
            for(int j=0;j<i;j++){
                if(nums[i] > nums[j])
                    prefix[i] = Math.max(prefix[i], prefix[j] + 1);
            }
            if(prefix[i] > lis_count)
                lis_count = prefix[i];
        }
        
        return lis_count;
        */
        
        //O(nlogn)
        int n = nums.length, lis_count = 1;
        if(n==1 || n==0)
            return n;
        
        List<Integer> list = new ArrayList<Integer>();
        list.add(nums[0]);
        
        for(int i=1;i<n;i++){
            if(nums[i] > list.get(list.size()-1))
                list.add(nums[i]);
            else{
                int find = helper(nums[i], list);
                list.set(find, nums[i]);
            }
        }
        
        return list.size();
    }
    
    public int helper(int num, List<Integer> list){
        int l = 0, r = list.size()-1;
        while(l<r){
            int mid = (l+r)/2;
            if(num == list.get(mid))
                return mid;
                
            if(num > list.get(mid))
                l = mid + 1;
            else
                r = mid;    //mid instead of mid - 1. The smallest number > num should be replaced hence we cannot ignore the mid too. or else for list 2, 5, 6 when 4 is inserted, 2 will get replaced instead of 5 which is incorrect.
        }
        return l;
    }
}