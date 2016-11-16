public class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i= 0;
        if(n==0)
            return;
        while(i!=m){
            if(nums1[i]>nums2[0]){
                int t = nums1[i];
                nums1[i] = nums2[0];
                nums2[0] = t;
            }    
            int j=0;
            while(j<n-1 && nums2[j]>nums2[j+1]){
                int t = nums2[j];
                nums2[j] = nums2[j+1];
                nums2[j+1] = t;
                j++;
            }
            i++;
        }
        int j = 0;
        while(j<n){
            nums1[i++] = nums2[j++];
        }
    }
}