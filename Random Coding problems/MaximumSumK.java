/*
public class Solution {
	public static int maxSubArraySumk(int a[], int k){
		int max_count=1, count_so_far=1, sum_so_far=a[0], start=0;
		for(int i=1;i<=a.length;i++){
			
			while(sum_so_far>k && start<i){
				//System.out.println("i count_so_far "+count_so_far+" sum_so_far "+sum_so_far);
				sum_so_far -= a[start];
				count_so_far--;
				start++;
				if(count_so_far > max_count)
					max_count=count_so_far;
			}
			if(i<a.length){
				sum_so_far += a[i];
				count_so_far++;
			}
		}
		return max_count>count_so_far?max_count:count_so_far;
	}
	public static void main(String[] args){
		System.out.println(maxSubArraySumk(new int[]{1,2,3,2},6));
   }
}
