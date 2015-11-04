load('workspace1.mat');

figure(5);
trisurf(TRI_total,Xc,Yc,Zc);
xlim([-x1 x1]);
ylim([-y1 y1]);
zlim([0 z1]);