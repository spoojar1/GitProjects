import java.util.*;

public class SherlockNAnagrams{
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int t=Integer.parseInt(sc.nextLine());
        while(t-->0){
            String s=sc.nextLine();
            int count=0;
            for(int i=0;i < s.length();i++){
                for(int k=i+1;k < s.length();k++){
                    int num=anagram(s.substring(i,k),s.substring(i+1),s.substring(i,k).length());
                    count=count+num;
                }
            }
            System.out.println(count);
        }
    }
    static int anagram(String s1,String s2,int len){
        int count = 0;

        char[] c1=s1.toCharArray();
        Arrays.sort(c1);
        String ss1=new String(c1);
        int length=s2.length();

        for(int i=0;i<length;i++){
            if(i+len<=length){
            String sub=s2.substring(i,i+len);
            char[] c2=sub.toCharArray();
            Arrays.sort(c2);
            String ss2=new String(c2);
            if(ss1.compareTo(ss2)==0)
                count++;
            }
        }
        return count;
    }
}