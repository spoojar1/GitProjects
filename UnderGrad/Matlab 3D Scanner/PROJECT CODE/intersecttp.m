function []=intersect(Xc,Yc,Zc,p11,p12,p13,p21,p22,p23)

x1=Xc(p11);
x2=Xc(p12);
x3=Xc(p13);

y1=Yc(p11);
y2=Yc(p12);
y3=Yc(p13);

z1=Zc(p11);
z2=Zc(p12);
z3=Zc(p13);

A=y1*(z2-z3)+y2*(z3-z1)+y3*(z1-z2);
B=z1*(x2-x3)+z2*(x3-x1)+z3*(x1-x2);
C=x1*(y2-y3)+x2*(y3-y1)+x3*(y1-y2);
D=x1*(y2*z3-y3*z2)+x2*(y3*z1-y1*z3)+x3*(y1*z2-y2*z1);

fprintf('A: %d B: %d C: %d D: %d \n',A,B,C,D);

x1=Xc(p21);
x2=Xc(p22);
x3=Xc(p23);

y1=Yc(p21);
y2=Yc(p22);
y3=Yc(p23);

z1=Zc(p21);
z2=Zc(p22);
z3=Zc(p23);

A=y1*(z2-z3)+y2*(z3-z1)+y3*(z1-z2);
B=z1*(x2-x3)+z2*(x3-x1)+z3*(x1-x2);
C=x1*(y2-y3)+x2*(y3-y1)+x3*(y1-y2);
D=x1*(y2*z3-y3*z2)+x2*(y3*z1-y1*z3)+x3*(y1*z2-y2*z1);

fprintf('A: %d B: %d C: %d D: %d \n',A,B,C,D);

end