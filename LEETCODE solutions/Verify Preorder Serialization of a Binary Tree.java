public class Solution {
    public boolean isValidSerialization(String preorder) {
        /*LOGIC:
        Logic is pretty clear from the code. Go read that !! :)    
        */
        Stack<String> s = new Stack<>();
        
        String c[] = preorder.split(",");
        s.push(c[0]);
        
        for(int i=1;i<c.length;i++){
            String ch = c[i];
            if(s.peek().equals("#")){
                s.pop();
                if(s.empty())
                    return false;
                else
                    s.pop();
            } 
            s.push(ch);
        }
        
        if(s.size()==1 && s.peek().equals("#"))
            return true;
        else
            return false;
    }
}