/*
Suraj Poojary C22_109
Aim:Implementation of Boyer Moore String Matching algorithm
*/
import java.util.*;
class StringMatching
{
	Scanner s=new Scanner(System.in);
	String s1,s2;
	int n,m;   //length of s1 and s2 respectively
	char t[],p[];
	void boyer_moore()
	{
		System.out.println("Enter original string");
		s1=s.next();
		System.out.println("Enter sub-string to be searched");
		s2=s.next();

		n=s1.length();
		m=s2.length();
		t=new char[n];
		p=new char[m];

		for(int i=0;i<n;i++)
			t[i]=s1.charAt(i);
		for(int i=0;i<m;i++)
			p[i]=s2.charAt(i);

	}
	void stringmatch()
	{
		int shift=0;
		int i,j,k;
		for(i=(m-1);i<n;)
		{
			for(j=i,k=m-1;k>=0;j--,k--)
			{
				if(t[j]!=p[k])
					break;
				shift++;
			}
			if(k<0)
			{
				System.out.println("match found at "+(i-m+1));
				break;
			}
			for(j=k-1;j>=0;j--)
			{
				if(t[i]==p[j])
				{
					shift++;
					break;
				}
				shift++;
			}
			System.out.println("shift= "+shift+" i= "+i);
			i=i+shift;
			shift=0;
		}
		if(i>=n)
			System.out.println("no match found");
	}
}
class BoyerMoore
{
	public static void main(String str[])
	{
		StringMatching st=new StringMatching();
		st.boyer_moore();
		st.stringmatch();
	}
}
/*
OUTPUT 1
Enter original string
0010010020001002012200
Enter sub-string to be searched
00100201
shift= 1 i= 7
shift= 2 i= 8
shift= 1 i= 10
shift= 1 i= 11
shift= 5 i= 12
match found at 10

******************************

OUTPUT 2
Enter original string
00100100200010012012200
Enter sub-string to be searched
00100201
shift= 1 i= 7
shift= 2 i= 8
shift= 1 i= 10
shift= 1 i= 11
shift= 5 i= 12
shift= 1 i= 17
shift= 5 i= 18
no match found
*/

