<%@ language="VBscript" %>
<html>
<head>
<script language="JavaScript" type="text/javascript" src="editform.js" >
</script>
</head>
<body>
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
<form name="regform"  method="post" action="editproaction.asp" onSubmit="return validation()">   <pre>


        First Name :   		<input name="FirstName"  type="text"  size="30" value=<% =rsEmp.fields("fname").value %>>*
        Last Name :    		<input name="LastName"  type="text"  size="30" value=<% =rsEmp.fields("lname").value %>>*
        Email Id :     		<input name="EmailId"  type="text"  size="30" value=<% =rsEmp.fields("emailid").value %>>*
					<% if rsEmp.fields("gender").value="Male" then %> 
	Gender:			<input name="gender" type="radio" checked="" value="Male">Male
               			<input name="gender" type="radio"  value="Female">Female
					<% else %>
	Gender:			<input name="gender" type="radio"  value="Male">Male
               			<input name="gender" type="radio"  checked value="Female">Female
					<% end if %>
        Phone No:		<input name="pno"  value="<% =rsEmp.fields("phno").value %>" type="text"  size="10" >*

        Address:		<textarea name="address" rows="5" columns="30" wrap=""><%=rsEmp.fields("address").value %></textarea>

        State:			<input name="state"  value="<% =rsEmp.fields("state").value %>" type="text"  size="26" >	

        Country:  		<input name="country"  value="<% =rsEmp.fields("country").value %>" type="text"  size="26" >	

        College:		<input name="college"  value="<% =rsEmp.fields("college").value %>" type="text"  size="26" >

        Interests:		<textarea name="interests" rows="5" columns="30" wrap=""><%=rsEmp.fields("interests").value %></textarea>

        
	<input type="Submit" value="SAVE" name="Submit" >   <input type="reset" value="CLEAR" name="CLEAR" >
	</pre>
      </form>

</body>
</html>
