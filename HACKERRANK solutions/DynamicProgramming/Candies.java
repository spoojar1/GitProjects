import java.io.*;
import java.util.*;

public class Candies {

    public static long OptimizeCandies(int a[]){
        int b[] = new int[a.length];
        Arrays.fill(b, 1);
        long count = 0;
        
        for(int i=1;i<a.length;i++){
            if(a[i] > a[i-1])
                b[i] = b[i-1] + 1;
        }
        for(int i=a.length-2;i>=0;i--){
            if(a[i] > a[i+1])
                b[i] = Math.max(b[i], b[i+1] + 1);
            count += b[i];
        }
        return count;
    }
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner s = new Scanner(System.in);
        int n = s.nextInt(), a[] = new int[n];
        for(int i=0; i<n; i++)
            a[i]=s.nextInt();
        System.out.println(OptimizeCandies(a));
    }
}