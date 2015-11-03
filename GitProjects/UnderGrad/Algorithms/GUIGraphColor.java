// Demonstrate Graph Coloring using GUI.

import java.awt.*;
import java.awt.event.*;
import java.applet.*;




public class GUIGraphColor extends Applet implements ActionListener
{
	TextField Number, Edge, From, To;
	int g[][],count=0;
	int color_tab[],n,m,edges;
	char n_no,e_no,to_no[],from_no[],x,y;
	boolean flag=true;
	public void init()
	{
		setBackground(Color.lightGray);

		g=new int[10][10];
		to_no= new char[10];
		from_no= new char[10];
		color_tab=new int[10];

		Label Numberp = new Label("Enter  No. of  Nodes : ", Label.RIGHT);
		Label Edgep = new Label("Enter  No. of  Edges : ", Label.RIGHT);
		Label Fromp = new Label("From Edge : ", Label.RIGHT);
		Label Top = new Label("To Edge : ", Label.RIGHT);


		Number = new TextField(1);
		Edge = new TextField(1);
		From = new TextField(1);
		To = new TextField(1);


		add(Numberp);
		add(Number);
		add(Edgep);
		add(Edge);
		add(Fromp);
		add(From);
		add(Top);
		add(To);


		// register to receive action events
		Number.addActionListener(this);
		Edge.addActionListener(this);
		From.addActionListener(this);
		To.addActionListener(this);
	}

	// User pressed Enter.
	public void actionPerformed(ActionEvent ae)
	{
		Graphics g=getGraphics();
		n_no=Number.getText().charAt(0);
		e_no=Edge.getText().charAt(0);
		n=n_no-48;
		edges=e_no-48;
		m=n-1;
		x=to_no[count]=To.getText().charAt(0);
		y=from_no[count]=From.getText().charAt(0);

		// Will draw a Line everytime  enter is pressed.
		g.drawLine(xmidpt(x-48), ymidpt(x-48), xmidpt(y-48),ymidpt(y-48));


		creategraph((int)to_no[count]-48,(int)from_no[count]-48);


		if(count==edges)
		{
			flag=false;
			Gr_coloring(1);

		}


		paint(g);
	}

    //Double Buffering.
	public void update(Graphics g)
	{
		Image buffer=createImage(400,400);
		paint(buffer.getGraphics());
		g.drawImage(buffer,0,0,this);
	}

	public void creategraph(int to_no,int from_no)
	{
		g[to_no][from_no]=g[from_no][to_no]=1;
		count ++;

	}

