#include<iostream.h>
#include<conio.h>
#include<graphics.h>
#include<math.h>
#include<dos.h>
void main()
{
 clrscr();
 float x,y,x1,x2,y1,y2,dx,dy,delx,dely;
 int length,gd,gm,i;
 cout<<"Enter co-ordinates of point 1\n";
 cin>>x1>>y1;
 cout<<"Enter co-ordinates of point 2\n";
 cin>>x2>>y2;
 detectgraph(&gd,&gm);
 initgraph(&gd,&gm,"");
 for(i=0;i<=640;i++)
  putpixel(i,240,WHITE);
 for(i=0;i<=480;i++)
  putpixel(320,i,WHITE);
 x1=x1+320;
 y1=240-y1;
 x2=x2+320;
 y2=240-y2;
 if(x1==x2&&y1==y2)
  putpixel(x1,y1,WHITE);
 else
 {
   dx=abs(x2-x1);
   dy=abs(y2-y1);
   if(dx>=dy)
    length=dx;
   else
    length=dy;
   dx=(x2-x1)/length;
   dy=(y2-y1)/length;
   float sign(float);
   x=x1+0.5*sign(dx);
   y=y1+0.5*sign(dy);
   i=1;
   while(i<=length)
   {
    putpixel(x,y,WHITE);
    x=x+dx;
    y=y+dy;
     i=i+1;
   }
 }
 getch();
}
float sign(float x)
{
 if(x>0)
  return 1;
 else
  return -1;
}