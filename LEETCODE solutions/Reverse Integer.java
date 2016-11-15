public class Solution {
    public int reverse(int x) {
        boolean flag = true;
        if(x==Integer.MIN_VALUE)
            return 0;
            
        if(x<0){
            flag = false;
            x = Math.abs(x);
        }
        
        System.out.println(x);
        
        long sum = 0;
        long mask = 10;
        while(x!=0){
            int lsd = x%10;
            x = x/10;
            sum = sum*mask + (long)lsd;
        }    
        
        if(sum>Integer.MAX_VALUE)
            sum = 0;
        
        return (int)(flag==true?sum:(-1*sum));
        
    }
}