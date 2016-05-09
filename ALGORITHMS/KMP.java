/*
KNUTH MONSS PRATT STRING MATCHING(KMP)
*/
import java.util.*;
class StringMatch
{
	Scanner s=new Scanner(System.in);
	String s1,s2;
	int m,n;
	int table[];
	void assign()
	{
		System.out.println("Enter String 1");
		s1=s.next();
		m=s1.length();
		System.out.println("Enter String 2");
		s2=s.next();
		n=s2.length();
		table=new int[n+1];
		createtable();
	}
	void createtable()
	{
		int count=0;
		table[0]=table[1]=0;
		int i,x,j,k;
		for(i=2;i<=n;i++)
		{
			for(x=1;x<i;x++)
			{
				for(j=0,k=i-x;j<x;j++,k++)
				{
					if(s2.charAt(j)!=s2.charAt(k))
						break;
				}
				if(j==x)
					count++;
				else
					break;
			}
			table[i]=count;
			count=0;
		}
		System.out.println("\nTABLE ARRAY");
		for(i=0;i<=n;i++)
			System.out.print(table[i]);
		System.out.println();
	}
	void match()
	{
		int i,j,k;
		for(i=0;i<=(m-n);)
		{
			for(j=i,k=0;k<n;j++,k++)
			{
				if(s1.charAt(j)!=s2.charAt(k))
					break;
			}
			if(k==n)
			{
				System.out.println("\nmatch found at position "+i);
				break;
			}
			i=j-table[k];
		}
		if(i>(m-n))
			System.out.println("\nmatch not found");
	}
}
class KMP
{
	public static void main(String str[])
	{
		StringMatch sm=new StringMatch();
		sm.assign();
		sm.match();
	}
}
/*
OUTPUT
Enter String 1
001001002001002102200
Enter String 2
00100210

TABLE ARRAY
001012001

match found at position 9
*/