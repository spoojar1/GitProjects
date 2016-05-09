% Edge detection
clear all
clc

X=[];
Y=[];
Z=[];

num=24;
th=(2*pi)/num;    % angle of rotation
threshold=20;

theta=pi/8;  % angle between laser and camera

%crop coordinates  i.e (c1,c2)  to (c1+w,c2+h)
c1=768;     %c1 should be approx center of original image
c2=700;     
w=150;
h=550;

c1=c1-w;                % 300 on both side of middle
w=2*w;

%view axis
x1=600;   
y1=600;    
z1=0;      
z2=600;     %   z2 > h

fol_name='images2';
q=num/2;  %no of columns

for no=1:1:num
    
    s=num2str(no);
    s=strcat(fol_name,'/',s,'.jpg');
    a = imread(s);
    a=imcrop(a,[c1 c2 w h]);     %(x1,y1) to (x1+w,y1+h)
    
    %figure(1);    
    
    subplot(2,q,no), imshow (uint8(a));
    
    a=a(:,:,1);   % red component extraction
    [row col]=size(a);
    
    %highlighting laser pixels i.e. turning non-laser pixels to white 
    
    for x=1:1:row
        for y=1:1:col
            if(a(x,y)>20)
                a(x,y)=255;
            end;
        end;
    end;
    
    
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
    
    %figure(2);
    subplot(2,q,no), imshow (uint8(a));
    
    b=a;
    
    %noise removal
    for x=1:1:row
        z=0;
        for y=1:1:col
            if(b(x,y)==0 && z<=1)
                z=z+1;
                b(x,y)=0;
            else
                b(x,y)=255;
            end;
        end;
    end;
    
    %figure(3);
    subplot(2,q,no), imshow (b);

    % 3d co-ordinate calculation
    diff=40;
    for x=1:diff:row
        for y=1:1:col
            if(b(x,y)==0)
                X(no,x)=y-(col/2);  %-10 just to avoid neg values of x  here c(x,1)=x axis dist   c(x,2)=y axis dist
                break;
            end;    
        end;
    end;
    
    s=0;
    i=0;     %count of rows having intensity not equal to 0(black)
    for x=1:diff:row
        s=s+X(no,x);
        if(X(no,x)~=0)
            i=i+1;
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
    
    % Z is x , X is y and Y is z
    
    %{
    for x=1:1:row
        for y=1:1:col
            if(b(x,y)==0)
                X(no,x)=y-(col/2);  %-10 just to avoid neg values of x  here c(x,1)=x axis dist   c(x,2)=y axis dist
                Y(no,x)=row-x;
                Z(no,x)=X(no,x)/tan(th);
                break;
            end;    
        end;
    end;
    %}
    
    %disp('X');
    %disp(X);

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

for x=1:1:row
    if(x~=row)
        fprintf('x : %d \n',x);
        if(x+(diff-1)<=row)
            X(:,x+1:x+(diff-1))=[];
            Y(:,x+1:x+(diff-1))=[];
            Z(:,x+1:x+(diff-1))=[];
        else
            if(x+1<=size(X,2))
                X(:,x+1:row)=[];
                Y(:,x+1:row)=[];
                Z(:,x+1:row)=[];
            end;
            break;
        end
        disp(size(X));
        row=row-(diff-1);
        fprintf('row : %d \n',row);
    end
end

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

scatter3(Xc,Yc,Zc,3);
xlabel('X');
ylabel('Y');
zlabel('Z');
    
    
xlim([-x1 x1]);
ylim([-y1 y1]);
zlim([z1 z2]);

%figure(5);

r=40;            %rho radius of ball
del=2*r;         %delta=2*rho     size of each voxel

pts=size(Zc,2);  % 1 is for row and 2 for col

x=300;
y=400;
z=600;

% limits on the voxel cube's boundaries used in find_seed_triangle
x2=ceil(2*x/del);      
y2=ceil(2*y/del);      
z2=ceil(z/del);        

  
for i=1:1:x2
    for j=1:1:y2
        for k=1:1:z2
            voxel(i,j,k)=struct('ll',[],'consider',[]);
        end
    end
end

%voxel is a 3d structure with each subcube containing a list of INDICES of
%points falling in that subcube in 3d space
%so for eg. voxel(5,6,7).ll=[ 12 17 30 45 ]

for i=1:1:pts
    Vx=(x+Xc(i))/del;
    Vy=(y-Yc(i))/del;
    Vz=(Zc(i)+1)/del;  
    
    %fprintf('Vx : %d Vy : %d Vz : %d  \n',Vx,Vy,Vz);
    voxel(ceil(Vx),ceil(Vy),ceil(Vz)).ll=cat(2,voxel(ceil(Vx),ceil(Vy),ceil(Vz)).ll,i);
end

%{
for i=1:1:x2
    for j=1:1:y2
        for k=1:1:z2
            if(~isempty(voxel(i,j,k).ll))
               fprintf('%d %d %d \n',i,j,k);
               disp(voxel(i,j,k).ll);
            end
        end
    end
end
%}
disp('dgfgfg');

