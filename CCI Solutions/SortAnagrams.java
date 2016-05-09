import java.util.*;

public class SortAnagrams {
	static void sort(String str[]){
		Hashtable<String,LinkedList<String>> h=new Hashtable<String,LinkedList<String>>();
		char c[];
		for(int i=0;i<str.length;i++){
			c=str[i].toCharArray();
			Arrays.sort(c);
			String key=String.valueOf(c);
			if(!h.containsKey(key))
				h.put(key,new LinkedList<String>());
			LinkedList<String> l=h.get(key);
			l.add(str[i]);	
		}
		
		for(String key: h.keySet()){
			LinkedList<String> l=h.get(key);
			for(String t:l)
				System.out.println(t);
		}
	}
    public static void main(String[] args) {
		Scanner s=new Scanner(System.in);
		int n=s.nextInt();
		String str[]=new String[n];
		for(int i=0;i<n;i++)
			str[i]=s.next();
		sort(str);
    }
}