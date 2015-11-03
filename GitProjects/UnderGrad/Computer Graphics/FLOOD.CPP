/*
BOUNDARY FILL
*/
#include<iostream.h>
#include<conio.h>
#include<graphics.h>
#include<dos.h>
void boundarystk(int sx,int sy,int fc,int bc);
void init()
{
 int gd,gm;
 detectgraph(&gd,&gm);
 initgraph(&gd,&gm,"");
}
void main()
{
 clrscr();
 int poly[20],n,x,y,fill,tp;
 cout<<"Enter no of vertices of the polygon\n";
 cin>>n;
 for(int i=0;i<n;i++)
 {
  cout<<"Enter x and y co-ordinates of point "<<(i+1)<<"\n";
  cin>>x>>y;
  poly[2*i]=320+x;
  poly[2*i+1]=240-y;
 }
 poly[2*n]=poly[0];
 poly[2*n+1]=poly[1];
 cout<<"Enter co-ordinates of any point inside the polygon\n";
 cin>>x>>y;
 cout<<"Enter fill color\n";
 cin>>fill;
 getch();
}
void boundarystk(int sx,int sy,int fc,int bc)
{
int stkx[10000];
int stky[10000];
int top=0;
stkx[0]=sx;
stky[0]=sy;
while(top!=-1)
{
 if(getpixel(stkx[top],stky[top])!=bc && getpixel(stkx[top],stky[top])!=fc )
 {
  putpixel(stkx[top],stky[top],fc);
  stkx[top+1]=stkx[top]+1;    //x+1
  stky[top+1]=stky[top];      //y
  stkx[top+2]=stkx[top];      //x
  stky[top+2]=stky[top]+1;    //y+1

  stkx[top+3]=stkx[top]-1;    //x-1
  stky[top+3]=stky[top];      //y
  stkx[top+4]=stkx[top];      //x
  stky[top+4]=stky[top]-1;    //y-1

  stkx[top+5]=stkx[top]+1;    //x+1
  stky[top+5]=stky[top]+1;    //y+1
  stkx[top+6]=stkx[top]-1;    //x-1
  stky[top+6]=stky[top]+1;    //y+1

  stkx[top+7]=stkx[top]-1;    //x-1
  stky[top+7]=stky[top]+1;    //y+1
  stkx[top+8]=stkx[top]-1;    //x-1
  stky[top+8]=stky[top]-1;    //y-1
  top=top+8;
 }//end if
 top--;
}//end while
}//end bdry