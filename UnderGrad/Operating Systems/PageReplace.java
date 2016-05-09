import java.util.*;

//for LRU , OPTIMAL and MODIFIED CLOCK
class Array
{
	char data;
	int bt;
	int u,m;   //for modified clock
	Array()
	{
		data='*';
		bt=0;
	}
}

//for FIFO
class Queue
{
	int max;
	char items[];
	int front,rear,hit=0,count=0;
	Queue(int e)
	{
		max=e;
		items=new char[max];
		front=0;
		rear=-1;
		for(int i=0;i<max;i++)
			items[i]='*';
	}
	void insert(char e)
	{
		if(rear==max-1)
		{
			rear=-1;
			front++;
		}
		if(front==max)
		{
			front=0;
		}
		items[++rear]=e;
	}
	void display()
	{
		for(int i=0;i<max;i++)
			if(items[i]!='*')
				System.out.print(items[i]+"\t");
	}
	void compare(char e)
	{
		hit=0;
		for(int i=0;i<max;i++)
			if(items[i]==e)
			{
				hit=1;
				count++;
				return;
			}
		insert(e);
	}
}

class PageReplace
{
	//for MODIFIED CLOCK
	static void test00(Array d[],char s,int frm_size)
	{
		for(int i=0;i<frm_size;i++)
			if(d[i].u==0&&d[i].m==0)
			{
				d[i].data=s;
				d[i].u=1;
				return;			//write return not break else it'll go to test01
			}
		//00 test fails therefore call test01
		test01(d,s,frm_size);
	}
	static void test01(Array d[],char s,int frm_size)
	{
		for(int i=0;i<frm_size;i++)
			if(d[i].u==0&&d[i].m==1)
			{
				d[i].data=s;
				d[i].u=1;
				return;			//write return not break else it'll go to test00
			}
			else
				d[i].u=0;

		//01 test fails therefore call test00 again
		test00(d,s,frm_size);
	}

	//for LRU and OPTIMAL
	static boolean compare(char s,int frm_size,Array q[])
	{
		for(int i=0;i<frm_size;i++)
			if(q[i].data==s)
				return true;
		return false;
	}
	static boolean qnotfull(Array q[],int frm_size)
	{
		for(int i=0;i<frm_size;i++)
			if(q[i].data=='*')
				return true;
		return false;
	}
	static int insert(char s,int pos,int frm_size,String str,Array q[],int hit,int flag)
	{
		if(compare(s,frm_size,q))
			hit++;
		else
		{
			if(qnotfull(q,frm_size))
			{
				 for(int i=0;i<frm_size;i++)
					if(q[i].data=='*')
					{
						q[i].data=s;
						break;
					}
			}
			else		//q full
			{
				if(flag==2)		//LRU
					check1(pos,frm_size,str,q);   //check which page to replace
				else            //OPTIMAL
					check2(pos,frm_size,str,q);   //check which page to replace
				for(int j=0;j<frm_size;j++)
					if(q[j].bt==0)
					{
						q[j].data=s;
						break;
					}
				for(int j=0;j<frm_size;j++)
					q[j].bt=0;
			}
		}
		return hit;
	}
	static void check1(int pos,int frm_size,String str,Array q[])
	{
		int count=frm_size-1;
		for(int j=pos-1;j>=0&&count>0;j--)
			for(int k=0;k<frm_size;k++)
				if(q[k].data==str.charAt(j))
				{
					count--;
					q[k].bt=1;
					break;
				}
	}
	static void check2(int pos,int frm_size,String str,Array q[])
	{
		int count=frm_size-1;
		int max=str.length();
		for(int j=pos+1;j<max&&count>0;j++)
			for(int k=0;k<frm_size;k++)
				if(q[k].data==str.charAt(j))
				{
					count--;
					q[k].bt=1;
					break;
				}
	}

