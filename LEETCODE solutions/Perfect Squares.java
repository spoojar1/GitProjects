public class Solution {
    /*
    Logic:
    Trick is in realizing the fact that result(14) = result(9) + result(4) + result(1);
    result(30) =result(1) + result(4) + result(25) but it starts as
    result(30) = result(1) + result(29), similary calculate result(29)
    Base case for formulating solution:
        result(3) = result(1) + result(2)
            result(2) = result(1) + result(1)
    */
    public int numSquares(int n) {
        int dp[] = new int[n+1];
        int max = (int)Math.sqrt(n);
        
        Arrays.fill(dp,Integer.MAX_VALUE);
        
        for(int i=1;i<=n;i++){
            for(int j=1;j<=max;j++){
                if(i==j*j)
                    dp[i]=1;
                else
                    if(i>j*j)
                        dp[i] = Math.min(dp[i], dp[i-j*j]+1);
            }
        }
        return dp[n];
    }
}