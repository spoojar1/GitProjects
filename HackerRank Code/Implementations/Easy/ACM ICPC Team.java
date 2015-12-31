import java.io.*;
import java.util.*;

class Node{
	int a,b;
	Node(int i,int j){
		a=i;
		b=j;
	}
}
public class Solution {
	static int compare(char c1[],char c2[]){
		int count1=0,count2=0;
		for(int i=0;i<c1.length;i++){
			if(c1[i]=='1')
				count1++;
			if(c2[i]=='1')
				count2++;
		}
		if(count1>count2)
			return 1;
		else if(count1<count2)
			return 2;
		else
			return -1;
	}
	static char[] calOR(char c1[],char c2[]){
		//System.out.println(max_array);
		char c[]=new char[c1.length];
		for(int i=0;i<c1.length;i++){
			if(c1[i]=='0' && c2[i]=='0'){
				c[i]='0';
				//System.out.println("if  "+String.valueOf(max_array));
			}
			else{
				c[i]='1';
				//System.out.println("else "+String.valueOf(max_array));
			}
		}
		//System.out.println(max_array);
		return c;
	}
    public static void main(String[] args) {
    	Scanner s=new Scanner(System.in);
    	int N,M,comp;
    	N=s.nextInt();
    	M=s.nextInt();
    	char c[][]=new char[N][M];
    	char max_array[]=new char[M];
    	char tmp[]=new char[M];
    	
    	ArrayList<Node> array=new ArrayList<Node>();
    	
    	for(int i=0;i<M;i++)
    		max_array[i]='0';
    	
    	for(int i=0;i<N;i++)
    		c[i]=(s.next()).toCharArray();

    	for(int i=0;i<N;i++){
    		//System.out.println("\nc[i] : "+String.valueOf(c[i])+"///////////////////");
    		for(int j=i+1;j<N;j++){
    			/*System.out.println("j : "+j);
    			System.out.println("c[j] : "+String.valueOf(c[j]));
    			System.out.println("max_array : "+String.valueOf(max_array));
    			System.out.println("tmp : "+String.valueOf(tmp));*/
    			tmp=calOR(c[i],c[j]);
    			/*System.out.println("max_array : "+String.valueOf(max_array));
    			System.out.println("tmp : "+String.valueOf(tmp));
    			*/
    			comp=compare(tmp,max_array);
    			//System.out.println(comp);
    			if(comp==1){  //>
    				array.clear();
    				Node n=new Node(i,j);
    				array.add(n);
    				max_array=tmp;
    			}else if(comp==-1){  //=
    				Node n=new Node(i,j);
    				array.add(n);
    			}
    			//System.out.println("max_array : "+String.valueOf(max_array));
    		}
    	}
    	
    	int count=0;
    	for(int i=0;i<M;i++){
    		if(max_array[i]=='1')
    			count++;
    	}
    	System.out.println(count);
    	System.out.println(array.size());
    }
}