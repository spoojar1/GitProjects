import java.io.*;
import java.net.*;
class server
{

	public static void main(String[] args)throws IOException
	{

		try
		{
			System.out.println("SERVER: \n");
			while(true)
			{
				BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
				ServerSocket s=new ServerSocket(4444);
				Socket skt1=s.accept();
				PrintWriter out=new PrintWriter(skt1.getOutputStream());
				System.out.println("Suraj: ");
				out.println(br.readLine());
				out.close();
				skt1.close();
				s.close();

				Socket skt=new Socket("10.10.1.157",4444);
				System.out.println("Satya: ");
				BufferedReader b=new BufferedReader(new InputStreamReader(skt.getInputStream()));
				System.out.println(b.readLine());
				b.close();

			}
		}
		catch(Exception e){}
	}
}
/*
OUTPUT
SERVER:

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



