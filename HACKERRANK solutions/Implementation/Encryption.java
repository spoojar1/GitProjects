import java.io.*;
import java.util.*;

public class Encryption {

    public static void main(String[] args) {
        Scanner s=new Scanner(System.in);
        String str=s.nextLine().replaceAll(" ","");
        //System.out.println(str);
        int r=(int) Math.floor(Math.sqrt(str.length()));
        int c=(int) Math.ceil(Math.sqrt(str.length()));
        
        if((r*c)<str.length())
        	r=c;
        
        char A[][]=new char[r][c];
        int k=0;
        for(int i=0;i<r;i++){
        	for(int j=0;j<c;j++){
        		if(k<str.length())
        			A[i][j]=str.charAt(k);
        		else
        			A[i][j]=' ';
        		k++;
        		//System.out.print(A[i][j]);
        	}
        	//System.out.println();
        }
        
        //System.out.println("c "+c+"r "+r);
        for(int i=0;i<c;i++){
        	for(int j=0;j<r;j++){
        		if(A[j][i]!=' ')
        			System.out.print(A[j][i]);
        	}
        	System.out.print(' ');
        }
    }
}