	public void paint(Graphics g)
	{


		g.drawString("No. of  Nodes : " + Number.getText(), 6, 80);

		g.drawString("No. of  Edges : " + Edge.getText(), 6, 100);

        for(int i=0;i<count;i++)
		{
			g.drawString("From  Edge : " + (from_no[i]-48), 6, 120+(20*i));
			g.drawString("To  Edge : " + (to_no[i]-48), 150, 120+(20*i));
		}

		// to check the values of color tab [].
		int max=0;

				if(count==edges)
				{
					max=sort(color_tab);
					String msg=" ";
					switch(max)
					{
						case 1:
						msg=" ONE ";
						break;

						case 2:
						msg=" TWO ";
						break;

						case 3:
						msg=" THREE ";
						break;

						case 4:
						msg=" FOUR ";
						break;

						case 5:
						msg=" FIVE ";
						break;

						case 6:
						msg=" SIX ";
						break;

						case 7:
						msg=" SEVEN ";
						break;

						case 8:
						msg=" EIGHT ";
						break;

						case 9:
						msg=" NINE ";
						break;
					}


					g.drawString(" NUMBER OF COLORS USED IN THE GRAPH = "+msg,400,200);
				}





		showStatus(" Color1= "+color_tab[1]+"  Color2= "+color_tab[2]+"  Color3= "+color_tab[3]+"  Color4= "+color_tab[4]+"  Count= "+ count+" Max= "+max + edges);




			for(int i=1;i<=(n_no-48);i++)
			{

				switch(i)
				{
					case 1:
					switch(color_tab[i])
					{

						case 1:
						if(flag==false)
							g.setColor(Color.BLUE);
						else
							g.setColor(Color.BLACK);
						break;

						case 2:

						if(flag==false)
							g.setColor(Color.RED);
						else
							g.setColor(Color.BLACK);
						break;

						case 3:
						if(flag==false)
							g.setColor(Color.GREEN);
						else
							g.setColor(Color.BLACK);
						break;

						case 4:
						if(flag==false)
							g.setColor(Color.YELLOW);
						else
							g.setColor(Color.BLACK);
						break;

						case 5:
						if(flag==false)
							g.setColor(Color.BLACK);
						else
							g.setColor(Color.BLACK);
						break;
					}


					g.fillOval(900, 250, 30, 30);
					g.setColor(Color.BLACK);
					g.drawString("1",915,240);
					break;

					case 2:
					switch(color_tab[i])
					{

						case 1:
						if(flag==false)
							g.setColor(Color.BLUE);
						else
							g.setColor(Color.BLACK);
						break;

						case 2:

						if(flag==false)
							g.setColor(Color.RED);
						else
							g.setColor(Color.BLACK);
						break;

						case 3:
						if(flag==false)
							g.setColor(Color.GREEN);
						else
							g.setColor(Color.BLACK);
						break;

						case 4:
						if(flag==false)
							g.setColor(Color.YELLOW);
						else
							g.setColor(Color.BLACK);
						break;

						case 5:
						if(flag==false)
							g.setColor(Color.BLACK);
						else
							g.setColor(Color.BLACK);
						break;
					}


					g.fillOval(700, 300, 30, 30);
					g.setColor(Color.BLACK);
					g.drawString("2",715,290);
					break;

					case 3:
					switch(color_tab[i])
					{

						case 1:
						if(flag==false)
							g.setColor(Color.BLUE);
						else
							g.setColor(Color.BLACK);
						break;

						case 2:

						if(flag==false)
							g.setColor(Color.RED);
						else
							g.setColor(Color.BLACK);
						break;

						case 3:
						if(flag==false)
							g.setColor(Color.GREEN);
						else
							g.setColor(Color.BLACK);
						break;

						case 4:
						if(flag==false)
							g.setColor(Color.YELLOW);
						else
							g.setColor(Color.BLACK);
						break;

						case 5:
						if(flag==false)
							g.setColor(Color.BLACK);
						else
							g.setColor(Color.BLACK);
						break;
					}

					g.fillOval(1100, 300, 30, 30);
					g.setColor(Color.BLACK);
					g.drawString("3",1115,290);
					break;

					case 4:
					switch(color_tab[i])
					{

						case 1:
						if(flag==false)
							g.setColor(Color.BLUE);
						else
							g.setColor(Color.BLACK);
						break;

						case 2:

						if(flag==false)
							g.setColor(Color.RED);
						else
							g.setColor(Color.BLACK);
						break;

						case 3:
						if(flag==false)
							g.setColor(Color.GREEN);
						else
							g.setColor(Color.BLACK);
						break;

						case 4:
						if(flag==false)
							g.setColor(Color.YELLOW);
						else
							g.setColor(Color.BLACK);
						break;

						case 5:
						if(flag==false)
							g.setColor(Color.BLACK);
						else
							g.setColor(Color.BLACK);
						break;
					}


					g.fillOval(825, 550, 30, 30);
					g.setColor(Color.BLACK);
					g.drawString("4",840,540);
					break;

					case 5:
					switch(color_tab[i])
					{

						case 1:
						if(flag==false)
							g.setColor(Color.BLUE);
						else
							g.setColor(Color.BLACK);
						break;

						case 2:

						if(flag==false)
							g.setColor(Color.RED);
						else
							g.setColor(Color.BLACK);
						break;

						case 3:
						if(flag==false)
							g.setColor(Color.GREEN);
						else
							g.setColor(Color.BLACK);
						break;

						case 4:
						if(flag==false)
							g.setColor(Color.YELLOW);
						else
							g.setColor(Color.BLACK);
						break;

						case 5:
						if(flag==false)
							g.setColor(Color.BLACK);
						else
							g.setColor(Color.BLACK);
						break;
					}


					g.fillOval(975, 550, 30, 30);
					g.setColor(Color.BLACK);
					g.drawString("5",990,530);
					break;

					case 6:
					switch(color_tab[i])
					{

						case 1:
						if(flag==false)
							g.setColor(Color.BLUE);
						else
							g.setColor(Color.BLACK);
						break;

						case 2:

						if(flag==false)
							g.setColor(Color.RED);
						else
							g.setColor(Color.BLACK);
						break;

						case 3:
						if(flag==false)
							g.setColor(Color.GREEN);
						else
							g.setColor(Color.BLACK);
						break;

						case 4:
						if(flag==false)
							g.setColor(Color.YELLOW);
						else
							g.setColor(Color.BLACK);
						break;

						case 5:
						if(flag==false)
							g.setColor(Color.BLACK);
						else
							g.setColor(Color.BLACK);
						break;
					}


					g.fillOval(650, 400, 30, 30);
					g.setColor(Color.BLACK);
					g.drawString("6",665,390);
					break;

					case 7:
					switch(color_tab[i])
					{

						case 1:
						if(flag==false)
							g.setColor(Color.BLUE);
						else
							g.setColor(Color.BLACK);
						break;

						case 2:

						if(flag==false)
							g.setColor(Color.RED);
						else
							g.setColor(Color.BLACK);
						break;

						case 3:
						if(flag==false)
							g.setColor(Color.GREEN);
						else
							g.setColor(Color.BLACK);
						break;

						case 4:
						if(flag==false)
							g.setColor(Color.YELLOW);
						else
							g.setColor(Color.BLACK);
						break;

						case 5:
						if(flag==false)
							g.setColor(Color.BLACK);
						else
							g.setColor(Color.BLACK);
						break;
					}


					g.fillOval(1150, 400, 30, 30);
					g.setColor(Color.BLACK);
					g.drawString("7",1165,390);

					break;

					case 8:
					switch(color_tab[i])
					{

						case 1:
						if(flag==false)
							g.setColor(Color.BLUE);
						else
							g.setColor(Color.BLACK);
						break;

						case 2:

						if(flag==false)
							g.setColor(Color.RED);
						else
							g.setColor(Color.BLACK);
						break;

						case 3:
						if(flag==false)
							g.setColor(Color.GREEN);
						else
							g.setColor(Color.BLACK);
						break;

						case 4:
						if(flag==false)
							g.setColor(Color.YELLOW);
						else
							g.setColor(Color.BLACK);
						break;

						case 5:
						if(flag==false)
							g.setColor(Color.BLACK);
						else
							g.setColor(Color.BLACK);
						break;
					}


					g.fillOval(700, 500, 30, 30);
					g.setColor(Color.BLACK);
					g.drawString("8",715,490);
					break;

					case 9:
					switch(color_tab[i])
					{

						case 1:
						if(flag==false)
							g.setColor(Color.BLUE);
						else
							g.setColor(Color.BLACK);
						break;

						case 2:

						if(flag==false)
							g.setColor(Color.RED);
						else
							g.setColor(Color.BLACK);
						break;

						case 3:
						if(flag==false)
							g.setColor(Color.GREEN);
						else
							g.setColor(Color.BLACK);
						break;

						case 4:
						if(flag==false)
							g.setColor(Color.YELLOW);
						else
							g.setColor(Color.BLACK);
						break;

						case 5:
						if(flag==false)
							g.setColor(Color.BLACK);
						else
							g.setColor(Color.BLACK);
						break;
					}

					g.fillOval(1100, 500, 30, 30);
					g.setColor(Color.BLACK);
					g.drawString("9",1115,490);
					break;

			}
		}
		repaint();
	}


