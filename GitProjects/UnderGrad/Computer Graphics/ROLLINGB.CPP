/*
ROLLING BALL
*/
#include<iostream.h>
#include<conio.h>
#include<graphics.h>
#include<math.h>
#include<dos.h>
void initialize()
{
 int gd,gm;
 detectgraph(&gd,&gm);
 initgraph(&gd,&gm,"");
}
double toRadians(float a)
{
 float ratio=3.14/180;
 return a*ratio;
}
void main()
{
 clrscr();
 double xa,ya,xb,yb,xc,yc;
 double a,r;
 double ac; //length AC
 double dist;
 double count=0;
 double temp,temp1,temp2,temp3,temp4,temp5,temp6,temp7,temp8,temp9;
 xa=-100;  ya=100;  xb=-100;  yb=-100;  xc=200;  yc=-100;
 a=33.69;
 a=toRadians(a);
 r=25;
 ac=sqrt(pow(xa-xc,2)+pow(ya-yc,2));
 dist=ac-(2*r);
 //triangle DIA
 temp1=(r/cos(a))+ya;  //y2
 temp2=temp1-ya;       //length of AD
 temp3=sqrt(pow(temp2,2)-pow(r,2));    //length of DI
 temp4=temp3+r;        //length of DF
 //triangle DEF
 temp5=(temp4*cos(a))+xa;  //x2
 temp6=temp1-sqrt(pow(temp4,2)-pow(temp5-xa,2));  //y2
 //(temp5,temp6)  co-ordinates of point F
 initialize();
 line(320+xa,240-ya,320+xb,240-yb);
 line(320+xa,240-ya,320+xc,240-yc);
 line(320+xb,240-yb,320+xc,240-yc);
 delay(1000);
 do
 {
  line(320+xa,240-ya,320+xb,240-yb);
  line(320+xa,240-ya,320+xc,240-yc);
  line(320+xb,240-yb,320+xc,240-yc);
  circle(320+temp5,240-temp6,r);
  temp7=temp5+1;   //incrementing x2 everytime
  temp8=1/cos(a);  //since x2-x1=1    //d for each iteration
  temp9=temp6-sqrt(pow(temp8,2)-pow(temp7-temp5,2));  //y2
  count=count+sqrt(pow(temp5-temp7,2)+pow(temp6-temp9,2)); //updating dist
  temp5=temp7;
  temp6=temp9;
  delay(30);
  cleardevice();
 }while(count<=dist);
 line(320+xa,240-ya,320+xb,240-yb);
 line(320+xa,240-ya,320+xc,240-yc);
 line(320+xb,240-yb,320+xc,240-yc);
 circle(320+temp5,240-temp6,r);
 getch();
}