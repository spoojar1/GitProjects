function varargout = Scan3D(varargin)
gui_Singleton = 1;
gui_State = struct('gui_Name',       mfilename, ...
                   'gui_Singleton',  gui_Singleton, ...
                   'gui_OpeningFcn', @Scan3D_OpeningFcn, ...
                   'gui_OutputFcn',  @Scan3D_OutputFcn, ...
                   'gui_LayoutFcn',  [] , ...
                   'gui_Callback',   []);
if nargin && ischar(varargin{1})
    gui_State.gui_Callback = str2func(varargin{1});
end

if nargout
    [varargout{1:nargout}] = gui_mainfcn(gui_State, varargin{:});
else
    gui_mainfcn(gui_State, varargin{:});
end

function Scan3D_OpeningFcn(hObject, eventdata, handles, varargin)
handles.output = hObject;
movegui(hObject,'center');

%set(handles.cmd_preprocessing,'Enable','off');
%set(handles.cmd_mesh,'Enable','off');
%set(handles.cmd_render,'Enable','off');
guidata(hObject, handles);
save('workspace1.mat');

function varargout = Scan3D_OutputFcn(hObject, eventdata, handles) 
varargout{1} = handles.output;

function cmd_initial_Callback(hObject, eventdata, handles)

Initial_Values;
%set(handles.cmd_preprocessing,'Enable','on');
save('workspace1.mat');

function cmd_preprocessing_Callback(hObject, eventdata, handles)
%handles.output = hObject;
pp;
%load('workspace1.mat');
%set(handles.cmd_preprocessing,'Enable','off');
%set(handles.cmd_mesh,'Enable','on');
save('workspace1.mat');

function cmd_mesh_Callback(hObject, eventdata, handles)
mesh;
%set(handles.cmd_mesh,'Enable','off');
save('workspace1.mat');

function cmd_render_Callback(hObject, eventdata, handles)
%copyfile('workspace1.mat','tp')
surf_render;
