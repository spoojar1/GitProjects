public class Solution {
    /*LOGIC:
    MAX-MIN principle:
    We assume choose a wrong number x, (L <= x <= R && x! = Y) then you should know that the next lookup is from [L, x-1] or [x + 1, R]. If we have solved [L, x-1] and [x + 1, R], then we should choose max (solve (L, x-1), solve (x + 1, R)) in seeking the worst case loss. The total loss is f (x) = x + max (solve (L, x-1), solve (x + 1, R))
    After evaluating all positions between L and R, we select the min loss.
    */
        
    public int getMoneyAmount(int n) {
        int dp[][] = new int[n+1][n+1];
        return helper(dp, 1, n);
    }
    public int helper(int dp[][], int start, int end){
        //termination
        if(start>=end)
            return 0;
        if(dp[start][end]!=0)
            return dp[start][end];
            
        dp[start][end] = Integer.MAX_VALUE;
        for(int i=start;i<=end;i++){
            dp[start][end] = Math.min(dp[start][end], i + Math.max(helper(dp,start,i-1),helper(dp,i+1,end)) );
        }
        return dp[start][end];
    }
    /*Iterative approach:
        int[][] dp = new int[n+1][n+1];
        for (int L = n - 1; L > 0; L--) {
			for (int R = L + 1; R <= n; R++) {
				dp[L][R] = 0x7FFFFFFF; //INT_MAX
				for (int i = L; i < R; i++) {
					dp[L][R] = Math.min(dp[L][R], i + Math.max(dp[L][i - 1], dp[i + 1][R]));
				}
			}
		}
		return dp[1][n];
	*/
}