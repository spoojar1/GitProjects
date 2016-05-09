function b = check_intersect(pt1,pt2,pt3)

    load('workspace1.mat','TRI_total','Xc','Yc','Zc','fid','del','del1');
    
    %----------------------------------------------
    %fprintf(fid,'++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ \n');
    %fprintf(fid,'inside check checking for points %d %d %d \n',pt1,pt2,pt3);
    %----------------------------------------------
    
    %fprintf('++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ \n');
    %fprintf('inside check checking for points %d %d %d \n',pt1,pt2,pt3);
    
    %fprintf('----------------------------------------------------------------- \n');
    %fprintf('inside check \n');
    
    b=1;
    len=size(TRI_total,1);
    
%{
p1=109;
p2=132;
p3=110;
p4=131;

p1=109;
p2=132;
p3=132;
p4=131;
%}
    for i=1:1:len
       
       bool=0;
       
       %----------------------------------------------
       %fprintf(fid,'new plane : %d  %d  %d \n',pt1,pt2,pt3);
       %fprintf(fid,'TRI_total plane : %d  %d  %d \n',TRI_total(i,1),TRI_total(i,2),TRI_total(i,3));
       %----------------------------------------------
    
       
       if(TRI_total(i,1)==pt1 || TRI_total(i,1)==pt2 || TRI_total(i,1)==pt3)
           p1=TRI_total(i,1);
           bool=bool+1;
       end
       
       if(TRI_total(i,2)==pt1 || TRI_total(i,2)==pt2 || TRI_total(i,2)==pt3)
           if(bool==1)
               p2=TRI_total(i,2);
           else
               p1=TRI_total(i,2);
           end
           bool=bool+1;
       end
       
       if(TRI_total(i,3)==pt1 || TRI_total(i,3)==pt2 || TRI_total(i,3)==pt3)
           p2=TRI_total(i,3);
           bool=bool+1;
       end
       
       
       if(bool~=2)    %atleast 2 points need to be same among 2 planes to check for their intersections 
           %----------------------------------------------
           %fprintf(fid,'no need to check for intersection \n');
           %----------------------------------------------
           continue;
       end
       
       
       tmp=unique([TRI_total(i,:) pt1 pt2 pt3]);
       t_len=size(tmp,2);
       
       t=[];
       for i=1:1:t_len
           if(tmp(i)==p1 || tmp(i)==p2)
               continue;
           else
              t=[t;tmp(i)];
           end
       end
       
       fprintf(fid,'distinct points : %d %d \n',t(1),t(2));
       
       %error=0.8*del;
       error=0.8*del1;
       
       tmp=sqrt((Xc(t(1))-Xc(t(2)))^2+(Yc(t(1))-Yc(t(2)))^2+(Zc(t(1))-Zc(t(2)))^2);
       %fprintf(fid,'error : %d    distance : %d \n',error,tmp);
       
       if(tmp>=error)   %if true then allow
           continue;
       else
           fprintf(fid,'not allowed \n');
           return;
       end
       
    end
    
    fprintf(fid,'allowed \n');
    b=0;
    
    %----------------------------------------------
    %fprintf(fid,'double hurray , check also satisfied \n');
    %----------------------------------------------
end


