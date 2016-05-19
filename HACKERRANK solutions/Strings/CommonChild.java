import java.io.*;
import java.util.*;

public class CommonChild {
	static int LCS(char str1[],char str2[]){
    	int n1=str1.length;
    	int n2=str2.length;
    	
    	//System.out.println(n1+" "+n2);
    	int result[][]=new int[n1][n2];
    	for(int i=0;i<n1;i++)
    		result[i][0]=0;
   		for(int j=0;j<n2;j++)
   			result[0][j]=0;
   		
   		for(int i=1;i<n1;i++){
   			for(int j=1;j<n2;j++){
   				if(str1[i]==str2[j])
   					result[i][j]=result[i-1][j-1]+1;
   				else
   					result[i][j]=(result[i-1][j]>result[i][j-1])?result[i-1][j]:result[i][j-1];
   				//System.out.print(result[i][j]+" ");
   			}
   			//System.out.println();
   		}
    	
    	return result[n1-1][n2-1];
	}
    public static void main(String[] args) {
    	Scanner s=new Scanner(System.in);
    	char str1[]=("0"+s.next()).toCharArray();
    	char str2[]=("0"+s.next()).toCharArray();
    	System.out.println(LCS(str1,str2));
    }
}