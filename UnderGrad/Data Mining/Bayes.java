/*
SURAJ POOJARY    C22_109
SATYA KAUL       C22_095

AIM: IMPLEMENTATION OF BAYESIAN CLASSIFIER
*/
import java.io.*;
import java.util.*;
class element
{
	double p[][];
	element(int n,int m)
	{
		p=new double[n][m];
	}
}
class Classifier
{
	int no_attr,no_rows=0;
	String fileArray[][];   //contents of the input file as a 2-D array
	String values[][];     //stores all possible values for an attribute
	double class_p[];   //array including probabilities of class variables
	int count;  //stores current no_rows in a column of values array
	element a[];

	void readFile(String fname)throws IOException    //readFile
	{
		FileInputStream in=null;
		try
		{
			File f=new File(fname);
			in =new FileInputStream(f);
		}
		catch(Exception e)
		{
			System.out.println("ERROR WHILE READING FILE");
			System.exit(1);
		}
		BufferedReader br=new BufferedReader(new InputStreamReader(in));
		String input="";

		input=br.readLine();
		StringTokenizer line=new StringTokenizer(input);
		no_attr=line.countTokens()-1;
		fileArray=new String[100][no_attr+1];

		for(int i=0;i<=no_attr;i++)
			fileArray[no_rows][i]=line.nextToken();

		while(true)
		{
			input=br.readLine();
			if(input==null)
				break;
			line=new StringTokenizer(input);
			no_rows++;
			for(int i=0;i<=no_attr;i++)
				fileArray[no_rows][i]=line.nextToken();
		}

		//value of no_rows = no_of_rows excluding the attr_name ka row
		getAllvalues();
		createTable();
		newEntry();
	}
	boolean in_values(int col_no,String temp)   //checks if temp is present in the specified col_no
	{
		for(int i=0;i<=count;i++)
			if(values[i][col_no]!=null && values[i][col_no].equals(temp))
				return true;
			else
				continue;
		return false;
	}
	void getAllvalues()   //gets all possible values for an attribute
	{
		values=new String[100][no_attr+1];
		count=0;
		String temp;

		//col major order traversal
		for(int i=0;i<=no_attr;i++)   //columns
		{
			for(int j=1;j<=no_rows;j++) //rows
			{
				temp=fileArray[j][i];
				if(in_values(i,temp))
					continue;
				values[count++][i]=temp;
			}
			count=0;
		}
	}
	int getlen(int col_no)   //returns no of rows in particular col_no in array 'values'
	{
		int i=0;
		while(values[i][col_no]!=null)
			i++;
		return i;
	}
	void display()
	{
		for(int i=0;i<=no_rows;i++)
		{
			for(int j=0;j<=no_attr;j++)
				System.out.print(values[i][j]+"	");
			System.out.println();
		}
	}
	//rem:no_attr is col_index of class variables in values
	int getcount(String temp,int col_no)
	{
		int tc=0;
		for(int i=1;i<=no_rows;i++)
			if(fileArray[i][col_no].equals(temp))
				tc++;
		return tc;
	}
	void createTable()   //creates a table indicating probabilities for all values of an attribute
	{
		int tp=getlen(no_attr);
		class_p=new double[tp];

		for(int i=0;i<tp;i++)   //rows in values for col containing class variables(no_of_class_var)
		{
			for(int j=1;j<=no_rows;j++)//no_rows in fileArray
			{
				if(values[i][no_attr].equals(fileArray[j][no_attr]))
					class_p[i]++;
			}
			class_p[i]/=no_rows;
			System.out.println("P("+values[i][no_attr]+")= "+class_p[i]);
		}

		//table creation
		int tc=0;
		a=new element[no_attr];
		for(int i=0;i<no_attr;i++)  //no of rows in vector
		{
			a[i]=new element(getlen(i),getlen(no_attr));

			for(int j=0;j<getlen(i);j++)   //no_rows in col_no 'i' of array 'values'
			{
				for(int k=0;k<getlen(no_attr);k++) //no of rows in class attribute column of array 'values'
				{
					for(int x=1;x<=no_rows;x++)
					{
						if(values[j][i].equals(fileArray[x][i]) && values[k][no_attr].equals(fileArray[x][no_attr]))
							tc++;
					}
					//tc = count for one attr's value satisfying one class variable
					a[i].p[j][k]=(double)tc/getcount(values[k][no_attr],no_attr);
					tc=0;
				}
			}
		}
	}
	void newEntry()  //takes sample input from user and processes it
	{
		Scanner s=new Scanner(System.in);
		System.out.println("\nEnter new entry");
		String entry[]=new String[no_attr];
		double p_entry[]=new double[getlen(no_attr)];

		String X="X=< ";
		for(int i=0;i<no_attr;i++)
		{
			System.out.println("Enter "+fileArray[0][i]);
			entry[i]=s.next();
			X+=entry[i]+" ";
		}
		X+=">";
		System.out.println("\nThe unseen sample is "+X+"\n");

		//process
		double large=0.0;
		int pos=-1;
		for(int i=0;i<getlen(no_attr);i++)
		{
			double product=1.0;
			for(int j=0;j<no_attr;j++)
			{
				product*=a[j].p[getindex(j,entry[j])][i];
			}
			p_entry[i]=class_p[i]*product;
			System.out.println("P(X|"+values[i][no_attr]+").P("+values[i][no_attr]+")= "+p_entry[i]);
			if(p_entry[i]>large)
			{
				large=p_entry[i];
				pos=i;
			}
		}
		//variable 'pos' is the index(row_no) in class attribute column in array 'values'
		System.out.println("\nTHE DECISION IS "+values[pos][no_attr]);
	}
	int getindex(int col_no,String temp)  //returns index of value of entry attr in values[]
	{
		for(int i=0;i<getlen(col_no);i++)
		{
			if(values[i][col_no].equals(temp))
				return i;
		}
		System.out.println("INVALID ENTRY");
		return 32676;
	}
}
class Bayes
{
	public static void main(String str[])throws IOException
	{
		Scanner s=new Scanner(System.in);
		Classifier c=new Classifier();
		System.out.println("Enter the name of the input file with its extension");
		c.readFile(s.next());
	}
}

