<%@ language="VBscript" %>
<html>
<head>
</head>
<body>
<%
dim sqlstr,count
fname=Request.Querystring("FirstName")
lname=Request.Querystring("LastName")
uname=Request.Querystring("UserName")
email=Request.Querystring("EmailId")
pass=Request.Querystring("Password")
gender=Request.Querystring("gender")
day1=Request.Querystring("day")
month1=Request.Querystring("month")
year1=Request.Querystring("year")
dob=day1&" / "&month1&" / "&year1
pno=Request.Querystring("pno")
address=Request.Querystring("address")
state=Request.Querystring("state")
country=Request.Querystring("country")
college=Request.Querystring("college")
interests=Request.Querystring("interests")
atype=Request.Querystring("acctype")

Dim conn
Dim rsEmp
Set conn = Server.CreateObject("ADODB.Connection")

conn.ConnectionString = "Provider=Microsoft.Jet.OLEDB.4.0;Data Source=" & Server.MapPath ("data/project.mdb") & ";"
conn.Open
Set rsEmp = Server.CreateObject("ADODB.Recordset")
rsEmp.Open "select uname from weusers where uname='"&uname&"'", conn
  Set rsEmp = Server.CreateObject("ADODB.Recordset")
  rsEmp.Open "insert into weusers values('"&fname&"','"&lname&"','"&uname&"','"&email&"','"&pass&"','"&gender&"','"&dob&"','"&pno&"','"&address&"','"&state&"','"&country&"','"&college&"','"&atype&"','"&interests&"')",conn
     set rsEmp=nothing
     response.cookies("regexists")="no"
     response.redirect("register.htm")
%>
</body>
</html>




