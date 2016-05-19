import java.io.*;
import java.util.*;

public class FindDigits {

    public static int check(long no){
        long no1=no,digit;
        int num=0;
        while(no1!=0){
            digit=no1%10;
            if(digit!=0 && no%digit==0)
                num++;
            no1=no1/10;
        }   
        return num;
    }
    
    public static void main(String[] args) {
        Scanner s=new Scanner(System.in);
        int n=s.nextInt();
        long no;
        for(int i=0;i<n;i++){
            no=s.nextInt();
            System.out.println(check(no));
        }
    }
}