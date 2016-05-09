import java.rmi.*;
import java.rmi.server.*;

public class Calc extends UnicastRemoteObject implements CalcInt
{
	public Calc() throws RemoteException
	{}
	public double add(double a,double b)throws RemoteException
	{
		return (a+b);
	}
	public double sub(double a,double b)throws RemoteException
	{
		return (a-b);
	}
	public double mul(double a,double b)throws RemoteException
	{
		return (a*b);
	}
	public double div(double a,double b)throws RemoteException
	{
		return (a/b);
	}
	public double mod(double a,double b)throws RemoteException
	{
		return (a%b);
	}
}
/*
Output:

C:\Program Files\Java\jdk1.6.0_24\bin>rmi Calc
C:\Program Files\Java\jdk1.6.0_24\bin>start rmiregistry

*/