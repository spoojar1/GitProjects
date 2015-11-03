/*
BEZIER CURVES
*/
#include<iostream.h>
#include<conio.h>
#include<graphics.h>
#include<Math.h>
typedef struct
{
 int x;
 int y;
}PT;
void initialize()
{
 int gd,gm;
 detectgraph(&gd,&gm);
 initgraph(&gd,&gm,"");
}
void display(PT z[],int count)
{
 for(int i=0;i<(count-1);i++)
     line(z[i].x,z[i].y,z[i+1].x,z[i+1].y);
}
void main()
{
 clrscr();
 int n,count;
 int approx;
 int x,y;
 int i;
 PT a[1000],temp[1000];
 cout<<"Enter no of control points to be entered\n";
 cin>>n;
 for(i=0;i<n;i++)
 {
  cout<<"Enter x and y co-ordinates of control point "<<(i+1)<<"\n";
  cin>>a[i].x>>a[i].y;
 }
 approx=sqrt(pow(a[0].x-a[n-1].x,2)+pow(a[0].y-a[n-1].y,2));
 approx=approx/10;
 initialize();
 for(i=0;i<n;i++)
     temp[i]=a[i];
 count=n;
 do
 {
  for(i=0;i<(count-1);i++)
  {
   x=(a[i].x+a[i+1].x)/2;
   y=(a[i].y+a[i+1].y)/2;
   temp[i+1].x=x;
   temp[i+1].y=y;
  }
  temp[i+1]=a[i];  //for last point
  count=i+2;       //no of points in temp
  for(i=0;i<count;i++)
      a[i]=temp[i];
 }while(count<=approx);  //any appropriate no
 display(a,count);
 getch();
}
