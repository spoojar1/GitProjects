public class Solution {
    public int maxProfit(int[] prices) {
        /*LOGIC
        Peak and valley concept: https://leetcode.com/articles/best-time-buy-and-sell-stock-ii/
        Check variation on solution 3 too.
        */
        
        int valley = 0, peak = 0, max_prof = 0;
        int i = 0, n = prices.length - 1;
        while(i<n){
            while(i<n && prices[i]>=prices[i+1])
                i++;
            valley = prices[i];
            
            while(i<n && prices[i]<=prices[i+1])
                i++;
            peak = prices[i];
            
            max_prof += peak - valley;
        }
        
        return max_prof;
    }
}