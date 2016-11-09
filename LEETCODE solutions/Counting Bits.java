public class Solution {
    public int[] countBits(int num) {
        
        if(num==0)
            return new int[]{0};
        if(num==1)
            return new int[]{0,1};
            
        int dp[] = new int[num+1];
        dp[0] = 0;
        dp[1] = 1;
        
        for(int i=2;i<=num;i++){
            double x = (Math.log(i)/Math.log(2));
            if((int)x==x)
                dp[i] = 1;
            else{
                int j = (int)Math.pow(2,(int)x);
                dp[i] = dp[j] + dp[i-j];
            }
        }
        
        return dp;
    }
}