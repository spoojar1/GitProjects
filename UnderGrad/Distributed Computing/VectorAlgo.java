import java.util.*;
class Event
{
	int clock[];
	Event(int n)
	{
		clock=new int[n+1];
	}
}
class Process
{
	int depend_p[],depend_e[],class_e[];
	Event e[];
	int count,current;
	Process(){}
	Process(int no,int n)   //no=no of events  n=no of processes
	{
		depend_p=new int[no+1];
		depend_e=new int[no+1];

		class_e=new int[no+1];  //0=internal 1=sender 2=receiver

		e=new Event[no+1];
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
			p[i]=new Process(event,n);
			for(int j=0;j<=event;j++)
				p[i].e[j]=new Event(n);
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
				for(int i=1;i<=n;i++)
					if(i!=cp)
						p[cp].e[ce].clock[i]=p[cp].e[ce-1].clock[i];
					else
						p[cp].e[ce].clock[i]=p[cp].count+1;

				if(p[cp].depend_e[ce]!=0)
				{
					int dp=p[cp].depend_p[ce];  //gives causal process
					int de=p[cp].depend_e[ce];	//gives causal event

					if(p[dp].e[de].clock[dp]!=0)
					{
						for(int i=1;i<=n;i++)
						{
							if(i!=cp)
								p[cp].e[ce].clock[i]=Math.max(p[dp].e[de].clock[i],p[cp].e[ce].clock[i]);
						}
					}
					else
					{
						break;
					}
				}
				p[cp].count=p[cp].e[ce].clock[cp];
				p[cp].current++;
			}
			cp=(cp%n)+1;
		}

		for(int i=1;i<=n;i++)
		{
			System.out.println("\nProcess : "+i);
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
				System.out.print("Timestamp: ");
				for(int k=1;k<=n;k++)
					System.out.print(p[i].e[j].clock[k]);
				System.out.println();
			}
		}

	}
	boolean check()   //checks if all events are processed
	{
		for(int i=1;i<=n;i++)
		{
			if(p[i].current!=p[i].depend_e.length-1)
				return false;
		}
		return true;
	}
}
class VectorAlgo
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
3
Enter the no. of events in the process: 2
4
Enter the no. of events in the process: 3
2
How many dependencies does process 1 have ?
1
Enter dependency no 1 for process 1 in the form CE DP DE
3
2
4
How many dependencies does process 2 have ?
2
Enter dependency no 1 for process 2 in the form CE DP DE
2
1
2
Enter dependency no 2 for process 2 in the form CE DP DE
3
3
1
How many dependencies does process 3 have ?
0

Process : 1
Event : 1  Internal  Timestamp: 100
Event : 2  Sender  	 Timestamp: 200
Event : 3  Receiver  Timestamp: 341

Process : 2
Event : 1  Internal  Timestamp: 010
Event : 2  Receiver  Timestamp: 220
Event : 3  Receiver  Timestamp: 231
Event : 4  Sender    Timestamp: 241

Process : 3
Event : 1  Sender    Timestamp: 001
Event : 2  Internal  Timestamp: 002

*/