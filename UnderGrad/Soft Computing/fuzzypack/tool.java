package fuzzypack;
import java.util.*;
public class tool
{
	public static Array a[],b[],c[];
	static int n;
	static Scanner s=new Scanner(System.in);
	public static void init()
	{
		n=2;
		a=new Array[n];
		b=new Array[n];
		c=new Array[n];

		for(int i=0;i<n;i++)
		{
			a[i]=new Array();
			b[i]=new Array();
			c[i]=new Array();
		}
		a[0].m=0.4;
		a[1].m=0.3;
		b[0].m=0.99;
		b[1].m=0.8;

	}
	public static void display(Array x[])
	{
		for(int i=0;i<n;i++)
		{
			System.out.println(x[i].str+"	"+x[i].m);
		}
		System.out.println();
	}
	public static Array[] intersect(Array x[],Array y[])
	{
		for(int i=0;i<n;i++)
		{
			c[i].str=x[i].str;
			c[i].m=Math.min(x[i].m,y[i].m);
		}
		//display(c);
		return c;
	}
	public static Array[] union(Array x[],Array y[])
	{
		for(int i=0;i<n;i++)
		{
			c[i].str=x[i].str;
			c[i].m=Math.max(x[i].m,y[i].m);
			System.out.println("x="+x[i].m+" y="+y[i].m+" c="+c[i].m);
		}
		//display(c);
		return c;
	}
	public static Array[] complement(Array x[])
	{
		for(int i=0;i<n;i++)
		{
			c[i].str=x[i].str;
			c[i].m=1-x[i].m;
		}
		//display(c);
		return c;
	}
	public static void input()
	{
		System.out.println("Enter no of elements in Set A and B");
		n=s.nextInt();
		init();
		System.out.println("SET A");
		for(int i=0;i<n;i++)
		{
			System.out.println("Enter class name of element "+(i+1));
			a[i].str=s.next();
			System.out.println("Enter membership of element "+(i+1));
			a[i].m=s.nextDouble();
		}
		System.out.println("SET B");
		for(int i=0;i<n;i++)
		{
			System.out.println("Enter class name of element "+(i+1));
			b[i].str=s.next();
			System.out.println("Enter membership of element "+(i+1));
			b[i].m=s.nextDouble();
		}
	}
}
