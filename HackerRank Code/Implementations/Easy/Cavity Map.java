import java.io.*;
import java.util.*;

public class Solution {
    
    public static void check(char A[][],int n){
        for(int i=1;i<n-1;i++){
            for(int j=1;j<n-1;j++){
                if(A[i][j]>A[i-1][j] && A[i][j]>A[i+1][j] && A[i][j]>A[i][j-1] && A[i][j]>A[i][j+1]){
                    A[i][j]='X';
                }
            }
        }
    }
    public static void main(String[] args) {
        Scanner s=new Scanner(System.in);
        int n=s.nextInt();
        char A[][]=new char[n][n];
        for(int i=0;i<n;i++){
        	String str=s.next();
            for(int j=0;j<n;j++){
                A[i][j]=str.charAt(j);
            }
        }
        
        check(A,n);
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                System.out.print(A[i][j]);
            }
            System.out.println();
        }
    }
}