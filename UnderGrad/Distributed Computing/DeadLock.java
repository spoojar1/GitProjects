import java.util.*;
class Matrix
{
	Scanner s=new Scanner(System.in);
	int m[][],p,r,e;
	String c_list[]=new String[100];
	int c_count=-1;
	int c_rsc[];

	//int plist[],ptest[][];
	int wfg[][],plist[];

	void init()
	{

		/*System.out.println("Enter no of processes");
		p=s.nextInt();
		System.out.println("Enter no of resources");
		r=s.nextInt();
		System.out.println("Enter no of edges");
		e=s.nextInt();

		c_rsc=new int[r+1];
		m=new int[p+1][r+1];

		plist=new int[p+1];
		ptest=new int[p+1][p+1];

		for(int i=1;i<=e;i++)
		{
			System.out.println("Enter details for edge "+i+" in the form Process Resource Relationship(1/2 : hold/wait)");
			m[s.nextInt()][s.nextInt()]=s.nextInt();
		}
		*/




		//*
		//EXAMPLE 1
		p=3;
		r=2;
		e=6;

		c_rsc=new int[r+1];
		m=new int[p+1][r+1];

		plist=new int[p+1];
		wfg=new int[p+1][p+1];
		//ptest=new int[p+1][p+1];

		m[1][1]=1;
		m[1][2]=2;
		m[2][1]=2;
		m[2][2]=1;
		m[3][1]=1;
		m[3][2]=2;
		//*/


		/*
		//EXAMPLE 2
		p=3;
		r=2;
		e=5;

		c_rsc=new int[r+1];
		m=new int[p+1][r+1];

		plist=new int[p+1];
		wfg=new int[p+1][p+1];
		//ptest=new int[p+1][p+1];

		m[1][1]=2;
		m[1][2]=1;
		m[2][1]=1;
		m[2][2]=2;
		m[3][2]=1;
		*/


		//*
		//EXAMPLE 3
		p=3;
		r=2;
		e=5;

		c_rsc=new int[r+1];
		m=new int[p+1][r+1];

		plist=new int[p+1];
		wfg=new int[p+1][p+1];
		//ptest=new int[p+1][p+1];

		m[1][1]=2;
		m[1][2]=1;
		m[2][1]=1;
		m[2][2]=2;
		m[3][2]=2;
		*/


	}

	void detect_cycle()
	{
		for(int i=1;i<=p;i++)
		{
			String str="P"+Integer.toString(i);
			cycle(i,i,str);
			System.out.println("--------------------");
		}
	}

	void cycle(int curr_p,int start_p,String str)
	{
		if(str.length()>2 && curr_p==start_p)
		{
			if(check_cycle(str))
				return;
			else
			{
				c_list[++c_count]=str;
				System.out.println("for process "+start_p+" cycle : "+c_list[c_count]);
			}
		}
		else
		{
			if(str.length()>2*e)
				return;

			int temp_r=wait_for(curr_p);

			if(temp_r==-1)
				return;

			System.out.println("start : "+start_p+" curr_p : "+curr_p+" wait_for "+temp_r);

			str+="R"+Integer.toString(temp_r);

			int temp[]=held_by(temp_r);
			int temp1=cal_len(temp);

			System.out.println("start : "+start_p+" curr_p : "+curr_p+" held_by len "+temp1);

			for(int j=1;j<=temp1;j++)
			{
				System.out.println("start : "+start_p+" curr_p : "+curr_p+" temp contents "+temp[j]);

				//wfg[temp[j]][start_p]=1;
				wfg[start_p][temp[j]]=1;

				cycle(temp[j],start_p,str+"P"+Integer.toString(temp[j]));
			}
		}
	}

	boolean check_cycle(String str)   //checks for already detected cycles
	{
		for(int i=0;i<=c_count;i++)
		{
			String temp1=c_list[i].substring(0,c_list[i].length()-2);
			String temp2=str.substring(0,str.length()-2);

			for(int j=0;j<temp2.length();j++)
			{
				if(temp1.equals(temp2))
					return true;

				temp2=temp2.substring(2)+temp2.substring(0,2);
			}
		}
		return false;
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

	void calc_cycle_rsc()
	{
		for(int i=0;i<=c_count;i++)
		{
			for(int j=0;j<c_list[i].length();)
			{
				if(c_list[i].charAt(j)=='R')
				{
					int temp=(int)(c_list[i].charAt(j+1))-48;
					c_rsc[temp]=1;
				}
				j=j+2;
			}
		}

		System.out.println("Contents of c_rsc with status of resource 1/0: involved in cycle/not involved in cycle");
		for(int i=1;i<=r;i++)
			System.out.println("Rsc "+i+" status : "+c_rsc[i]);
	}

	void knot()
	{
		for(int i=1;i<=r;i++)
		{
			if(c_rsc[i]==1)
			{
				for(int j=1;j<=p;j++)
				{
					if(m[j][i]==1 || m[j][i]==2)
						plist[j]=1;
				}
			}
		}

		for(int i=1;i<=p;i++)
		{
			for(int j=1;j<=p;j++)
			{
				if(wfg[i][j]!=1)
				{
					System.out.println("NO KNOT PRESENT");
					System.exit(1);
				}
			}
		}
		System.out.println("KNOT PRESENT");
	}
}
class DeadLock
{
	public static void main(String str[])
	{
		Matrix m=new Matrix();
		m.init();
		m.detect_cycle();
		m.calc_cycle_rsc();
		m.knot();
	}
}