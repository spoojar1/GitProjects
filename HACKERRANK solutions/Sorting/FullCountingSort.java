import java.io.*;
import java.util.*;

public class FullCountingSort {
    public static void main(String[] args) throws IOException {
    	BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(in.readLine());
        int c[]=new int[100],val;
        String a[][]=new String[100][n],str[];
        for(int i=0;i<n;i++){
        	str=in.readLine().split(" ");
        	val=Integer.parseInt(str[0]);
        	if(i<n/2)
        		a[val][c[val]++]="-";
        	else
        		a[val][c[val]++]=str[1];
        }
        
        StringBuilder sb=new StringBuilder("");
        for(int i=0;i<100;i++){
        	for(int j=0;j<c[i];j++)
                sb.append(a[i][j]+" ");
        }
        System.out.println(sb.toString());
    }
}