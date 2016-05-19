import java.util.*;

public class SherlockNValidStrings {
	static String checkSherlockValidString(String str){
		int count[]=new int[26];
		
		for(int i=0;i<str.length();i++)
			count[str.charAt(i)-'a']++;
		
		for(int i=0;i<26;i++){
			if(count[i]==0)
				count[i]=-1000000;
		}
		
		if(checkValid(count))
			return "YES";
		
		/*for(int i=0;i<26;i++){
			if(count[i]!=-1000000)
				System.out.println(i+" "+count[i]);
		}*/
		
		int min=1000000,max=-1,minindex=-1,maxindex=-1;
		for(int i=0;i<26;i++){
			if(count[i]!=-1000000){
				if(count[i]<min){
					min=count[i];
					minindex=i;
				}
				if(count[i]>max){
					max=count[i];
					maxindex=i;
				}
			}
		}
		
		//System.out.println("minindex "+minindex+" maxindex"+maxindex);
		
		int counttmp[]=new int[26];
		for(int i=0;i<26;i++){
			counttmp[i]=count[i];
		}
		counttmp[minindex]--;
		
		/*for(int i=0;i<26;i++){
			if(counttmp[i]!=-1000000)
				System.out.println(i+" "+counttmp[i]);
		}*/
		
		if(checkValid(counttmp))
			return "YES";
		
		for(int i=0;i<26;i++){
			counttmp[i]=count[i];
		}
		
		counttmp[maxindex]--;
		
		/*for(int i=0;i<26;i++){
			if(counttmp[i]!=-1000000)
				System.out.println(i+" "+counttmp[i]);
		}*/
		
		if(checkValid(counttmp))
			return "YES";
		
		return "NO";
	}
	
	static boolean checkValid(int count[]){
		boolean flag=false,check=true;
		int tmp=0;
		for(int i=0;i<26;i++){
			if(count[i]!=-1000000 && count[i]!=0){
				if(!flag){
					if(count[i]!=-1000000){
						tmp=count[i];
						flag=true;
					}
				}else{
					if(count[i]!=tmp){
						check=false;
						break;
					}
				}
			}
		}
		return check;
	}
    public static void main(String[] args) {
		Scanner s=new Scanner(System.in);
		String str=s.next();
		System.out.println(checkSherlockValidString(str));
    }
}