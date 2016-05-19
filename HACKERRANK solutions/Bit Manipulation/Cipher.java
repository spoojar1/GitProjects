import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Cipher {
	static void cipher(int c[],int k){
		int str[]=new int[c.length-k+1];
		int xor,no_1s=0;
		str[0]=c[0];
		if(str[0]==1)
			no_1s++;
		xor=str[0];
		System.out.print(str[0]);
		for(int i=1;i<str.length;i++){
			str[i]=c[i]^xor;
			if(str[i]==1)
				no_1s++;
			if(i>=k-1){
				if(str[i-k+1]==1)
					no_1s--;
			}
			if(no_1s%2==0)
				xor=0;
			else
				xor=1;
			System.out.print(str[i]);
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	   String tmp[]=br.readLine().split(" ");
	   int n=Integer.parseInt(tmp[0]);
	   int k=Integer.parseInt(tmp[1]);
	   String str=br.readLine();
	   int a[]=new int[str.length()];
	   for(int i=0;i<str.length();i++)
		   a[i]=str.charAt(i)-'0';
	   cipher(a,k);
   }
}
