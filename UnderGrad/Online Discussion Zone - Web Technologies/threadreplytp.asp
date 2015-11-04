<%
response.cookies("thid")=request.QueryString("thid")
response.cookies("thid").path="/"
response.redirect("threadreply.asp")
%>

