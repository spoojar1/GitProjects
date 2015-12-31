import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) {
    	Scanner s=new Scanner(System.in);
    	int M,N,a[][],tmp;
    	long R;
    	M=s.nextInt();
    	N=s.nextInt();
    	R=s.nextLong();
    	a=new int[M][N];
    	
    	for(int i=0;i<M;i++){
    		for(int j=0;j<N;j++){
    			a[i][j]=s.nextInt();
    		}
    	}
    	
    	/*for(int i=0;i<M;i++){
    		for(int j=0;j<N;j++){
    			System.out.print(a[i][j]+" ");
    		}
    		System.out.println();
    	}*/
    	int tmpM=M,tmpN=N,tmpR=0;
    	for(int k=0;k<M/2&&k<N/2;k++){
    		tmpR=(int) (R%((tmpM*tmpN)-((tmpM-2)*(tmpN-2))));
    		for(int r=0;r<tmpR;r++){
    			int i=k,j;
    			tmp=a[k][k];
    			for(j=k;j<=N-2-k;j++){
    				a[i][j]=a[i][j+1];
    			}
    			
    			j=N-1-k;
    			for(i=k;i<=M-2-k;i++){
    				a[i][j]=a[i+1][j];
    			}
    			
    			i=M-1-k;
    			for(j=N-1-k;j>=k+1;j--){
    				a[i][j]=a[i][j-1];
    			}
    			
    			j=k;
    			for(i=M-1-k;i>=k+2;i--){
    				a[i][j]=a[i-1][j];
    			}
    			
    			a[k+1][k]=tmp;
    		}
    		tmpM-=2;
    		tmpN-=2;
    	}
    	for(int i=0;i<M;i++){
        	for(int j=0;j<N;j++){
        		System.out.print(a[i][j]+" ");
       		}
       		System.out.println();
        }
    	
    }
}