/*
OUTPUT   1:

Enter the name of the input file
Bayesinput1.txt

P(SUNBURNED)= 0.375
P(NONE)= 0.625

Enter new entry
Enter HAIR
BROWN
Enter HEIGHT
TALL
Enter WEIGHT
AVERAGE
Enter DUBLIN
NO

The unseen sample is X=< BROWN TALL AVERAGE NO >

P(X|SUNBURNED).P(SUNBURNED)= 0.0
P(X|NONE).P(NONE)= 0.03200000000000001

THE DECISION IS NONE
*/

/*
OUTPUT   2:

Enter the name of the input file
Bayesinput2.txt

P(YES)= 0.5
P(NO)= 0.5

Enter new entry
Enter COLOR
RED
Enter TYPE
SUV
Enter ORIGIN
DOMESTIC

The unseen sample is X=< RED SUV DOMESTIC >

P(X|YES).P(YES)= 0.024
P(X|NO).P(NO)= 0.072

THE DECISION IS NO
*/

/*
OUTPUT   3:

Enter the name of the input file
Bayesinput3.txt

P(SHORT)= 0.26666666666666666
P(TALL)= 0.2
P(MEDIUM)= 0.5333333333333333

Enter new entry
Enter GENDER
M
Enter HEIGHT
[1.91,2.0]

The unseen sample is X=< M [1.91,2.0] >

P(X|SHORT).P(SHORT)= 0.0
P(X|TALL).P(TALL)= 0.06666666666666667
P(X|MEDIUM).P(MEDIUM)= 0.016666666666666666

THE DECISION IS TALL
*/