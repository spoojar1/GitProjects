class MyStack {
    Deque<Integer> q1 = new ArrayDeque<Integer>();
    Deque<Integer> q2 = new ArrayDeque<Integer>();
    boolean flag = true;
    
    // Push element x onto stack.
    public void push(int x) {
        if(flag)
            q1.add(x);
        else
            q2.add(x);
    }

    // Removes the element on top of the stack.
    public void pop() {
        if(flag){
            while(q1.size()!=1)
                q2.add(q1.poll());
            
            if(q1.size()>0)
                q1.poll();
        }else{
            while(q2.size()!=1)
                q1.add(q2.poll());
            
            if(q2.size()>0)
                q2.poll();
        }
        flag = !flag;
    }

    // Get the top element.
    public int top() {
        int x = -1;
        if(flag){
            while(q1.size()!=0){
                x = q1.poll();
                q2.add(x);
            }
        }else{
            while(q2.size()!=0){
                x = q2.poll();
                q1.add(x);
            }
        }
        flag = !flag;
        return x;
    }

    // Return whether the stack is empty.
    public boolean empty() {
        if(flag)
            return q1.isEmpty();
        else
            return q2.isEmpty();
    }
}