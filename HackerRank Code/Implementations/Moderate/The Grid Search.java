import java.io.*;
import java.util.*;

public class Solution {

    public static String check(String G[],String P[],int r1,int r2,int c1,int c2){
		
		int pos,k1,k2;
        for(int i=0;i<=r1-r2;i++){
        	if(G[i].indexOf(P[0])!=-1){
        		
        		k1=i+1;
        		k2=1;
        		pos=G[i].indexOf(P[0]);
        		while(k2<r2){
        			//System.out.println("Inside Check"+i+" "+k1+" "+k2+" "+G[k1].substring(pos, pos+c2));
        			if(!G[k1].substring(pos, pos+c2).equals(P[k2]))
        					break;
        			k2++;
        			k1++;
        		}
        		//System.out.println(k1+" "+k2);
        		if(k2==r2)
        			return "YES";
        	}
        }
        return "NO";
    }
    
    public static void main(String[] args) {
        Scanner s=new Scanner(System.in);
        int n=s.nextInt();
        int r1,c1,r2,c2;
        String G[],P[];
        for(int k=0;k<n;k++){
            r1=s.nextInt();
            c1=s.nextInt();
            G=new String[r1];
            for(int i=0;i<r1;i++)
            	G[i]=s.next();
            
            /*System.out.println("r1 "+r1+" c1 "+c1);
            for(int i=0;i<r1;i++)
            	System.out.println(G[i]);*/
            
            r2=s.nextInt();
            c2=s.nextInt();
            P=new String[r2];
            for(int i=0;i<r2;i++)
            	P[i]=s.next();
            
           /* System.out.println("r2 "+r2+" c2 "+c2);
            for(int i=0;i<r2;i++)
            	System.out.println(P[i]);*/
            
            
            if(r1>=r2 && c1>=c2)
            	System.out.println(check(G,P,r1,r2,c1,c2));
            else
            	if(r1<=r2 && c1<=c2)
            		System.out.println(check(P,G,r2,r1,c2,c1));
            	else
            		System.out.println("NO");
        }
    }
}