<html>
<head>
</head>
<body>
<%
Dim cnnOracle
Dim rsEmp
Dim th_name,th_data
Dim uname
Dim sfid,thid
Dim d_today
d_today=Date
count1=0

th_name=request.QueryString("th_name")
th_data=request.QueryString("th_data")
sfid=request.cookies("sfid")
uname=request.cookies("loginId")

Set cnnOracle = Server.CreateObject("ADODB.Connection")
cnnOracle.provider="microsoft.jet.oledb.4.0"
cnnOracle.ConnectionString = "Provider=Microsoft.Jet.OLEDB.4.0;Data Source=" & Server.MapPath ("data/hostwe.mdb") & ";"
cnnOracle.Open
Set rsEmp = Server.CreateObject("ADODB.Recordset")
rsEmp.Open "insert into thread values(0,'"&th_name&"',0,'"&th_data&"','"&d_today&"','"&uname&"') ", cnnOracle
set rsEmp=nothing

Set rsEmp = Server.CreateObject("ADODB.Recordset")
rsEmp.Open "select * from thread ", cnnOracle
do until rsEmp.eof
	count1=count1 + 1
rsEmp.movenext
loop
set rsEmp=nothing

Set rsEmp = Server.CreateObject("ADODB.Recordset")
rsEmp.Open "update thread set thread_id="&count1&" where thread_id=0 ", cnnOracle
set rsEmp=nothing

thid=count1

Set rsEmp = Server.CreateObject("ADODB.Recordset")
rsEmp.Open "insert into subforum values("&sfid&","&thid&") ", cnnOracle
set rsEmp=nothing

Set rsEmp = Server.CreateObject("ADODB.Recordset")
rsEmp.Open "update forum set no_of_threads=no_of_threads+1 where sfid="&sfid&" ", cnnOracle
set rsEmp=nothing

response.redirect("thread.asp")
%>


