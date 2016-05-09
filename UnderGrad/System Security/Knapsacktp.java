import java.util.*;
class Cryptosystem
{
	int b[]=new int[7];
	int permute[]={4,2,5,3,1,7,6};
	int n,r,r_inv,s,s_inv;
	int t[]=new int[7];
	int a[]=new int[7];
	int x[]=new int[7];

	void init()
	{
		n=super_i_tuple(7);
		r=find_r();
	}
	int gcd(int a,int b)
	{
		if(b==0)
			return a;
		else
			return gcd(b,a%b);
	}
	int find_r()
	{
		int i=1;
		do
		{
			i++;
		}while(gcd(i,n)!=1 && i<n);
		return i;
	}
	int super_i_tuple(int start)
	{
		int sum=0;
		for(int i=0;i<7;i++)
		{
			if(i==0)
				b[i]=start;
			else
				b[i]=sum+1;
			sum+=b[i];
		}
		return sum+1;
	}
	void calc_t()
	{
		for(int i=0;i<7;i++)
			t[i]=(r*b[i])%n;
	}
	int[] permute(int b[])
	{
		int temp[]=new int[7];
		for(int i=0;i<7;i++)
			temp[i]=b[permute[i]-1];

		return temp;
	}
	void display(int temp[])
	{
		for(int i=0;i<temp.length;i++)
			System.out.print(temp[i]+" ");
		System.out.println();
	}
	void encrypt(char c)
	{
		System.out.println("ENCRYPTION");
		String temp=Integer.toBinaryString((int)c);

		for(int i=0;i<7;i++)
			x[i]=(int)temp.charAt(i)-48;

		init();
		calc_t();
		a=permute(t);
		System.out.println("\nThe super-increasing tuple : ");
		display(b);
		System.out.println("\nn : "+n);
		System.out.println("\nr : "+r);
		System.out.println("\nThe temporary tuple : ");
		display(t);
		System.out.println("\nThe public key : ");
		display(a);
		System.out.println("\nThe message tuple : ");
		display(x);
		knapsack();
		System.out.println("\nThe encrypted message is s = "+s);

	}
	void decrypt()
	{
		System.out.println("\n\nDECRYPTION");
		r_inv=mul_inv(r);
		System.out.println("\nMultiplicative inverse of r : "+r_inv);
		s_inv=(s*r_inv)%n;
		System.out.println("\ns_inv : "+s_inv);
		x=inv_knapsack(s_inv);
		int x_rev[]=permute(x);
		System.out.println("\nThe inverse message tuple after permute : ");
		display(x_rev);

		String temp="";
		for(int i=0;i<7;i++)
			temp+=Integer.toString(x_rev[i]);

		System.out.println("\nThe original charcter is :"+(char)Integer.parseInt(temp,2));
	}
	int mul_inv(int r)
	{
		int r1=n,r2=r,t1=0,t2=1;
		while(r2>0)
		{
			int q=r1/r2;
			int temp1=r1-q*r2;
			r1=r2;
			r2=temp1;
			int temp2=t1-q*t2;
			t1=t2;
			t2=temp2;
		}
		if(t1<0)
			return n+t1;
		else
			return t1;
	}
	void knapsack()
	{
		s=0;
		for(int i=0;i<7;i++)
			s+=a[i]*x[i];
	}
	int[] inv_knapsack(int s)
	{
		int x[]=new int[7];
		for(int i=6;i>=0;i--)
		{
			if(s>=b[i])
			{
				s=s-b[i];
				x[i]=1;
			}
		}
		return x;
	}
}
class Knapsacktp
{
	public static void main(String str[])
	{
		Cryptosystem c=new Cryptosystem();
		c.encrypt('g');
		c.decrypt();
	}
}
/*
OUTPUT
ENCRYPTION

The super-increasing tuple :
7 8 16 32 64 128 256

n : 512

r : 3

The temporary tuple :
21 24 48 96 192 384 256

The public key :
96 24 192 48 21 256 384

The message tuple :
1 1 0 0 1 1 1

The encrypted message is s = 781


DECRYPTION

Multiplicative inverse of r : 171

s_inv : 431

The inverse message tuple after permute :
1 1 0 0 1 1 1

The original charcter is : g

*/