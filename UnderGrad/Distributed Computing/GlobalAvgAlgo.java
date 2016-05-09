import java.util.*;
class Time
{
	int hr,min,sec,diff[],pass;
	Time()
	{}
	Time(int hr,int min,int sec,int n)
	{
		this.hr=hr;
		this.min=min;
		this.sec=sec;
		this.diff=new int[n];  //sec
		pass=0;
	}
}
class Sync
{
	int n;
	Time t[];
	Time to;
	float avg[];  //seconds
	Scanner s=new Scanner(System.in);

	void init()
	{
		System.out.println("Enter number of processes ");
		n=s.nextInt();
		t=new Time[n];
		avg=new float[n];

		for(int i=0;i<n;i++)
		{
			System.out.println("Enter hr min and sec for PROCESS : "+i);
			t[i]=new Time(s.nextInt(),s.nextInt(),s.nextInt(),n);
		}

		System.out.println("Enter predecided hr min and sec");
		to=new Time(s.nextInt(),s.nextInt(),s.nextInt(),n);

		System.out.println("\nProcess No	HR	MIN	SEC");
		for(int i=0;i<n;i++)
		{
			System.out.println("Process "+i+" : 	"+t[i].hr+"	"+t[i].min+"	"+t[i].sec);
		}
		System.out.println("\nTo+IR	HR	MIN	SEC");
		System.out.println("	"+to.hr+"	"+to.min+"	"+to.sec);
	}
	void sync()
	{
		int temp=-1,pos=-1;
		for(int x=0;x<n;x++)
		{
			int inc=0;
			long small=9999999;
			for(int i=0;i<n;i++)
			{
				if(t[i].pass==0)
				{
					int time_sec=Time2sec(t[i]);
					int to_sec=Time2sec(to);
					if(to_sec>=time_sec)
					{
						int diff=to_sec-time_sec;
						if(diff>=0)
						{
							if(diff<small)
							{
								small=diff;
								pos=i;
								inc=diff;
							}
						}
					}
				}
			}
			t[pos].pass=1;

			//increment
			for(int i=0;i<n;i++)
			{
					Time tmp=sec2Time(Time2sec(t[i])+inc);
					t[i].hr=tmp.hr;
					t[i].min=tmp.min;
					t[i].sec=tmp.sec;

					//check which process reaches predecided time
					if(to.hr==t[i].hr && to.min==t[i].min && to.sec==t[i].sec)
					{
						temp=i;
					}
			}
			for(int i=0;i<n;i++)
			{
				if(i!=temp)
				{
					t[temp].diff[i]=Time2sec(t[i])-Time2sec(to);
				}
			}
		}
		calc_avg();
		display_skew();
		for(int i=0;i<n;i++)
		{
			int tmp1=1;
			if(avg[i]<0)
			{
				tmp1=0;
				avg[i]=Math.abs(avg[i]);
			}
			Time tmp2;
			if(tmp1==1)
				tmp2=sec2Time(Time2sec(t[i])-(int)avg[i]);
			else
				tmp2=sec2Time(Time2sec(t[i])+(int)avg[i]);
			t[i].hr=tmp2.hr;
			t[i].min=tmp2.min;
			t[i].sec=tmp2.sec;
		}
		System.out.println("\nProcess No	HR	MIN	SEC");
		for(int i=0;i<n;i++)
		{
			System.out.println("Process "+i+" : 	"+t[i].hr+"	"+t[i].min+"	"+t[i].sec);
		}
	}
	int Time2sec(Time t)
	{
		return(t.hr*60*60+t.min*60+t.sec);
	}
	Time sec2Time(int sec)
	{
		Time t=new Time();
		t.hr=(sec/3600);
		sec=sec%3600;
		t.min=sec/60;
		sec=sec%60;
		t.sec=sec;
		return t;
	}
	void display_skew()
	{
		System.out.println("\nSKEW TABLE (Difference in seonds)");
		System.out.print("		");
		for(int i=0;i<n;i++)
			System.out.print(i+"		");
		System.out.println();
		for(int i=0;i<n;i++)
		{
			System.out.print("Process "+i+"	");
			for(int j=0;j<n;j++)
				System.out.print(t[i].diff[j]+"		");
			System.out.println();
		}
		System.out.print("Average		");
		for(int i=0;i<n;i++)
			System.out.print(avg[i]+"		");
		System.out.println();
	}
	void calc_avg()
	{
		for(int i=0;i<n;i++)
		{
			//System.out.println(i);
			float sum=0;
			for(int j=0;j<n;j++)
			{
				sum+=t[j].diff[i];
			}
			sum/=n;
			//System.out.println(sum);
			avg[i]=sum;
		}
	}
}
class GlobalAvgAlgo
{
	public static void main(String args[])
	{
		Sync s=new Sync();
		s.init();
		s.sync();
	}
}
/*
OUTPUT
Enter number of processes
5
Enter hr min and sec for PROCESS : 0
7
45
55
Enter hr min and sec for PROCESS : 1
9
25
35
Enter hr min and sec for PROCESS : 2
8
40
55
Enter hr min and sec for PROCESS : 3
10
45
55
Enter hr min and sec for PROCESS : 4
3
45
20
Enter predecided hr min and sec
11
45
30

Process No      HR      MIN     SEC
Process 0 :     7       45      55
Process 1 :     9       25      35
Process 2 :     8       40      55
Process 3 :     10      45      55
Process 4 :     3       45      20

To+IR   HR      MIN     SEC
        11      45      30

SKEW TABLE (Difference in seonds)
                0               1               2               3               4
Process 0       0               5980            3300            10800           -14435
Process 1       -5980           0               -2680           4820            -20415
Process 2       -3300           2680            0               7500            -17735
Process 3       -10800          -4820           -7500           0               -25235
Process 4       14435           20415           17735           25235           0
Average         -1129.0         4851.0          2171.0          9671.0          -15564.0

Process No      HR      MIN     SEC
Process 0 :     16      4       54
Process 1 :     16      4       54
Process 2 :     16      4       54
Process 3 :     16      4       54
Process 4 :     16      4       54

*/