	// returns the x co-ordinate of the line to be drawn.
	public int xmidpt(int node)
	{
		switch(node)
		{

			case 1:
			return 915;

			case 2:
			return 715;

			case 3:
			return 1115;

			case 4:
			return 840;

			case 5:
			return 990;

			case 6:
			return 665;

			case 7:
			return 1165;

			case 8:
			return 715;

			case 9:
			return 1115;
		}
		return 0;
	}

	// returns the y co-ordinate of the line to be drawn.
    public int ymidpt(int node)
	{
		switch(node)
		{
			case 1:
			return 265;

			case 2:
			return 315;

			case 3:
			return 315;

			case 4:
			return 565;

			case 5:
			return 565;

			case 6:
			return 415;

			case 7:
			return 415;

			case 8:
			return 515;

			case 9:
			return 515;
		}
		return 0;
	}

	public void Gen_Col_Value(int k)
	{
		int j,a,b;
		while(true)
		{
			a=color_tab[k]+1;
			b=m+1;
			color_tab[k]=a%b;
			if(color_tab[k]==0) return;

			for(j=1;j<=n;j++)
			{
				if(g[k][j]!=0 && color_tab[k]==color_tab[j])
				break;
			}
			if(j==n+1) return;
		}
	}

	public void Gr_coloring(int k)
	{
		Gen_Col_Value(k);
		if(color_tab[k]==0) return;
		if(k==n) return;
		else Gr_coloring(k+1);
	}

	public int sort (int color_tab[])
	{
		int max=0;
		for(int i=1;i<=edges;i++)
		{
			if(color_tab[i]>max)
				max=color_tab[i];
		}
	return max;
	}




	/*public void color(int code)
	{
		Graphics g=getGraphics();
		switch(code)
		{

			case 1:
			if(flag==false)
				g.setColor(Color.BLUE);
			else
				g.setColor(Color.BLACK);
			break;

			case 2:

			if(flag==false)
				g.setColor(Color.RED);
			else
				g.setColor(Color.BLACK);
			break;

			case 3:
			if(flag==false)
				g.setColor(Color.BLACK);
			else
				g.setColor(Color.BLACK);
			break;

			case 4:
			if(flag==false)
				g.setColor(Color.GREEN);
			else
				g.setColor(Color.BLACK);
			break;

			case 5:
			if(flag==false)
				g.setColor(Color.YELLOW);
			else
				g.setColor(Color.BLACK);
			break;
		}
	}*/

}