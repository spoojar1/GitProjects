public class MinStack {
    
    Stack<Integer> s, min;
    
    /** initialize your data structure here. */
    public MinStack() {
        s = new Stack<Integer>();;
        min = new Stack<Integer>();
    }
    
    public void push(int x) {
        s.push(x);
        
        if(min.empty())
            min.push(x);
        else{
            if(x<=min.peek())
                min.push(x);    
        }
    }
    
    public void pop() {
        if(!s.empty()){
            int x = s.pop();
            if(x==min.peek())
                min.pop();
        }
    }
    
    public int top() {
        if(s.empty())
            return -1;
        else
            return s.peek();
    }
    
    public int getMin() {
        return min.peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */