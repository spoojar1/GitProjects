/*
COHEN-SUTHERLAND LINE CLIPPING ALGORITHM
*/
#include<iostream.h>
#include<conio.h>
#include<graphics.h>
#include<dos.h>
typedef int CodeMatrix[4];
CodeMatrix a1,a2;
float xl,xh,yl,yh,ax1,ax2,ay1,ay2,m;
void initialize()
{
 int gd,gm;
 detectgraph(&gd,&gm);
 initgraph(&gd,&gm,"");
 line(320,0,320,480);
 line(0,240,640,240);
}
void code(int ax,int ay,CodeMatrix temp)
{
 if(ax<xl)
    temp[3]=1;
  else
     temp[3]=0;
 if(ax>xh)
    temp[2]=1;
  else
     temp[2]=0;
 if(ay<yl)
    temp[1]=1;
  else
     temp[1]=0;
 if(ay>yh)
    temp[0]=1;
  else
     temp[0]=0;
}
int checkzero(CodeMatrix temp)
{
 for(int i=0;i<4;i++)
    if(temp[i]!=0)
	return -1;
 return 1;
}
void and(CodeMatrix temp)
{
 for(int i=0;i<4;i++)
    if(a1[i]==1 && a2[i]==1)
	temp[i]=1;
    else
	temp[i]=0;
}
int matchcode(CodeMatrix temp,char c[])
{
  for(int i=0;i<4;i++)
  {
     if(temp[i]==0)
     {
      if(c[i]!='0')
	return -1;
     }
     else
     {
      if(c[i]!='1')
	return -1;
     }
  }
  return 1;
}
void newpoints(CodeMatrix temp,int x1,int y1,int no)
{
  if(matchcode(temp,"0001")==1)   //left edge
   { y1=y1+m*(xl-x1); x1=xl;  }
  else
   if(matchcode(temp,"0010")==1)   //right edge
     {y1=y1+m*(xh-x1); x1=xh;  }
   else
    if(y1>yh)
    {
     if(matchcode(temp,"1001")==1)    //top left and left edge
     {  y1=y1+m*(xl-x1);  x1=xl;}
     if(matchcode(temp,"1010")==1)    //top right and right edge
     {  y1=y1+m*(xh-x1);   x1=xh;}
     if(matchcode(temp,"1000")==1)    //top edge
     {  x1=x1+((yh-y1)/m);  y1=yh;}
    }
    else
     if(y1<yl)
     {
       if(matchcode(temp,"0101")==1)    //bottom left and left edge
       { y1=y1+m*(xl-x1);  x1=xl;}
       if(matchcode(temp,"0110")==1)    //bottom right and right edge
       { y1=y1+m*(xh-x1);   x1=xh;}
       if(matchcode(temp,"0100")==1)    //bottom edge
       { x1=x1+((yl-y1)/m);  y1=yl;}
     }
  if(no==1)
  {
   ax1=x1;
   ay1=y1;
  }
  if(no==2)
  {
   ax2=x1;
   ay2=y1;
  }
}
void display()
{
 line(320,0,320,480);
 line(0,240,640,240);
 rectangle(320+int(xl),240-int(yh),320+int(xh),240-int(yl));
 line(320+int(ax1),240-int(ay1),320+int(ax2),240-int(ay2));
}
void main()
{
  clrscr();
  float num,den;
  CodeMatrix temp;
  cout<<"Enter window co-ordinates\nEnter xmin and xmax\n";
  cin>>xl>>xh;
  cout<<"Enter ymin and ymax\n";
  cin>>yl>>yh;
  cout<<"Enter x and y co-ordinates of first end-point of line 1\n";
  cin>>ax1>>ay1;
  cout<<"Enter x and y co-ordinates of second end-point of line 1\n";
  cin>>ax2>>ay2;
  initialize();
  display();
  delay(2000);
  cleardevice();
  num=ay2-ay1;
  den=ax2-ax1;
  m=num/den;
  again:

  code(ax1,ay1,a1);
  code(ax2,ay2,a2);
  if(checkzero(a1)==1&&checkzero(a2)==1)
    display();
  else    //(checkzero(a1)==-1&&checkzero(a2)==-1)
  {
   and(temp);
   if(checkzero(temp)==1)  //!=0 means line is not visible inside window
   {
    //intersection points
    newpoints(a1,ax1,ay1,1);
    newpoints(a2,ax2,ay2,2);
    goto again;
   }
   //line discard
  }
  getch();
}