import java.util.*;
import java.io.*;

class rsa
{
	public static void main(String ar[])
	{
		System.out.println("Enter the two primes");
		Scanner sc=new Scanner(System.in);
		int p=sc.nextInt(),q=sc.nextInt(),n=p*q;
		int phi=(p-1)*(q-1),e=2,d=0,n1=phi,n2=e;
		boolean fl=true;
		for(;e<phi&&fl;e++)
		{
			n1=phi;
			n2=e;
			while(n1!=n2)
			if(n1>n2)
			n1=n1-n2;
			else
			n2=n2-n1;
			if(n1==1)
			fl=true;
			else
			fl=false;
		}
		System.out.println("Public Key ("+e+","+n+")");
		int r1=phi,r2=e,t1=0,t2=1,q2,r,t;
		while(r2>0)
		{
			q2=(int)r1/r2;
			r=r1-q2*r2;
			r1=r2;
			r2=r;
			t=t1-q2*t2;
			t1=t2;
			t2=t;
		}
		if(r1==1)
		{
			while(t1<0)
			t1=t1+phi;
			d=t1;
		}
		System.out.println("Private Key "+d);
		System.out.println("Enter the number");
		int m=sc.nextInt();
		int c=((int)Math.pow(m,e))%n;
		System.out.println("Cipher Text : "+c);
		int md=((int)Math.pow(c,d))%n;
		System.out.println("Decrypted Text : "+md);
	}
}
/*Output
Enter the two primes
11
3
Public Key (3,33)
Private Key 7
Enter the number
15
Cipher Text : 9
Decrypted Text : 15
Press any key to continue . . .*/