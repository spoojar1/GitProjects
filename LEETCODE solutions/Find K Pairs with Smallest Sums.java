public class Solution {
    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        /*LOGIC
        keep the addition of nums1[i] and nums2[0] in a priority queue and based on which addition is less, add that combo to the result and forward the pointer index(of nums2) for that element in nums1
        */
        if(nums2.length==0)
            return new ArrayList<int[]>();
            
        PriorityQueue<int[]> p = new PriorityQueue<int[]>(new Comparator<int[]>(){
            public int compare(int p1[], int p2[]){
                return ((Integer)p1[1]).compareTo(p2[1]);
            }
        });
        
        for(int i=0;i<nums1.length;i++)
            p.add( new int[]{nums1[i], nums1[i]+nums2[0], 0} );
        
        List<int[]> res = new ArrayList<int[]>();
        while(k!=0 && p.size()!=0){
            int arr[] = p.poll();
            res.add(new int[]{ arr[0], arr[1] - arr[0]} );
            
            arr[2]++;
            if(arr[2]<nums2.length)
                p.add( new int[]{ arr[0], arr[0] + nums2[arr[2]], arr[2]} );
            
            k--;
        }
        
        return res;
    }
}