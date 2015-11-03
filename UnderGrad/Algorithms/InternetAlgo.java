
/*
SURAJ POOJARY C22_109
AIM:Naive String Matching Algorithm
*/
import java.io.*;

class InternetAlgo
{
	public static void main(String args[])
	throws IOException
	{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Enter 1st string: ");
		String s1=br.readLine();
		System.out.print("\nEnter String to be found: ");
		String s2=br.readLine();

		char t[]=s1.toCharArray();
		char p[]=s2.toCharArray();

		int n=t.length,m=p.length;
		int found=0,s=0,i=0;
		boolean flag=false;
		for(s=0;s<=n-m+1;s++)
		{
			for(i=0;i<m;i++)
			{
				if(t[s+i]==p[i])
				{
					flag=true;
					//count++;
				}
				else
				{
					flag=false;
					break;
				}
			}
			if(flag==true)
			{
				found=s;
				break;
			}
		}
		if(flag==true)
			System.out.println("\nFound at bit position: "+(found+1));
		else
			System.out.println("\nNot found");
	}
}

/*
OUTPUT:
Enter 1st string: 12345678910

Enter String to be found: 567

Found at bit position: 5

Press any key to continue . . .
*/