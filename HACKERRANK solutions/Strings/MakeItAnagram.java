import java.io.*;
import java.util.*;

public class MakeItAnagram {
    public static void main(String[] args) {
    	Scanner s=new Scanner(System.in);
    	char str1[]=(s.next()).toCharArray();
    	char str2[]=(s.next()).toCharArray();
    	HashMap<String,Integer> h=new HashMap<String,Integer>();
    	for(int i=0;i<str1.length;i++)
    		h.put("1"+str1[i],h.get("1"+str1[i])==null?1:h.get("1"+str1[i])+1);
    	
    	/*for (Map.Entry<String,Integer> entry : h.entrySet())
    	    System.out.println(entry.getKey()+" "+entry.getValue());
    	*/
    	
    	for(int i=0;i<str2.length;i++){
    		if(h.get("1"+str2[i])==null || h.get("1"+str2[i])==0)
    			h.put("2"+str2[i],h.get("2"+str2[i])==null?1:h.get("2"+str2[i])+1);
    		else
    			h.put("1"+str2[i],h.get("1"+str2[i])-1);
    	}
    	//System.out.println();
    	/*for (Map.Entry<String,Integer> entry : h.entrySet())
    	    System.out.println(entry.getKey()+" "+entry.getValue());
    		*/
    	int sum=0;
    	for(Integer i : h.values())
    	   sum+=i;
    	
    	System.out.println(sum);
    }
}