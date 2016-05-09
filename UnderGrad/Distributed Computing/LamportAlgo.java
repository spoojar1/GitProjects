import java.util.*;
class Process
{
	int depend_p[],depend_e[],e[],class_e[];
	int count,current;
	Process(){}
	Process(int n)   //n=no of events
	{
		depend_p=new int[n+1];
		depend_e=new int[n+1];
		e=new int[n+1];
		class_e=new int[n+1];  //0=internal 1=sender 2=receiver
		count=0;     // stores timestamp
		current=0;   //event last executed
	}
}
class Algo
{
	int n;
	Process p[];
	Scanner s=new Scanner(System.in);
	void init()
	{
		System.out.println("Enter the no. of process: ");
		n=s.nextInt();
		p=new Process[n+1];

		for(int i=1;i<=n;i++)
		{
			System.out.println("Enter the no. of events in the process: "+i);
			int event=s.nextInt();
			p[i]=new Process(event);
		}
		for(int i=1;i<=n;i++)
		{
			System.out.println("How many dependencies does process "+i+" have ?");
			int temp=s.nextInt();
			for(int j=1;j<=temp;j++)
			{
				System.out.println("Enter dependency no "+j+" for process "+i+" in the form CE DP DE ");
				int ce=s.nextInt();
				int dp=s.nextInt();
				int de=s.nextInt();

				p[i].depend_p[ce]=dp;
				p[i].depend_e[ce]=de;

				p[i].class_e[ce]=2;
				p[dp].class_e[de]=1;
			}
		}
	}
	void calc()
	{
		int cp=1;
		while(!check())
		{
			for(int ce=p[cp].current+1;ce<p[cp].depend_e.length;ce++)
			{
				if(p[cp].depend_e[ce]==0)
					p[cp].e[ce]=p[cp].count+1;
				else
				{
					int dp=p[cp].depend_p[ce];  //gives causal process
					int de=p[cp].depend_e[ce];	//gives causal event

					if(p[dp].e[de]!=0)
					{
						p[cp].e[ce]=Math.max(p[dp].e[de]+1,p[cp].count+1);
					}
					else
					{
						break;
					}

				}
				p[cp].count=p[cp].e[ce];
				p[cp].current++;
			}
			cp=(cp%n)+1;
		}

		for(int i=1;i<=n;i++)
		{
			System.out.println("Process : "+i);
			for(int j=1;j<p[i].e.length;j++)
			{
				System.out.print("Event : "+j+"  ");
				if(p[i].class_e[j]==0)
					System.out.print("Internal	");
				else
					if(p[i].class_e[j]==1)
						System.out.print("Sender	");
					else
						System.out.print("Receiver	");
				System.out.print("Timestamp: "+p[i].e[j]);
				System.out.println();
			}
		}

	}
	boolean check()   //checks if all events are processed
	{
		for(int i=1;i<=n;i++)
			if(p[i].current!=p[i].depend_e.length-1)
				return false;
		return true;
	}
}
class LamportAlgo
{
	public static void  main(String str[])
	{
		Algo a=new Algo();
		a.init();
		a.calc();
	}
}
/*
OUTPUT
Enter the no. of process:
3
Enter the no. of events in the process: 1
4
Enter the no. of events in the process: 2
4
Enter the no. of events in the process: 3
1
How many dependencies does process 1 have ?
2
Enter dependency no 1 for process 1 in the form CE DP DE
2
2
1
Enter dependency no 2 for process 1 in the form CE DP DE
3
3
1
How many dependencies does process 2 have ?
2
Enter dependency no 1 for process 2 in the form CE DP DE
2
1
1
Enter dependency no 2 for process 2 in the form CE DP DE
4
1
4
How many dependencies does process 3 have ?
0
Process : 1
Event : 1  Sender    Timestamp: 1
Event : 2  Receiver  Timestamp: 2
Event : 3  Receiver  Timestamp: 3
Event : 4  Sender    Timestamp: 4
Process : 2
Event : 1  Sender    Timestamp: 1
Event : 2  Receiver  Timestamp: 2
Event : 3  Internal  Timestamp: 3
Event : 4  Receiver  Timestamp: 5
Process : 3
Event : 1  Sender    Timestamp: 1

*/