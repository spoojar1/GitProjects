import java.util.*;

class Com
{
	double w[][],x[][],C;
	int n,m;
	Com()
	{
		Scanner src=new Scanner(System.in);
		System.out.println("Enter no. of input patterns:");
		n=src.nextInt();
		System.out.println("Enter no. of elements:");
		m=src.nextInt();
		w=new double[n][m];
		x=new double[n][m];
		System.out.println("Enter weight matrix:");
		for(int i=0;i<n;i++)
		{
			System.out.println("For input: "+(i+1));
			for(int j=0;j<m;j++)
			w[i][j]=src.nextDouble();
		}
		System.out.println("Enter input matrix:");
		for(int i=0;i<n;i++)
		{
			System.out.println("For input: "+(i+1));
			for(int j=0;j<m;j++)
			x[i][j]=src.nextDouble();
		}
		System.out.println("Enter value of learning constant:");
		C=src.nextDouble();
		System.out.println("Enter no of cycles:");
		int n=src.nextInt();
		for(int i=0;i<n;i++)
		{
			System.out.println();
			compute();
		}
	}
	void compute()
	{
		double w1[];
		for(int i=0;i<n;i++)
		{
			w1=multiply(w,x[i]);
			System.out.println("On Multiplication:\t"+Arrays.toString(w1));
			System.out.println();

			double max=-9999;int pos=-1;
			for(int j=0;j<n;j++)
			if(w1[j]>max)
			{
				max=w1[j];
				pos=j;
			}
			System.out.println("Max:\t"+max+"\tat pos:\t"+pos);
			System.out.println();
			for(int j=0;j<m;j++)
			w[pos][j]=w[pos][j]+C*(x[i][j]-w[pos][j]);

			System.out.println("New w("+pos+"):\t"+Arrays.toString(w[pos]));
			System.out.println();
		}
	}
	double[] multiply(double[][] a,double[] x)
	{
		double w1[]=new double[n];
		double sum=0;
		for(int i=0;i<n;i++)
		for(int j=0;j<m;j++)
		w1[i]=w1[i]+a[i][j]*x[j];
		return w1;
	}
}
class Compete
{
	public static void main(String args[])
	{
		Com obj=new Com();
		Scanner src=new Scanner(System.in);
		int n=src.nextInt();
	}
}
/* OUTPUT:
Enter no. of input patterns:
3
Enter no. of elements:
2
Enter weight matrix:
For input: 1
0 -1
For input: 2
-0.894427 0.447216
For input: 3
-0.447216 0.894427
Enter input matrix:
For input: 1
-1 0
For input: 2
0 1
For input: 3
0.707106 0.707106
Enter value of learning constant:
0.5
Enter no of cycles:
2

On Multiplication:      [0.0, 0.894427, 0.447216]
Max:    0.894427        at pos: 1
New w(1):       [-0.9472134999999999, 0.223608]

On Multiplication:      [-1.0, 0.223608, 0.894427]
Max:    0.894427        at pos: 2
New w(2):       [-0.223608, 0.9472134999999999]

On Multiplication:      [-0.707106, -0.5116657906829999, 0.5116657906829999]
Max:    0.5116657906829999      at pos: 2
New w(2):       [0.24174900000000002, 0.82715975]


On Multiplication:      [0.0, 0.9472134999999999, -0.24174900000000002]
Max:    0.9472134999999999      at pos: 1
New w(1):       [-0.97360675, 0.111804]

On Multiplication:      [-1.0, 0.111804, 0.82715975]
Max:    0.82715975      at pos: 2
New w(2):       [0.12087450000000001, 0.9135798749999999]

On Multiplication:      [-0.707106, -0.6093858953415, 0.7314688952887499]
Max:    0.7314688952887499      at pos: 2
New w(2):       [0.41399025, 0.8103429375]
*/