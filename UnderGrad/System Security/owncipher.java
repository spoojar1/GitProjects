import java.io.*;
import java.util.*;

class encryption
{
	BufferedReader in;
	BufferedReader fr,fi;
	BufferedWriter fw;
	int k1,k2,k;
	void input()throws Exception
	{
		File f1=new File("input.txt");
		File f2=new File("output.txt");
		in=new BufferedReader(new InputStreamReader(System.in));
		fr=new BufferedReader(new FileReader(f1));
		fi=new BufferedReader(new FileReader(f2));
		fw=new BufferedWriter(new FileWriter(f2));

		System.out.println("ENCRYPTION");
		System.out.println("Enter the two part keys k1 and k2");
		k1=Integer.parseInt(in.readLine());
		k2=Integer.parseInt(in.readLine());
		encrypt(k1,k2);
	}

	int GCD(int a, int b)
	{
	   if (b==0) return a;
	   return GCD(b,a%b);
	}

	void encrypt(int k1,int k2)throws Exception
	{

		String a1=fr.readLine();
		System.out.println("\nText before Encryption :\n"+a1);

		k=GCD(k1,k2);

		System.out.println("\nText after Encryption : ");
		for(int i=0;i<a1.length();i++)
		{
			fw.write((char)(a1.charAt(i)+k)%256);
			System.out.print((char)((a1.charAt(i)+k)%256));
		}
		fw.close();
		decrypt();

	}
	void decrypt()throws Exception
	{
		System.out.println();
		System.out.println("\nDECRYPTION");
		System.out.println("Enter the 2 keys ");
		int j1=Integer.parseInt(in.readLine());
		int j2=Integer.parseInt(in.readLine());
		if((j1==k1 && j2==k2) || (j1==k2 && j2==k1))
		{
			String b1=fi.readLine();
			System.out.println("\nOriginal Text :");
			for(int i=0;i<b1.length();i++)
			{
				System.out.print((char)((b1.charAt(i)-k)%256));
			}
		}
		else
		System.out.println("\nThe entered Key is wrong");
		System.out.println();

	}


}
class owncipher
{
	public static void main(String args[])throws Exception
	{
		encryption e=new encryption();
		e.input();

	}
}
/*
ENCRYPTION
Enter the two part keys k1 and k2
2
10

Text before Encryption :
OUR OWN LITTLE CIPHER !!

Text after Encryption :
QWT"QYP"NKVVNG"EKRJGT"##

DECRYPTION
Enter the 2 keys
10
2

Original Text :
OUR OWN LITTLE CIPHER !!

-------------------------------------

ENCRYPTION
Enter the two part keys k1 and k2
2
8

Text before Encryption :
OUR OWN LITTLE CIPHER !!

Text after Encryption :
QWT"QYP"NKVVNG"EKRJGT"##

DECRYPTION
Enter the 2 keys
2
4

The entered Key is wrong

*/