import java.rmi.*;
class RmiServer
{
	public static void main (String[] str)
	{
		try
		{
			Naming.rebind("rmi://localhost/RmiServer",new Calc());
			System.out.println("Hello..Server is ready");
		}
		catch(Exception e)
		{
			System.out.println("RmiServer failed: " + e);
		}
	}
}

/*
Output:
C:\Program Files\Java\jdk1.6.0_24\bin>javac RmiServer.java
C:\Program Files\Java\jdk1.6.0_24\bin>java RmiServer
Hello..Server is ready

*/