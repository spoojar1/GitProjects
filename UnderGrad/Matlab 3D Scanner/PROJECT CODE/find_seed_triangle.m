function seed = find_seed_triangle()

load('workspace1.mat');
seed=0;
%error=0;

%av,bv and cv are 1X3 arrays storing voxel positions of 1st, 2nd and 3rd pt respectively
%eg: av=[30 24 10]
%pt1,pt2 and pt3 hold index nos of the 3 pts  
%eg: pt1=8816    pt2=3000
%v1 v2 and v3 are the positions of the voxel under consideration

temp=[];
for i=1:1:x2
    for j=1:1:y2
        for k=1:1:z2
            
            %fprintf('outside consider');
            len=size(voxel(i,j,k).ll,2);
            if(len~=0 && isempty(voxel(i,j,k).consider))
            %if(len~=0) 
                %fprintf('inside consider');
                
                voxel(i,j,k).consider=1;
                
                v1=i;
                v2=j;
                v3=k;
                %----------------------------------------------
                fprintf(fid,'\n!!!!!!!!!!!!!!!!!!!!!!!\nSEED TRIANGLE \n CONSIDER VOXEL %d %d %d *************************************************\n',i,j,k);
                %----------------------------------------------
         
                %initially consider pts within same voxel
                for p=1:1:len
                    temp=cat(2,temp,voxel(i,j,k).ll(p));
                end
            
                %len=size(temp,2);
                for p=1:1:len-1
                    for q=p+1:1:len
                        tmp1=temp(p);
                        tmp2=temp(q);
                        
                        
                        save('workspace1.mat');
                        if(pass>1)
                            if(auipp(tmp1,tmp2))   %already used in previous pass
                                continue;
                            end
                        end
                                         
                        %if(abs(sqrt((Xc(tmp1)-Xc(tmp2))^2+(Yc(tmp1)-Yc(tmp2))^2+(Zc(tmp1)-Zc(tmp2))^2)-del)<=error)   %2nd pt found
                        if(sqrt((Xc(tmp1)-Xc(tmp2))^2+(Yc(tmp1)-Yc(tmp2))^2+(Zc(tmp1)-Zc(tmp2))^2)<=del1)   %2nd pt found
                            
                            pt1=tmp1;
                            pt2=tmp2;
                            %fprintf(fid,'considering  seed points : pt1 pt2 %d %d \n',pt1,pt2);
                            save('workspace1.mat');
                            help_seed_func();
                            load('workspace1.mat');   % for pt3 and v3
                            %disp('in same cijk');
                            %disp(cijk.x);
                            %disp(cijk.y);
                            %disp(cijk.z);
                            if(~isnan(cijk.x))     %3rd pt found
                                %len=-1;
                                %disp('fgdfgdfg');
                                seed=1;
                                save('workspace1.mat');
                                return;
                                %break; 
                            end
                        end
                    end
                end
                %----------------------------------------------
                fprintf(fid,'lets search adjacent voxels\n');
                %----------------------------------------------
                
                %then consider pts in 26 adjacent voxels
                for i1=i-1:1:i+1
                    for j1=j-1:1:j+1
                        for k1=k-1:1:k+1
                            if((i1<1 || i1>x2) || (j1<1 || j1>y2) || (k1<1 || k1>z2))  %x2 , y2 and z2 are limits on voxel cube's boundaries
                                continue;
                            else
                                if(i1~=i && j1~=j && k1~=k)    %since pts in voxel i j k have already been considered
                                    %fprintf(fid,'\nCONSIDER ADJACENT VOXEL %d %d %d *************************************************\n',i1,j1,k1);
                                    len=size(voxel(i1,j1,k1).ll,2);
                                    for p=1:1:len
                                        temp=cat(2,temp,voxel(i1,j1,k1).ll(p));
                                    end
                                end
                            end
                        end
                    end
                end
                
                len=size(temp,2);
                for p=1:1:len-1
                    for q=p+1:1:len
                        tmp1=temp(p);
                        tmp2=temp(q);
                        
                        save('workspace1.mat');
                        if(pass>1)
                            if(auipp(tmp1,tmp2))   %already used in previous pass
                                continue;
                            end
                        end
                        
                        %if(abs(sqrt((Xc(tmp1)-Xc(tmp2))^2+(Yc(tmp1)-Yc(tmp2))^2+(Zc(tmp1)-Zc(tmp2))^2)-del)<=error)   %2nd pt found
                        if(sqrt((Xc(tmp1)-Xc(tmp2))^2+(Yc(tmp1)-Yc(tmp2))^2+(Zc(tmp1)-Zc(tmp2))^2)<=del1)   %2nd pt found
                            
                            %calculating voxel positions for pts pt1 and pt2
                            
                            pt1=tmp1;
                            pt2=tmp2;
                            %----------------------------------------------
                            fprintf(fid,'considering seed points : pt1 pt2 %d %d \n',pt1,pt2);
                            %----------------------------------------------
                            save('workspace1.mat');
                            help_seed_func();
                            load('workspace1.mat');          % for pt3 and v3
                            %disp('in others cijk');
                            %disp(cijk.x);
                            %disp(cijk.y);
                            %disp(cijk.z);
                            if(~isnan(cijk.x))     %3rd pt found
                                %len=-1;
                                %disp('fgdfgdfg');
                                seed=1;
                                save('workspace1.mat');
                                return;
                                %break; 
                            end
                        end
                    end
                end
            
                if(len==-1)
                    %exit find_seed_triangle
                end

                temp=[];
                %fprintf('******************\n');
            end
        end
    end
end
    
disp('no seed');

end


