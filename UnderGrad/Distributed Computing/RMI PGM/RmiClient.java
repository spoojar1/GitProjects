import java.rmi.*;
class RmiClient
{
	public static void main(String[] str)
	{
		CalcInt solve;
		String name = "rmi://10.10.1.153/RmiServer";
		try
		{
			solve = (CalcInt)Naming.lookup(name);
			System.out.println("Addition: "+solve.add(5,3));
			System.out.println("Subtraction : "+solve.sub(5,3));
			System.out.println("Multiplication : "+solve.mul(5,3));
			System.out.println("Division : "+solve.div(5,3));
			System.out.println("Modulus : "+solve.mod(5,3));
		}
		catch(Exception e)
		{
			System.out.println("RmiClient exception: " + e);
		}
	}
}



/*
Output:
C:\Program Files\Java\jdk1.6.0_24\bin>javac RmiClient.java
C:\Program Files\Java\jdk1.6.0_24\bin>java RmiClient
Addition: 8.0
Subtraction : 0.0
Multiplication : 15.0
Division : 1.6666666666666667
Modulus : 2.0

*/