%{
x=[];
y=[];
z=[];

for i=1:1:x2
    for j=1:1:y2
        for k=1:1:z2
            if(~isempty(voxel(i,j,k).ll))
               x=cat(2,x,i);
               y=cat(2,y,j);
               z=cat(2,z,k);
            end
        end
    end
end

scatter3(x,y,z,3);
xlabel('X');
ylabel('Y');
zlabel('Z');

%}

%FIND SEED TRIANGLE

%{
figure(1);
tr=trimesh(TRI,reshape(Xc,pts,1),reshape(Yc,pts,1),reshape(Zc,pts,1));

xlim([-600 600]);
ylim([-600 600]);
zlim([0 600]);
%}

min_x=min(Xc)-5;
max_x=max(Xc)+5;
min_y=min(Yc)-5;
max_y=max(Yc)+5;
min_z=min(Zc)-5;
max_z=max(Zc)+5;

fid = fopen('output.txt', 'w');
fprintf(fid,'');
fclose(fid);

fid = fopen('output.txt','a');

front=struct('e1',{},'e2',{},'op',{},'c',[]);
count=1;
TRI=[];

while(true)
    fprintf('\n !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! \ncount : %d \n',count);
    
    save('workspace.mat');
    if(~find_seed_triangle())
        disp('yes no seed');
        break; 
    end
    load('workspace.mat');
    
    TRI=[TRI;[pt1 pt2 pt3]];
    fprintf('len of TRI %d \n',size(TRI,1));
    fprintf(fid,'CONFIRMED seed points : pt1 pt2 pt3 %d %d %d \n',pt1,pt2,pt3);
        
    front=[front;struct('e1',pt1,'e2',pt2,'op',pt3,'c',[cijk.x cijk.y cijk.z])];
    front=[front;struct('e1',pt1,'e2',pt3,'op',pt2,'c',[cijk.x cijk.y cijk.z])];
    front=[front;struct('e1',pt2,'e2',pt3,'op',pt1,'c',[cijk.x cijk.y cijk.z])];
    
    %fprintf(fid,'count : %d B4 ENTERING INSIDE WHILE #####################################################################\n',count);
    while(~isempty(front))
                 
        % assigning values for ball_pivot
        i=struct('x',Xc(front(1).e1),'y',Yc(front(1).e1),'z',Zc(front(1).e1));
        j=struct('x',Xc(front(1).e2),'y',Yc(front(1).e2),'z',Zc(front(1).e2));
        c=struct('x',front(1).c(1),'y',front(1).c(2),'z',front(1).c(3));
        
        temp=[];
        %disp(temp);
        save('workspace.mat');
        [cijk,new_pt]=ball_pivot();
        
        %fprintf(fid,'$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$');
        save('workspace.mat');
        if(~isnan(cijk.x))
            %if(not_used(new_pt))
                fprintf(fid,'LEN OF FRONT : %d \n',size(front,1));
                fprintf(fid,'count : %d BALL PIVOT WORKED ##################################################################### \n',count);
                TRI=[TRI;[front(1).e1 front(1).e2 new_pt]];
                fprintf('len of TRI : %d \n',size(TRI,1));
                save('workspace.mat');
                front=[front;struct('e1',front(1).e1,'e2',new_pt,'op',front(1).e2,'c',[cijk.x cijk.y cijk.z])];
                front=[front;struct('e1',front(1).e2,'e2',new_pt,'op',front(1).e1,'c',[cijk.x cijk.y cijk.z])];
            %end
        end
        front(1)=[];
        fprintf(fid,'LEN OF FRONT : %d \n',size(front,1));
    end
    count=count+1;
end

%i and j are end points of active edge
%o is the point opposite to edge ij
%r is rho
%c is the center of the rho sphere touching i , j and o
%k is the new point to be checked

%BALL PIVOT

%i and j are end points of active edge
%o is the point opposite to edge ij
%r is rho
%c is the center of the rho sphere touching i , j and o
%k is the new point to be checked


%{
i=struct('x',10,'y',10,'z',13.6);
j=struct('x',10,'y',10,'z',10);
k=struct('x',6.5,'y',13.5,'z',14.2);
% k=struct('x',1.5,'y',13.5,'z',14.2); for imaginary ans
o=struct('x',13.6,'y',10,'z',15.2);
c=struct('x',13,'y',10,'z',11.8);
%r=3.5;

%cijk=struct('x','','y','','z','');
save('workspace.mat');

%cijk=ball_pivot(i,j,o,r,c,k);
cijk=ball_pivot();
%}

%normals with surface

%[u v w]=surfnorm(Z,X,Y);  %can't use Zc,Xc,Yc here since matrices should be atleast 3X3

%normal vectors without surface
%quiver3(Z,X,Y,u,v,w);


%{
%TETRAHEDRON MESH
T=delaunay3(Zc,Xc,Yc);
X = [Zc(:) Xc(:) Yc(:)];
tetramesh(T,X);
%}


%{
%TRIANGULAR MESH
T=delaunay(Zc,Xc);
trimesh(T,Zc,Xc,Yc);
%}



