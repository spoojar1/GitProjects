import java.util.*;

public class GridChallenge {
	public static String checkGrid(String str[]){
		String str1[]=helpGridCheck(str);
		
		//check columns
		for(int i=0;i<str1.length;i++){
			for(int j=1;j<str1[i].length();j++){
				if(str1[i].charAt(j)<str1[i].charAt(j-1))
					return "NO";
			}
		}
		//check rows
		for(int i=0;i<str1.length;i++){
			for(int j=1;j<str1[i].length();j++){
				if(str1[j].charAt(i)<str1[j-1].charAt(i))
					return "NO";
			}
		}
		return "YES";
	}
	public static String[] helpGridCheck(String str[]){
		//sort columns
		for(int i=0;i<str.length;i++){
			char c[]=str[i].toCharArray();
			Arrays.sort(c);
			str[i]=String.valueOf(c);
		}
		return str;
	}
    public static void main(String args[] ) throws Exception {
    	Scanner s=new Scanner(System.in);
    	int t=s.nextInt();
    	int n;
    	String str[];
    	for(int i=0;i<t;i++){
    		n=s.nextInt();
    		str=new String[n];
    		for(int j=0;j<n;j++)
    			str[j]=s.next();
    		System.out.println(checkGrid(str));
    	}
    }
}