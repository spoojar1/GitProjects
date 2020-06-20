class Solution {
    int num;
    List<String> result;
    
    public List<String> generateParenthesis(int n) {
        result = new ArrayList<>();
        num = n;
        generateHelper("(", 1, 0);
        return result;
    }
    
    public void generateHelper(String paranths, int open, int closed){
        if(paranths.length()==2*num){
            result.add(paranths);
            return;
        }
          
        if(open+1<=num && open+1>=closed)
            generateHelper(paranths+"(", open+1, closed);
        if(open<=num && open>=closed+1)
            generateHelper(paranths+")", open, closed+1);
    }
}