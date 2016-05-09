import fuzzypack.*;
import java.util.*;
class demorgan extends tool
{
	public static void main(String str[])
	{
		Scanner s=new Scanner(System.in);
		init();
		int op;
		do
		{
			System.out.println("MENU\n1:Complement of Union\n2:Complement of Intersection\nChoose your option");
			op=s.nextInt();
			switch(op)
			{
				case 1:	display(complement(union(a,b)));
						System.out.println("asas");
						display(intersect(complement(b),complement(a)));
						System.out.println("asas");
						break;
				case 2:	display(complement(intersect(a,b)));
						System.out.println("asas");
						System.out.println("111");
						display(complement(b));
						display(complement(a));
						Array d[]=complement(b);
						Array e[]=complement(a);

						display(d);
						display(e);
						display(complement(b));
						display(complement(a));
						System.out.println("111");
						display(union(complement(b),complement(a)));
						System.out.println("asas");
						break;
				case 3:
						break;
			}
		}while(op!=3);
	}
}