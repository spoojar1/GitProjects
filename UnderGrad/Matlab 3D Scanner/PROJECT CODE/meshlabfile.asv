fid = fopen('output.txt', 'w');
fprintf(fid,'');
fclose(fid);

fid = fopen('output.txt','a');

for i=1:1:size(Xc,1)
    a(i,1)=Xc(i);
    a(i,2)=Yc(i);
    a(i,3)=Zc(i);
end

Xc(:,2)=0;
Xc(:,3)=0;
Yc(:,2)=0;
Yc(:,3)=0;
Zc(:,2)=0;
Zc(:,3)=0;
surfnorm(Xc,Yc,Zc);
for i=1:1:size(a,1)
    fprintf(fid,'%d %d %d\n',round(a(i,1)),round(a(i,2)),round(a(i,3)));
end