#include<iostream.h>
#include<conio.h>
#include<graphics.h>
#include<math.h>
void display(int,int,int,int);
void main()
{
 clrscr();
 float x,y,r,p,xc,yc;
 int gd,gm;
 cout<<"Enter radius of the circle\n";
 cin>>r;
 cout<<"Enter co-ordinates of the center\n";
 cin>>xc>>yc;
 xc=320+xc;
 yc=240-yc;
 detectgraph(&gd,&gm);
 initgraph(&gd,&gm,"");
 line(320,0,320,480);
 line(0,240,640,240);
 x=0;
 y=r;
 p=1.25-r;
 do
 {
  display(xc,yc,x,y);
  if(p<0)
  {
   x=x+1;
   p=p+2*x+1;
  }
  else
  {
   x=x+1;
   y=y-1;
   p=p+2*x-2*y+1;
  }
 }while(x<y);
 getch();
}
void display(int xc,int yc,int x,int y)
{
 putpixel(xc+x,yc+y,WHITE);
 putpixel(xc+x,yc-y,WHITE);
 putpixel(xc-x,yc+y,WHITE);
 putpixel(xc-x,yc-y,WHITE);
 putpixel(xc+y,yc+x,WHITE);
 putpixel(xc+y,yc-x,WHITE);
 putpixel(xc-y,yc+x,WHITE);
 putpixel(xc-y,yc-x,WHITE);
}