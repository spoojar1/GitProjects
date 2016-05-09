<%@ language="VBscript" %>
<html>
<head>
</head>
<body>
<%
dim sqlstr,count
fname=Request.Form("FirstName")
lname=Request.Form("LastName")
email=Request.Form("EmailId")
gender=Request.Form("gender")
pno=Request.Form("pno")
address=Request.Form("address")
state=Request.Form("state")
country=Request.Form("country")
college=Request.Form("college")
interests=Request.Form("interests")
uname=request.cookies("loginId")

Dim cnnOracle
Dim rsEmp	
Set cnnOracle = Server.CreateObject("ADODB.Connection")
cnnOracle.provider="microsoft.jet.oledb.4.0"
cnnOracle.ConnectionString = "Provider=Microsoft.Jet.OLEDB.4.0;Data Source=" & Server.MapPath ("data/hostwe.mdb") & ";"
cnnOracle.Open
Set rsEmp = Server.CreateObject("ADODB.Recordset")
rsEmp.Open "update weusers set fname='"&fname&"',lname='"&lname&"',emailid='"&email&"',gender='"&gender&"',phno="&pno&","&_
" address='"&address&"',state='"&state&"',country='"&country&"',college='"&college&"',"&_
" interests='"&interests&"' where uname='"&uname&"' ",cnnOracle
set rsEmp=nothing
%>
<script type="text/javascript">
window.parent.location.reload();
</script>
</body>
</html>