public class Solution {
    public int maxProfit(int[] prices) {
		/*LOGIC:
		keep track of min at every index and calculate profit and compare with max_profit
		*/
		
        if(prices.length==0)
            return 0;
            
        int max_profit = Integer.MIN_VALUE;
        int min = prices[0];
        
        for(int i=1;i<prices.length;i++){
            if(prices[i] - min > max_profit)
                max_profit = prices[i] - min;
                
            if(prices[i] < min)
                min = prices[i];
        }
        
        return max_profit>0?max_profit:0;
    }
}