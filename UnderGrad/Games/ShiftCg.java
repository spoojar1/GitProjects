/*
SURAJ POOJARY    C22_109
SUMESH LUND      C22_106
CG PROJECT
*/

import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;
public class ShiftCg extends Applet implements KeyListener,MouseMotionListener,MouseListener
{
	int width=840;
	int height=740;

    Man m;		Mans ms;
	Door d;	   	Doors ds;
	Shift s;          Shifts s1;
	Level1 l1;        Level1s l1s;
	Level2 l2;        Level2s l2s;
	Level3 l3;        Level3s l3s;
	Level4a l4a;      Level4as l4as;
	Level4b l4b;      Level4bs l4bs;
	Gameover go;       Gameovers gos;

	int flag=0;
	boolean pg1=true,pg2=true,pg3=true,pg4=true,pg5=true,pg6=true,space=true;

	long lastUpdate;
	int lastkey=0;
	int time_limit=50;
	int focus=0;
	int start=0;
	int gameover=0;


	public void init()
	{
		m=new Man();		ms=new Mans();
		d=new Door();		ds=new Doors();
		s=new Shift();		s1=new Shifts();
		l1=new Level1();	l1s=new Level1s();
		l2=new Level2();	l2s=new Level2s();
		l3=new Level3();	l3s=new Level3s();
		l4a=new Level4a();	l4as=new Level4as();
		l4b=new Level4b();	l4bs=new Level4bs();
		go=new Gameover();   gos=new Gameovers();

		s.shift=Toolkit.getDefaultToolkit().getImage("openingNEW.jpg");
		s1.shifts=Toolkit.getDefaultToolkit().getImage("openingNEWINV.jpg");
		l1.level1 = Toolkit.getDefaultToolkit().getImage("shift 1.jpg");
		l1s.level1s = Toolkit.getDefaultToolkit().getImage("shift 1INV.jpg");
		l2.level2 = Toolkit.getDefaultToolkit().getImage("shift 2.jpg");
		l2s.level2s = Toolkit.getDefaultToolkit().getImage("shift 2INV.jpg");
		l3.level3 = Toolkit.getDefaultToolkit().getImage("shift 3.jpg");
		l3s.level3s = Toolkit.getDefaultToolkit().getImage("shift 3INV.jpg");
		l4a.level4a = Toolkit.getDefaultToolkit().getImage("shift 4.1.jpg");
		l4as.level4as = Toolkit.getDefaultToolkit().getImage("shift 4.1inv.jpg");
		l4b.level4b = Toolkit.getDefaultToolkit().getImage("shift 4.2.jpg");
		l4bs.level4bs = Toolkit.getDefaultToolkit().getImage("shift 4.2inv.jpg");
		go.gmover= Toolkit.getDefaultToolkit().getImage("gameover.jpg");
		gos.gmovers= Toolkit.getDefaultToolkit().getImage("gameoverinv.jpg");

		reset();
		addMouseListener(this);
		addMouseMotionListener(this);
		addKeyListener(this);
		lastUpdate=new Date().getTime();
		requestFocus(); // request input focus
	}
	public void destroy()
	{

	}

