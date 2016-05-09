/*
LIANG-BARSKY LINE CLIPPING ALGORITHM
*/
#include<iostream.h>
#include<conio.h>
#include<graphics.h>
#include<dos.h>
float p1,p2,p3,p4;
float q1,q2,q3,q4;
float x1,x2,y1,y2,dx,dy,xmax,xmin,ymax,ymin;
float umin,umax,uleft,uright,utop,ubottom;
float a[4];
void assign()
{
 dx=x2-x1;     dy=y2-y1;
 p1=-dx;       p2=dx;         p3=-dy;         p4=dy;
 q1=x1-xmin;   q2=xmax-x1;    q3=y1-ymin;     q4=ymax-y1;
 umin=0;       umax=1;
 uleft=q1/p1;  uright=q2/p2;  ubottom=q3/p3;  utop=q4/p4;
}
void compare()
{
  int i=0;
  float temp;
  if(uleft>=0&&uleft<=1)
  {   a[i]=uleft;    i++;   }
  if(uright>=0&&uright<=1)
  {   a[i]=uright;   i++;   }
  if(ubottom>=0&&ubottom<=1)
  {   a[i]=ubottom;  i++;   }
  if(utop>=0&&utop<=1)
  {   a[i]=utop;     i++;   }
  //compare  and i is length of a[] now
  for(int j=i-2;j>=0;j--)
  for(int k=0;k<=j;k++)
  {
     if(a[j]>=a[j+1])
     {
      temp=a[j];
      a[j]=a[j+1];
      a[j+1]=temp;
     }
  }
   if(i!=1)     //line starting from inside window
      umin=a[0];
   umax=a[i-1];
}
void initialize()
{
 int gd,gm;
 detectgraph(&gd,&gm);
 initgraph(&gd,&gm,"");
 line(320,0,320,480);
 line(0,240,640,240);
}
void display()
{
 line(320,0,320,480);
 line(0,240,640,240);
 rectangle(320+xmin,240-ymax,320+xmax,240-ymin);
 line(320+(x1+(dx*umin)),240-(y1+(dy*umin)),320+(x1+(dx*umax)),240-(y1+(dy*umax)));
}
void main()
{
  clrscr();
  cout<<"Enter window co-ordinates\nEnter xmin and xmax\n";
  cin>>xmin>>xmax;
  cout<<"Enter ymin and ymax\n";
  cin>>ymin>>ymax;
  cout<<"Enter x and y co-ordinates of first end-point of line 1\n";
  cin>>x1>>y1;
  cout<<"Enter x and y co-ordinates of second end-point of line 1\n";
  cin>>x2>>y2;
  assign();
  initialize();
  display();
  delay(3000);
  closegraph();
  compare();     //compare() will not be called until graph is closed
  initialize();
  if(umin<umax)
     display();
  else
  {
     rectangle(320+xmin,240-ymax,320+xmax,240-ymin);
     outtextxy(10,10,"No line to draw");
  }
  getch();
}

