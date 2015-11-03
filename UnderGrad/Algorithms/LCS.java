/*
LONGEST COMMOM SEQUENCE
*/
import java.util.*;
class StringMatch
{
	Scanner s=new Scanner(System.in);
	String s1,s2;
	int m,n;
	int L[][];
	char lcs[];
	int count=0;
	void input()
	{
		System.out.println("Enter String 1");
		s1=s.next();
		m=s1.length();
		System.out.println("Enter String 2");
		s2=s.next();
		n=s2.length();

		lcs=new char[m];   //m or n cannot be greater
		L=new int[m+n][n+1];
	}
	void match()
	{
		char a,b;
		for(int i=1;i<=m;i++)
		{
			for(int j=1;j<=n;j++)
			{
				a=s1.charAt(i-1);
				b=s2.charAt(j-1);
				if(a==b)
					L[i][j]=L[i-1][j-1]+1;
				else
					if(L[i-1][j]>L[i][j-1])
						L[i][j]=L[i-1][j];
					else
						L[i][j]=L[i][j-1];
			}
		}

		for(int i=m,j=1;j<=n;j++)
			if(L[i-1][j-1]==L[i][j]-1)
				lcs[++count]=s2.charAt(j-1);

		System.out.println("\nL MATRIX");
		for(int i=0;i<=m;i++)
		{
			for(int j=0;j<=n;j++)
				System.out.print(L[i][j]+" ");
			System.out.println();
		}
		System.out.print("\nlongest common sequence is");
		System.out.println(lcs);
	}
}
class LCS
{
	public static void main(String str[])
	{
		StringMatch sm=new StringMatch();
		sm.input();
		sm.match();
	}
}
/*
OUTPUT 1
Enter String 1
abbcab
Enter String 2
abccb

L MATRIX
0 0 0 0 0 0
0 1 1 1 1 1
0 1 2 2 2 2
0 1 2 2 2 3
0 1 2 3 3 3
0 1 2 3 3 3
0 1 2 3 3 4

longest common sequence is abcb

OUTPUT 2
Enter String 1
1234567
Enter String 2
246914

L MATRIX
0 0 0 0 0 0 0
0 0 0 0 0 1 1
0 1 1 1 1 1 1
0 1 1 1 1 1 1
0 1 2 2 2 2 2
0 1 2 2 2 2 2
0 1 2 3 3 3 3
0 1 2 3 3 3 3

longest common sequence is 246

*/
