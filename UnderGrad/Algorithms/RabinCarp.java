/*
Suraj Poojary C22_109
Aim:Implementation of Rabin Carp String Matching algorithm
*/
import java.util.*;
class StringMatch
{
	Scanner s=new Scanner(System.in);
	String T,P;
	int t[],p[],n,m;
	int digestT,digestP,decimalT,decimalP;
	int q=13;   //any prime no
	void input()
	{
		System.out.println("Enter reference string");
		T=s.next();
		n=T.length();
		t=new int[n];
		System.out.println("Enter string to be searched");
		P=s.next();
		m=P.length();
		p=new int[m];
		for(int i=0;i<n;i++)
			t[i]=(int)T.charAt(i)-48;
		for(int i=0;i<m;i++)
			p[i]=(int)P.charAt(i)-48;

		System.out.println("\nT string");
		System.out.println(T);
		System.out.println("\nP string");
		System.out.println(P);
	}
	int decimal(int a[],int i,int j)
	{
		int k,sum=0;
		int num=j-i;   //4 for m=5(j-i+1)
		for(k=i;k<=j;k++)
			sum=sum+(a[k]*(int)Math.pow(10,num--));
		return sum;
	}
	void rabincarp()
	{
		decimalP=decimal(p,0,m-1);
		digestP=decimalP%q;
		int i;
		for(i=0;i<=n-m;i++)
		{
			decimalT=decimal(t,i,i+m-1);      //e.g for m=5, 2 to 6  i.e   i=2 to i=2+5-1=6
			digestT=decimalT%q;
			if(digestT==digestP)
			{
				if(decimalT==decimalP)  //more than one sequences may have digestT=digestP
									    //hence check sequence also
				{
					System.out.println("\nSequence found at position "+i);
					break;
				}
			}
		}
		if(i==n-m+1)
			System.out.println("Sequence not found");
	}
}
class RabinCarp
{
	public static void main(String str[])
	{
		StringMatch sm=new StringMatch();
		sm.input();
		sm.rabincarp();
	}
}
/*
OUTPUT
Enter reference string
2359023141526739921
Enter string to be searched
31415

T string
2359023141526739921

P string
31415

Sequence found at position 6

*/