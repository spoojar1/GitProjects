/*
DEVANSHU SAVE
C11 084027
*/
import java.io.*;

class FController
{
	public static void main(String args[])throws IOException
	{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int[] x,y,z;
		System.out.println("Enter number of descriptors for Dirtiness of Load (X)");
		final int xn=Integer.parseInt(br.readLine());
		System.out.println("Enter number of descriptors for Weight of Laundry (Y)");
		final int yn=Integer.parseInt(br.readLine());
		System.out.println("Enter number of descriptors for Amount of Detergent (Z)");
		final int zn=Integer.parseInt(br.readLine());
		x=new int[xn];
		y=new int[yn];
		z=new int[zn];
		int prime[]={2,3,5,7,11,13,17,19,23,29,31};
		for(int i=0;i<xn;i++)
			x[i]=prime[i];
		for(int i=0;i<yn;i++)
			y[i]=prime[i];

		int max=x[xn-1]+y[yn-1];
		int val,temp=0;
		z[0]=4;

		for(int i=1;i<zn;i++)
			z[i]=z[i-1]+(max/zn);
////////Creation of fuzzy table

		int fuz[][]=new int[xn][yn];
		for(int i=0;i<xn;i++)
		{
			for(int j=0;j<yn;j++)
			{
				val=x[i]+y[j];
				for(int k=0;k<zn;k++)
				{
					if(val==z[0])
					{
						temp=0;
						break;
					}

					if(val<z[k])
					{
						temp=k;
						break;
					}

					if(val>=z[zn-2])
					{
						temp=zn-1;
						break;
					}
				}
				fuz[i][j]=temp+1;
			}
		}
///// Display fuzzy table
		System.out.print("\t");
		for(int i=0;i<yn;i++)
			System.out.print("Y "+(i+1)+"\t");
		System.out.println();
		for(int i=0;i<xn;i++)
		{
			System.out.print("X "+(i+1)+"\t" );
			for(int j=0;j<yn;j++)
				System.out.print(fuz[i][j]+"\t");
			System.out.println("\n");
		}

		System.out.println("Enter degree of Dirtiness of Load (X) in percentage");
		final double xamt=Double.parseDouble(br.readLine());
		System.out.println("Enter Weight of Laundry (Y) max=8kg");
		final double yamt=Double.parseDouble(br.readLine());
		double zamt;
		double temp1=100/xn,temp2=temp1;
		int ii=0,jj=0;
		for(int i=0;i<xn;i++)
		{
			ii=i;
			if(temp2>=xamt)
				break;
			else
				temp2=temp1*(i+2);
		}
		//System.out.println("iiiiiiiiiiiii"+ii);
		temp1=100/yn;
		temp2=temp1;
		for(int i=0;i<yn;i++)
		{
			jj=i;
			if(temp2>=(yamt*12.5))
				break;
			else
				temp2=temp1*(i+2);
		}
		//System.out.println("jjjjjjjjjjjj"+jj);
		//System.out.println("fuz[][]"+fuz[ii][jj]);
		zamt=fuz[ii][jj]*(100/zn);
		if(fuz[ii][jj]==zn)
		zamt=100;
		System.out.println("Amount of detergent required is "+(int)zamt+" grams.");
	}
}

/*
OUTPUT
Enter number of descriptors for Dirtiness of Load (X)
5
Enter number of descriptors for Weight of Laundry (Y)
5
Enter number of descriptors for Amount of Detergent (Z)
9
        Y 1     Y 2     Y 3     Y 4     Y 5
X 1     1       2       3       4       6

X 2     2       3       4       5       7

X 3     3       4       5       6       8

X 4     4       5       6       7       9

X 5     6       7       8       9       9

Enter degree of Dirtiness of Load (X) in percentage
55
Enter Weight of Laundry (Y) max=8kg
6
Amount of detergent required is 66 grams.
*/