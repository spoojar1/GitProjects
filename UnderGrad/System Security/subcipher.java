import java.io.*;
import java.util.*;

class encryption
{
	BufferedReader in;
	BufferedReader fr,fi;
	BufferedWriter fw;
	int k;
	void input()throws Exception
	{
		File f1=new File("input.txt");
		File f2=new File("output.txt");
		in=new BufferedReader(new InputStreamReader(System.in));
		fr=new BufferedReader(new FileReader(f1));
		fi=new BufferedReader(new FileReader(f2));
		fw=new BufferedWriter(new FileWriter(f2));

		System.out.println("ENCRYPTION");
		System.out.println("Enter the key K");
		k=Integer.parseInt(in.readLine());
		encrypt(k);
	}

	void encrypt(int k)throws Exception
	{
		String a1=fr.readLine();
		System.out.println("\nText before Encryption :\n"+a1);
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
		System.out.println("\n\nDECRYPTION");
		System.out.println("Enter the key K");
		int j=Integer.parseInt(in.readLine());
		if(j==k)
		{
			System.out.println("\nOriginal text : ");
			String b1=fi.readLine();
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
class subcipher
{
	public static void main(String args[])throws Exception
	{
		encryption e=new encryption();
		e.input();

	}
}
/*
ENCRYPTION

Enter the key K
2

Text before Encryption :
abcdefghi this is the ceasar's cipher.

Text after Encryption :
cdefghijk"vjku"ku"vjg"egcuct)u"ekrjgt0

DECRYPTION

Enter the key K
2

Original text :
abcdefghi this is the ceasar's cipher.

*/