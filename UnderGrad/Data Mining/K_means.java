import java.util.*;
class Element
{
	int value[],prev[];
	double mean;
	int count=0,prevcount=0;
	Element()
	{
		value=new int[100];
		prev=new int[100];
		mean=0.0;
	}
}
class Suraj
{
	int i_count=0;
	Scanner s=new Scanner(System.in);
	int n,k,input[],max=0;
	int first=1;
	Element e[];
	void input()
	{
		System.out.println("Enter no of elements");
		n=s.nextInt();
		System.out.println("Enter no of clusters");
		k=s.nextInt();
		e=new Element[k];
		for(int i=0;i<k;i++)
			e[i]=new Element();
		input=new int[n];
		for(int i=0;i<n;i++)
		{
			System.out.println("Enter element "+(i+1));
			input[i]=s.nextInt();
		}
		System.out.println();
		process();
		disp_prev();
		processing();
	}
	void process()
	{
		max=n/k;
		int count=0;
		for(int i=0;i<k;i++)
		{
			int j;
			for(j=0;j<max || (count!=n && i==(k-1));j++)
			{
				e[i].prev[j]=e[i].value[j]=input[count++];
				e[i].mean+=e[i].value[j];
			}
			e[i].mean=e[i].mean/j;
			e[i].prevcount=j;
		}
	}
	void next()
	{
		double min=32767;
		int pos=-1;
		for(int i=0;i<n;i++)
		{
			for(int j=0;j<k;j++)
			{
				double temp=Math.abs(input[i]-e[j].mean);
				if(temp<min)
				{
					min=temp;
					pos=j;
				}
			}
			min=32767;
			e[pos].value[e[pos].count++]=input[i];
		}
		next_mean();
	}
	void next_mean()
	{
		for(int i=0;i<k;i++)
		{
			e[i].mean=0.0;
			for(int j=0;j<e[i].count;j++)
			{
				e[i].mean+=e[i].value[j];
			}
			if(e[i].count!=0)
				e[i].mean/=e[i].count;
		}
	}
	void next_init()
	{
		//initialize  previous sets to zero
		for(int i=0;i<k;i++)
		{
			for(int j=0;j<e[i].prevcount;j++)
			{
				e[i].prev[j]=0;
			}
			e[i].prevcount=0;
		}
		//copy current set to previous set
		for(int i=0;i<k;i++)
		{
			for(int j=0;j<e[i].count;j++)
			{
				e[i].prev=e[i].value;
			}
			e[i].prevcount=e[i].count;
		}
		//set current set to zero
		for(int i=0;i<k;i++)
		{
			for(int j=0;j<e[i].count;j++)
			{
				e[i].value[j]=0;
			}
			e[i].count=0;
		}
	}
	void processing()
	{
		while(check()!=true || first==1)
		{
			first=0;
			next_init();
			next();
			disp_next();
		}
	}
	boolean check()
	{
		for(int i=0;i<k;i++)
		{
			if(e[i].prevcount!=e[i].count)
				return false;
			for(int j=0;j<e[i].prevcount;j++)
			{
				if(e[i].value[j]==e[i].prev[j])
					continue;
				return false;
			}
		}
		return true;
	}
	void disp_prev()
	{
		for(int i=0;i<k;i++)
		{
			for(int j=0;j<e[i].prevcount;j++)
			{
				System.out.print(e[i].prev[j]+"\t");
			}
			System.out.print("\t\t"+e[i].mean);
			System.out.println();
		}
		System.out.println();
	}
	void disp_next()
	{
		System.out.println("Iteration "+(++i_count)+"\n");
		for(int i=0;i<k;i++)
		{
			System.out.print("Kluster "+(i+1)+":\t");
			for(int j=0;j<e[i].count;j++)
			{
				System.out.print(e[i].value[j]+"\t");
			}
			System.out.print("\t    Mean:   "+e[i].mean);
			System.out.println();
		}
		System.out.println();
	}
}
class K_means
{
	public static void main(String str[])
	{
		Suraj s=new Suraj();
		s.input();
	}
}
/*
OUTPUT
Enter no of elements
9
Enter no of clusters
3
Enter element 1
22
Enter element 2
18
Enter element 3
2
Enter element 4
8
Enter element 5
15
Enter element 6
9
Enter element 7
6
Enter element 8
12
Enter element 9
3

22      18      2                       14.0
8       15      9                       10.666666666666666
6       12      3                       7.0

22      18      15                      18.333333333333332
9       12                      10.5
2       8       6       3                       4.75

false
22      18      15                      18.333333333333332
8       9       12                      9.666666666666666
2       6       3                       3.6666666666666665

false
22      18      15                      18.333333333333332
8       9       12                      9.666666666666666
2       6       3                       3.6666666666666665

true
*/
