import java.net.*;
import java.io.*;
class client
{
	public static void main(String args[])throws IOException
	{
		BufferedReader sc=new BufferedReader(new InputStreamReader(System.in));
		String str=new String();
		while(true)
		{
			try
			{
				Socket skt=new Socket("10.10.1.158",4444);
				BufferedReader in=new BufferedReader(new InputStreamReader(skt.getInputStream()));
				str=in.readLine();
				System.out.println("CLIENT");
				System.out.println("Satya :"+str);
				in.close();

				ServerSocket s=new ServerSocket(4444);
				Socket skt1=s.accept();
				PrintWriter out=new PrintWriter(skt1.getOutputStream());
				System.out.println("Suraj :");
				str=sc.readLine();
				out.print(str);
				out.close();
				skt1.close();
				s.close();
			}
			catch(Exception e){}
		}
	}
}
/*
OUTPUT
CLIENT:

Suraj:
hi satya !!

Satya:
hi suraj

Suraj::
did u go to the carpenter ?

Satya:
not yet. sinkesh went .he is a genius

Suraj::
i will complain to rp mam

Satya:
no u wont

Suraj::
k. bye tc !!

Satya:
bye
*/