import java.util.*;
import java.io.*;
class encrypt
{
	String L1,R1,L2,R2;
	String L3,R3;
	String key="1A4D3E7B6C4A";
	String expand="";

	String R1_bin="",key_bin="";
	int s_box1[][]=new int[4][16];

	void readFile(String fname)throws IOException
	{
		FileInputStream in=null;
		try
		{
			File f=new File(fname);
			in=new FileInputStream(f);
		}
		catch(Exception e)
		{
			System.out.println("ERROR READING FILE");
			System.exit(1);
		}
		BufferedReader br=new BufferedReader(new InputStreamReader(in));
		String input="";
		for(int i=0;i<4;i++)
		{
			input=br.readLine();
			StringTokenizer line=new StringTokenizer(input);
			for(int j=0;j<16;j++)
				s_box1[i][j]=Integer.parseInt(line.nextToken());
		}
	}

	String to_bin(String str)
	{
		String s="";
		for(int i=0;i<str.length();i++)
			s+=buffer4(Integer.toBinaryString(Integer.parseInt(Character.toString(str.charAt(i)),16)));
		return s;
	}

	String bin2hex(String s)
	{
		String str="";
		for(int i=0;i<s.length();)
		{
			str+=Integer.toHexString(Integer.parseInt(s.substring(i,i+4),2));
			i=i+4;
		}
		return str;
	}

	String buffer4(String s)   //pads binary strings to make them of length 4
	{
		while(s.length()!=4)
			s="0"+s;
		//s+=" ";
		return s;
	}

	void disp(String s,int gap)   //displays a string with space after every "gap" positions
	{
		for(int i=1;i<=s.length();)
		{
			System.out.print(s.substring(i-1,i-1+gap)+" ");
			i+=gap;
		}
		System.out.println();
	}

	String xor(String s1,String s2)
	{
		String s="";
		for(int i=0;i<s1.length();i++)
		{
			s+=s1.charAt(i)^s2.charAt(i);
		}
		return s;
	}

	void expand()    //expands the 32 bit string into 48 bit string
	{
		R1_bin=to_bin(R1);
		System.out.println("\nINSIDE DES FUNCTION \n\nOriginal 32 bit String");
		disp(R1_bin,4);

		expand=R1_bin.substring(R1_bin.length()-1);  //last bit
		for(int i=1;i<=R1_bin.length();i++)   //i=1 to simplify mod operation
		{
			expand+=Character.toString(R1_bin.charAt(i-1));   		//actually i if i starts from 0
			if(i%4==0 && i!=R1_bin.length())
			{
				expand+=Character.toString(R1_bin.charAt(i));  	//actually (i+1) if i starts from 0
				expand+=Character.toString(R1_bin.charAt(i-1));  	//actually i if i starts from 0
			}
		}
		expand+=Character.toString(R1_bin.charAt(0));
		System.out.println("\nExpanded 48 bit String");
		disp(expand,6);
	}

	String S_box()throws IOException
	{
		String box_res="";
		key_bin=to_bin(key);
		String xor_res=xor(expand,key_bin);
		System.out.println("\nEx-Ored result of 48 bit key and expanded 48 bit String");
		disp(xor_res,6);

		readFile("s_box1.txt");

		for(int i=0;i<xor_res.length();)
		{
			String s=xor_res.substring(i,i+6);
			int a=Integer.parseInt(Character.toString(s.charAt(0))+Character.toString(s.charAt(5)),2);
			int b=Integer.parseInt(s.substring(1,5),2);

			box_res+=buffer4(Integer.toBinaryString(s_box1[a][b]));
			i=i+6;
		}
		System.out.println("\nS-Box1 Result i.e Result Of The DES Function");
		disp(box_res,4);
		return box_res;
	}

	void round1(String input)throws IOException
	{
		L1=input.substring(0,8);   //8 nibbles = 32 bits
		R1=input.substring(8);

		System.out.println("\nL1  :  "+L1);
		System.out.println("R1  :  "+R1);

		expand();
		String temp=S_box();

		temp=xor(temp,to_bin(L1));

		R2=bin2hex(temp);
		L2=R1;

		System.out.println("\nL2  :  "+L2);
		System.out.println("R2  :  "+R2);
	}
	void decrypt()throws IOException
	{
		System.out.println("DECRYPTION");
		System.out.println("Decrypted Text : "+L2+R2);
		R3=L2;
		R1=L2;
		expand();
		String temp=S_box();

		temp=xor(temp,to_bin(R2));
		L3=bin2hex(temp);

		System.out.println("\nL3  :  "+L3);
		System.out.println("R3  :  "+R3);
	}
}
class DES
{
	public static void main(String str[])throws IOException
	{
		Scanner s=new Scanner(System.in);
		encrypt e=new encrypt();
		System.out.println("Enter plain text");
		String input=s.next();
		e.round1(input);
		e.decrypt();
	}
}

/*
OUTPUT
Enter plain text
C1DE231A1A2B3C4D

L1  :  C1DE231A
R1  :  1A2B3C4D

INSIDE DES FUNCTION

Original 32 bit String
0001 1010 0010 1011 0011 1100 0100 1101

Expanded 48 bit String
100011 110100 000101 010110 100111 111000 001001 011010

Ex-Ored result of 48 bit key and expanded 48 bit String
100101 010000 110001 101000 111001 001110 111000 010000

S-Box1 Result i.e Result Of The DES Function
1000 0011 0101 1101 1010 1000 0011 0011

L2  :  1A2B3C4D
R2  :  42838b29

DECRYPTION
Decrypted Text : 1A2B3C4D42838b29

INSIDE DES FUNCTION

Original 32 bit String
0001 1010 0010 1011 0011 1100 0100 1101

Expanded 48 bit String
100011 110100 000101 010110 100111 111000 001001 011010

Ex-Ored result of 48 bit key and expanded 48 bit String
100101 010000 110001 101000 111001 001110 111000 010000

S-Box1 Result i.e Result Of The DES Function
1000 0011 0101 1101 1010 1000 0011 0011

L3  :  c1de231a
R3  :  1A2B3C4D

*/