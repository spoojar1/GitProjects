import java.util.*;
import java.io.*;
class encryption
{
	BufferedReader in;
	BufferedReader fr,fi;
	BufferedWriter fw;
	int k1,k2,l;
	String plain,cipher;
	int rand[];
	void input()throws IOException
	{
		File f1=new File("input.txt");
		File f2=new File("output.txt");
		in=new BufferedReader(new InputStreamReader(System.in));
		fr=new BufferedReader(new FileReader(f1));
		fi=new BufferedReader(new FileReader(f2));
		fw=new BufferedWriter(new FileWriter(f2));
	}
	int gen_rand()
	{
		return (int)Math.round(Math.random()*100);
	}
	void encrypt()throws IOException
	{
		System.out.println("ENCRYPTION\nEncrypted Text");
		plain=fr.readLine();
		l=plain.length();
		rand=new int[l];
		for(int i=0;i<l;i++)
		{
			rand[i]=gen_rand();
			char c=(char)((((int)plain.charAt(i)-65+rand[i])%26)+65);
			System.out.print(c);
			fw.write(c);
		}
		fw.close();
	}
	int correct(int x)
	{
		while(x<0)
		{
			x+=26;
		}
		return x;
	}
	void decrypt()throws IOException
	{
		System.out.println("\nDECRYPTION");
		System.out.println("\nDecrypted Text");
		cipher=fi.readLine();
		l=cipher.length();
		for(int i=0;i<l;i++)
		{
			char c=(char)(correct(((int)cipher.charAt(i)-65)-rand[i])+65);
			System.out.print(c);
		}
		System.out.println();
		fw.close();
	}
	void keydisplay()
	{
		System.out.println("RANDOM KEY GENERATED");
		for(int i=0;i<l;i++)
		{
			System.out.println(rand[i]);
		}
	}
}
class VCipher
{
	public static void main(String str[])throws IOException
	{
		encryption e=new encryption();
		e.input();
		e.encrypt();
		System.out.println();
		//e.keydisplay();
		e.decrypt();
	}
}
/*
OUTPUT

ENCRYPTION
Encrypted Text
KGWEJHETPXSRAAYBGLBZIKYJJOCLXEXRDG

DECRYPTION

Decrypted Text
ILIKETOWRITESENTENCESWITHOUTSPACES

*/