	//update is auto-called whenever x and y values are changed
	//basically after mouse and key listeners
	public void update(Graphics g)
	{
		Image buffer=createImage(width,height);
		paint(buffer.getGraphics());
		g.drawImage(buffer,0,0,this);
	}
	public void paint(Graphics g)
	{
		if(pg1!=false)		//to remove flikker when mouse moves over shift box
		{
			g.setColor(Color.WHITE);
			g.fillRect(0,0,width-1,height-1);
		}
		if(flag==0)
		{
			if(pg1==true)
				s.paint(g);
			else
				s1.paint(g);
		}
		if(flag==1)
		{
			if(pg2==true)
			{
				l1.paint(g);
				d.paint(g);
				m.paint(g);
			}
			else
			{
				l1s.paint(g);
				ds.paint(g);
				ms.paint(g);
			}
		}
		if(flag==2)
		{
			if(pg3==true)
			{
				l2.paint(g);
				d.paint(g);
				m.paint(g);
			}
			else
			{
				l2s.paint(g);
				ds.paint(g);
				ms.paint(g);
			}
		}
		if(flag==3)
		{
			if(pg4==true)
			{
				l3.paint(g);
				d.paint(g);
				m.paint(g);
			}
			else
			{
				l3s.paint(g);
				ds.paint(g);
				ms.paint(g);
			}
		}
		if(flag==4)
		{
			if(pg5==true)
			{
				l4a.paint(g);
				m.paint(g);
			}
			else
			{
				l4as.paint(g);
				ms.paint(g);
			}
		}
		if(flag==5)
		{
			if(pg6==true)
			{
				l4b.paint(g);
				m.paint(g);
				if(gameover==1)
				{
					g.setColor(Color.RED);
					g.fillRect(m.x,m.y,m.width,m.height);
					go.paint(g);   //gameover
				}
			}
			else
			{
				l4bs.paint(g);
				ms.paint(g);
				if(gameover==1)
				{
					g.setColor(Color.RED);
					g.fillRect(ms.x,ms.y,ms.width,ms.height);
					gos.paint(g);    //gameover
				}
			}
		}
		repaint();
	}
	void reset()      //for level 1,2 and 4
	{
		d.x1=700;
		d.y1=height/2-d.height1;
		d.x2=720;
		d.y2=height/2-d.height2;

		m.x=50;
		m.y=height/2-m.height;

		ds.x1=width-d.x1-d.width1;
		ds.x2=width-d.x2-d.width2;
		ds.y1=height-d.y1-d.height1;
		ds.y2=height-d.y2-d.height2;
	}
	void reset1()     //for level 3
	{
		d.x1=400;
		d.y1=276-d.height1;
		d.x2=420;
		d.y2=276-d.height2;

		ds.x1=width-d.x1-d.width1;
		ds.x2=width-d.x2-d.width2;
		ds.y1=height-d.y1-d.height1;
		ds.y2=height-d.y2-d.height2;

		m.x=50;
		m.y=370-m.height;
	}
	public void mouseClicked(MouseEvent e)
	{
		Graphics g;
		g=getGraphics();
		int x,y;
		x=e.getX();
		y=e.getY();
		if(flag==0&&y>=225&&y<=370&&x>=234&&x<=545)
		{
			flag=1;
			start=1;  //level 1 loaded
			paint(g);
		}
	}
	public void mouseMoved(MouseEvent e)
	{
		Graphics g;
		g=getGraphics();
		int x,y;
		x=e.getX();
		y=e.getY();
		if(flag==0) //flag==1 also so that it shld not interfere with level1
			if(y>=225&&y<=370&&x>=234&&x<=545)
			{
				pg1=false;
				paint(g);
			}
			else
				pg1=true;  //out of shift
	}
	public void mouseDragged(MouseEvent e)
	{}
	public void mouseExited(MouseEvent me)
	{}
	public void mouseEntered(MouseEvent me)
	{}
	public void mousePressed(MouseEvent me)
	{}
	public void mouseReleased(MouseEvent me)
	{}
	public void keyPressed(KeyEvent ke)
	{
		int key=0;
		if(focus!=1)  //don't take key_input until focus=0
			key=ke.getKeyCode();
		if(start==1||key==16)  //so that at first instance of key_input control goes in switch and also that shift shld not face any problems
			lastkey=key;
		start=0;
		Graphics g;
		g=getGraphics();
		switch(lastkey)
		{
			case KeyEvent.VK_LEFT:	if(lastkey==KeyEvent.VK_LEFT && key==KeyEvent.VK_SPACE)
									{
										if(new Date().getTime()-lastUpdate<time_limit )
									  	{
											focus=1;
									  		if(flag==1)
									  		{
												if(pg2==true)
									  			{
													spaceL1();
												}
												else
												{
                         							 spaceL1s();
												}
											 }
											 if(flag==2)
											 {
												 if(pg3==true)
                                                 {
                                                 	spaceL1();
                                                 }
                                                 else
                                                 {
                                              		spaceL1s();
                                                 }
											 }
											 if(flag==3)
											 {
												 if(pg4==true)
                                                 {
                                              	 	spaceL1();
                                                 }
                                                 else
                                                 {
                                                 	spaceL1s();
                                                 }
											 }
											 if(flag==4)
											 {
												 if(pg5==true)
                                                 {
                                              	 	spaceL1();
                                                 }
                                                 else
                                                 {
                                                 	spaceL1s();
                                                 }
											 }
											 focus=0;
									  	}

									}
									lastUpdate=new Date().getTime();
									lastkey=ke.getKeyCode();
									if(key==37&&(lastkey==37||lastkey==0))
									{
										if(m.x>5&&ms.x>5)
										{
											if(flag==1)
											{

												if(pg2==true)
												{
													m.x-=m.speed;
													if((m.cx>=d.cx-10)&&(m.cx<=d.cx+10))
													{
														painttempdoor();;
														m.paint(g);
														for(int i=0;i<50;i++)
															delay();
														reset();
														flag=2;   //level 2 starts
														paint(g);
													}
												}
												else
													if(ms.cy!=ds.cy)   //not possible
														ms.x-=ms.speed;
											}
											if(flag==2)
											{
												if(pg3==true)
												{
													if(m.cx!=d.cx)
													{
														if(checkfall())
															m.x-=m.speed;
														else
														    startlvl2();
													}
													if((m.cx>=d.cx-5)&&(m.cx<=d.cx+5))
													{
														painttempdoor();;
														m.paint(g);
														for(int i=0;i<50;i++)
                                            			 	delay();
														reset1();
														flag=3;   //level 3 starts
													}
												}
												else
													if(ms.cy!=ds.cy)   //not possible
													{
														ms.x-=ms.speed;
														if(col_detLVL2(2))
															return;
													}
											}
											if(flag==3)
                                            {
                                            	if(pg4==true)
                                            	{;
													if(m.cx!=d.cx)
                                            		{
														comeDown3();
														if(!col_detLVL3())   //no collision
															m.x-=m.speed;
													}
                                            		if((m.cx>=d.cx-5)&&(m.cx<=d.cx+5)&&m.cy==227)
                                            		{
                                            			 painttempdoor();;
                                            			 m.paint(g);
                                            			 for(int i=0;i<50;i++)
                                            			 	delay();
                                            			 reset();
                                            			 flag=4;
                                            		}
												}
                                            	else
                                   					if(ms.cy!=ds.cy)   //not possible
                                            		{
														comeDown3();
                                            			if(!col_detLVL3())
                                            				ms.x-=ms.speed;
                                            		}
                                            }
                                            if(flag==4)
                                            {
												if(pg5==true)
												{
													m.x-=m.speed;
												}
												else
												{
													if(!gameover())
														ms.x-=ms.speed;
												}
											}
										}
									}
									break;
			case KeyEvent.VK_SPACE: lastkey=ke.getKeyCode();
									break;
			case KeyEvent.VK_RIGHT: if(lastkey==KeyEvent.VK_RIGHT && key==KeyEvent.VK_SPACE)
									{
										if(new Date().getTime()-lastUpdate<time_limit )
									  	{
											focus=1;
									  		if(flag==1)
									  		{
												if(pg2==true)
									  			{
													spaceR1();
												}
												else
												{
													spaceR1s();
												}
											}
											if(flag==2)
											{
												if(pg3==true)
												{
													spaceR1();
												}
												else
												{
													spaceR1s();
												}
											}
											if(flag==3)
											{
												if(pg4==true)
												{
													spaceR1();
												}
												else
												{
													spaceR1s();
												}
											}
											if(flag==4)
											{
												if(pg5==true)
												{
													spaceR1();
												}
												else
												{
													spaceR1s();
												}
											}
											focus=0;
									  	}

									}
									lastUpdate=new Date().getTime();
									lastkey=ke.getKeyCode();
									if(key==39&&(lastkey==39||lastkey==0))
									{
										if(flag==1)
										{
											if(pg2==true)
											{
												if((m.x+m.width)<(width-5))
													m.x+=m.speed;
											}
											else
												if((ms.x+ms.width)<(width-5))
													ms.x+=ms.speed;
										}
										if(flag==2)
										{
											if(pg3==true)
                                            {
                                      			if((m.x+m.width)<(width-5))
                                      		    {
													if(checkfall())
														m.x+=m.speed;
													else
													    startlvl2();
												}
                                            }
                                            else
                                     		{
												if((ms.x+ms.width)<(width-5))
												{
													ms.x+=ms.speed;
													if(col_detLVL2(1))
														return;
												}
											}
										}
										if(flag==3)
										{
											if(pg4==true)
                                            {
                                      			if((m.x+m.width)<(width-5))
                                      		    {
													if(checkfall3R()==true)
														m.x+=m.speed;
												}
											}
                                            else
                                     		{
												if((ms.x+ms.width)<(width-5))
												{
													if(checkfall3R()==true)  //false means x=180
														ms.x+=ms.speed;
													if(col_detLVL3())
														return;
												}
											}
										}
										if(flag==4)
										{
											if(pg5==true)
											{
												if(!gameover())
													m.x+=m.speed;
											}
											else
											{
												if(ms.x<=790)
													ms.x+=ms.speed;
											}
										}
									}
									break;
			case KeyEvent.VK_SHIFT: start=1;
									if(flag==1)
									{
										pg2=!pg2;  //level1 is shifted
										if(pg2==false)
										{
											ms.x=width-m.x-m.width;
											ms.y=height-m.y-(2*m.height);
										}
										if(pg2==true)
										{
											m.x=width-ms.x-ms.width;
											m.y=height-ms.y-(2*ms.height);
										}
										paint(g);
									}
									if(flag==2)
									{
										pg3=!pg3;  //level2 is shifted
                                        if(pg3==false)
                                        {
											ms.x=width-m.x-m.width;
											ms.y=height-m.y-(2*m.height);
										}
                                        if(pg3==true)
                                       	{
											m.x=width-ms.x-ms.width;
											m.y=height-ms.y-(2*ms.height);
										}
										paint(g);
									}
									if(flag==3)
									{
										pg4=!pg4;  //level3 is shifted
                                        if(pg4==false)
                                        {
											ms.x=width-m.x-m.width;
											ms.y=height-m.y-(2*m.height);
										}
                                        if(pg4==true)
                                       	{
											m.x=width-ms.x-ms.width;
											m.y=height-ms.y-(2*ms.height);
										}
										paint(g);
									}
									if(flag==4)
									{
										pg5=!pg5;  //level4 is shifted
                                        if(pg5==false)
                                        {
											ms.x=width-m.x-m.width;
											ms.y=height-m.y-(2*m.height);
										}
                                        if(pg5==true)
                                       	{
											m.x=width-ms.x-ms.width;
											m.y=height-ms.y-(2*ms.height);
										}
										paint(g);
									}
									break;
		}
	}
	public void keyReleased(KeyEvent ke)
	{}
	public void keyTyped(KeyEvent ke)
	{}
	void delay()
	{
		for(long i=0;i<4000;i++)
		for(long j=0;j<i;j++)
		{}
	}
	boolean gameover()     //returns true if game over
	{
		Graphics g;
		g=getGraphics();
		int pos,posy;
		if(pg5==true)
		{
			pos=m.x+(m.width/2);
			posy=m.y+m.height;
			if(pos>155)
			{
				flag=5;               //pg6 is already true
				paint(g);
				if(pos==160)
					m.x=185;
				while(posy<=565)
				{
					m.y=m.y+5;
					posy=m.y+m.height;
					paint(g);
					delay();
				}
				m.y=573-m.height;
				focus=0;
				gameover=1;
				return true;
			}
		}
		if(pg5==false)
		{
			pos=ms.x+(ms.width/2);
			posy=ms.y+ms.height;
			if(pos<=650)
			{
				flag=5;
				if(pos==650)
					ms.x=615;
				pg6=false;            //pg6 is shifted
				while(posy<=565)
				{
					ms.y=ms.y+5;
					posy=ms.y+ms.height;
					paint(g);
					delay();
				}
				ms.y=573-ms.height;

				focus=0;
				gameover=1;
				return true;
			}
		}
		return false;
	}
	//these comedown are for jumps near the walls
	void comeDown1()
	{
		Graphics g;
		g=getGraphics();
		while(m.y<(height/2-m.height))
		{
			m.y=m.y+5;
			paint(g);
			delay();
		}
	}
	void comeDown1s()
	{
		Graphics g;
		g=getGraphics();
		while(ms.y<(height/2-ms.height))
		{
			ms.y=ms.y+5;
			paint(g);
			delay();
		}
	}
	void comeDown3()
	{
		Graphics g;
		g=getGraphics();
		int pos,posy;
		if(pg4==true)
		{
			pos=m.x;
			posy=m.y+m.height;
			if(pos>=530&&pos<=630&&posy==277)  //come down between x=530 to x=630
			{
				if(m.x==630)
					m.x=610;
				while(posy<=571)    //576-5
				{
					m.y=m.y+5;
					posy=m.y+m.height;
					paint(g);
					delay();
				}
				m.y=576-m.height;
				paint(g);
			}
			if(pos<=335&&posy==277)
			{
				if(m.x>=235)
				{
					if(m.x>=340)
						m.x=315;
					else
						m.x=240;
					while(posy<=502)    //507-5
					{
						m.y=m.y+5;
						posy=m.y+m.height;
						paint(g);
						delay();
					}
					m.y=507-m.height;
					paint(g);
				}
				else
				{
					m.x=220;
					while(posy<=432)    //437-5
					{
						m.y=m.y+5;
						posy=m.y+m.height;
						paint(g);
						delay();
					}
					m.y=437-m.height;
					paint(g);
				}
			}
		}
		else
		{
			pos=ms.x;
			posy=ms.y+ms.height;
			if(pos>=5&&pos<=155&&posy==164)  //come down between x=5 to x=155
			{
				if(ms.x>=140)
					ms.x=140;
				while(posy<=454)
				{
					ms.y=ms.y+5;
					paint(g);
					delay();
					posy=ms.y+ms.height;
				}
				if(posy==459)
				{
					posy=463;
					ms.y=posy-ms.height;
					paint(g);
				}
			}
		}
	}
	void spaceR1()           //jump right in level1
	{
		Graphics g;
		g=getGraphics();
		if(flag==1)
		{
			for(int i=1;i<=20&&m.y<=(height/2-m.height)&&m.x<795;i++)
			{
				if(i<=13)
					m.x=m.x+(int)(1.5*i);
				if(i==14)
					m.x=m.x+1;
				if(i<=10)
					m.y=m.y-5;
				else
				m.y=m.y+5;

				paint(g);
				delay();
			}
			if(m.x>=(width-m.width-5))   //>=795
			{
				m.x=width-m.width-5; //it may become greater
				comeDown1();
			}
		}
		if(flag==2)
		{
			for(int i=1;i<=20&&m.y<=(height/2-m.height)&&m.x<(width-m.width-5);i++)
			{
				if(i<=13)  //to limit x-dir movement in jump
					m.x=m.x+(int)(1.5*i);      //limit of ms.x=19
				if(i==14)
					m.x=m.x+1;            //to keep m.x as multiple of 5  m.x=m.x+1=20
				if(i<=10)
					m.y=m.y-5;
				else
					m.y=m.y+5;

				if((m.y+m.height)>=365)     //only for level 2
				{
					if(!checkfall())   //fall fails
					{
						startlvl2();
						return ;
					}
				}
				paint(g);
				delay();
			}
			if(m.x>=(width-m.width-5))   //>=795
			{
				comeDown1();
				m.x=width-m.width-5; //it may become greater
			}
		}
		if(flag==3)
		{
			int posy=m.y+m.height;
			if(posy==370||posy==437||posy==507||posy==576||posy==277)
			{
				for(int i=1;i<=20;i++)
				{
					if(i<=15)  //to limit x-dir movement in jump
						m.x=m.x+(int)i;
					if(i<=10)
						m.y=m.y-5;
					else
						m.y=m.y+5;

					if(!checkfall3R())   //fn returned false i.e. man encounters wall at 655  || man encounters 840 wall while jump
					{
						//for jump at 840 action already taken
						if(m.x==610)     //encounter at 655
						{
							while(posy<=571)    //576-5
							{
								m.y=m.y+5;
								posy=m.y+m.height;
								paint(g);
								delay();
							}
							m.y=576-m.height;
							paint(g);
						}
						return ;
					}
			 		paint(g);
					delay();
				}
			}
		}
		if(flag==4)
		{
			for(int i=1;i<=20&&m.y<=(height/2-m.height)&&m.x<795;i++)
			{
				if(i<=13)
					m.x=m.x+(int)(1.5*i);
				if(i==14)
					m.x=m.x+1;
				if(i<=10)
					m.y=m.y-5;
				else
				m.y=m.y+5;

				paint(g);
				delay();
			}
			if(gameover())
				return;
		}
	}
	void spaceR1s()         //jump right in level1 shifted
	{
		Graphics g;
		g=getGraphics();
		if(flag==1)
		{
			for(int i=1;i<=20&&ms.y<=(height/2-ms.height)&&ms.x>5;i++)
			{
				if(i<=13)
					ms.x=ms.x+(int)(1.5*i);
				if(i==14)
					ms.x=ms.x+1;
				if(i<=10)
					ms.y=ms.y-5;
				else
				ms.y=ms.y+5;

				paint(g);
				delay();
			}
			if(ms.x>=(width-ms.width-5))   //>=795
			{
				comeDown1s();
				ms.x=width-ms.width-5; //it may become greater
			}
		}
		if(flag==2)
		{
			for(int i=1;i<=20&&ms.y<=(height/2-ms.height)&&ms.x<(width-ms.width-5);i++)
			{
				if(i<=13)  //to limit x-dir movement in jump
					ms.x=ms.x+(int)(1.5*i);      //limit of ms.x=19
				if(i==14)
					ms.x=ms.x+1;    //to keep ms.x as multiple of 5   ms.x=ms.x+1=20
				if(i<=10)
					ms.y=ms.y-5;
				else
					ms.y=ms.y+5;

				if(col_detLVL2(1))
					break;
				paint(g);
				delay();
			}
			if(ms.x>=(width-ms.width-5))   //>=795
			{
				comeDown1s();
				ms.x=width-ms.width-5; //it may become greater
			}
		}
		if(flag==3)
		{
			int posy=ms.y+ms.height;
			if(posy==463||posy==164||posy==233||posy==303||posy==370)
			{
				for(int i=1;i<=20;i++)
				{
					if(i<=15)  //to limit x-dir movement in jump
						ms.x=ms.x+(int)i;
					if(i<=10)
						ms.y=ms.y-5;
					else
						ms.y=ms.y+5;

					if(!checkfall3R())   //fn returned false i.e. man encounters x=184
					{
						ms.x=140;
						while(posy<=455)
						{
							ms.y=ms.y+5;
							posy=ms.y+ms.height;
							paint(g);
							delay();
						}
						if(posy>455)
						{
							ms.y=463-ms.height;
							paint(g);
							return ;
						}
					}
				 	paint(g);
					delay();
				}
			}
		}
		if(flag==4)
		{
			for(int i=1;i<=20&&ms.y<=(height/2-ms.height)&&ms.x<=785;i++)
			{
				if(i<=13)
					ms.x=ms.x+(int)(1.5*i);
				if(i==14)
					ms.x=ms.x+1;
				if(i<=10)
				ms.y=ms.y-5;
					else
				ms.y=ms.y+5;

				paint(g);
				delay();
			}
			if(ms.x>=785)
			{
				ms.x=795;
				comeDown1s();
			}
		}
	}
	void spaceL1()       //jump left in level1
	{
		Graphics g;
		g=getGraphics();
		if(flag==1)
		{
			for(int i=1;i<=20&&m.y<=(height/2-m.height)&&m.x>5;i++)
			{
				if(i<=13)
					m.x=m.x-(int)(1.5*i);
				if(i==14)
					m.x=m.x+1;
				if(i<=10)
					m.y=m.y-5;
					else
				m.y=m.y+5;

				paint(g);
				delay();
			}
			if(m.x<=5)
			{
				comeDown1();
				m.x=5;
			}
		}
		if(flag==2)
		{
			for(int i=1;i<=20&&m.y<=(height/2-m.height)&&m.x>5;i++)
			{
				if(i<=13)
					m.x=m.x-(int)(1.5*i);
				if(i==14)
					m.x=m.x+1;
				if(i<=10)
					m.y=m.y-5;
				else
					m.y=m.y+5;

				if(flag==2&&(m.y+m.height)>=365)        //only for level 2
				{
					if(!checkfall())   //fall fails
					{
						startlvl2();
						return ;
					}
				}
				paint(g);
				delay();
			}
			if(m.x<=5)
			{
				comeDown1();
				m.x=5;
			}
		}
		if(flag==3)
		{
			int posy=m.y+m.height;
			if(posy==370||posy==437||posy==507||posy==576||posy==277)
			{
				for(int i=1;i<=30;i++)
				{
					if(i<=10)  //to limit x-dir movement in jump
						m.x=m.x-(int)i;
					if(i>15&&i<=20)
						m.x=m.x-(int)i;
					if(i<=20)
						m.y=m.y-5;
					else
					{
						m.y=m.y+10;
						comeDown3();
						if(checkfall3L())   //true if jump to be interrupted
						{
							paint(g);
							return;
						}
					}
					paint(g);
					delay();
				}
			}
		}
		if(flag==4)
		{
			for(int i=1;i<=20&&m.y<=(height/2-m.height)&&m.x>5;i++)
			{
				if(i<=13)
					m.x=m.x-(int)(1.5*i);
				if(i==14)
					m.x=m.x+1;
				if(i<=10)
				m.y=m.y-5;
					else
				m.y=m.y+5;

				paint(g);
				delay();
			}
			if(m.x<=5)
			{
				comeDown1();
				m.x=5;
			}
		}
	}
	void spaceL1s()        //jump left in level1 shifted
	{
		Graphics g;
		g=getGraphics();
		if(flag==1)
		{
			for(int i=1;i<=20&&ms.y<=(height/2-ms.height)&&ms.x>5;i++)
			{
				if(i<=13)
					ms.x=ms.x-(int)(1.5*i);
				if(i==14)
					ms.x=ms.x+1;
				if(i<=10)
					ms.y=ms.y-5;
				else
				ms.y=ms.y+5;

				paint(g);
				delay();
			}
			if(ms.x<=5)
			{
				comeDown1s();
				ms.x=5;
			}
		}
		if(flag==2)
		{
			for(int i=1;i<=20&&ms.y<=(height/2-ms.height)&&ms.x>5;i++)
			{
				if(i<=13)
					ms.x=ms.x-(int)(1.5*i);
				if(i==14)
					ms.x=ms.x+1;
				if(i<=10)
					ms.y=ms.y-5;
				else
					ms.y=ms.y+5;

				if(col_detLVL2(2))
					break;
				paint(g);
				delay();
			}
			if(ms.x<=5)
			{
				comeDown1s();
				ms.x=5;
			}
		}
		if(flag==3)
		{
			int posy=ms.y+ms.height;
			if(posy==164||posy==233||posy==303||posy==370||posy==463)
			{
				for(int i=1;i<=30;i++)
				{
					if(i<=10)  //to limit x-dir movement in jump
						ms.x=ms.x-(int)i;
					if(i>15&&i<=20)
						ms.x=ms.x-(int)i;
					if(i<=20)
						ms.y=ms.y-5;
					else
					{
						ms.y=ms.y+10;
						comeDown3();    //fall between x=5 to x=155
						if(checkfall3L())   //true if jump to be interrupted
						{

							paint(g);
							return;
						}
					}
					paint(g);
					delay();
				}
			}
		}
		if(flag==4)
		{
			for(int i=1;i<=20&&ms.y<=(height/2-ms.height);i++)
			{
				if(i<=13)
					ms.x=ms.x-(int)(1.5*i);
				if(i==14)
					ms.x=ms.x-1;
				if(i<=10)
					ms.y=ms.y-5;
				else
				ms.y=ms.y+5;

				paint(g);
				delay();
			}
			if(gameover())
				return;
		}
	}
	boolean checkfall()	 //returns false if fall fails
	{
		Graphics g;
		g=getGraphics();
		int pos=m.x+(m.width/2);    //bottom center of man
		if((pos>=197&&pos<=319)||(pos>=517&&pos<=639))  //boundary co-ordinates of gaps
		{
			if(pos>=197&&pos<=319)
			{
				m.x=(197+319)/2;    //if pos is closer to 197 man will fall closer to 197
 				m.x=(pos+m.x)/2-20;    //if closer to 319 he will fall closer to 319
			}
			else
			{
				m.x=(517+639)/2;
				m.x=(pos+m.x)/2-20;   //20 just an approximation
			}
			while((m.y+m.height)<=613)
			{
				m.y=m.y+5;
				paint(g);
				delay();
			}
			return false;
		}
		else
		    return true;
	}
	boolean checkfall3L()    //returns true if jump has to be interrupted
	{
		Graphics g;
		g=getGraphics();
		int posy;
		int pos;
		if(pg4==true)
		{
			posy=m.y+m.height;
			pos=m.x;
			if((pos>=10&&pos<=90&&posy==367)||(pos>=120&&pos<=210&&posy==437)||(pos>=240&&pos<=330&&posy==506)||pos<=5) //boundary co-ordinates of gaps
			{

				if(pos>=10&&pos<=90&&posy==367)
				{
					m.y=370-m.height;
					return true;
				}
				if(pos>=120&&pos<=210&&posy==437)
				{
					return true;
				}
				if(pos>=240&&pos<=330&&posy==506)
				{
					m.y=507-m.height;
					return true;
				}
				if(pos<=5)
				{
					m.x=5;
					while(posy<=365)
					{
						m.y=m.y+5;
						posy=m.y+m.height;
						paint(g);
						delay();
					}
					return true;
				}
			}
			return false;
		}
		else
		{
			posy=ms.y+ms.height;
			pos=ms.x;
			if((pos>=360&&posy==163)||(pos>=490&&pos<=575&&posy==233)||(pos>=605&&pos<=695&&posy==300)||pos<=5) //boundary co-ordinates of gaps
			{

				if(pos>=360&&posy==163)
				{
					ms.y=164-ms.height;
					return true;
				}
				if(pos>=490&&pos<=575&&posy==233)
				{
					return true;
				}
				if(pos>=605&&pos<=695&&posy==300)
				{
					ms.y=303-ms.height;
					return true;
				}
				if(pos<=5)
				{
					ms.x=5;
					while(posy<=455)
					{
						ms.y=ms.y+5;
						posy=ms.y+ms.height;
						paint(g);
						delay();
					}
					if(posy>455)
					{
						ms.y=463-ms.height;
						return true;
					}
				}
			}
			return false;
		}
	}
	boolean checkfall3R()	 //for pg4=true ,it returns false only when man encounters wall at 655
	{
		Graphics g;
		g=getGraphics();
		int pos,posy;
		if(pg4==true)
		{
			posy=m.y+m.height;
			pos=m.x+(m.width/2);    //bottom center of man
			if((pos>=120&&pos<=240&&posy==370)||(pos>=240&&pos<=355&&posy==437)||(pos>=355&&pos<635&&posy==507)||(pos>=530&&pos<=650&&posy==277)||(pos>=810&&posy<=277))  //boundary co-ordinates of gaps
			{
				if(pos>=120&&pos<=240&&posy==370)
				{
					if(pos==120)
						m.x=125;
					while(posy<=430)      //370+10(5)=430
					{
						if(posy==430)
							posy=437;
						else
							posy=posy+5;

						m.y=posy-m.height;

						paint(g);
						delay();
					}
				}
				if(pos>=240&&pos<=355&&posy==437)
				{
					if(pos==240)
						m.x=245;
					while(posy<=502)       //437+13(5)=502
					{
						if(posy==502)
							posy=507;
						else
							posy=posy+5;

						m.y=posy-m.height;

						paint(g);
						delay();
					}
				}
				if(pos>=355&&pos<635&&posy==507)     //635=655-20
				{
					if(pos==355)
						m.x=360;
					while(posy<=572)       //507+13(5)=572
					{
						if(posy==572)
							posy=576;
						else
							posy=posy+5;

						m.y=posy-m.height;

						paint(g);
						delay();
					}
				}
				if(pos>=530&&pos<=645&&posy==277)   //fall at border of x=530 or between 530 and 645
				{
					if(pos==530)
						m.x=535;
					if(pos>=640)
						m.x=610;
					while(posy<=572)
					{
						if(posy==572)
							posy=576;
						else
							posy=posy+5;

						m.y=posy-m.height;

						paint(g);
						delay();
					}
				}
				if(pos>=810&&posy<=277)
				{
					m.x=790;
					while(posy<=267)
					{
						posy=posy+5;
						m.y=posy-m.height;

						paint(g);
						delay();
					}
					m.y=277-m.height;
					paint(g);
					return false;
				}
			}
			if((pos==630&&posy==576)||(pos>=628&&posy>=476&&posy<=576))   //do not go beyond 655
			{

				m.x=650-m.width;
				return false;
			}
			return true;
		}
		else
		{
			posy=ms.y+ms.height;
			pos=ms.x+(ms.width/2);    //bottom center of man
			if((pos>=485&&pos<=605&&posy==164)||(pos>=605&&pos<=725&&posy==233)||(pos>=725&&posy==303)||(pos>=155&&pos<=180))  //boundary co-ordinates of gaps
			{
				if(pos>=485&&pos<=605&&posy==164)
				{
					if(pos==485)
						ms.x=485;
					while(posy<=229)      //370+10(5)=430
					{
						if(posy==229)
							posy=233;
						else
							posy=posy+5;

						ms.y=posy-ms.height;

						paint(g);
						delay();
					}
				}
				if(pos>=605&&pos<=725&&posy==233)
				{
					if(pos==605)
						ms.x=605;
					while(posy<=298)       //437+13(5)=502
					{
						if(posy==298)
							posy=303;
						else
							posy=posy+5;

						ms.y=posy-ms.height;

						paint(g);
						delay();
					}
				}
				if(pos>=725&&posy==303)     //635=655-20
				{
					if(pos==725)
						ms.x=725;
					while(posy<=368)       //507+13(5)=572
					{
						if(posy==368)
							posy=370;
						else
							posy=posy+5;

						ms.y=posy-ms.height;

						paint(g);
						delay();
					}
				}
				if(pos>=155&&pos<=180&&posy!=463)  //man encounters 184
				{
					return false;
				}
			}
			if((pos==160&&posy==463)||(pos>=160&&pos<=180&&posy>=300&&posy<=463))//do not go beyond 180
			{

				ms.x=180-ms.width;
				return false;
			}
			return true;
		}
	}
	void startlvl2()
	{
		Graphics g;
		g=getGraphics();
		g.setColor(Color.RED);
		g.fillRect(m.x,m.y,m.width,m.height);
		reset();
		for(int i=1;i<=20;i++)
			delay();
	}
	boolean col_detLVL2(int num)         //collision detection returns true if collision occurs
	{
		int pos;
		if(num==1)     //right
		{
			pos=ms.x+ms.width;
			if((pos>=198&&pos<=240)||(pos>=518&&pos<=560))    //220 and 540 just an approximation
			{
				if(pos>=198&&pos<=220)
					pos=195;
				if(pos>=518&&pos<=540)
					pos=515;
				ms.x=pos-ms.width;
				comeDown1s();
				return true;
			}
		}
		if(num==2)     //left
		{
			pos=ms.x;
			if((pos<=321&&pos>=280)||(pos<=641&&pos>=600))    //300 and 620 just an approximation
			{
				if(ms.x<=321&&pos>=280)
					pos=325;
				if(ms.x<=641&&pos>=600)
					pos=645;
				ms.x=pos;
				comeDown1s();
				return true;
			}
		}
		return false;
	}
	boolean col_detLVL3()
	{
		if(pg4==true)
		{
			if(m.x==360||m.x==240||m.x==120)
				return true;
			else
				return false;
		}
		else
		{

			if(ms.x==725||ms.x==605||ms.x==485)
				return true;
			else
				return false;
		}

	}
	void painttempdoor()
	{
		Graphics g;
		g=getGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(d.x2,d.y2,50,130);
	}
}

