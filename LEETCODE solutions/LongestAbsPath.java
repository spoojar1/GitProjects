import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class LongestAbsPath {
	public static int lengthLongestPath(String input) {
        //using deque instead of stack as deque is not thread safe and hence faster
    	Deque<Integer> stack = new ArrayDeque<Integer>();
    	int maxlen = 0;
        
    	//to prevent stack.peek() from returning null pointer 
    	//exception while handling main parent "dir"
    	stack.push(0);
    	
    	String str[] = input.split("\n");
    	for(int i=0;i<str.length;i++){
    		//'\t' is one character
    		int level = str[i].lastIndexOf("\t") + 1; //returns number of '/t' -1
    		
    		//pop until you see parent
    		while(stack.size() > level + 1)	
    			stack.pop();
    		
    		//-level to remove '/t' occurrences and +1 to include '/'
    		int len = stack.peek() + str[i].length() - level + 1;
    		stack.push(len);
    		
    		//update maxlen
    		if(str[i].indexOf(".")!=-1)
    			maxlen = Math.max(maxlen, len-1);
    	}
    	return maxlen;
    }
	
    public static void main(String[] args) {
		Scanner s=new Scanner(System.in);
		System.out.println(lengthLongestPath(s.nextLine()));
	}
}