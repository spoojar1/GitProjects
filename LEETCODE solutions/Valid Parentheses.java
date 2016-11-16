public class Solution {
    public boolean isValid(String s) {
        Stack<Character> brac = new Stack<Character>();
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            if(c=='(' || c=='[' || c=='{')
                brac.push(c);
            else{
                if(brac.empty())
                    return false;
                
                char tmp = brac.pop();
                if((tmp=='{' && c=='}') || (tmp=='[' && c==']') || (tmp=='(' && c==')'))
                    continue;
                else
                    return false;
            }
        }
        
        if(!brac.empty())
            return false;
            
        return true;
    }
}