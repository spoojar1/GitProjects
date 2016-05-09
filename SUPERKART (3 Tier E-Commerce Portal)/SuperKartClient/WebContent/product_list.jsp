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

function fetchcategory(productcat) {
	var pcat;
	//alert(productcat);
	switch(productcat){
		case "M":	pcat="mobiles";
					break;
		case "L":	pcat="laptops";
					break;
		case "T":	pcat="tablets";
					break;
		case "C":	pcat="cameras";
					break;
		default:	pcat="misc";
					break;  
	}
	//alert(productcat+" "+pcat)
	return pcat;
}


$(document).ready(function(){
	
	$('#hiddenlogin').hide();
	$('#hiddenregister').hide();
	
	<%	if (request.getAttribute("valid")!=null){
			if(request.getAttribute("fail-attempts")!=null){%>
				if (localStorage.clickcount){
		            localStorage.clickcount = Number(localStorage.clickcount)+1;
		        } else {
		            localStorage.clickcount = 1;
		        }
				alert(localStorage.clickcount);
			<%}%>	
			var id = $('#login').attr('href');
			var maskHeight = $(document).height();
			var maskWidth = $(window).width();
			$('#mask').css({'width':maskWidth,'height':maskHeight});
			//$('#mask').fadeIn(1000);	
			$('#mask').fadeTo(0,0.5);	
			var winH = $(window).height();
			var winW = $(window).width();
			$(id).css('top',  winH/2-$(id).height()/2);
			$(id).css('left', winW/2-$(id).width()/2);
			$(id).fadeIn(0);
			$('#hiddenlogin').show();
	<%	}
	%>
	
	<%	if (request.getAttribute("taken")!=null){%>
			var id = $('#register').attr('href');
			var maskHeight = $(document).height();
			var maskWidth = $(window).width();
			$('#mask').css({'width':maskWidth,'height':maskHeight});
			//$('#mask').fadeIn(1000);	
			$('#mask').fadeTo(0,0.5);	
			var winH = $(window).height();
			var winW = $(window).width();
			$(id).css('top',  winH/2-$(id).height()/2);
			$(id).css('left', winW/2-$(id).width()/2);
			$(id).fadeIn(0);
			$('#hiddenregister').show();
	<%	}
	%>
	
	//LOGIN
	$('#login').click(function(e) {
		e.preventDefault();
		var id = $(this).attr('href');
		var maskHeight = $(document).height();
		var maskWidth = $(window).width();
		$('#mask').css({'width':maskWidth,'height':maskHeight});
		$('#mask').fadeIn(1000);	
		$('#mask').fadeTo("slow",0.5);	
		var winH = $(window).height();
		var winW = $(window).width();
		$(id).css('top',  winH/2-$(id).height()/2);
		$(id).css('left', winW/2-$(id).width()/2);
		$(id).fadeIn(2000);
	});
	
	//REGISTER
	$('#register').click(function(e) {
		e.preventDefault();
		var id = $(this).attr('href');
		var maskHeight = $(document).height();
		var maskWidth = $(window).width();
		$('#mask').css({'width':maskWidth,'height':maskHeight});
		$('#mask').fadeIn(1000);	
		$('#mask').fadeTo("slow",0.5);	
		var winH = $(window).height();
		var winW = $(window).width();
		$(id).css('top',  winH/2-$(id).height()/2);
		$(id).css('left', winW/2-$(id).width()/2);
		$(id).fadeIn(2000);
	});
	
	//Common for LOGIN and REGISTER
	$('.window #close').click(function (e) {
		e.preventDefault();
		$('#mask').hide();
		$('.window').hide();
		$('#hiddenlogin').hide();
	});		
	$('#mask').click(function () {
		$(this).hide();
		$('.window').hide();
		$('#hiddenlogin').hide();
	});
	
	<% if(session!=null){ %>
		var str="<%=(String)request.getAttribute("xmlDoc")%>";
		//alert(str);
		var option;
		if(str.indexOf('^')>=0){
			option=str.substring(str.indexOf('^')+1);
			str=str.substring(0,str.indexOf('^'));
			el = document.getElementsByTagName('select')[0]; 
			el.selectedIndex = option-1;
		}
		//alert("Hello "+str+" "+option);
		
		var xml = $.parseXML(str);
		var xmlDoc = xml.getElementsByTagName("Products");
		var output="";
		
		var pcatswitch="<%=(String)request.getAttribute("productcat")%>";
		var searchstring="<%=(String)request.getAttribute("searchstring")%>";
		
		//alert("Hello"+searchstring);
		if(searchstring!="null")
			document.getElementById("searchstring").value=searchstring;
		
		var ProductCatHeading="";
		var pcat="";
		switch(pcatswitch){
			case "M":	pcat="mobiles";
						ProductCatHeading="MOBILES";
						break;
			case "L":	pcat="laptops";
						ProductCatHeading="LAPTOPS";
						break;
			case "T":	pcat="tablets";
						ProductCatHeading="TABLETS";
						break;
			case "C":	pcat="cameras";
						ProductCatHeading="CAMERAS";
						break;
			default:	pcat="misc";
						break;
		}
		//(pcat);
		
		for (var i=0; i<xmlDoc.length; i++){
		    output+='<div class="column">';
			
		    //alert("Hello"+pcatswitch+" "+(pcatswitch=="null"));
		    //alert("Hello "+fetchcategory(xmlDoc[i].getElementsByTagName('ProductCategory')[0].firstChild.nodeValue));
			if(pcatswitch=="null")	//for search funcionality
				output+='<img class="thumbnail" src="images/'+fetchcategory(xmlDoc[i].getElementsByTagName('ProductCategory')[0].firstChild.nodeValue)+'/'+xmlDoc[i].getElementsByTagName('ProductId')[0].firstChild.nodeValue+'.jpg">';
			else
				output+='<img class="thumbnail" src="images/'+pcat+'/'+xmlDoc[i].getElementsByTagName('ProductId')[0].firstChild.nodeValue+'.jpg">';
		    output+='<h5>'+xmlDoc[i].getElementsByTagName('ProductName')[0].firstChild.nodeValue+'</h5>';
		    output+='<p>$'+xmlDoc[i].getElementsByTagName('Price')[0].firstChild.nodeValue+'</p>';
		    
		    var uname="<%=session.getAttribute("USER")%>";
			if (uname!=null)
				output+='<form action="ProductControllerServlet" method="get"><input type="hidden" name="username" value="'+uname+'"/><input type="hidden" name="productid" value="'+xmlDoc[i].getElementsByTagName('ProductId')[0].firstChild.nodeValue+'"/><input type="submit" class="button expanded" value="Check This Out"/></form></div>';
			else
				output+='<form action="ProductControllerServlet" method="get"><input type="hidden" name="username" value=""/><input type="hidden" name="productid" value="'+xmlDoc[i].getElementsByTagName('ProductId')[0].firstChild.nodeValue+'"/><input type="submit" class="button expanded" value="Check This Out"/></form></div>';
		}
		document.getElementById("ProductCatHeading").innerHTML=ProductCatHeading;

		if(output=="")
			document.getElementById("firstdivrow").innerHTML="Your search string did not return any results. Please try again with a different string.";
		else
			document.getElementById("firstdivrow").innerHTML=output;
		
		document.getElementById("productcatSort").value=pcatswitch;
		
	<%} else{ %>
		alert("Hi");
	<%} %>
	
	
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
});
</script>
<style>

