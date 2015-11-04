%clear all
%clc

load('workspace1.mat');

X=[];
Y=[];
Z=[];

%{
fprintf('theta : %d',theta);
fprintf('th : %d',th);
fprintf('thresh : %d',thresh);
fprintf('theta : %d',theta);
fprintf('theta : %d',theta);
fprintf('theta : %d',theta);
fprintf('theta : %d',theta);
fprintf('theta : %d',theta);
fprintf('theta : %d',theta);

%}

%num=16;
th=(2*pi)/num;    % angle of rotation
%threshold=20;

%theta=pi/8;  % angle between laser and camera

%crop coordinates  i.e (c1,c2)  to (c1+w,c2+h)

%{
c1=105;     %c1 should be approx center of original image
c2=0;     
w=105;
h=213;
%}

c1=c1-w;                % 300 on both side of middle
w=2*w;

%fol_name='vaseline';
%fol_name='dermicool';

div=16;
%div=1;
q=num/div;  %no of rows

%num=1;
%q=1;

for no=1:1:num
    
    fprintf('cghfgh');
    s=num2str(no);
    s=strcat(fol_name,'/',s,'.jpg');
    a = imread(s);
    a=imcrop(a,[c1 c2 w h]);     %(x1,y1) to (x1+w,y1+h)
    
    figure(1);  
    subplot(q,div,no), imshow (uint8(a));
    
    a=a(:,:,1);   % red component extraction
    [row col]=size(a);
    
    %thresholding i.e. selecting appropriate laser pixels and turning them black
    
    for x=1:1:row
        for y=1:1:col
            if(a(x,y)>threshold)
                a(x,y)=0;
            else
                a(x,y)=255;
            end;
        end;
    end;
    
    figure(2);
    subplot(q,div,no), imshow (uint8(a));
    
    b=a;
    
    %noise removal
    for x=1:1:row
        z=0;
        for y=round(col/2):1:col
            if(b(x,y)==0 && z<1)
                z=z+1;
                %b(x,y)=0;
                %fprintf('x: %d y: %d z: %d\n',x,y,z);
            else
                %fprintf('------z: %d\n',z);
                b(x,y)=255;
            end;
        end;
    end;
    
    figure(3);
    subplot(q,div,no), imshow (b);

    % 3d co-ordinate calculation
    
    for x=1:diff:row
        for y=round(col/2):1:col
            if(b(x,y)==0)
                X(no,x)=y-(col/2);  %-10 just to avoid neg values of x  here c(x,1)=x axis dist   c(x,2)=y axis dist
                break;
            end;    
        end;
    end;
    
    row=size(X,2);
    
    s=0;
    i=0;     %count of rows having intensity not equal to 0(black)
    for x=1:diff:row
        if(x<=size(X,2))
            s=s+X(no,x);
            if(X(no,x)~=0)
                i=i+1;
            end
        end
    end;
    
    if(i~=0)
        avg=s/i;
    else
        avg=s/row;
    end
    
    for x=1:diff:row
        if(X(no,x)==0 || X(no,x)<(avg-10) || X(no,x)>(avg+10) )
            X(no,x)=avg;
        end
    end;
    
    for x=1:diff:row
        Y(no,x)=row-x;
        Z(no,x)=X(no,x)/tan(theta);    
    end;
    
end;    %end of main loop

%3D rotation
angle=th;
for z=2:1:num
    temp1=[cos(angle) -sin(angle) 0 0;sin(angle) cos(angle) 0 0;0 0 1 0;0 0 0 1];    
    for x=1:diff:row
        temp2=[Z(z,x);X(z,x);Y(z,x);0];
        temp2=temp1*temp2;
        Z(z,x)=temp2(1,1);
        X(z,x)=temp2(2,1);
        Y(z,x)=temp2(3,1);
    end
    angle=angle+th;
end 

X1=[];
Y1=[];
Z1=[];

for x=1:1:num
    tx=[];
    ty=[];
    tz=[];
    for y=1:diff:row
        tx=cat(2,tx,X(x,y));
        ty=cat(2,ty,Y(x,y));
        tz=cat(2,tz,Z(x,y));
    end
    X1=cat(1,X1,tx);
    Y1=cat(1,Y1,ty);
    Z1=cat(1,Z1,tz);
end

X=X1;
Y=Y1;
Z=Z1;

row=size(X,2);

Xc=[];
Yc=[];
Zc=[];
for z=1:1:num
    Xc=cat(2,Xc,Z(z,:));
    Yc=cat(2,Yc,X(z,:));
    Zc=cat(2,Zc,Y(z,:));
end

X=[];
Y=[];
Z=[];
a=[];
b=[];

% Xc is x , Yc is y and Zc is z      

figure(4);
%figure(1);

scatter3(Xc,Yc,Zc,3);
xlabel('X');
ylabel('Y');
zlabel('Z');
    
    
xlim([-x1 x1]);
ylim([-y1 y1]);
zlim([0 z1]);

save('workspace1.mat');