class Man
{
	int x,y,cx,cy;			//cx,cy   centroid
	int width=40;
	int height=100;
	int speed=5;
	public void paint(Graphics g)
	{
		cx=x+width/2;
		cy=y+height/2;
		g.setColor(Color.BLACK);
		g.fillRect(x,y,width,height);
	}
}
class Mans
{
	int x,y,cx,cy;			//cx,cy   centroid
	int width=40;
	int height=100;
	int speed=5;
	public void paint(Graphics g)
	{
		cx=x+width/2;
		cy=y+height/2;
		g.setColor(Color.WHITE);
		g.fillRect(x,y,width,height);
	}
}
class Door extends ShiftCg
{
	int x1,x2,y1,y2;
	int width1=90,width2=50;
	int height1=150,height2=130;
	int cx,cy;			//cx2,cy2  centroid of inner rect
	public void paint(Graphics g)
	{
		cx=x1+(width1/2);
		cy=y1+(height1/2);
		g.setColor(Color.BLACK);
		g.fillRect(x1,y1,width1,height1);
		g.setColor(Color.GRAY);
		g.fillRect(x2,y2,width2,height2);

	}
}
class Doors	extends ShiftCg
{
	int x1,x2,y1,y2;
	int width1=90,width2=50;
	int height1=150,height2=130;
	int cx,cy;			//cx2,cy2  centroid of inner rect
	public void paint(Graphics g)
	{
		cx=x1+(width1/2);
		cy=y1+(height1/2);
		g.setColor(Color.BLACK);
		g.fillRect(x1,y1,width1,height1);
		g.setColor(Color.GRAY);
		g.fillRect(x2,y2,width2,height2);
	}
}
class Shift extends ShiftCg
{
	Image shift;
	public void paint(Graphics g)
	{
		g.drawImage(shift,0,0,this);
	}
}
class Shifts extends ShiftCg
{
	Image shifts;
	public void paint(Graphics g)
	{
		g.drawImage(shifts,0,0,this);
	}
}
class Level1 extends ShiftCg
{
	Image level1;
	public void paint(Graphics g)
	{
		g.drawImage(level1, 0, 0, this);
	}
}
class Level1s extends ShiftCg
{
	Image level1s;
	public void paint(Graphics g)
	{
		g.drawImage(level1s, 0, 0, this);
	}
}
class Level2 extends ShiftCg
{
	Image level2;
	public void paint(Graphics g)
	{
		g.drawImage(level2, 0, 0, this);
	}
}
class Level2s extends ShiftCg
{
	Image level2s;
	public void paint(Graphics g)
	{
		g.drawImage(level2s, 0, 0, this);
	}
}
class Level3 extends ShiftCg
{
	Image level3;
	public void paint(Graphics g)
	{
		g.drawImage(level3, 0, 0, this);
	}
}
class Level3s extends ShiftCg
{
	Image level3s;
	public void paint(Graphics g)
	{
		g.drawImage(level3s, 0, 0, this);
	}
}
class Level4a extends ShiftCg
{
	Image level4a;
	public void paint(Graphics g)
	{
		g.drawImage(level4a, 0, 0, this);
	}
}
class Level4as extends ShiftCg
{
	Image level4as;
	public void paint(Graphics g)
	{
		g.drawImage(level4as, 0, 0, this);
	}
}
class Level4b extends ShiftCg
{
	Image level4b;
	public void paint(Graphics g)
	{
		g.drawImage(level4b, 0, 0, this);
	}
}
class Level4bs extends ShiftCg
{
	Image level4bs;
	public void paint(Graphics g)
	{
		g.drawImage(level4bs, 0, 0, this);
	}
}
class Gameover extends ShiftCg
{
	Image gmover;
	public void paint(Graphics g)
	{
		g.drawImage(gmover,300,200, this);
	}
}
class Gameovers extends ShiftCg
{
	Image gmovers;
	public void paint(Graphics g)
	{
		g.drawImage(gmovers,300,200, this);
	}
}
