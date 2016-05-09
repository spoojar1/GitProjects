import java.util.*;
import java.io.*;
class Thread1 extends Thread
{
	int no,count=1;
	Thread1(String name,int n)
	{
		super(name);
		no=n;
		start();
	}
	public void run()
	{
		while(no!=1)
		{
			Thread.currentThread().setPriority(Thread.MIN_PRIORITY);

			if(no%2==0)
				no=no/2;
			else
				no=3*no+1;
			System.out.println("Iteration : "+count++ +"	Modified number : "+no);
		}
		System.out.println("Total no. of iterations : "+(--count));
	}
}
class Thread2 extends Thread
{
	Thread2(String name)
	{
		super(name);
		start();
	}
	public void run()
	{

		for(int a=1;a<=50;a++)
			for(int b=a;b<=50;b++)
			{
				Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
				int c=check(Math.pow(a,2)+Math.pow(b,2));
				if(c!=-1)
					System.out.println("a = "+a+" b = "+b+" c = "+c);
			}
	}
	int check(double no)
	{
		if(Math.pow((int)Math.sqrt(no),2)==no)
			return (int)Math.sqrt(no);

		return -1;
	}
}
class DC1
{
	public static void main(String str[])throws IOException
	{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter a no for the first thread");
		Thread1 t1=new Thread1("Thread 1",Integer.parseInt(br.readLine()));
		Thread2 t2=new Thread2("Thread 2");
	}
}
/*
Output:

Enter a no for the first thread
45
Iteration : 1   Modified number : 136
Iteration : 2   Modified number : 68
Iteration : 3   Modified number : 34
Iteration : 4   Modified number : 17
a = 3 b = 4 c = 5
Iteration : 5   Modified number : 52
Iteration : 6   Modified number : 26
a = 5 b = 12 c = 13
Iteration : 7   Modified number : 13
a = 6 b = 8 c = 10
Iteration : 8   Modified number : 40
a = 7 b = 24 c = 25
Iteration : 9   Modified number : 20
a = 8 b = 15 c = 17
Iteration : 10  Modified number : 10
a = 9 b = 12 c = 15
Iteration : 11  Modified number : 5
a = 9 b = 40 c = 41
Iteration : 12  Modified number : 16
a = 10 b = 24 c = 26
Iteration : 13  Modified number : 8
Iteration : 14  Modified number : 4
a = 12 b = 16 c = 20
Iteration : 15  Modified number : 2
a = 12 b = 35 c = 37
Iteration : 16  Modified number : 1
a = 14 b = 48 c = 50
Total no. of iterations : 16
a = 15 b = 20 c = 25
a = 15 b = 36 c = 39
a = 16 b = 30 c = 34
a = 18 b = 24 c = 30
a = 20 b = 21 c = 29
a = 20 b = 48 c = 52
a = 21 b = 28 c = 35
a = 24 b = 32 c = 40
a = 24 b = 45 c = 51
a = 27 b = 36 c = 45
a = 28 b = 45 c = 53
a = 30 b = 40 c = 50
a = 33 b = 44 c = 55
a = 36 b = 48 c = 60
a = 40 b = 42 c = 58

*/