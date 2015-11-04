function b=not_used(new_pt,pt1,pt2)
load('workspace1.mat','TRI_total','fid');
    b=1;
    %l=size(TRI_total,1);
    %fprintf(fid,'IN NOT USED FUNCTION \n');
    %fprintf(fid,'len of TRI_total : %d \n',size(TRI_total,1));
    %fprintf(fid,'pt1 : %d pt2 : %d new_pt : %d \n',pt1,pt2,new_pt);
    %if(l==0)
     %   b=1;
        %fprintf(fid,'TRUE NOT USED\n');
      %  return;
    %end
    
    if(isempty(find(TRI_total(:,1)==pt1 & TRI_total(:,2)==pt2 & TRI_total(:,3)==new_pt,1)))
        if(isempty(find(TRI_total(:,1)==pt1 & TRI_total(:,2)==new_pt & TRI_total(:,3)==pt2,1)))
            if(isempty(find(TRI_total(:,1)==pt2 & TRI_total(:,2)==pt1 & TRI_total(:,3)==new_pt,1)))
                if(isempty(find(TRI_total(:,1)==pt2 & TRI_total(:,2)==new_pt & TRI_total(:,3)==pt1,1)))
                    if(isempty(find(TRI_total(:,1)==new_pt & TRI_total(:,2)==pt1 & TRI_total(:,3)==pt2,1)))
                        if(isempty(find(TRI_total(:,1)==new_pt & TRI_total(:,2)==pt2 & TRI_total(:,3)==pt1,1)))
                            %fprintf(fid,'TRUE NOT USED\n');
                            return; 
                        end
                    end
                end
            end
        end
    end
    b=0;
    %fprintf(fid,'FALSE USED\n');  
end




