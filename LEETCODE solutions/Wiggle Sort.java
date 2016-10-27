public class Solution {
    public void wiggleSort(int[] nums) {
        /*Logic:
		Alternatively keep on finding the min and maximum in the array and form the new array.
		eg: [2,3,1,4,5,0] has 2 parts [0,1,2] and [3,4,5]
		new array is [0,3,1,4,2,5]
		
		This is in place and O(n^2). For out of place, you could sort the array and then break it into 2 parts and create the new array as shown above. That would be O(nlogn) time but O(n) space
		*/
		
		boolean flag=true;
        
        for(int i=0; i<nums.length-1; i++){
            if(flag){   //min
                int min = nums[i], min_pos = i;
                for(int j=i+1; j<nums.length; j++){
                    if(nums[j] < min){
                        min = nums[j];
                        min_pos = j;
                    }
                }
                if(min_pos!=i){
                    int t = nums[min_pos];
                    nums[min_pos] = nums[i];
                    nums[i] = t;
                }
                flag=!flag;
            }else{
                int max = nums[i], max_pos = i;
                for(int j=i+1; j<nums.length; j++){
                    if(nums[j] > max){
                        max = nums[j];
                        max_pos = j;
                    }
                }
                if(max_pos!=i){
                    int t = nums[max_pos];
                    nums[max_pos] = nums[i];
                    nums[i] = t;
                }
                flag=!flag;
            }
        }
    }
}