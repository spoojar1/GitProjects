//2-D TRANSFORMATION
#include<iostream.h>
#include<conio.h>
#include<graphics.h>
#include<math.h>
#include<dos.h>
typedef float Matrix3x3[3][3];
typedef float Matrix9x3[9][3];
Matrix9x3 matrix;
void display(Matrix9x3,int);
float toRadians(float a)
{
 float ratio=3.14/180;
 return a*ratio;
}
void multiply(Matrix9x3 a,Matrix3x3 b,int row)
{
  int i,j,sum;
  Matrix9x3 temp;    //rx3
  for(i=0;i<row;i++)
  {
   for(j=0;j<3;j++)
   {
    sum=0;
    for(int k=0;k<3;k++)
     sum=sum+(a[i][k]*b[k][j]);
    temp[i][j]=sum;
   }
  }

  for(i=0;i<row;i++)
  for(j=0;j<3;j++)
    a[i][j]=temp[i][j];
}
void identity(Matrix3x3 m)
{
 int i,j;
 for(i=0;i<3;i++)
  for(j=0;j<3;j++)
   m[i][j]=(i==j);
}
void translate(float tx,float ty,int row)
{
 Matrix3x3 m;
 identity(m);
 m[2][0]=tx;
 m[2][1]=ty;
 multiply(matrix,m,row);
}
void scale(float sx,float sy,int row,int x,int y)
{
 Matrix3x3 m;
 identity(m);
 m[0][0]=sx;
 m[1][1]=sy;
 m[2][0]=x*(1-sx);     // fixed pt
 m[2][1]=y*(1-sy);     // fixed pt
 multiply(matrix,m,row);
}
void rotate(float a,int row,int x,int y)
{
 Matrix3x3 m;
 a=toRadians(a);
 identity(m);
 m[0][0]=cos(a);
 m[0][1]=sin(a);
 m[1][0]=-sin(a);
 m[1][1]=cos(a);
 m[2][0]=y*sin(a)+x*(1-cos(a));
 m[2][1]=y*(1-cos(a))-x*sin(a);
 multiply(matrix,m,row);
}
void reflect(int x,int y,int row)
{
 Matrix3x3 m;
 identity(m);
 m[0][0]=y;
 m[1][1]=x;
 multiply(matrix,m,row);
}
void initialize()
{
 int gd,gm;
 detectgraph(&gd,&gm);
 initgraph(&gd,&gm,"");
 line(320,0,320,480);
 line(0,240,640,240);
}
void display(Matrix9x3 m,int n)
{
 int poly[20];
 for(int i=0;i<n;i++)
 for(int j=0;j<2;j++)
  if(j==0)
   poly[(2*i)+j]=320+m[i][j];
  else
   poly[(2*i)+j]=240-m[i][j];
 poly[2*n]=poly[0];
 poly[(2*n)+1]=poly[1];

 drawpoly(n+1,poly);
}
int input(Matrix9x3 a)
{
 int n;
 cout<<"Enter number of vertices in the polygon\n";
 cin>>n;
 for(int i=0;i<n;i++)
 {
  cout<<"Enter co-ordinates of the vertex "<<(i+1)<<"\n";
  for(int j=0;j<2;j++)
   cin>>a[i][j];
  a[i][j]=1;
 }
 return n;
}
void main()
{
 clrscr();
 int ch,n,x,y;
 again:
 cout<<"MENU\n1:Translate\t2:Scale \t3:Rotate\t4:Reflect\t5:Exit\nEnter your choice\n";
 cin>>ch;
 if(ch!=5)
{n=input(matrix);
 initialize();
 display(matrix,n);
 delay(2000);
 closegraph();
 switch(ch)
 {
  case 1:
  {
   float tx,ty;
   cout<<"Enter tx and ty\n";
   cin>>tx>>ty;
   translate(tx,ty,n);
   initialize();
   display(matrix,n);
   delay(2000);
   cleardevice();
   break;
  }
  case 2:
  {
   float sx,sy;
   cout<<"Enter sx and sy\n";
   cin>>sx>>sy;
   cout<<"Enter fixed point about which object has to be scaled\n";
   cin>>x>>y;
   scale(sx,sy,n,x,y);
   initialize();
   display(matrix,n);
   delay(2000);
   cleardevice();
   break;
  }
  case 3:
  {
   float a;
   cout<<"Enter angle by which figure has to be rotated\n";
   cin>>a;
   cout<<"Enter fixed point about which object has to be rotated\n";
   cin>>x>>y;
   rotate(a,n,x,y);
   initialize();
   display(matrix,n);
   delay(2000);
   cleardevice();
   break;
  }
  case 4:
  {
   int c;
   cout<<"1:Along X axis\n2:Along Y axis\n";
   cin>>c;
   if(c==1)
    reflect(-1,1,n);
   else
    reflect(1,-1,n);
   initialize();
   display(matrix,n);
   delay(2000);
   cleardevice();
   break;
  }
 }
 goto again;
}
 getch();
}
