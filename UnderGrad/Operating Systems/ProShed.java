/*
SURAJ POOJARY  C22_109
Aim:Simulation of process scheduling techniques
*/
import java.util.*;
class Process
{
	int no,at,bt,st,wt,tt,pt,temp;
	boolean yes;
	Process()
	{
		st=wt=tt=0;
		yes=false;
	}
}
class  FCFS
{
	Scanner s=new Scanner(System.in);
	void fcfs(Process p[],int n)
	{
		int i,sum=0,wtsum=0,ttsum=0;
		for(i=0;i<n;i++)
		{
			p[i].st=sum;
			p[i].wt=p[i].st-p[i].at;
			sum=+sum+p[i].bt;
			p[i].tt=p[i].wt+p[i].bt;
			wtsum+=p[i].wt;
			ttsum+=p[i].tt;
			System.out.println("PROCESS "+(i+1));
			System.out.println("Waiting time for process "+(i+1)+" is "+p[i].wt);
			System.out.println("Turn around time for process "+(i+1)+" is "+p[i].tt);
		}
		System.out.println("\nAverage waiting time for the processes is "+((float)wtsum/n));
		System.out.println("Average turn around time for the processes is "+((float)ttsum/n));
	}
}
class SJF    //non-preemptive
{
	Scanner s=new Scanner(System.in);
	void sjf(Process p[],int n)
	{
		//priority irrelevant
		int j,i,sum=0,wtsum=0,ttsum=0;
		Process temp;
		//sort according to burst time   EXCEPT THE FIRST PROCESS
		for(i=n-2;i>=0;i--)
		for(j=1;j<=i;j++)    //j=1
		if(p[j].bt>p[j+1].bt)
		{
			temp=p[j];
			p[j]=p[j+1];
			p[j+1]=temp;
		}
		for(i=0;i<n;i++)
		{
			p[i].st=sum;
			p[i].wt=p[i].st-p[i].at;
			sum=+sum+p[i].bt;
			p[i].tt=p[i].wt+p[i].bt;
			wtsum+=p[i].wt;
			ttsum+=p[i].tt;
			System.out.println("PROCESS "+(i+1));
			System.out.println("Waiting time for process "+(i+1)+" is "+p[i].wt);
			System.out.println("Turn around time for process "+(i+1)+" is "+p[i].tt);
		}
		System.out.println("\nAverage waiting time for the processes is "+((float)wtsum/n));
		System.out.println("Average turn around time for the processes is "+((float)ttsum/n));
	}	
}
class PRIORITY
{
	Scanner s=new Scanner(System.in);
	void priority(Process p[],int n)
	{
		//arrival time irrelevant
		int j,i,sum=0,wtsum=0,ttsum=0;
		Process temp;
		//sort according to priority
		for(i=n-2;i>=0;i--)
		for(j=0;j<=i;j++)
		if(p[j].pt<p[j+1].pt)
		{
			temp=p[j];
			p[j]=p[j+1];
			p[j+1]=temp;
		}
		for(i=0;i<n;i++)
		{
			p[i].st=sum;
			p[i].wt=p[i].st;      //(-p[i].at) is neglected
			sum+=p[i].bt;
			p[i].tt=p[i].wt+p[i].bt;
			wtsum+=p[i].wt;
			ttsum+=p[i].tt;
			System.out.println("PROCESS "+(i+1));
			System.out.println("Waiting time for process "+(i+1)+" is "+p[i].wt);
			System.out.println("Turn around time for process "+(i+1)+" is "+p[i].tt);
		}
		System.out.println("\nAverage waiting time for the processes is "+((float)wtsum/n));
		System.out.println("Average turn around time for the processes is "+((float)ttsum/n));
	}
}
class RR
{
	Scanner s=new Scanner(System.in);
	void rr(Process p[],int n)
	{
		int tmp,qt,i,sum=0,wtsum=0,ttsum=0;
		System.out.println("Enter quantum");
		qt=s.nextInt();
		do
		{
			for(i=0;i<n;i++)
				if(p[i].bt!=0)	
					if(p[i].bt>=4||p[i].yes==true) //yes:>1 pass(here 2nd only)
					{
						tmp=p[i].bt;
						if(p[i].bt>=4)		
							p[i].bt=p[i].bt-qt;
						else 
							p[i].bt=0;	
						if(p[i].yes==true)		//here 2nd pass
							p[i].wt=sum-qt-p[i].at;
						else
							p[i].wt=sum-p[i].at;
						if(p[i].bt>0)		//goes to 2nd pass
						{
							p[i].yes=true;
							sum+=qt;
						}
						else
							sum+=tmp;
					}
					else		// <4 in 1st pass
					{
						p[i].wt=sum-p[i].at;
						sum+=p[i].bt;
						p[i].bt=0;
					}
				else			//already completed
					continue;
		}while(!rrfinish(p,n));
		for(i=0;i<n;i++)
		{
			System.out.println("PROCESS "+(i+1));
			System.out.println("Waiting time for process "+(i+1)+" is "+p[i].wt);
			p[i].bt=p[i].temp;
			p[i].tt=p[i].wt+p[i].bt;
			System.out.println("Turn around time for process "+(i+1)+" is "+p[i].tt);
			wtsum+=p[i].wt;
			ttsum+=p[i].tt;
		}
		System.out.println("\nAverage waiting time for the processes is "+((float)wtsum/n));	
		System.out.println("Average turn around time for the processes is "+((float)ttsum/n));		
	}
	boolean rrfinish(Process p[],int n)
	{
		for(int i=0;i<n;i++)
			if(p[i].bt==0)
				continue;
			else 
				return false;
		return true;
	}
}
class ProShed
{
	public static void main(String str[])
	{
		Scanner s=new Scanner(System.in);
		int n,ch,i;
		float avgwt,avgtt;
		System.out.println("Enter no of processes to be accepted");
		n=s.nextInt();
		Process p[]=new Process[n];
		Process t[]=new Process[n];
		for(i=0;i<n;i++)
		{
			p[i]=new Process();
			t[i]=new Process();
			System.out.println("Enter process no,arrival time,burst time and priority");
			t[i].no=p[i].no=s.nextInt();
			t[i].at=p[i].at=s.nextInt();
			t[i].bt=t[i].temp=p[i].temp=p[i].bt=s.nextInt();
			t[i].pt=p[i].pt=s.nextInt();
		}
		do
		{
			System.out.println("\nMENU\n1:FCFS\t2:SJF\t3:PRIORITY\t4:ROUND ROBIN\t5:EXIT\nEnter your choice");
			ch=s.nextInt();
			switch(ch)
			{
				case 1: FCFS f=new FCFS();
						f.fcfs(p,n);
						break;
				case 2: SJF sj=new SJF();
						sj.sjf(p,n);
						break;
				case 3: PRIORITY pr=new PRIORITY();
						pr.priority(p,n);
						break;
				case 4: RR r=new RR();
						r.rr(p,n);
						break;
				case 5: break;
			}
			for(i=0;i<n;i++)		//restoring original values
				p[i]=t[i];
		}while(ch!=5);
	}
}
/*
 OUTPUT
 Enter no of processes to be accepted
5
Enter process no,arrival time,burst time and priority
1
0
3
2
Enter process no,arrival time,burst time and priority
2
2
6
4
Enter process no,arrival time,burst time and priority
3
4
4
5
Enter process no,arrival time,burst time and priority
4
6
5
3
Enter process no,arrival time,burst time and priority
5
8
2
1

MENU
1:FCFS  2:SJF   3:PRIORITY      4:ROUND ROBIN   5:EXIT
Enter your choice
1
PROCESS 1
Waiting time for process 1 is 0
Turn around time for process 1 is 3
PROCESS 2
Waiting time for process 2 is 1
Turn around time for process 2 is 7
PROCESS 3
Waiting time for process 3 is 5
Turn around time for process 3 is 9
PROCESS 4
Waiting time for process 4 is 7
Turn around time for process 4 is 12
PROCESS 5
Waiting time for process 5 is 10
Turn around time for process 5 is 12

Average waiting time for the processes is 4.6
Average turn around time for the processes is 8.6

MENU
1:FCFS  2:SJF   3:PRIORITY      4:ROUND ROBIN   5:EXIT
Enter your choice
3
PROCESS 1
Waiting time for process 1 is 0
Turn around time for process 1 is 4
PROCESS 2
Waiting time for process 2 is 4
Turn around time for process 2 is 10
PROCESS 3
Waiting time for process 3 is 10
Turn around time for process 3 is 15
PROCESS 4
Waiting time for process 4 is 15
Turn around time for process 4 is 18
PROCESS 5
Waiting time for process 5 is 18
Turn around time for process 5 is 20

Average waiting time for the processes is 9.4
Average turn around time for the processes is 13.4

MENU
1:FCFS  2:SJF   3:PRIORITY      4:ROUND ROBIN   5:EXIT
Enter your choice
4
Enter quantum
4
PROCESS 1
Waiting time for process 1 is 0
Turn around time for process 1 is 3
PROCESS 2
Waiting time for process 2 is 11
Turn around time for process 2 is 17
PROCESS 3
Waiting time for process 3 is 3
Turn around time for process 3 is 7
PROCESS 4
Waiting time for process 4 is 9
Turn around time for process 4 is 14
PROCESS 5
Waiting time for process 5 is 7
Turn around time for process 5 is 9

Average waiting time for the processes is 6.0
Average turn around time for the processes is 10.0

MENU
1:FCFS  2:SJF   3:PRIORITY      4:ROUND ROBIN   5:EXIT
Enter your choice
5
*/