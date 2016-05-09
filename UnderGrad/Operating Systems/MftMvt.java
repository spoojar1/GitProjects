/*
SURAJ POOJARY   C22  109
Aim:Implementation of MFT and MVT
*/
 import java.io.*;
import java.util.*;
class Partition
{
	int pro_size,size,pro_no,db;
	Partition()
	{
		pro_size=0;
		size=8;
		pro_no=0;
		db=0;
	}
}
class Mft
{
	int i,intfrg;
	Partition p[]=new Partition[5];
	void process()
	{
		for(i=0;i<5;i++)
			p[i]=new Partition();
	}
	void addprocess(int size,int no)
	{
		for(i=0;i<5;i++)
		{
			if(p[i].db==0)
			{
				if(size>8)
					System.out.println("OVERLAY has occured");
				else
				{
					p[i].pro_size=size;
					p[i].pro_no=no;
					p[i].db=1;
					intfrg=p[i].size-size;
					System.out.println("INTERNAL FRAGMENTATION of "+intfrg+" MB has occured");
				}
				display();
				break;
			}		
		}
	}
	void removeprocess(int no)
	{
		for(i=0;i<5;i++)
		{
			if(p[i].pro_no==no)
			{
				p[i].pro_no=0;
				p[i].pro_size=0;
				p[i].db=0;
			}
		}
		display();
	}
	void display()
	{
		System.out.println("SIZE|no|size");
		for(i=0;i<5;i++)
		{
			System.out.println("-----------");
			if(p[i].db==1)
				System.out.println("|"+p[i].size+" | "+p[i].pro_no+" | "+p[i].pro_size+" |");
			else
			System.out.println("|          |");	
		}
		System.out.println("-----------");
	}	    
}	
class Memory
{
	int pro_no,pro_size;
	Memory()
	{
		pro_no=0;
		pro_size=0;
	}
}
class Mvt
{
	Memory p[]=new Memory[20];
	void process()
	{
		for(int i=0;i<20;i++)
			p[i]=new Memory();
	}
	void addprocess(int size,int no)
	{
		int j,k,sc=0;
		for(int i=0;i<20;)
		{
			if(p[i].pro_no==0)
			{
				j=i;
				while(j<20&&p[j].pro_no==0)
				{
					sc++;
					j++;
				}
				if(size<=sc)
				{
					for(k=0;k<size;k++)
					{
						p[k+i].pro_no=no;
						p[k+i].pro_size=size;
					}
					break;
				}
				i=j-1;
			}
			i++;
		}
	}
	void removeprocess(int no)
	{
		int extfrg=0,size,j;
		int i=0;
	    while(p[i].pro_no!=no)
			i++;
		size=p[i].pro_size;
		for(j=0;j<size;j++)
		{
			p[j+i].pro_no=0;
			p[j+i].pro_size=0;
		}
		for(i=0;i<20;i++)
		{
			if(p[i].pro_no==0)
				extfrg++;	
		}
		System.out.println("External Fragmentation of "+extfrg+" MB has occured");
		display();
	}
	void display()
	{
		int size=-1;
		System.out.println("No | Size");
		int i=0;
		while(i<20)
		{
			size=p[i].pro_size;
			System.out.println("--------");
			System.out.println("| "+p[i].pro_no+" | "+p[i].pro_size+" |");
			i++;
		}
		System.out.println("---------");
	}
}
class MftMvt
{
	public static void main(String str[])
	{
	 	Scanner s=new Scanner(System.in);
	 	int ch,ch1;
	 	do
	 	{
	 		System.out.println("1:MFT\n2:MVT\n3:EXIT\nEnter ur choice");
	 		ch=s.nextInt();
	 		switch(ch)
	 		{
	 			case 1: Mft m1=new Mft();
	 					int size,no;
	 					m1.process();
	 					do
	 					{
	 						System.out.println("MFT\n1:Add a process\n2:Remove a process\n3:Exit\nEnter ur choice");
	 						ch1=s.nextInt();
	 						switch(ch1)
	 						{
	 							case 1: System.out.println("Enter size and no of the process to be added");
	 									size=s.nextInt();
	 									no=s.nextInt();
	 									m1.addprocess(size,no);
	 									break;
	 							case 2: System.out.println("Enter no of the process to be removed");
	 							        no=s.nextInt();
	 							        m1.removeprocess(no);
	 							        break;
	 							case 3: break;	
	 						}
	 					}while(ch1!=3);
	 					break;
	 			case 2: Mvt m2=new Mvt();
	 					int size1,no1;
	 					m2.process();
	 					do
	 					{
	 						System.out.println("MVT\n1:Add a process\n2:Remove a process\n3:Exit\nEnter ur choice");
	 						ch1=s.nextInt();
	 						switch(ch1)
	 						{
	 							case 1: System.out.println("Enter size and no of the process to be added");
	 									size1=s.nextInt();
	 									no1=s.nextInt();
	 									m2.addprocess(size1,no1);
	 									break;
	 							case 2: System.out.println("Enter no of the process to be removed");
	 							        no1=s.nextInt();
	 							        m2.removeprocess(no1);
	 							        break;
	 							case 3: break;	
	 						}
	 					}while(ch1!=3);break;
	 			case 3: break;
	 		}
	 	}while(ch!=3);	
	}
}
/*
 OUTPUT
 1:MFT
2:MVT
3:EXIT
Enter ur choice
1
MFT
1:Add a process
2:Remove a process
3:Exit
Enter ur choice
1
Enter size and no of the process to be added
5
4
INTERNAL FRAGMENTATION of 3 MB has occured
SIZE|no|size
-----------
|8 | 4 | 5 |
-----------
|          |
-----------
|          |
-----------
|          |
-----------
|          |
-----------
MFT
1:Add a process
2:Remove a process
3:Exit
Enter ur choice
1
Enter size and no of the process to be added
7
5
INTERNAL FRAGMENTATION of 1 MB has occured
SIZE|no|size
-----------
|8 | 4 | 5 |
-----------
|8 | 5 | 7 |
-----------
|          |
-----------
|          |
-----------
|          |
-----------
MFT
1:Add a process
2:Remove a process
3:Exit
Enter ur choice
2
Enter no of the process to be removed
4
SIZE|no|size
-----------
|          |
-----------
|8 | 5 | 7 |
-----------
|          |
-----------
|          |
-----------
|          |
-----------
MFT
1:Add a process
2:Remove a process
3:Exit
Enter ur choice
3
1:MFT
2:MVT
3:EXIT
Enter ur choice
2
MVT
1:Add a process
2:Remove a process
3:Exit
Enter ur choice
1
Enter size and no of the process to be added
5
4
MVT
1:Add a process
2:Remove a process
3:Exit
Enter ur choice
1
Enter size and no of the process to be added
7
3
MVT
1:Add a process
2:Remove a process
3:Exit
Enter ur choice
2
Enter no of the process to be removed
4
External Fragmentation of 13 MB has occured
No | Size
--------
| 0 | 0 |
--------
| 0 | 0 |
--------
| 0 | 0 |
--------
| 0 | 0 |
--------
| 0 | 0 |
--------
| 3 | 7 |
--------
| 3 | 7 |
--------
| 3 | 7 |
--------
| 3 | 7 |
--------
| 3 | 7 |
--------
| 3 | 7 |
--------
| 3 | 7 |
--------
| 0 | 0 |
--------
| 0 | 0 |
--------
| 0 | 0 |
--------
| 0 | 0 |
--------
| 0 | 0 |
--------
| 0 | 0 |
--------
| 0 | 0 |
--------
| 0 | 0 |
---------
MVT
1:Add a process
2:Remove a process
3:Exit
Enter ur choice
3
1:MFT
2:MVT
3:EXIT
Enter ur choice
3
Press any key to continue...
 */
 