import java.io.*;
import java.util.*;

public class Anagram {
	static int anagram(String str1,String str2){
    	HashMap<String,Integer> h=new HashMap<String,Integer>();
    	
    	for(int i=0;i<str1.length();i++)
    		h.put("1"+str1.charAt(i),h.get("1"+str1.charAt(i))==null?1:h.get("1"+str1.charAt(i))+1);
    	
    	for(int i=0;i<str2.length();i++){
    		if(h.get("1"+str2.charAt(i))==null || h.get("1"+str2.charAt(i))==0)
    			h.put("2"+str2.charAt(i),h.get("2"+str2.charAt(i))==null?1:h.get("2"+str2.charAt(i))+1);
    		else
    			h.put("1"+str2.charAt(i),h.get("1"+str2.charAt(i))-1);
    	}
    	
    	int sum=0;
    	for (Map.Entry<String,Integer> entry : h.entrySet()){
    		if(entry.getKey().startsWith("1"))
    			sum+=entry.getValue();
    	}
    	return sum;
	}
	
    public static void main(String[] args) {
    	Scanner s=new Scanner(System.in);
        String str,str1,str2;
		
		int n=s.nextInt();
    	for(int i=0;i<n;i++){
    		str=s.next();
    		if(str.length()%2!=0)
    			System.out.println(-1);
    		else{
    			str1=str.substring(0,str.length()/2);
    			str2=str.substring(str.length()/2);
    			System.out.println(anagram(str1,str2));
    		}
    	}
    }
}