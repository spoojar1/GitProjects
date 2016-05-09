import java.util.*;
class Election
{
	int ps[],n,in;
	Scanner s=new Scanner(System.in);
	Queue q=new LinkedList();
	void init()
	{
		System.out.println("Enter no of processes");
		n=s.nextInt();
		ps=new int[n+1];
		System.out.println("Enter process statuses as a string");
		String str=s.next();
		for(int i=1;i<=n;i++)
			ps[i]=(int)str.charAt(i-1)-48;
		System.out.println("Enter initiator process");
		in=s.nextInt();
		System.out.println("Assuming process "+n+" was the coordinator and it has crashed");
	}
	void election()
	{
		int temp;
		q.add(in);
		while(true)
		{
			System.out.println();
			Iterator it=q.iterator();
			while(it.hasNext())
			{
				in=(Integer)it.next();
				for(int i=in+1;i<=n;i++)
					System.out.println("Process "+in+" sends ELECTION message to Process "+i);
			}
			temp=q.size();
			System.out.println();
			while(temp!=0)
			{
				in=(Integer)q.poll();
				temp--;
				for(int i=in+1;i<=n;i++)
				{
					if(ps[i]!=0)
					{
						System.out.println("Process "+i+" sends OK message to Process "+in);
						if(!q.contains(i))
							q.add(i);
					}
				}
				if(q.peek()==null)
				{
					System.out.println();
					for(int i=1;i<n && i!=in;i++)
						System.out.println("Process "+in+" sends I AM THE NEW COORDINATOR message to Process "+i);
					System.exit(1);
				}
			}
		}
	}
}
class Bully
{
	public static void main(String str[])
	{
		Election e=new Election();
		e.init();
		e.election();
	}
}
/*
OUTPUT
Enter no of processes
8
Enter process statuses as a string
10111010
Enter initiator process
3
Assuming process 8 was the coordinator and it has crashed

Process 3 sends ELECTION message to Process 4
Process 3 sends ELECTION message to Process 5
Process 3 sends ELECTION message to Process 6
Process 3 sends ELECTION message to Process 7
Process 3 sends ELECTION message to Process 8

Process 4 sends OK message to Process 3
Process 5 sends OK message to Process 3
Process 7 sends OK message to Process 3

Process 4 sends ELECTION message to Process 5
Process 4 sends ELECTION message to Process 6
Process 4 sends ELECTION message to Process 7
Process 4 sends ELECTION message to Process 8
Process 5 sends ELECTION message to Process 6
Process 5 sends ELECTION message to Process 7
Process 5 sends ELECTION message to Process 8
Process 7 sends ELECTION message to Process 8

Process 5 sends OK message to Process 4
Process 7 sends OK message to Process 4
Process 7 sends OK message to Process 5

Process 7 sends I AM THE NEW COORDINATOR message to Process 1
Process 7 sends I AM THE NEW COORDINATOR message to Process 2
Process 7 sends I AM THE NEW COORDINATOR message to Process 3
Process 7 sends I AM THE NEW COORDINATOR message to Process 4
Process 7 sends I AM THE NEW COORDINATOR message to Process 5
Process 7 sends I AM THE NEW COORDINATOR message to Process 6

*/