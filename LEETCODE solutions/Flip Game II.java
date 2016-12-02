public class Solution {
    /*LOGIC:
    for a given position, if the opposition canot place any valid +s, then the current player wins. Take this logic backward and you get the solution. Since for each combination, there are n possible combinations, your tree has n branches and each branch has height n, your time complexity is n^n
    */
    public boolean canWin(String s) {
        if(s==null || s.length()==0)
            return false;
        
        return canWinHelper(s.toCharArray());    
    }
    
    public boolean canWinHelper(char c[]){
        for(int i=0;i<c.length-1;i++){
            if(c[i]=='+' && c[i+1]=='+'){
                c[i]='-';
                c[i+1]='-';
                
                boolean flag = canWinHelper(c);
                
                c[i]='+';
                c[i+1]='+';
                
                if(!flag)
                    return true;
            }
        }
        return false;
    }
}