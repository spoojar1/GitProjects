import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class WordBreak2 {
	static boolean flag = true;
	static void wordbreak(String dict[], String str, ArrayList<String> result){
		if(str.equals("")){
			flag = false;
			String tmpstring="(";
			for(String temp : result)
				tmpstring+=temp+" ";
			System.out.print(tmpstring.substring(0,tmpstring.length()-1)+")");
			return;
		}
		
		ArrayList<String> result2 = new ArrayList<String>();
		for(String temp: result)
			result2.add(temp);
		
		//int len = result2.size();
		for(String temp : dict){
			if(str.length()<temp.length())
				continue;
			if(str.substring(0,temp.length()).equals(temp)){
				int index = str.indexOf(temp);
				result2.add(temp);
				//System.out.println(str.substring(temp.length()));
				wordbreak(dict, str.substring(temp.length()), result2);
				result2.remove(result2.size()-1);
			}
		}
		//if(len!=result2.size())
		//	return;
	}
	public static void main(String[] args) {
		Scanner s= new Scanner(System.in);
		/*String dict[]={"snake","snakes","and","sand","ladder"};
		String str="snakesandladder";*/
		int t,n;
		ArrayList<String> result;
		t=s.nextInt();
		for(int i=0;i<t;i++){
			n=s.nextInt();
			//dummy nextline to go to next line since nextInt does not consume the last newline character
			s.nextLine();
			String dict[]=s.nextLine().split(" ");
			result = new ArrayList<String>();
			wordbreak(dict,s.next(),result);
			if(flag)
				System.out.println("Empty");
			else
				System.out.println();
			flag = false;
		}
	}
}
