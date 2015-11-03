#include<iostream.h>
#include<conio.h>
#include<graphics.h>
#include<math.h>
void main()
{
 clrscr();
 float x,y,x1,x2,y1,y2,delx,dely,e;
 int gd,gm,i;
 cout<<"Enter co-ordinates of point 1\n";
 cin>>x1>>y1;
 cout<<"Enter co-ordinates of point 2\n";
 cin>>x2>>y2;
 x1=320+x1;
 x2=320+x2;
 y1=240-y1;
 y2=240-y2;
 detectgraph(&gd,&gm);
 initgraph(&gd,&gm,"");
 line(320,0,320,480);
 line(0,240,640,240);
 if(x1==x2&&y1==y2)
  putpixel(x1,y1,WHITE);
 else
 {
  delx=abs(x2-x1);
  dely=abs(y2-y1);
  x=x1;
  y=y1;
  e=(2*dely)-delx;
  i=1;
  do
  {
   putpixel(x,y,WHITE);
   while(e>=0)
   {
    if(y1>y2)
     y=y-1;
    else
     y=y+1;
    e=e-(2*delx);
   }
   if(x1>x2)
    x=x-1;
   else
    x=x+1;
   e=e+(2*dely);
   i=i+1;
  }while(i<=delx);
 }
 getch();
}