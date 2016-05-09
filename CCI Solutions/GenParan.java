import java.util.*;

public class GenParan {
	static void genParan(int n,int m,String str){
		if(n==0 && m==0){
			System.out.println(str);
			return;
		}
		if(m<n)
			return;
		if(n>0 && m>0){
			genParan(n-1,m,str+"(");
			genParan(n,m-1,str+")");
		}else{
			if(m<=0)
				genParan(n-1,m,str+"(");
			else
				genParan(n,m-1,str+")");
		}	
	}
	
    public static void main(String[] args) {
		Scanner s=new Scanner(System.in);
		int n=s.nextInt();
		genParan(n-1,n,"(");
    }
}