	//COMMON for ALL
	public static void main(String args[])
	{
		Scanner s=new Scanner(System.in);
		System.out.println("Enter reference String");
		String str=s.next();
		System.out.println("Enter no of frames");
		int frm_size=s.nextInt();
		int ch;
		do
		{
			System.out.println("\nMENU\n1:FIFO\t2:LRU\t3:Optimal\t4:Modified Clock\t5:Exit\nEnter your choice\n");
			ch=s.nextInt();
			int hit;
			switch(ch)
			{
				case 1: Queue a=new Queue(frm_size);
						for(int i=0;i<str.length();i++)
						{
							a.compare(str.charAt(i));
							a.display();
							if(a.hit==1)
								System.out.print("Page HIT");
							System.out.println();
						}
						System.out.println("HIT Ratio: "+a.count+"/"+str.length());
						break;
				case 2: Array b[]=new Array[frm_size];
						for(int i=0;i<frm_size;i++)
							b[i]=new Array();
						hit=0;
						for(int i=0;i<str.length();i++)
						{
							hit=insert(str.charAt(i),i,frm_size,str,b,hit,ch);
							for(int j=0;j<frm_size;j++)
								if(b[j].data!='*')
									System.out.print(" "+b[j].data+" ");
							System.out.println();
						}
					System.out.println("hit ratio="+hit+"/"+str.length());
						break;
				case 3: Array c[]=new Array[frm_size];
						for(int i=0;i<frm_size;i++)
							c[i]=new Array();
						hit=0;
						for(int i=0;i<str.length();i++)
						{
							hit=insert(str.charAt(i),i,frm_size,str,c,hit,ch);
							for(int j=0;j<frm_size;j++)
							if(c[j].data!='*')
									System.out.print(" "+c[j].data+" ");
							System.out.println();
						}
						System.out.println("hit ratio="+hit+"/"+str.length());
						break;
				case 4: //in modified clock(for pracs) everything is defined by programmer as told by RP
						String str1="12345";
						Array d[]=new Array[4];
						for(int i=0;i<4;i++)
							d[i]=new Array();

						//set random u and m for each page
						d[0].u=1;	d[0].m=1;
						d[1].u=1;	d[1].m=0;
						d[2].u=0;	d[2].m=1;
						d[3].u=0;	d[3].m=0;

						d[0].data='6';
						d[1].data='7';
						d[2].data='8';
						d[3].data='9';
						System.out.println("Reference string: "+str1);
						System.out.println("Original contents");
						for(int j=0;j<4;j++)
							System.out.print(d[j].data+" ");
						System.out.println();
						for(int i=0;i<str1.length();i++)
						{
							test00(d,str1.charAt(i),4);
							for(int j=0;j<4;j++)
								System.out.println(d[j].data+"	"+d[j].u+"	"+d[j].m);
							System.out.println();
							System.out.println();
						}
						break;
			}	//switch
		}while(ch!=5);		//do
	}
}
/*
OUTPUT
Enter reference String
232152453252
Enter no of frames
3

MENU
1:FIFO  2:LRU   3:Optimal   4:Modified Clock   5:Exit
Enter your choice

1
2
2       3
2       3       Page HIT
2       3       1
5       3       1
5       2       1
5       2       4
5       2       4       Page HIT
3       2       4
3       2       4       Page HIT
3       5       4
3       5       2
HIT Ratio: 3/12

MENU
1:FIFO  2:LRU   3:Optimal   4:Modified Clock   5:Exit
Enter your choice

2
 2
 2  3
 2  3
 2  3  1
 2  5  1
 2  5  1
 2  5  4
 2  5  4
 3  5  4
 3  5  2
 3  5  2
 3  5  2
hit ratio=5/12

MENU
1:FIFO  2:LRU   3:Optimal   4:Modified Clock    5:Exit
Enter your choice

3
 2
 2  3
 2  3
 2  3  1
 2  3  5
 2  3  5
 4  3  5
 4  3  5
 4  3  5
 2  3  5
 2  3  5
 2  3  5
hit ratio=6/12

MENU
1:FIFO  2:LRU   3:Optimal   4:Modified Clock    5:Exit
Enter your choice

4
Reference string: 12345

Original contents
6 7 8 9

6       1       1
7       1       0
8       0       1
1       1       0


6       0       1
7       0       0
2       1       1
1       1       0


6       0       1
3       1       0
2       1       1
1       1       0


4       1       1
3       1       0
2       1       1
1       1       0


4       0       1
5       1       0
2       0       1
1       0       0



MENU
1:FIFO  2:LRU   3:Optimal       4:Modified Clock        5:Exit
Enter your choice

5

*/
