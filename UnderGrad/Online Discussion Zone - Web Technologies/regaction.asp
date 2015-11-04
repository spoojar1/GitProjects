<%@ language="VBscript" %>
<html>
<head>
</head>
<body>
<%
dim sqlstr,count
fname=Request.Form("FirstName")
lname=Request.Form("LastName")
uname=Request.Form("UserName")
email=Request.Form("EmailId")
pass=Request.Form("Password")
gender=Request.Form("gender")
day1=Request.Form("day")
month1=Request.Form("month")
year1=Request.Form("year")
dob=day1&" / "&month1&" / "&year1
pno=Request.Form("pno")
address=Request.Form("address")
state=Request.Form("state")
country=Request.Form("country")
college=Request.Form("college")
interests=Request.Form("interests")
atype=Request.Form("acctype")

Dim cnnOracle
Dim rsEmp
Set cnnOracle = Server.CreateObject("ADODB.Connection")
cnnOracle.provider="microsoft.jet.oledb.4.0"
cnnOracle.ConnectionString = "Provider=Microsoft.Jet.OLEDB.4.0;Data Source=" & Server.MapPath ("data/hostwe.mdb") & ";"
cnnOracle.Open
Set rsEmp = Server.CreateObject("ADODB.Recordset")
rsEmp.Open "select * from weusers where uname='"&uname&"'", cnnOracle
if rsEmp.eof then
     s="insert into weusers values('"&fname&"','"&lname&"','"&uname&"','"&email&"','"&pass&"','"&gender&"','"&dob&"','"&pno&"','"&address&"','"&state&"','"&country&"','"&college&"','"&atype&"','"&interests&"')"
     cnnOracle.execute(s)
     response.cookies("regexists")="no"
     response.redirect("register.htm")
else
     response.cookies("regexists")="yes"
     response.redirect("register.htm")
end if

%>
</body>
</html>