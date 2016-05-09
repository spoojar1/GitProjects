import java.util.*;

public class GenPermute {
	static void genPerm(String str){
		ArrayList<String> a=new ArrayList<String>();
		ArrayList<String> tmp=new ArrayList<String>();;
		
		a.add(str.substring(0,1));
		for(int i=1;i<str.length();i++){
			tmp=new ArrayList<String>();
			for(String s: a){
				for(int j=0;j<s.length();j++){
					tmp.add(s.substring(0,j)+str.substring(i,i+1)+s.substring(j));
				}
				tmp.add(s+str.substring(i,i+1));
			}

			a.clear();
			a.addAll(tmp);
		}
		for(String s: tmp)
			System.out.println(s);
	}
	
    public static void main(String[] args) {
		Scanner s=new Scanner(System.in);
		String str=s.next();
		genPerm(str);
    }
}