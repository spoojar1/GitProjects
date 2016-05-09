<html>
<head>
</head>
<body>
<%
Dim cnnOracle
Dim rsEmp
Dim reply_data
Dim thid
Dim d_today
d_today=Date
count1=0

reply_data=request.cookies("reply")
uname=request.cookies("loginId")
thid=request.cookies("thid")

Set cnnOracle = Server.CreateObject("ADODB.Connection")
cnnOracle.provider="microsoft.jet.oledb.4.0"
cnnOracle.ConnectionString = "Provider=Microsoft.Jet.OLEDB.4.0;Data Source=" & Server.MapPath ("data/hostwe.mdb") & ";"
cnnOracle.Open

Set rsEmp = Server.CreateObject("ADODB.Recordset")
rsEmp.Open "insert into reply values('"&thid&"',0,'"&reply_data&"','"&uname&"','"&d_today&"') ", cnnOracle
set rsEmp=nothing

Set rsEmp = Server.CreateObject("ADODB.Recordset")
rsEmp.Open  "select * from reply where thread_id="&thid&"  ", cnnOracle
do until rsEmp.eof
	count1=count1 + 1
rsEmp.movenext
loop
set rsEmp=nothing

Set rsEmp = Server.CreateObject("ADODB.Recordset")
rsEmp.Open  "update reply set reply_no="&count1&" where reply_no=0 ", cnnOracle
set rsEmp=nothing

Set rsEmp = Server.CreateObject("ADODB.Recordset")
rsEmp.Open  "update thread set no_of_replies=no_of_replies+1 where thread_id="&thid&" ", cnnOracle
set rsEmp=nothing

response.redirect("threadreply.asp")
%>

