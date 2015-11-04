<%@ language="VBscript" %>
<html>
<head>
<style type="text/css">
th, td
{
 text-align:left; 
 width:250px;
 height:40px;
 font-size:12pt;
}
</style>
</head>
<body>
<p style="font-size:12pt"><b>YOUR PROFILE DETAILS</b></p><br>
<%
Dim uname
Dim cnnOracle
Dim rsEmp
uname=request.cookies("loginId")
	
Set cnnOracle = Server.CreateObject("ADODB.Connection")
cnnOracle.provider="microsoft.jet.oledb.4.0"
cnnOracle.ConnectionString = "Provider=Microsoft.Jet.OLEDB.4.0;Data Source=" & Server.MapPath ("data/hostwe.mdb") & ";"
cnnOracle.Open
Set rsEmp = Server.CreateObject("ADODB.Recordset")
rsEmp.Open "select * from weusers where uname='"&uname&"' ", cnnOracle
%>
<table >
<% 
for each x in rsEmp.fields
if x.name <> "password" then
%>
<tr>
<th > <% response.write(ucase(x.name)&":") %> </th>
<td > <% response.write(x.value) %> </td>
</tr>
<% 
end if
next 
%>
</table>
<% set rsEmp=nothing %>
</body>
</html>
