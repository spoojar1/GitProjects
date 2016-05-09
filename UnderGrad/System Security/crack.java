import java.io.*;
import java.util.*;

class s
{
	BufferedReader s= new BufferedReader(new InputStreamReader (System.in));
	String pass;
	String in[]=new String[36];
	long count=0,t;
	Queue q=new LinkedList();
	Console console = System.console();

	void input()throws IOException
	{
		pass=new String(console.readPassword("Enter your secret password (a-z and 0-9): "));
		System.out.println("Processing...");
		q.add("");
		for(int i=0,j=97;i<36;i++)
		{
			if(i>9)
			{
				String s=Character.toString((char)j++);
				in[i]=s;
			}
			else
			{
				String s=Integer.toString(i);
				in[i]=s;
			}
		}

		Runtime r=Runtime.getRuntime();
		try
		{
			t=System.currentTimeMillis();
			cracking();
		}
		finally
		{
			System.out.println("Number of permutations tried : "+count);
			System.out.println("Time taken to crack the password : "+(System.currentTimeMillis()-t)+"ms");
		}

	}
	boolean check(String s)
	{
		if(s.equals(pass))
			return true;
		else
			return false;
	}
	boolean cracking()
	{
		while(q.peek()!=null)
		{
			for(int i=0;i<36;i++)
			{
				count++;
				if(check(q.peek()+in[i])==true)
				{
					System.out.println("Password Cracked : "+q.peek()+in[i]);
					return true;
				}
				else
				{
					q.add(q.peek()+in[i]);
				}
			}
			q.remove();
		}
		return false;
	}
}
class crack
{
	public static void main(String str[])throws IOException
	{
		s se=new s();
		se.input();
	}
}
/*
OUTPUT
Enter your secret password (a-z and 0-9): *****		//9zx0s

Processing...
Password Cracked : 9zx0s

Number of permutations tried : 18519905

Time taken to crack the password : 21309ms

*/
