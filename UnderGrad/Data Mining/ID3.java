import java.io.*;
import java.util.*;
class Suraj
{
	public int no_attr;
	public String attrnames[];
	public Vector maintab[];
}
class RowData
{
	int attr[];
	RowData(int no_attr)
	{
		attr=new int[no_attr];
	}
}
class TreeNode extends Suraj
{
	double entropy;
	Vector data;
	int div_attr,div_val;
	TreeNode children[],parent;
	TreeNode()
	{
		data=new Vector();
	}
	TreeNode root;


	int getSymVal(int attr,String sym)
	{
		int index=maintab[attr].indexOf(sym);
		if(index<0)
		{
			maintab[attr].addElement(sym);
			index=maintab[attr].size()-1;
		}
		return index;
	}
	int [] getAllVal(Vector data,int attr)
	{
		Vector values=new Vector();
		int no_row=data.size();
		for(int i=0;i<no_row;i++)
		{
			RowData row=(RowData)data.elementAt(i);
			String sym=(String)maintab[attr].elementAt(row.attr[attr]);
			int index=values.indexOf(sym);
			if(index<0)
				values.addElement(sym);
		}
		int array[]=new int[values.size()];
		for(int i=0;i<array.length;i++)
		{
			String sym=(String)values.elementAt(i);
			array[i]=maintab[attr].indexOf(sym);
		}
		//values=null;
		return array;
	}
	Vector getSubset(Vector data,int attr,int value)
	{
		Vector subset=new Vector();
		int no_row=data.size();
		for(int i=0;i<no_row;i++)
		{
			RowData row=(RowData)data.elementAt(i);
			if(row.attr[attr]==value)
				subset.addElement(row);
		}
		return subset;
	}
	Vector getSubsetComp(Vector data,Vector oldset)
	{
		Vector subset=new Vector();
		int no_row=data.size();
		for(int i=0;i<no_row;i++)
		{
			RowData row=(RowData)data.elementAt(i);
			if(oldset.indexOf(row)<0)
				subset.addElement(row);
		}
		return subset;
	}
	double entropyCalc(Vector data)
	{
		int no_row=data.size();
		if(no_row==0)
			return 0;
		int class_index=no_attr-1;  //index of class variable
		int no_val=maintab[class_index].size();
		double sum=0.0;
		for(int i=0;i<no_val;i++)
		{
			int count=0;
			for(int j=0;j<no_row;j++)
			{
				RowData row=(RowData)data.elementAt(j);
				if(row.attr[class_index]==i)
					count++;
			}
			double probability=(double)count/no_row;
			if(count>0)
				sum+=-probability*(Math.log(probability)/Math.log(2));
		}
		return sum;
	}
	boolean alreadyDiv(TreeNode node,int attr,int value)
	{
		if(node.children!=null)
			if(node.div_attr==attr && node.div_val==value)
				return true;
		if(node.parent==null)   //for root
			return false;

		return alreadyDiv(node.parent,attr,value);
	}
	void divNode(TreeNode node)
	{
		double best_entropy;
		Boolean selected=false;
		int selected_attr=0,selected_val=0;
		int no_row=node.data.size();
		int no_ip_attr=no_attr-1;
		double init_entropy=best_entropy=node.entropy=entropyCalc(node.data);
		if(node.entropy==0)
			return;

		for(int i=0;i<no_ip_attr;i++)
		{
			int no_val=maintab[i].size();
			for(int j=0;j<no_val;j++)
			{
				if(alreadyDiv(node,i,j))
					continue;
				Vector subset=getSubset(node.data,i,j);
				if(subset.size()==0)
					continue;
				Vector complement=getSubsetComp(node.data,subset);
				double e1=entropyCalc(subset);
				double e2=entropyCalc(complement);
				double entropy=(e1*subset.size()+e2*complement.size())/no_row;

				if(entropy<best_entropy)
				{
					selected=true;
					best_entropy=entropy;
					selected_attr=i;
					selected_val=j;
				}
			}
		}
		if(selected==false)
			return;

		node.div_attr=selected_attr;
		node.div_val=selected_val;
		node.children=new TreeNode[2];
		node.children[0]=new TreeNode();
		node.children[0].parent=node;
		node.children[0].data=getSubset(node.data,selected_attr,selected_val);
		node.children[1]=new TreeNode();
		node.children[1].parent=node;

		for(int i=0;i<no_row;i++)
		{
			RowData row=(RowData)node.data.elementAt(i);
			if(node.children[0].data.indexOf(row)>=0)
				continue;
			node.children[1].data.addElement(row);
		}

		divNode(node.children[0]);
		divNode(node.children[1]);

		//node.data=null;
	}
	void readFile(String fname)throws IOException
	{
		root=new TreeNode();
		FileInputStream in=null;
		try
		{
			File f=new File(fname);
			in=new FileInputStream(f);
		}
		catch(Exception e)
		{
			System.out.println("ERROR WHILE READING FILE");
			System.exit(1);
		}
		BufferedReader br=new BufferedReader(new InputStreamReader(in));
		String input;

		input=br.readLine();
		if(input==null)
		{
			System.out.println("NO ENTRY ON FIRST LINE IN FILE");
			System.exit(1);
		}
		StringTokenizer line=new StringTokenizer(input);
		no_attr=line.countTokens();

		maintab=new Vector[no_attr];
		attrnames=new String[no_attr];
		for(int i=0;i<no_attr;i++)
		{
			maintab[i]=new Vector();
			attrnames[i]=line.nextToken();
		}

		while(true)
		{
			input=br.readLine();
			if(input==null)
				break;
			line=new StringTokenizer(input);

			RowData row=new RowData(no_attr);
			for(int i=0;i<no_attr;i++)
				row.attr[i]=getSymVal(i,line.nextToken());

			root.data.addElement(row);
		}
		br.close();
	}
	void printTree(TreeNode node,String tab)
	{
		int op_attr=no_attr-1;
		//System.out.println(tab);
		if(node.children==null)
		{
			int values[]=getAllVal(node.data,op_attr);
			if(values.length==1)
			{
				System.out.println(tab+"\t"+attrnames[op_attr]+"=\""+maintab[op_attr].elementAt(values[0])+"\";");
				return;
			}
			System.out.println(tab+"\t"+attrnames[op_attr]+"={");
			for(int i=0;i<values.length;i++)
			{
				System.out.print("\""+maintab[op_attr].elementAt(values[i])+"\"");
				if(i!=values.length-1)
					System.out.print(",");
			}
			System.out.println("};");
			return;
		}

		System.out.println(tab+"if("+attrnames[node.div_attr]+"==\""+maintab[node.div_attr].elementAt(node.div_val)+"\")	{");

		printTree(node.children[0],tab+"\t");
		System.out.println(tab+"} else { ");
		printTree(node.children[1],tab+"\t");
		System.out.println(tab+"}");
	}
	void createTree()
	{
		divNode(root);
		printTree(root,"");
	}
}
class ID3
{
	public static void main(String str[])throws IOException
	{
		TreeNode s=new TreeNode();
		s.readFile("ID3inputfile2.txt");
		s.createTree();
	}
}

/*
if(AGE=="31...40")      {

                BUYS="yes";
} else {

        if(STUDENT=="no")       {

                if(AGE=="<=30") {

                                BUYS="no";
                } else {

                        if(CREDIT_RATING=="fair")       {

                                        BUYS="yes";
                        } else {

                                        BUYS="no";
                        }
                }
        } else {

                if(CREDIT_RATING=="fair")       {

                                BUYS="yes";
                } else {

                        if(AGE=="<=30") {

                                        BUYS="yes";
                        } else {

                                        BUYS="no";
                        }
                }
        }
}
*/