#firstdivrow img{
	height: 400px;
	width: 300px;
}

#firstdivrow h5{
	text-align:center;
}

#firstdivrow p{
	text-align:center;
}

.log-in-form {
	border: 1px solid #cacaca;
	padding: 1rem;
	border-radius: 3px; 
	background-color:#fff;
}

#mask 
{
  position:absolute;
  left:0;
  top:0;
  z-index:9000;
  background-color:#000;
  display:none;
}
  
#boxes .window 
{
  position:absolute;
  left:0;
  top:0;
  display:none;
  z-index:9999;
}

#boxes #dialog1,#dialog2
{
  width:400px; 
}

#close{
 float:right;
}

.row{
	padding-left: .9375rem;
	padding-right: .9375rem;
}
.row #category{
	display: block;
	width:85%;
	float: left;
	height:100%;
}
.row #viewitems{
	display: block;
	float: right;
	width:15%;
	height:40px;
}
#viewitems .button{
	float:right;
	height:80%;
	line-height:0.2rem;
}
hr{
	padding: 0px;  
	margin-top:0.5rem;
	margin-bottom:0.5rem;
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
			<!-- <li><a href="product_list.html">Mobiles</a></li> -->
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
			<% String uname = (String)session.getAttribute("USER");
				if (uname==null){ %>
					<li><a id="login" href="#dialog1">Login</a></li>
					<li><a id="register" href="#dialog2">Register</a></li>
				<%}else{%>
					<li><a href="prevorders.jsp">My Orders</a></li>
					<li><a href="cart.jsp" id="cart-image" style="padding-top:0px;padding-bottom:0px;"><img src="images/cart.png" style="width:35px;height:25px;"></img></a></li>
					<li><form action="ProfileDetailsControllerServlet" method="post"><input type="submit" id="profilefetch" value="Profile"/></form></li>
					<li><a id="logout">Logout</a></li>
			<%	}
			%>
		</ul>
	</div>
</div>

<div class="row column"> 
	<div class="text-center" style="float:left;width:70%;height:100%;">
		<h3 id="ProductCatHeading" style="padding-left:40%;"></h3>
	</div>
	<div class="text-center" style="float:left;width:30%;height:100%;padding-right:1em;">
	<form style="text-align:right;" action="ProductControllerServlet" method="post">
		<input type="hidden" name="limit" value="" />
		<input type="hidden" id="searchstring" name="searchstring" value="" />
		<input type="hidden" id="productcatSort" name="productcat" value="" />
		<select name="sortorder" style="width:150px;margin-top:0.5em;height:30px;padding:0 0;">
			<option value="1">Random Order</option>
			<option value="2">Price Low to High</option>
			<option value="3">Price High to Low</option>
		</select>
		<input style="height:30px;" type="submit" value="Sort"/>
	</form>
</div>
</div>

<div class="row small-up-2 large-up-4" id="firstDiv">
	<div class="row" id="firstdivrow">
	</div>
</div>

<div id="boxes">
	<div id="dialog1" class="window">
		<div class="row">
			<div id="form">
				<form action="LoginControllerServlet" method="post">
					<div class="row log-in-form">
						<div id="close"><a>X</a></div>
						<h4 class="text-center">LOGIN</h4>
						<label>Username
						<input type="text" placeholder="ssp151830" name="username" value="UserName1" >
						</label>
						<label>Password
						<input type="password" placeholder="Password" name="password" value="Pass123" >
						</label>
						<p id="hiddenlogin">Invalid UserName/Password</p>
						<p><input type="submit" class="button expanded" value="Log In"></input></p>
					</div>
				</form>
			</div>
		</div>
	</div>
	<div id="mask"></div>
</div>

<div id="boxes">
	<div id="dialog2" class="window">
		<div class="row">
			<div id="form">
				<form action="RegisterControllerServlet" method="post">
					<div class="row log-in-form">
						<div id="close"><a>X</a></div>
						<h4 class="text-center">CREATE ACCOUNT</h4>
						<label>Username
						<input type="text" placeholder="Enter a Unique username" value="UserName6" name="username">
						</label>
						<p id="hiddenregister">UserName already taken</p>
						<label>Email
						<input type="text" placeholder="john.doe@gmail.com" value="UserName6" name="emailid">
						</label>
						<label>Password
						<input type="password" placeholder="Password" value="UserName6" name="password">
						</label>
						<label>Verify Password
						<input type="password" placeholder="Password">
						</label>
						<p><input type="submit" class="button expanded" value="Create Account"></input></p>
					</div>
				</form>
			</div>
		</div>
	</div>
	<div id="mask"></div>
</div>

<div class="callout secondary">
	<div class="row">
		<div class="large-4 columns">
			<h5>Your ONE STOP SHOP for all the Electronics you can think of !!</h5>
			<p>From mobiles, to laptops, to LCDs. You can find all of your household and workplace electronics here at COMPANYNAME. We provide the best customer service and truly believe that Customer is King.</p>
		</div>
		<div class="large-3 large-offset-2 columns">
			<ul class="menu vertical">
				<li>
					<a href="#">
						<p style="font-weight:bold;">COMPANY NAME</p>
					</a>
				</li>
				<li><a href="#">About Us</a></li>
				<li><a href="contactus.jsp">Contact Us</a></li>
				<li><a href="#">Careers</a></li>
			</ul>
		</div>
		<div class="large-3 columns">
			<ul class="menu vertical">
				<li>
					<a href="#">
						<p style="font-weight:bold;">HELP</p>
					</a>
				</li>
				<li><a href="#">FAQ</a></li>
				<li><a href="#">Cancellation & Returns</a></li>
				<li><a href="#">Gift Cards</a></li>
			</ul>
		</div>
	</div>
</div>

<script src="https://code.jquery.com/jquery-2.1.4.min.js"></script>
<script src="https://dhbhdrzi4tiry.cloudfront.net/cdn/sites/foundation.js"></script>
<script>
      $(document).foundation();
    </script>
<script type="text/javascript" src="https://intercom.zurb.com/scripts/zcom.js"></script>
</body>
</html>
