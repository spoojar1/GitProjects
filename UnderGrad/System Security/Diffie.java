import java.util.*;
class Hellman
{
	int r1,r2,x,y,p,g,k;
	Scanner s=new Scanner(System.in);

	void input()
	{
		System.out.println("Enter p ");
		p=s.nextInt();
		g=pri_root(p);
		//g=7;
		System.out.println("g : "+g);
	}
	int pri_root(int p)
	{
		for(int i=2;;i++)
		{
			int temp[]=new int[1000];
			int j;
			for(j=0;j<p-1;j++)
			{
				temp[j]=((int)Math.pow(i,j))%p;
				if(check(temp,j)==true)
					break;
			}
			if(j==p-1)
				return i;
		}
	}
	boolean check(int temp[],int j)
	{
		for(int i=0;i<j;i++)
		{
			if(temp[j]==temp[i])
				return true;
		}
		return false;
	}
	int rand(int p)
	{
		while(true)
		{
			int x=(int)(Math.random()*10);
			if(x<=(p-1))
				return x;
		}
	}
	void enc_dec()
	{
		x=rand(p);
		System.out.println("x :"+x);
		y=rand(p);
		System.out.println("y :"+y);
		System.out.println("ALICE");
		r1=((int)Math.pow(g,x))%p;
		System.out.println("R1 : "+r1);

		System.out.println("BOB");
		r2=((int)Math.pow(g,y))%p;
		System.out.println("R2 : "+r2);

		System.out.println("ALICE");
		k=((int)Math.pow(r2,x))%p;
		System.out.println("k : "+k);

		System.out.println("BOB");
		k=((int)Math.pow(r1,y))%p;
		System.out.println("k : "+k);
	}
}
class Diffie
{
	public static void main(String str[])
	{
		Hellman h=new Hellman();
		h.input();
		h.enc_dec();
	}
}
/*

OUTPUT
Enter p
7
g : 3

x :4
y :5

ALICE
R1 : 4

BOB
R2 : 5

ALICE
k : 2

BOB
k : 2

*/