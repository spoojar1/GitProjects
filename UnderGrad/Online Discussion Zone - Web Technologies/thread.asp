<html>
<head>
<link rel="stylesheet" href="forum.css"></link>
<script type="text/javascript" src="jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="jquery.curvycorners.packed.js"></script>
<script type="text/javascript">
$(document).ready(function()
{
	$("#back").hide();
	$("#mythreadtable").hide();
	$("#postnewthread").hide();
	$("#newthread").hide();

	$("#mythreadtable").fadeIn(1000);
	$("#back").fadeIn(1000);
	$("#postnewthread").fadeIn(1000);	

	$('#back').corner
	({
		  tl: { radius: 5 },
		  tr: { radius: 5 },
		  bl: { radius: 5 },
		  br: { radius: 5 }
	});
	$('#postnewthread').corner
	({
		  tl: { radius: 5 },
		  tr: { radius: 5 },
		  bl: { radius: 5 },
		  br: { radius: 5 }
	});
	$('#newthread').corner
	({
		  tl: { radius: 5 },
		  tr: { radius: 5 },
		  bl: { radius: 5 },
		  br: { radius: 5 }
	});

});
function addthread()
{
 	$("#newthread").fadeIn(1000);
}
</script>
<style type="text/css">
#back
{
 position:relative;
 margin-top:10px;
 margin-left:0px;
 height:25px;
 width:165px;
 background: #1589FF;
 border-style: solid;
 border-width: 3px;
 border-color: #87AFC7;
 text-align:center;
}
#back a, #postnewthread a
{
 font-weight: bold;
 color: #fff;
}
#postnewthread
{
 position:relative;
 margin-top:30px;
 margin-left:0px;
 height:25px;
 width:135px;
 background: #1589FF;
 border-style: solid;
 border-width: 3px;
 border-color: #87AFC7;
 text-align:center;
}

</style>
</head>
<body>
<%
Dim cookvar(4000)
Dim cnnOracle
Dim rsEmp
Dim sforumid

sforumid=request.cookies("sfid")
Set cnnOracle = Server.CreateObject("ADODB.Connection")
cnnOracle.provider="microsoft.jet.oledb.4.0"
cnnOracle.ConnectionString = "Provider=Microsoft.Jet.OLEDB.4.0;Data Source=" & Server.MapPath ("data/hostwe.mdb") & ";"
cnnOracle.Open
Set rsEmp = Server.CreateObject("ADODB.Recordset")
rsEmp.Open "select thread.thread_id,thread_name, uname, post, no_of_replies from thread, subforum "&_
" where thread.thread_id=subforum.thread_id and subforum.sfid = "&sforumid&" ", cnnOracle
%>

<div id="back">
<a href="forum.asp" style="text-decoration:none">Back To Sub-Forum List</a>
</div>

<div id="mythreadtable">
<table class="ttable" cellpadding=5px>
<tr>
<th colspan=5 style="color:blue; text-align:left" >&nbsp&nbsp Getting Started</th>
</tr>
<tr>
<th></th>
<th>Thread</th>
<th>Posted By</th>
<th>Post Date</th>
<th>Replies</th>
</tr>
<%
do until rsEmp.EOF
%>
<tr>
<td style=" width: 35px; align:center;"><img src="images/forumpost.jpg"></img></td>
<td><a href="threadreplytp.asp?thid=<% =rsEmp.fields("thread_id")%>">
<% response.write(rsEmp.fields("thread_name").value) %></a></td>
<td><% response.write(rsEmp.fields("uname").value) %> </td>
<td><% response.write(rsEmp.fields("post").value) %> </td>
<td><% response.write(rsEmp.fields("no_of_replies").value) %> </td>
</tr>
<%
rsEmp.MoveNext
loop
set rsEmp =nothing
%>
</table>
</div>

<div id="postnewthread">
<a href="#" style="text-decoration:none" onClick="addthread()">Post New Thread</a>
</div>

<div id="newthread">

<div id="newthreadhead">
<div id="newthreaddetail">
New  Thread  Details
</div>
</div>

<div id="newthreadform"
<form name="newthform" action="newthreadpost.asp" >
<pre>
  Thread Name
  <input type="text" name="th_name" size="20"></input>

  Thread Content
  <textarea name="th_data" rows="12" cols="55" wrap="" ></textarea>
  
  <input type="Submit" value="Submit"></input>
</pre> 
</form>
</div>

</div>

</body>
</html>