/*
Sony bitrai
C22_103
Aim:Simulation of process State transition model
*/
import java.io.*;
import java.util.*;
class P1 extends Thread
{
	int i=0;
	public void run()
	{
		for(i=0;i<10;i++)
		{
			if(i==0||i==6)
				System.out.println("Process P1 is Running");
			try
			{
				if(i==5)
				{
					System.out.println("Process P1 Blocked ");
					Thread.sleep(300);
					System.out.println("Process P1 Ready");
				}
			}
			catch(Exception e){}
		}
		System.out.println("Process P1 is Completed");
	}
}
class P2 extends Thread
{
	int i=0;
	public void run()
	{
		for(i=0;i<10;i++)
		{
			if(i==0||i==6)
				System.out.println("Process P2 is Running");
			try
			{
				if(i==5)
				{
					System.out.println("Process P2 Blocked ");
					Thread.sleep(10000);
					System.out.println("Process P2 Ready");
				}
			}
			catch(Exception e){}
		}
		System.out.println("Process P2 is Completed");
	}
}
class P3 extends Thread
{
	int i=0;
	public void run()
	{
		for(i=0;i<10;i++)
		{
			if(i==0||i==6)
				System.out.println("Process P3 is Running");
			try
			{
				if(i==5)
				{
					System.out.println("Process P3 Blocked ");
					Thread.sleep(300);
					System.out.println("Process P3 Ready");
				}
			}
			catch(Exception e){}
		}
		System.out.println("Process P3 is Completed");
	}
}
class P4 extends Thread
{
	int i=0;
	public void run()
	{
		for(i=0;i<10;i++)
		{
			if(i==0)
				System.out.println("Process P4 is Running");
		}
		System.out.println("Process P4 is Completed");
	}
}
class processState
{
	public static void main(String str[])
	{
		System.out.println("Process P1 is in the new state");
		System.out.println("Process P2 is in the new state");
		System.out.println("Process P3 is in the new state");
		System.out.println("Process P4 is in the new state");
		P1 p1=new P1();
		P2 p2=new P2();
		P3 p3=new P3();
		P4 p4=new P4();
		p1.setPriority(Thread.MIN_PRIORITY);
		p2.setPriority(Thread.NORM_PRIORITY);
		p3.setPriority(Thread.MAX_PRIORITY);
		p4.setPriority(Thread.NORM_PRIORITY);
		p1.start();
		p2.start();
		p3.start();
		p4.start();
	}
}
/*
OUTPUT
Process P1 is in the new state
Process P2 is in the new state
Process P3 is in the new state
Process P4 is in the new state
Process P3 is Running
Process P3 Blocked
Process P2 is Running
Process P2 Blocked
Process P4 is Running
Process P4 is Completed
Process P1 is Running
Process P1 Blocked
Process P3 Ready
Process P3 is Running
Process P3 is Completed
Process P1 Ready
Process P1 is Running
Process P1 is Completed
Process P2 Ready
Process P2 is Running
Process P2 is Completed
*/

