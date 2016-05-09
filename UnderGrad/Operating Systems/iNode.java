/* Sumesh Lund C22 106
   Implementation of UNIX INode   */

import java.io.*;

class iNode
{

	public static void main(String args[])throws IOException
	{
		double add;
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

		System.out.println(" Enter The Location of the address");
		add=Double.parseDouble(br.readLine());
		switch(address(add))
		{
			case 0:
				System.out.println("Incorrect Value");
				break;
			case 1:
			    direct(add);
			    break;
			case 2:
				singleIndirect(add);
				break;
			case 3:
			    doubleIndirect(add);
			    break;
			case 4:
				tripleIndirect(add);
				break;
		}
}

	public static int address(double add)
	{

		if(add>=0&&add<10240)
			return 1;
		if(add>=10240&&add<272384)
			return 2;
		if(add>=272384&&add<67381248)
			return 3;
		if(add>=67381248&&add<(1.7*Math.pow(10,10)))
			return 4;
		return 0;
	}

	public static void direct(double add)
	{
		int dblock,offset;
		dblock =(int) add/1024;
		offset =(int) add-(dblock*1024);
		System.out.println(" OFFSET = "+offset+" DIRECT BLOCK NO. = "+dblock);
	}

	public static void singleIndirect(double add)
	{
		double sblock;
		sblock = add-10240;
		direct(sblock);
	}

	public static void doubleIndirect(double add)
	{
		double chor;
		int dblock,sblock;
		chor=add-272384;
		//System.out.println(chor);
		dblock=(int)chor/(256*1024);
		//System.out.println(dblock);
		sblock=(int)chor-(dblock*1024*256);
		//System.out.println(sblock);
		direct(sblock);
	}

	public static void tripleIndirect(double add)
	{
		double temp,temp2,temp3;
		double india;
		int tblock,dblock;

		temp = add-67381248;
		tblock=(int)(temp/(256*256*1024));
		india=tblock*256*256*1024;
		temp2=temp-india;
		dblock=(int)temp2/(256*1024);
		temp3=temp2-(dblock*256*1024);
		direct(temp3);
	}
}

/* OUTPUT
 Enter The Location of the address
1103561097
 OFFSET = 393 DIRECT BLOCK NO. = 182
*/