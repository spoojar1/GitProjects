import java.util.*;
class Matrix
{
	Scanner s=new Scanner(System.in);
	int m[][],p,r,e;
	String c_list[]=new String[100];
	int c_count=-1;
	int wfg[][];

	void init()
	{
		System.out.println("Enter no of processes");
		p=s.nextInt();
		System.out.println("Enter no of resources");
		r=s.nextInt();
		System.out.println("Enter no of edges");
		e=s.nextInt();

		m=new int[p+1][r+1];
		wfg=new int[p+1][p+1];

		for(int i=1;i<=e;i++)
		{
			System.out.println("Enter details for edge "+i+" in the form Process Resource Relationship(1/2 : hold/wait)");
			m[s.nextInt()][s.nextInt()]=s.nextInt();
		}
	}
	void detect_cycle()
	{
		for(int i=1;i<=p;i++)
		{
			String str="P"+Integer.toString(i);
			cycle(i,i,str);
		}

		if(c_count!=-1)
			knot();
		else
			System.out.println("No cycles present, hence NO DEADLOCK");
	}
	void cycle(int curr_p,int start_p,String str)
	{
		if(str.length()>2 && curr_p==start_p)
		{
			c_list[++c_count]=str;
			System.out.println("for process "+start_p+" cycle : "+c_list[c_count]);
		}
		else
		{
			if(str.length()>2*e)
				return;

			int temp_r=wait_for(curr_p);
			if(temp_r==-1)
				return;

			str+="R"+Integer.toString(temp_r);
			int temp[]=held_by(temp_r);
			int temp1=cal_len(temp);

			for(int j=1;j<=temp1;j++)
			{
				wfg[start_p][temp[j]]=1;
				cycle(temp[j],start_p,str+"P"+Integer.toString(temp[j]));
			}
		}
	}
	int cal_len(int temp[])
	{
		for(int i=1;i<=100;i++)
			if(temp[i]==0)
				return i-1;

		return -1;
	}
	int[] held_by(int rc)
	{
		int t[]=new int[100];
		int count=0;
		for(int i=1;i<=p;i++)
			if(m[i][rc]==1)
				t[++count]=i;
		return t;
	}
	int wait_for(int pr)
	{
		for(int i=1;i<=r;i++)
			if(m[pr][i]==2)
				return i;
		return -1;
	}
	void knot()
	{
		for(int i=1;i<=p;i++)
		{
			for(int j=1;j<=p;j++)
			{
				if(wfg[i][j]!=1)
				{
					System.out.println("NO KNOT PRESENT HENCE NO DEADLOCK");
					System.exit(1);
				}
			}
		}
		System.out.println("KNOT PRESENT HENCE DEADLOCK PRESENT");
	}
}
class DL
{
	public static void main(String str[])
	{
		Matrix m=new Matrix();
		m.init();
		m.detect_cycle();
	}
}

/*
OUTPUT
Enter no of processes
3
Enter no of resources
2
Enter no of edges
6
Enter details for edge 1 in the form Process Resource Relationship(1/2 : hold/wait)
1
1
1
Enter details for edge 2 in the form Process Resource Relationship(1/2 : hold/wait)
1
2
2
Enter details for edge 3 in the form Process Resource Relationship(1/2 : hold/wait)
2
1
2
Enter details for edge 4 in the form Process Resource Relationship(1/2 : hold/wait)
2
2
1
Enter details for edge 5 in the form Process Resource Relationship(1/2 : hold/wait)
3
1
1
Enter details for edge 6 in the form Process Resource Relationship(1/2 : hold/wait)
3
2
2
for process 1 cycle : P1R2P2R1P1
for process 2 cycle : P2R1P1R2P2
for process 2 cycle : P2R1P3R2P2
for process 3 cycle : P3R2P2R1P3

KNOT PRESENT
*/