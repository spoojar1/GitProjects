function [] = help_seed_func()

load('workspace1.mat');
%fprintf('   2 pts found in voxel : %d %d %d are %d %d\n',v1,v2,v3,pt1,pt2);

%av,bv and cv are 1X3 arrays storing voxel positions of 1st, 2nd and 3rd pt respectively
%eg: av=[30 24 10]
%pt1,pt2 and pt3 hold index nos of the 3 pts  
%eg: pt1=8816    pt2=3000
%v1 v2 and v3 are the positions of the voxel under consideration

i2=v1;
j2=v2;
k2=v3;

i=struct('x',Xc(pt1),'y',Yc(pt1),'z',Zc(pt1));
j=struct('x',Xc(pt2),'y',Yc(pt2),'z',Zc(pt2));
syms e1 e2 e3;

%pt1=-101   245    59
%pt2=-102   247    50

%e1=(i.x-x)^2+(i.y-y)^2+(i.z-z)^2-r^2;
%e2=(j.x-x)^2+(j.y-y)^2+(j.z-z)^2-r^2;
e1=strcat('(',num2str(i.x),'-x)^2+(',num2str(i.y),'-y)^2+(',num2str(i.z),'-z)^2-',num2str(r),'^2');
e2=strcat('(',num2str(j.x),'-x)^2+(',num2str(j.y),'-y)^2+(',num2str(j.z),'-z)^2-',num2str(r),'^2');

%{
syms e1 e2 e3;


result=solve(e1,e2,e3);

e1=(-101-x)^2+(245-y)^2+(59-z)^2-r^2;
e2=(-102-x)^2+(247-y)^2+(50-z)^2-r^2;
e3=(164-x)^2+(68-y)^2+(533-z)^2-r^2;
result=solve(e1,e2,e3);
%}

%e1=strcat('(',num2str(i.x),'-x)^2+(',num2str(i.y),'-y)^2+(',num2str(i.z),'-z)^2-r^2');
%e2=strcat('(',num2str(j.x),'-x)^2+(',num2str(j.y),'-y)^2+(',num2str(j.z),'-z)^2-r^2');;

cijk=struct('x',NaN,'y',NaN,'z',NaN);

for i1=i2-1:1:i2+1
    for j1=j2-1:1:j2+1
        for k1=k2-1:1:k2+1
            if((i1<1 || i1>x2) || (j1<1 || j1>y2) || (k1<1 || k1>z2))  %x2 , y2 and z2 are limits on voxel cube's boundaries
                continue;
            else
                len=size(voxel(i1,j1,k1).ll,2);
                for p=1:1:len
                    temp=voxel(i1,j1,k1).ll(p);
                    if(temp~=pt1 && temp~=pt2) 
                        %temp=164   68   533
                        %fprintf('          finding 3rd pt in voxel %d %d %d : temp pt1 pt2 %d %d %d \n',i1,j1,k1,temp,pt1,pt2);
                        k=struct('x',Xc(temp),'y',Yc(temp),'z',Zc(temp));
                        %e3=(k.x-x)^2+(k.y-y)^2+(k.z-z)^2-r^2
                        %e3=(164-x)^2+(68-y)^2+(533-z)^2-r^2;
                        e3=strcat('(',num2str(k.x),'-x)^2+(',num2str(k.y),'-y)^2+(',num2str(k.z),'-z)^2-',num2str(r),'^2');
                        
                        %{
                        e4=strcat('x>',num2str(min_x));
                        e5=strcat('x<',num2str(max_x));
                        e6=strcat('y>',num2str(min_y));
                        e7=strcat('y<',num2str(max_y));
                        e8=strcat('z>',num2str(min_z));
                        e9=strcat('z<',num2str(max_z));
                        %}
                        
                        try
                            result=solve(e1,e2,e3);
                        catch err
                            fprintf(fid,'error \n');
                        end
                        %save('workspace1.mat');
                        %disp(result.x);
                        if(~isempty(result))
                            %fprintf('result is not empty \n');
                            %fprintf('pt1x pt1y pt1z %d %d %d\n',Xc(pt1),Yc(pt1),Zc(pt1));
                            %fprintf('pt2x pt2y pt2z %d %d %d\n',Xc(pt2),Yc(pt2),Zc(pt2));
                            %fprintf('pt3x pt3y pt3z %d %d %d\n',Xc(temp),Yc(temp),Zc(temp));
                            %disp(result.x);
                            %disp(result.y);
                            %disp(result.z);
                            if(isreal(result.x) && isreal(result.y) && isreal(result.z)) 
                                %------------------------------------------
                                fprintf(fid,'result is real \n');
                                %------------------------------------------
                                cijk=struct('x',result.x(1),'y',result.y(1),'z',result.z(1));
                                pt3=temp;
                                cv=[i1 j1 k1];
                                save('workspace1.mat','cijk','pt3','cv');
                                %len=-1;
                                return;
                            else
                                cijk=struct('x',NaN,'y',NaN,'z',NaN);
                                %fprintf('result is not real \n');
                            end
                        end        
                    end
                end
            end
        end
    end
end
save('workspace1.mat','cijk');
end

