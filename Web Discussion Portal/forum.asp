<html>
<head>
<link rel="stylesheet" href="forum.css"></link>
<script type="text/javascript" src="cookies.js"></script>
<script type="text/javascript" src="jquery-1.4.2.min.js"></script>
<script type="text/javascript">
$(document).ready(function()
{
	$("#myforumtable").hide();
	$("#myforumtable").fadeIn(1000);
});
</script>
</head>
<body>
<%
Dim cookvar(4000)
Dim cnnOracle
Dim rsEmp
Dim atype
atype=request.cookies("acctype")

Set cnnOracle = Server.CreateObject("ADODB.Connection")
cnnOracle.provider="microsoft.jet.oledb.4.0"
cnnOracle.ConnectionString = "Provider=Microsoft.Jet.OLEDB.4.0;Data Source=" & Server.MapPath ("data/hostwe.mdb") & ";"
cnnOracle.Open
Set rsEmp = Server.CreateObject("ADODB.Recordset")
rsEmp.Open "select * from forum where acc_type in("&atype&",2) ", cnnOracle
%>
<div id="myforumtable">
<table class="ftable" cellpadding=5px>
<tr>
<th colspan=4 style="color:blue; text-align:left" >&nbsp&nbsp<u>FORUM<u></th>
</tr>
<tr>
<th>Sub Forum ID</th>
<th>Sub Forum Name</th>
<th>Threads</th>
</tr>
<%
do until rsEmp.EOF
%>
<tr>
<td><% response.write(rsEmp.fields("sfid").value) %></td>
<td><a href="threadtp.asp?sfid=<% =rsEmp.fields("sfid").value %>">
<%
cookvar(rsEmp.fields("sfid").value)=rsEmp.fields("sfid").value
response.write(rsEmp.fields("sfname").value)
%>
</a></td>
<td><% response.write(rsEmp.fields("no_of_threads").value) %></td>
</tr>
<%
rsEmp.MoveNext
loop
set rsEmp =nothing
%>
</table>
</div>

</body>
</html>