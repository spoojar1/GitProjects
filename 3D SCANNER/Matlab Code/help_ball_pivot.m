function cijk = help_ball_pivot()
    
    %load('workspace1.mat','fid');
    
    %i and j are end points of active edge
    %o is the point opposite to edge ij
    %r is rho
    %c is the center of the rho sphere touching i , j and o
    %k is the new point to be checked
    
    load('workspace1.mat');
    
    
   %{ 
    pt1=109;
    pt2=108;
    temp=131;
    r=10;
    
    front(1).e1=pt1;
    front(1).e2=pt2;
    front(1).c=[-12.00,17.95,7];

    i=struct('x',Xc(front(1).e1),'y',Yc(front(1).e1),'z',Zc(front(1).e1));
    j=struct('x',Xc(front(1).e2),'y',Yc(front(1).e2),'z',Zc(front(1).e2));
    c=struct('x',front(1).c(1),'y',front(1).c(2),'z',front(1).c(3));
    k=struct('x',Xc(temp),'y',Yc(temp),'z',Zc(temp));

    
    fprintf('pt1 %d pt2 %d pt3 %d c.x %d c.y %d c.z %d\n',pt1,pt2,temp,double(front(1).c(1)),double(front(1).c(2)),double(front(1).c(3)));
    %}
    
    syms x y z Cijo;

    % Cijo is equation of circle after substituting z=c.z in equation of sphere
    % with center c
    Cijo=subs((x-c.x)^2+(y-c.y)^2+(z-c.z)^2-r^2,z,c.z);

    %m is midpoint of edge ij
    m.x=(i.x+j.x)/2;
    m.y=(i.y+j.y)/2;
    m.z=(i.z+j.z)/2;

    % r_m2c is distance between m and c
    r_m2c=sqrt((m.x-c.x)^2+(m.y-c.y)^2);

    syms c1;
    %c1 is equation of circle with center m and radius r_m2c
    c1=(x-m.x)^2+(y-m.y)^2-r_m2c^2;

    syms s1 c2;
    % s1 is equation of sphere with center k and radius r
    % c2 is equation of circle after substituting z=c.z in equation of sphere
    % with center k
    s1=(x-k.x)^2+(y-k.y)^2+(z-k.z)^2-r^2;
    c2=subs(s1,z,c.z);

    c3=struct('x',{},'y',{});
    
    %{
    e4=strcat('x>',num2str(min_x));
    e5=strcat('x<',num2str(max_x));
    e6=strcat('y>',num2str(min_y));
    e7=strcat('y<',num2str(max_y));
    %}
    c3=solve(c2,c1);
    %disp(double([c3.x]));
    %disp(double([c3.y]));


    if(~isempty(c3))
        if(isreal(c3.x) && isreal(c3.y))
            disp(size(c3,1));
            if(size(c3,1)~=1)
                %fprintf('multiple \n');
                if(double(c3.x(1))>double(c3.x(2)))
                    cijk=struct('x',c3.x(1),'y',c3.y(1),'z',c.z);
                else
                    cijk=struct('x',c3.x(2),'y',c3.y(2),'z',c.z);
                end
            else
                %fprintf('single \n');
                cijk=struct('x',c3.x(1),'y',c3.y(1),'z',c.z);
            end
        else
            %fprintf('nothing \n');
            cijk=struct('x',NaN,'y',NaN,'z',NaN);
        end
    end
    %save('workspace1.mat');

end

