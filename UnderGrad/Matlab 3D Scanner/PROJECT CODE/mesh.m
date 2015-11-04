load('workspace1.mat');

%{
figure(1);
tr=trimesh(TRI,reshape(Xc,pts,1),reshape(Yc,pts,1),reshape(Zc,pts,1));

xlim([-250 250]);
ylim([-310 310]);
zlim([0 600]);
%}

%{
figure(1);
tr=trimesh(TRI,reshape(Xc,pts,1),reshape(Yc,pts,1),reshape(Zc,pts,1));

xlim([-100 100]);
ylim([-100 100]);
zlim([0 200]);
%}

%{
figure(2)
trisurf(TRI_total,Xc,Yc,Zc);
xlim([-400 400]);
ylim([-400 400]);
zlim([0 700]);
%}

%{
figure(1);
tr=trimesh(TRI,reshape(Xc,pts,1),reshape(Yc,pts,1),reshape(Zc,pts,1));

xlim([-400 400]);
ylim([-400 400]);
zlim([0 700]);

figure(1);
tr=trimesh(TRI,reshape(Xc,pts,1),reshape(Yc,pts,1),reshape(Zc,pts,1));

xlim([-300 300]);
ylim([-300 300]);
zlim([0 600]);

figure(2);
tr=trimesh(TRI_temp,reshape(Xc,pts,1),reshape(Yc,pts,1),reshape(Zc,pts,1));

xlim([-400 400]);
ylim([-400 400]);
zlim([0 700]);

figure(1);
tr=trimesh(TRI_total,reshape(Xc,pts,1),reshape(Yc,pts,1),reshape(Zc,pts,1));

xlim([-400 400]);
ylim([-400 400]);
zlim([0 700]);

TRI_total=[];
for i=1:1:pass-1
TRI_total=[TRI_total;TRI_3d(i).pass];
end

edge_total=[];
for i=1:1:pass-1
edge_total=[edge_total;edge_3d(i).pass];
end

for i=1:1:pass

figure(i);
tr=trimesh(TRI_3d(i).pass,reshape(Xc,pts,1),reshape(Yc,pts,1),reshape(Zc,pts,1));

xlim([-400 400]);
ylim([-400 400]);
zlim([0 700]);

end

%}
%#########################################################################

%rp=[20;25;26;27;28;29;30;31;32;33;34;35];
%rp=[5];

no=size(rp,2);

save('workspace1.mat');
load('workspace1.mat');

fid = fopen('output.txt', 'w');
fprintf(fid,'');
fclose(fid);

fid = fopen('output.txt','a');
pass=1;
%pass=11;

%r=rp(1);
del=2*rp(1);        %delta=2*rho     size of each voxel

TRI_3d=struct('pass',[]);
edge_3d=struct('pass',[]);

%+++++++++++++++++++++++++
for i=1:1:no
   bdry_3d(i)=struct('pass',[]); 
end
%+++++++++++++++++++++++++

%TRI_3d.pass=TRI_init;
%edge_3d.pass=edge_init;

%++++++++++++++++++++++++++
%bdry_3d(1).pass=bdry_init;
%++++++++++++++++++++++++++

%------------------------

TRI_total=[];
edge_total=[];
%bdry_pass=[];

%----------------------
%TRI_total=TRI_3d(1).pass;
%edge_total=edge_3d(1).pass;
%------------------------

save('workspace1.mat');
load('workspace1.mat');
 
