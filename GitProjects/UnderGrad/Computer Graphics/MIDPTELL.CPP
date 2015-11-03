#include<iostream.h>
#include<conio.h>
#include<graphics.h>
#include<math.h>
void display(int,int,int,int);
void main()
{
 clrscr();
 float x,y,rx,ry,d1,d2,dx,dy,xc,yc;
 int gd,gm;
 cout<<"Enter length of the axis along x direction\n";
 cin>>rx;
 cout<<"Enter length of the axis along y direction\n";
 cin>>ry;
 cout<<"Enter co-ordinates of the center\n";
 cin>>xc>>yc;
 xc=xc+320;
 yc=240-yc;
 detectgraph(&gd,&gm);
 initgraph(&gd,&gm,"");
 line(320,0,320,480);
 line(0,240,640,240);
 x=0;
 y=ry;
 d1=ry*ry-rx*rx*ry+(rx*rx)/4;
 dx=2*ry*ry*x;
 dy=2*rx*rx*y;
 do
 {
  display(xc,yc,x,y);
  if(d1<0)
  {
   x=x+1;
   y=y;
   dx=dx+2*ry*ry;
   d1=d1+dx+ry*ry;
  }
  else
  {
   x=x+1;
   y=y-1;
   dx=dx+2*ry*ry;
   dy=dy-2*rx*rx;
   d1=d1+dx-dy-ry*ry;
  }
 }while(dx<dy);

 d2=ry*ry*(x+0.5)*(x+0.5)+rx*rx*(y-1)*(y-1)-rx*rx*ry*ry;
 do
 {
  display(xc,yc,x,y);
  if(d2>0)
  {
   x=x;
   y=y-1;
   dy=dy-2*rx*rx;
   d2=d2-dy+rx*rx;
  }
  else
  {
   x=x+1;
   y=y-1;
   dy=dy-2*rx*rx;
   dx=dx+2*ry*ry;
   d2=d2+dx-dy+rx*rx;
  }
 }while(y>0);
 getch();
}
void display(int xc,int yc,int x,int y)
{
 putpixel(xc+x,yc+y,WHITE);
 putpixel(xc+x,yc-y,WHITE);
 putpixel(xc-x,yc+y,WHITE);
 putpixel(xc-x,yc-y,WHITE);
}