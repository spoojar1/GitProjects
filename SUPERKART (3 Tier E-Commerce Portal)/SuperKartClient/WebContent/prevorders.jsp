<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
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
	
	
	$.ajax({
		type: "POST",
		url: "OrderControllerServlet",
		dataType: "xml",
		data: {},
		success: function(xml){
			//alert(xml);
			var xmlDoc = xml.getElementsByTagName("Orders");
			var output="";
			
			output+='<colgroup><col width="20%"><col width="15%"><col width="25%"><col width="15%"><col width="15%"></colgroup>';
			output+='<tr><th>ORDER ID</th><th>TOTAL BILL</th><th>PAYMENT TYPE</th><th>ORDER DATE</th><th>ORDER STATUS</th></tr>';
			output+='<tr>';
			
			
			for (var i=0; i<xmlDoc.length; i++){
				output+='<tr>';
				output+='<td>'+xmlDoc[i].getElementsByTagName('OrderId')[0].firstChild.nodeValue+'</td>';
				output+='<td>$'+xmlDoc[i].getElementsByTagName('TotalBill')[0].firstChild.nodeValue+'</td>';
				output+='<td>'+xmlDoc[i].getElementsByTagName('PaymentMethod')[0].firstChild.nodeValue+'</td>';
				output+='<td>'+xmlDoc[i].getElementsByTagName('OrderDate')[0].firstChild.nodeValue+'</td>';
				output+='<td>'+xmlDoc[i].getElementsByTagName('OrderStatus')[0].firstChild.nodeValue+'</td>';
				output+='</tr>';
			}
			
			document.getElementById("carttable").innerHTML=output;
		},
		error: function() {
			alert("An error occurred while processing XML file.");
		}
	});	
});

</script>
<style>
#cart{
	width: 70%;
	margin: 0 auto;
	height: 500px;
	overflow: hidden;
	border-collapse: collapse;
	border-left: 1px solid #d0d0d0;
	border-right: 1px solid #d0d0d0;
}
#cart #header{
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
   
#cart #item-list{
	height: 74%;
	overflow-y: auto;
}
#cart #amount{
	background-color:#eeeeee;
	height: 10%;
}
#amount p {
   font-weight:bold;
   text-align:right;
   padding: 0.8rem 2rem;
}

#cart #place-order{
	background-color:#000000;
	margin: 0 auto;
	height: 8%;
}
#cart td{
	height: 90px;
}
table {
    border-collapse: collapse;
    border-style: hidden;
}
table td{
    background-color:#ffffff;
	border-bottom: 1px solid #d0d0d0;
	border-left: 1px solid #e2e2e2;
	text-align:center;
	position: relative;
	padding: 1rem 1rem 1rem 1rem;
}
table th {
	background-color:#eeeeee;
	border-bottom: 1px solid #d0d0d0;
	border-left: 1px solid #e2e2e2;
}
#place-order #button1{
	display: block;
	width:50%;
	float: left;
	height:100%;
}
#place-order #button2{
	display: block;
	float: left;
	width:50%;
	border-left: 1px solid #ffffff;
	height:100%;
}
.button.expanded{
	height:100%;
}
#cart-image{
	padding: 0rem 1rem;
}
#qty{
	width:25px;
	height:25px;
	padding:2px;
	margin : 0 auto;
	text-align:center;
}
#product_name{
	text-align:left;
	vertical-align:top;
}
#remove{
	position: absolute;
    bottom: 0.2rem;
    right: 0.5rem;
}
#profilefetch, #fetchproduct, #buttonreformat{
  background:none;
  border:none;
  font-size:1em;
  color:blue;
  width:60px;
  text-align:center;
  color:#2199e8;
}

#buttonreformat{
  background:none;
  border:none;
  font-size:1em;
  color:blue;
  width:80px;
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
			<li><a href="prevorders.jsp">My Orders</a></li>
			<li><a href="cart.jsp" id="cart-image" style="padding-top:0px;padding-bottom:0px;"><img src="images/cart.png" style="width:35px;height:25px;"></img></a></li>
			<li><form action="ProfileDetailsControllerServlet" method="post"><input type="submit" id="profilefetch" value="Profile"/></form></li>
			<li><a id="logout">Logout</a></li>
		</ul>
	</div>
</div>

<div id="cart">
	<div id="header">
		<p>YOUR PREVIOUS ORDERS</p>
	</div>
	<div id="item-list">
		<table width="100%" id="carttable">
		</table>
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
