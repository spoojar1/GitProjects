<%@ language="VBscript" %>
<html>
<head>
</head>
<body>
<%
dim sqlstr,count
uname=Request.Form("logname")
upass=Request.Form("logpass")

Dim cnnOracle
Dim rsEmp	
Set cnnOracle = Server.CreateObject("ADODB.Connection")
cnnOracle.ConnectionString = "Provider=Microsoft.Jet.OLEDB.4.0;Data Source=" & Server.MapPath ("data/hostwe.mdb") & ";"
cnnOracle.Open
Set rsEmp = Server.CreateObject("ADODB.Recordset")
rsEmp.Open "select * from weusers where uname='"&uname&"' and password='"&upass&"' ", cnnOracle
if rsEmp.eof then
	response.cookies("logexists")="no"
	response.cookies("logexists").path="/"
	response.redirect("default.shtml")
else
	response.cookies("loginId")=uname
        response.cookies("loginId").path="/"
	response.cookies("logexists")="yes"
        response.cookies("logexists").path="/"
	set rsEmp=nothing
	Set rsEmp = Server.CreateObject("ADODB.Recordset")
	rsEmp.Open "select type from weusers where uname='"&uname&"' ", cnnOracle
	if rsEmp.fields("type")="Student" then
		response.cookies("acctype")=0
                response.cookies("acctype").path="/"
	else
		response.cookies("acctype")=1
                response.cookies("acctype").path="/"
	end if
	response.redirect("default.shtml")
end if
%>
</body>
</html>