while(pass<=no)
    
    TRI=[];
    edgelist=[];

    r=rp(pass);
    save('workspace1.mat');
    
    del1=2*r;        %delta=2*rho     size of each voxel
    
    fprintf(fid,' r : %d \n',r);
    
    pts=size(Zc,2);  % 1 is for row and 2 for col
    
    x=x1/2;
    y=y1/2;
    z=z1;
    %z=700;
    %z=220;
    
    fprintf(fid,' del : %d del1 : %d \n',del,del1);
    
    % limits on the voxel cube's boundaries used in find_seed_triangle
    x2=ceil(2*x/del);
    y2=ceil(2*y/del);     
    z2=ceil(z/del); 
      
    fprintf('x2 : %d y2 : %d z2 : %d \n',x2,y2,z2);
    
    clear voxel;
    
    save('workspace1.mat');
    load('workspace1.mat');
    
    for i=1:1:x2
        for j=1:1:y2
            for k=1:1:z2
                voxel(i,j,k)=struct('ll',[],'consider',[]);
                fprintf('len of ll %d %d %d : %d \n',i,j,k,size(voxel(i,j,k).ll,2));
            end
        end
    end
        
    save('workspace1.mat');
    load('workspace1.mat');
    
    for i=1:1:pts
        Vx=(x+Xc(i))/del;
        Vy=(y-Yc(i))/del;
        Vz=(Zc(i)+1)/del;  

        fprintf('\n x : %d y : %d z : %d \n',Xc(i),Yc(i),Zc(i));
        fprintf('Vx : %d Vy : %d Vz : %d  %d \n',Vx,Vy,Vz,i);
        voxel(ceil(Vx),ceil(Vy),ceil(Vz)).ll=cat(2,voxel(ceil(Vx),ceil(Vy),ceil(Vz)).ll,i);
        fprintf('len of ll %d \n',size(voxel(ceil(Vx),ceil(Vy),ceil(Vz)).ll,2));

    end

    save('workspace1.mat');
    load('workspace1.mat');
    %return;
    
    if(pass>1)
        if(~np_voxel_selects)
            fprintf(fid,'no more voxels left \n');
            break;
        end
    end
    
    load('workspace1.mat');
    
    front=struct('e1',{},'e2',{},'op',{},'c',[]);
    count=1;
    
    fprintf(fid,'PASS %d \n\n',pass);
    fprintf(fid,'start time %d \n',fix(clock));
    while(true)
        fprintf(fid,'\n !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! \ncount : %d \n',count);

        save('workspace1.mat');
        
        if(~find_seed_triangle())
            disp('yes no seed');
            break; 
        end
        load('workspace1.mat');

        TRI=[TRI;[pt1 pt2 pt3]];
        TRI_total=[TRI_total;[pt1 pt2 pt3]];
        edgelist=[edgelist;[pt1 pt2];[pt1 pt3];[pt2 pt3]];
        
        %----------------------------------------------
        fprintf('NEW POINTS IN TRI : %d %d %d \n',pt1,pt2,pt3);
        fprintf(' r : %d \n',r);
        fprintf(fid,'len of TRI %d \n',size(TRI,1));
        fprintf(fid,'CONFIRMED seed points : pt1 pt2 pt3 %d %d %d \n',pt1,pt2,pt3);
        %----------------------------------------------

        front=[front;struct('e1',pt1,'e2',pt2,'op',pt3,'c',[cijk.x cijk.y cijk.z])];
        front=[front;struct('e1',pt1,'e2',pt3,'op',pt2,'c',[cijk.x cijk.y cijk.z])];
        front=[front;struct('e1',pt2,'e2',pt3,'op',pt1,'c',[cijk.x cijk.y cijk.z])];

        %fprintf(fid,'count : %d B4 ENTERING INSIDE WHILE #####################################################################\n',count);
        while(~isempty(front))

            temp=[];
            %disp(temp);
            save('workspace1.mat');
            [cijk,new_pt,bdry_edge]=ball_pivot();
            
            %[cijk,new_pt]=ball_pivot();
            
            
            if(bdry_edge==0)
                fprintf(fid,'HI THERE %d \n',size(bdry_3d(pass).pass,1));
                bdry_3d(pass).pass=[bdry_3d(pass).pass;[front(1).e1 front(1).e2]];
                fprintf(fid,'HI THERE %d \n',size(bdry_3d(pass).pass,1));
            end
           
            
            save('workspace1.mat');
            
            if(~isnan(cijk.x))
                %----------------------------------------------
                fprintf(fid,'NEW POINTS IN TRI : %d %d %d \n',front(1).e1,front(1).e2,new_pt);
                fprintf(fid,'LEN OF FRONT : %d \n',size(front,1));
                fprintf(fid,'BALL PIVOT WORKED ##################################################################### \n');
                %----------------------------------------------

                TRI=[TRI;[front(1).e1 front(1).e2 new_pt]];
                TRI_total=[TRI_total;[front(1).e1 front(1).e2 new_pt]];
                edgelist=[edgelist;[front(1).e1 new_pt];[front(1).e2 new_pt]];
               
                %----------------------------------------------
                fprintf(fid,'len of TRI : %d \n',size(TRI,1));
                %----------------------------------------------

                save('workspace1.mat');
                load('workspace1.mat');
                
                if(pass==1)
                    front=[front;struct('e1',front(1).e1,'e2',new_pt,'op',front(1).e2,'c',[cijk.x cijk.y cijk.z])];
                    front=[front;struct('e1',front(1).e2,'e2',new_pt,'op',front(1).e1,'c',[cijk.x cijk.y cijk.z])];
                else
                    if(~auipp(front(1).e1,new_pt))
                        front=[front;struct('e1',front(1).e1,'e2',new_pt,'op',front(1).e2,'c',[cijk.x cijk.y cijk.z])];
                    end
                    if(~auipp(front(1).e2,new_pt))
                        front=[front;struct('e1',front(1).e2,'e2',new_pt,'op',front(1).e1,'c',[cijk.x cijk.y cijk.z])];
                    end
                end
                
              %else
            end
            front(1)=[];
            
            %----------------------------------------------
            fprintf(fid,'LEN OF FRONT : %d \n',size(front,1));
            %----------------------------------------------
            for t=1:1:size(front,1)
                if(size(front,2)~=0)
                    %----------------------------------------------
                    %fprintf(fid,'e1 %d e2 %d op %d c.x %d c.y %d c.z %d\n',front(t).e1,front(t).e2,front(t).op,double(front(t).c(1)),double(front(t).c(2)),double(front(t).c(3)));
                    %----------------------------------------------
                end
            end
        end
       
        count=count+1;
        save('workspace1.mat');
        load('workspace1.mat');
    end

    fprintf(fid,' end time %d \n',fix(clock));
    TRI_3d(pass).pass=TRI;
    edge_total=[edge_total;edgelist];
    edge_3d(pass).pass=edgelist;
    
    pass=pass+1;
    save('workspace1.mat');
    load('workspace1.mat');
end

fprintf(fid,'complete atlast \n');