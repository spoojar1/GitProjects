function validation()
{
	var fname_str=document.regform.FirstName;
	var lname_str=document.regform.LastName;
	var uname_str=document.regform.UserName;
	var str=document.regform.EmailId.value;
	var pwd_str=document.regform.Password;
	var vpwd_str=document.regform.VPassword;
	var pno_str=document.regform.pno.value;
	if(fname_str.value=="")
	{
		alert("First name missing");
		return false;
	}
	if(lname_str.value=="")
	{
		alert("Last name missing");
		return false;
	}
	if(uname_str.value=="")
	{
		alert("User name missing");
		return false;
	}
	
	var index_at=str.indexOf("@");
	var len=str.length;
	var index_dot=str.indexOf(".");
	var email=document.regform.EmailId;
	
	if(email.value=="")
	{
		alert("Enter your email Id");
		email.focus();
		return false;
	}
	if(index_at==-1)
	{
		alert("Invalid email Id");
		return false;
	}
	if(index_dot==-1 || index_dot==0 || index_dot==index_at)
	{
		alert("Invalid email Id");
		return false;
	}
	
	if(str.indexOf("@",(index_at+1))!=-1)
	{
		alert("Invalid email Id");
		return false;
	}
	if(str.indexOf(" ")!=-1)
	{
		alert("Invalid email Id");
		return false;
	}

	if((pwd_str.value=="")||(vpwd_str.value==""))
	{
		alert("Password field/fields Empty");
		return false;
	}
	if(pwd_str.value!="" && pwd_str.value!=vpwd_str.value)
	{
		alert("Password Mismatch");
		return false;	
	}
	if(pno_str=="")
	{
		alert("Enter Phone Number");
		return false;
	}
	if( isNaN(pno_str) )
	{
		alert("phone no is not numeric");
		return false;
	}
}