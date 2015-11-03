/*
Suraj Poojary C22_109
Aim:Implementation of Fractional Knapsack(Greedy)
*/
import java.io.*;
class Knapsack
{
public static void main(String args[])throws IOException
	{
	BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	System.out.println("Enter the no. of baskets");
	int num=Integer.parseInt(br.readLine());
	String basket[]=new String[num];
	float weight[]=new float[num];
	float profit[]=new float[num];
	float pbyw[]=new float[num];

		for(int i=0;i<num;i++)
			{System.out.println("Enter the name,weight,profit");
			basket[i]=br.readLine();
			weight[i]=Float.parseFloat(br.readLine());
			profit[i]=Float.parseFloat(br.readLine());
			pbyw[i]=profit[i]/weight[i];
			}
System.out.println("Enter Knapsize");
float Knapsize=Float.parseFloat(br.readLine());
	for(int i=0;i<num;i++)
	{
		for(int j=i+1;j<num;j++)
		{
			if(pbyw[j]>pbyw[i])
			{

				float temp=pbyw[i];      //pbyw exchanged
				pbyw[i]=pbyw[j];
				pbyw[j]=temp;

				temp=weight[i];           //weight exchanged
				weight[i]=weight[j];
				weight[j]=temp;

				temp=profit[i];           //profit exchanged
				profit[i]=profit[j];
				profit[j]=temp;

				String temp1=basket[i];   //name exchanged
				basket[i]=basket[j];
				basket[j]=temp1;
			}
		}
	}    ///basket with lowest pby w ratio goes to the bottom of pbyw array
System.out.println("The KNAPSIZE is"+" "+Knapsize);
System.out.println("Name"+"	"+"Weight"+"	"+"profit"+"	"+"profit/weight");
for(int i=0;i<num;i++)
	{
	System.out.print(basket[i]+"	"+weight[i]+"	"+profit[i]+"	"+pbyw[i]);
	System.out.println();
	}
float factor[]=new float[num];
float earned=0;
float value=0;

int i=0;
System.out.println("the selected Basket"+"	"+"Fraction taken"+"	"+"capacityleft"+"	"+"profit");
for( i=0;i<num;i++)
{
	if(weight[i]>Knapsize)
		break;
	else
	{
	factor[i]=1;
	Knapsize=Knapsize-weight[i];
	value=factor[i]*weight[i]*pbyw[i];  //profit
	earned+=value;
	System.out.print(basket[i]+"			"+factor[i]+"		   "+Knapsize+"	         "+value);
	System.out.println();
	}
	//System.out.println("asdasd");
}
	if(i<num)
		{
		factor[i]=Knapsize/weight[i];
		Knapsize=0;
 		value=factor[i]*weight[i]*pbyw[i];
		earned+=value;
		System.out.print(basket[i]+"			"+factor[i]+"		   "+Knapsize+"	         "+value);
		System.out.println();
		}
System.out.println("total profit is"+"	"+earned);
	}
}

/*OUTPUT
Enter the no. of baskets
3
Enter the name,weight,profit
p1
18
25
Enter the name,weight,profit
p2
15
24
Enter the name,weight,profit
p3
10
15
Enter Knapsize
20
The KNAPSIZE is 20.0
Name    Weight  profit  profit/weight
p2      15.0    24.0    1.6
p3      10.0    15.0    1.5
p1      18.0    25.0    1.3888888888888888
the selected Basket     Fraction taken  capacityleft    profit
p2                      1.0             5.0     24.0
p3                      0.5             5.0     7.5
total profit is 31.5
Press any key to continue . . .
*/