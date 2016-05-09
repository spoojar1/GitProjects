import java.rmi.*;

public interface CalcInt extends Remote
{
	double add(double a,double b) throws RemoteException;
	double sub(double a,double b) throws RemoteException;
	double mul(double a,double b) throws RemoteException;
	double div(double a,double b) throws RemoteException;
	double mod(double a,double b) throws RemoteException;
}
