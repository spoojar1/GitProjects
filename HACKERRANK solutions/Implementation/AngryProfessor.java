import java.io.*;
import java.util.*;

public class AngryProfessor {

    public static void main(String[] args) {
        Scanner s=new Scanner(System.in);
        int n=s.nextInt();
        int a,b,count=0;
        for(int i=0;i<n;i++){
            a=s.nextInt();
            b=s.nextInt();
            for(int j=0;j<a;j++){
                if(s.nextInt()<=0)
                    count++;
            }
            if(count>=b)
                System.out.println("NO");
            else
                System.out.println("YES");
            count=0;
        }
    }
}