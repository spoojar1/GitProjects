<html>
<head>
<link rel="stylesheet" href="threadreply.css"></link>
<script type="text/javascript" src="jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="cookies.js"></script>
<script type="text/javascript" src="jquery.curvycorners.packed.js"></script>
<script type="text/javascript">
$(document).ready(function()
{
	$("#back").hide();
	$("#mythreadreply").hide();
	$("#back").fadeIn(1000);
	$("#mythreadreply").fadeIn(1000);
	$("#mythreadreply #threadbox1").hide();
	$('#back').corner
	({
		  tl: { radius: 5 },
		  tr: { radius: 5 },
		  bl: { radius: 5 },
		  br: { radius: 5 }
	});
	$('#comment').corner
	({
		  tl: { radius: 5 },
		  tr: { radius: 5 },
		  bl: { radius: 5 },
		  br: { radius: 5 }
	});
	$('#submit').corner
	({
		  tl: { radius: 5 },
		  tr: { radius: 5 },
		  bl: { radius: 5 },
		  br: { radius: 5 }
	});
});
function showDiv()
{
	$("#mythreadreply #threadbox1").fadeIn(1000);
}
function c_reply()
{
	setCookie("reply",document.post1.reply.value);
	window.location="threadreplypost.asp";
}
</script>
<style type="text/css">
#back
{
 position:absolute;
 margin-top:10px;
 margin-left:25px;
 height:25px;
 width:150px;
 background: #1589FF;
 border-style: solid;
 border-width: 3px;
 border-color: #87AFC7;
 text-align:center;
}
#back a
{
 font-weight:bold;
 color: #fff;
}
#comment, #submit
{
 position:absolute;
 margin-top:10px;
 margin-left:480px;
 height:23px;
 width:80px;
 background: #1589FF;
 border-style: solid;
 border-width: 3px;
 border-color: #fff;
 text-align:center;
}
#comment a, #submit a
{
 font-weight:bold;
 color: #fff;
}

</style>
</head>
<body>
<%
Dim cnnOracle
Dim rsEmp
Dim thid
Dim th_uname
thid=request.cookies("thid")

Set cnnOracle = Server.CreateObject("ADODB.Connection")
cnnOracle.provider="microsoft.jet.oledb.4.0"
cnnOracle.ConnectionString = "Provider=Microsoft.Jet.OLEDB.4.0;Data Source=" & Server.MapPath ("data/hostwe.mdb") & ";"
cnnOracle.Open
Set rsEmp = Server.CreateObject("ADODB.Recordset")
rsEmp.Open "select thread_name,uname,data,post from thread where thread_id="&thid&" ", cnnOracle
th_uname=rsEmp.fields("uname")
%>

<div id="back">
<a href="thread.asp" style="text-decoration:none"">Back To Thread List</a>
</div>

<DIV id="mythreadreply">

<!-- for thread posted  -->
<%
do until rsEmp.EOF
%>
<Div id="threadbox">

<div id="threadhead" >

<div id="threadname">
<% response.write(rsEmp.fields("thread_name").value) %>
</div>
<div id="comment">
<a href="#" style="text-decoration:none" onClick="showDiv()">Comment</a>
</div>

</div>

<div id="threadside" >

<div id="thread_detail">
POSTED BY<br>
<% response.write(rsEmp.fields("uname").value) %><br><br>
ON<br>
<% response.write(rsEmp.fields("post").value) %>
</div>

</div>

<div id="threadtext" >
<form name="post">
<textarea name="reply" rows="14" cols="45" wrap="" readonly><% response.write(rsEmp.fields("data").value) %> </textarea>
</form>
</div>

</Div>
<%
rsEmp.MoveNext
loop
set rsEmp =nothing
%>
<!-- xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx  -->
<!-- for threadreplies   -->
<%
Set rsEmp = Server.CreateObject("ADODB.Recordset")
rsEmp.Open " select uname,reply,post from reply where thread_id="&thid&" ", cnnOracle
do until rsEmp.EOF
%>
<Div id="threadbox">

<div id="threadhead" >

<div id="threadname">
Reply To Thread Posted By <% =th_uname %>
</div>

</div>

<div id="threadside" >

<div id="thread_detail">
REPLY<br>
POSTED BY<br>
<% response.write(rsEmp.fields("uname").value) %><br><br>
ON<br>
<% response.write(rsEmp.fields("post").value) %>
</div>

</div>

<div id="threadtext" >
<form name="post">
<textarea name="reply" rows="14" cols="45" wrap="" readonly><% response.write(rsEmp.fields("reply").value) %> </textarea>
</form>
</div>

</Div>
<%
rsEmp.MoveNext
loop
set rsEmp =nothing
%>
<!-- xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx  -->
<Div id="threadbox1">

<div id="threadhead1" >
<div id="submit">
<a href="#" style="text-decoration:none" onClick="c_reply()">Submit</a>
</div>
</div>

<div id="threadside1" ></div>

<div id="threadtext1" >
<form name="post1" >
<textarea name="reply" rows="14" cols="45" wrap="" ></textarea>
</form>
</div>

</Div>

</DIV>
</body>
</html>
