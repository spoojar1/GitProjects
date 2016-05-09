import java.util.*;
class ABC
{
	int a,b,c;
	ABC()
	{
		a=0;
		b=0;
		c=0;
	}
}
class Array
{
	ABC alloc=new ABC(),max=new ABC(),need=new ABC();
	boolean finish;
	String str;
	Array()
	{
		finish=false;
		str="";
	}
}
class Banker
{
	Scanner s=new Scanner(System.in);
	ABC avail=new ABC(),work=new ABC();
	Array p[];
	int n;
	String SS="";   //SafeSequence
	void input(ABC x)
	{
		System.out.println("Enter A,B and C");
		int num=s.nextInt();
		x.c=num%10;
		num=num/10;
		x.b=num%10;
		num=num/10;
		x.a=num%10;
	}
	void display(ABC x)
	{
		System.out.println("A="+x.a+"\nB="+x.b+"\nC="+x.c);
	}
	boolean compare(ABC x,ABC y)
	{
		if(x.a<=y.a&&x.b<=y.b&&x.b<=y.b)
			return true;
		else
		    return false;
	}
	ABC add(ABC x,ABC y)
	{
		x.a=x.a+y.a;
		x.b=x.b+y.b;
		x.c=x.c+y.c;
		return x;
	}
	boolean finish(Array p[])
	{
		for(int i=0;i<n;i++)
			if(p[i].finish==false)
				return false;
		return true;
	}
	void algo()
	{
		System.out.println("Enter no of processes");
		n=s.nextInt();
		p=new Array[n];
		for(int i=0;i<n;i++)
		{
			p[i]=new Array();
			System.out.println("\nEnter process name of process no "+(i+1));
			p[i].str=s.next();
			System.out.println("Enter data for ALLOCATION");
			input(p[i].alloc);
			System.out.println("Enter data for MAX");
			input(p[i].max);
			System.out.println("Enter data for NEED");
			input(p[i].need);
		}
		System.out.println("\nEnter data for AVAILABLE");
		input(avail);
		work.a=avail.a;	   work.b=avail.b;	  work.c=avail.c;  //work=available
		do
		{
			for(int i=0;i<n;i++)
			{
				if(p[i].finish==false)
				{
					if(compare(p[i].need,work))    //returns true if need[i]<=work
					{
						System.out.println("\nProcess "+p[i].str);
						work=add(work,p[i].alloc);  //work=work+alloc[i]
						p[i].finish=true;
						SS=SS+p[i].str+" ";  //generating safe sequence
						System.out.println("WORK");
						display(work);
					}
				}
			}
		}while(!finish(p));
		System.out.println("\nSAFE SEQUENCE is  "+SS);
	}
}
class BankerAlgo
{
	public static void main(String str[])
	{
		Banker b=new Banker();
		b.algo();
	}
}
/*
OUTPUT
Enter no of processes
5

Enter process name of process no 1
p0
Enter data for ALLOCATION
Enter A,B and C
10
Enter data for MAX
Enter A,B and C
753
Enter data for NEED
Enter A,B and C
743

Enter process name of process no 2
p1
Enter data for ALLOCATION
Enter A,B and C
200
Enter data for MAX
Enter A,B and C
322
Enter data for NEED
Enter A,B and C
122

Enter process name of process no 3
p2
Enter data for ALLOCATION
Enter A,B and C
302
Enter data for MAX
Enter A,B and C
902
Enter data for NEED
Enter A,B and C
600

Enter process name of process no 4
p3
Enter data for ALLOCATION
Enter A,B and C
211
Enter data for MAX
Enter A,B and C
222
Enter data for NEED
Enter A,B and C
11

Enter process name of process no 5
p4
Enter data for ALLOCATION
Enter A,B and C
2
Enter data for MAX
Enter A,B and C
433
Enter data for NEED
Enter A,B and C
431

Enter data for AVAILABLE
Enter A,B and C
332

Process p1
WORK
A=5
B=3
C=2

Process p3
WORK
A=7
B=4
C=3

Process p4
WORK
A=7
B=4
C=5

Process p0
WORK
A=7
B=5
C=5

Process p2
WORK
A=10
B=5
C=7

SAFE SEQUENCE is  p1 p3 p4 p0 p2

*/