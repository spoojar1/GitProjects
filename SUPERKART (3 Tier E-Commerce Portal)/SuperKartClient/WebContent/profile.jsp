<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="SuperKartClient.SuperKartClient.UsersBean"%>
<!doctype html>
<html class="no-js" lang="en">
<head>
<meta charset="utf-8"/>
<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
<title>SuperKart</title>
<link rel="stylesheet" href="https://dhbhdrzi4tiry.cloudfront.net/cdn/sites/foundation.min.css">
<link href='https://cdnjs.cloudflare.com/ajax/libs/foundicons/3.0.0/foundation-icons.css' rel='stylesheet' type='text/css'>
<script src="https://code.jquery.com/jquery-2.1.4.min.js"></script>

<script type="text/javascript">
$(document).ready(function(){
	
	//AJAX
	$('#logout').click(function(event){
		$.get('LoginControllerServlet', function(responseText){
			if(responseText=="invalid"){
				window.location.href='index.jsp';
			}
		})
		.fail(function(){
			alert("AJAX Fail");
		});
		return false;
	});
	
	//AJAX
	/*$('#viewprofile').click(function(event){
		$.post('ProfileDetailsControllerServlet',{mode:"midviewprofile"}, function(responseText){
			alert(responseText);
			if(responseText=="midviewprofile"){
				window.location.href='profile.jsp';
			}
		})
		.fail(function(){
			alert("AJAX Fail");
		});
		return false;
	});*/
});
</script>

<style>
#profile{
	background-color:#ebebef;
	width: 70%;
	margin: 0 auto;
	height: 380px;
	border-collapse: collapse;
	border-left: 1px solid #d0d0d0;
	border-right: 1px solid #d0d0d0;
}
#profile #form{
	padding: 1rem;
}
#profile #col1{
	float:left;
	width:45%;
	height: 350px;
	margin-left: 1rem;
}
#profile #col2{
	float:right;
	width:45%;
	height: 350px;
	margin-right: 1rem;
}
#profile #header{
	background-color:#fcfcfa;
	height: 8%;
	position: relative; 
}
#header p {
   position: absolute;            
   top: 50%;                         
   transform: translate(0, -50%);
   padding:1rem;
   font-weight:bold;
}

#profilefetch, #fetchproduct{
  background:none;
  border:none;
  font-size:1em;
  color:blue;
  width:60px;
  text-align:center;
  color:#2199e8;
}
</style>

</head>
<body>

<div class="top-bar">
	<div class="top-bar-left">
		<ul class="menu">
			<li><a href="index.jsp" style="color:black;font-weight:bold;">SUPERKART</a></li>
			<li>
				<form action="ProductControllerServlet" method="post">
					<input type="hidden" id="hiddenusername" name="username" value="" />
					<input type="hidden" name="limit" value="" />
					<input type="hidden" name="productcat" value="M" />
					<input style="width:70px;" type="submit" id="fetchproduct" value="Mobiles"/>
				</form>
			</li>
			<li>
				<form action="ProductControllerServlet" method="post">
					<input type="hidden" id="hiddenusername" name="username" value="" />
					<input type="hidden" name="limit" value="" />
					<input type="hidden" name="productcat" value="L" />
					<input style="width:70px;" type="submit" id="fetchproduct" value="Laptops"/>
				</form>
			</li>
			<li>
				<form action="ProductControllerServlet" method="post">
					<input type="hidden" id="hiddenusername" name="username" value="" />
					<input type="hidden" name="limit" value="" />
					<input type="hidden" name="productcat" value="C" />
					<input style="width:70px;" type="submit" id="fetchproduct" value="Cameras"/>
				</form>
			</li>
			<li>
				<form action="ProductControllerServlet" method="post">
					<input type="hidden" id="hiddenusername" name="username" value="" />
					<input type="hidden" name="limit" value="" />
					<input type="hidden" name="productcat" value="T" />
					<input type="submit" id="fetchproduct" value="Tablets"/>
				</form>
			</li>
		</ul>
	</div>
	<div class="top-bar-right">
		<ul class="menu">
			<li>
				<form action="ProductControllerServlet" method="post">
					<input type="search" name="searchstring" style="display:inline; height:30px; width:300px; margin-right:0;" placeholder="Search">
					<input type="submit" class="button" style="width:100px; height:30px;line-height:0.3rem; margin-right:0;" value="Search"/>
				</form>
			</li>
			
			<li><a href="cart.jsp" id="cart-image" style="padding-top:0px;padding-bottom:0px;"><img src="images/cart.png" style="width:35px;height:25px;"></img></a></li>
			<li><form action="ProfileDetailsControllerServlet" method="post"><input type="submit" id="profilefetch" value="Profile"/></form></li>
			<li><a id="logout">Logout</a></li>
		</ul>
	</div>
</div>
 

<div id="profile">
	<div id="header">
		<p>PROFILE DETAILS</p>
	</div>
	<div id="form">
		<div>
			<% UsersBean bean=(UsersBean)request.getAttribute("bean"); %>
			<form action="ProfileDetailsControllerServlet" method="get">
				<div id="col1">
					<label>FirstName
						<input type="text" name="fname" value=<%= bean.getFirstName() %>>
					</label>
					<input type="hidden" name="username" value=<%= bean.getUserName() %>>
					<label>EmailId
						<input type="text" name="emailid" value=<%= bean.getEmailId() %>>
					</label>
					<label>ShippingAddress
						<input type="text" name="shipaddr" value=<%= bean.getShippingAddress() %>>
					</label>
					<label>Date Of Birth
						<input type="date" name="dob" value=<%= bean.getDateOfBirth() %>>
					</label>
				</div>
				<div id="col2">	
					<label>LastName
						<input type="text" name="lname" value=<%= bean.getLastName() %>>
					</label>
					<label>ContactNo
						<input type="tel" name="contact" value=<%= bean.getContactNo() %>>
					</label>
					<label>BillingAddress
						<input type="text" name="billaddr" value=<%= bean.getBillingAddress() %>>
					</label>
					<br>   
					<input type="submit" class="button expanded" value="Save Profile">
				</div>
			</form>
		</div>
	</div>
</div>

<div class="callout secondary">
	<div class="row">
		<div class="large-4 columns">
			<h5>Your ONE STOP SHOP for all the Electronics you can think of !!</h5>
			<p>From mobiles, to laptops, to LCDs. You can find all of your household and workplace electronics here at COMPANYNAME. We provide the best customer service and truly believe that Customer is King.</p>
		</div>
		<div class="large-3 large-offset-2 columns">
			<ul class="menu vertical">
				<li><a href="#"><p style="font-weight:bold;">COMPANY NAME</p></a></li>
				<li><a href="#">About Us</a></li>
				<li><a href="contactus.jsp">Contact Us</a></li>
				<li><a href="#">Careers</a></li>
			</ul>
		</div>
		<div class="large-3 columns">
			<ul class="menu vertical">
				<li><a href="#"><p style="font-weight:bold;">HELP</p></a></li>
				<li><a href="#">FAQ</a></li>
				<li><a href="#">Cancellation & Returns</a></li>
				<li><a href="#">Gift Cards</a></li>

			</ul>
		</div>
	</div>
</div>

<script src="https://dhbhdrzi4tiry.cloudfront.net/cdn/sites/foundation.js"></script>
<script>
      $(document).foundation();
</script>
<script type="text/javascript" src="https://intercom.zurb.com/scripts/zcom.js"></script>
</body>
</html>
