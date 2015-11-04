<%
response.cookies("sfid")=request.QueryString("sfid")
response.cookies("sfid").path="/"
response.redirect("thread.asp")
%>
