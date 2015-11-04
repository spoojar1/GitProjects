function b=auipp(p1,p2)  %already_used_in_prev_pass
load('workspace1.mat');

b=0;
a=ismember(edge_total,[p1 p2]);
a(all(a==0,2),:)=[] ;

s=size(a,1);
for i=1:1:s
    if((a(i,1)==1 && a(i,2)==1))
        b=1;
        return;
    end
end

end