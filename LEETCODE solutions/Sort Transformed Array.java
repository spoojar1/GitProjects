public class Solution {
    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        /*LOGIC:
        [-4 -2 2 4] a=1 b=3 c=5 yields  [9 3 15 33]. Array is sorted asc for positive numbers.
        [-4 -2 2 4] a=1 b=-3 c=5 yields [33 15 3 9]. Array is sorted desc for negative numbers.
        [-4 -2 2 4] a=-1 b=3 c=5 yields [-23 -5 9 1]. Array is sorted desc for negative numbers.
        [-4 -2 2 4] a=-1 b=-3 c=5 yields [1 7 -5 -23]. Array is sorted asc for positive numbers.
        
        In both cases, either try to arrange numbers in ascending or descending order. At the end only part of the array will be sorted.
        The last step is a common 2 pointer step that will then sort the array, irrespective of a=+ve or a=-ve
        For: a>0
        b = +ve yields [33 15 9 3]
        b = -ve yields [33 15 9 3]
        
        For: a<0
        b = +ve yields [-23 -5 1 7]
        b = -ve yields [-23 -5 1 7]
        
        */
        
        int l = 0, r = nums.length-1, k = -1;
        int result[] = new int[nums.length];
        
        while(l<=r){
            int l_val = transform(nums[l],a,b,c) , r_val = transform(nums[r],a,b,c);
            if(a>0){
                result[++k] = l_val > r_val ? l_val : r_val;
                if(l_val > r_val)
                    l++;
                else
                    r--;
            }else{
                result[++k] = l_val < r_val ? l_val : r_val;
                if(l_val < r_val)
                    l++;
                else
                    r--;
            }
        }
        
        
        l = 0;
        r = nums.length-1;
        int temp = 0;
        while(l < r){
            if(result[l]>result[r]){
                temp = result[l];
                result[l] = result[r];
                result[r]= temp; 
            }
            l++;
            r--;
        }
        
        return result;
    }
    
    public int transform(int num, int a, int b, int c){
        return a*num*num + b*num + c;
    }
}