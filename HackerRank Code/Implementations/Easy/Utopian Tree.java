import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner s=new Scanner(System.in);
        int n=s.nextInt(),m;
        for(int i=0;i<n;i++){
            m=s.nextInt();
            if(m%2==0)
                System.out.println((int)Math.pow(2,(m/2)+1)-1);
            else
                System.out.println((int)Math.pow(2,Math.ceil((double)m/2)+1)-2);
        }
    }
}